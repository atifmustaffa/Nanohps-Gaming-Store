package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://localhost:3306/nanohps";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "qwe123";
	
	static Item[] items = new Item[15];;
	
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * NaNoHps Gaming Store
		 * if incorrect input use cookies to store and redisplay
		 * customer n items database will be stored in the sql server
		 * ^including procedure n trigger
		 * use session to store item into cart
		 * jsp for simple java scriptlet ~maybe :D
		 * 
		 */
		
		// session for cart
		HttpSession session = request.getSession();
		ArrayList<String> cart = (ArrayList<String>)session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList();
		}
		if(request.getParameter("addedtocart") != null) {
			if(!cart.contains(request.getParameter("addedtocart"))) {
				cart.add(request.getParameter("addedtocart"));
				System.out.println("Item[" + request.getParameter("addedtocart") + "] has been added to cart");
				session.setAttribute("cart", cart);
			}
		}
		System.out.println("Items in cart: " +session.getAttribute("cart"));
		
		accessDb();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String html = printHeader() + printPage(request, response) + printFooter();
		out.println(html);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void accessDb() {
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "";
			int count = 0;
			for(int i = 0; i < 5; i++) {
				switch(i) {
				case 0: sql = "call getKeyboards()"; break;
				case 1: sql = "call getMouse()"; break;
				case 2: sql = "call getHeadset()"; break;
				case 3: sql = "call getMouseMats()"; break;
				case 4: sql = "call getAccessories()"; break;
				}
				
				// Execute a query
				System.out.println("Executing SQL query...\n" +sql);
				stmt = conn.createStatement();

				stmt.executeQuery(sql);
				ResultSet resultSet = stmt.executeQuery(sql);
				
				resultSet.beforeFirst();
				int first3items = 0;				
				while(resultSet.next()) {
					if(first3items < 3) {
						int id = Integer.parseInt(resultSet.getString(1));
						String name = resultSet.getString(2);
						String desc = resultSet.getString(3);
						int stock = Integer.parseInt(resultSet.getString(4));
						double price = Double.parseDouble(resultSet.getString(5));
						String imgurl = resultSet.getString(6);
						String brand = resultSet.getString(7);
						items[count] = new Item(id, stock, price, name, desc, brand, imgurl);
						count++;
					}
					first3items++;
				}
				
			}

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}
		System.out.println("Database operation ended.");
	}
	
	private String printHeader() {
		
		String title = "Nano Hps Gaming Store :: Home";
		
		return "<!DOCTYPE html>" +"\n" +
		"<html>" +"\n" +
		"<head>" +"\n" +
			"<meta charset='ISO-8859-1'>" +"\n" +
			"<link href='https://fonts.googleapis.com/css?family=Poppins|Rubik' rel='stylesheet'>" +"\n" +
			"<link href='./mainStyles.css' rel='stylesheet'>" +"\n" +
			"<script src='./jquery.min.js'></script>" +"\n" +
			"<script src='./mainJavascript.js'></script>" +"\n" +
			"<title>" + title +"</title>" +"\n" +
		"</head>" +"\n" +
		"<body>" +"\n" +
			"<header>" +"\n" +
				"<div class='hiddenTopBar'>" +"\n" +
					"<div id='container'>" +"\n" +
						"<div class='headerLeft'>" +"\n" +
							"<a href='./main'><img id='banner' src='./images/HEADERbANNERsmall.png'/></a>" +"\n" +
						"</div>" +"\n" +
						"<div class='searchPanel'><form id='searchForm1' method='get' action='./shop'>" +"\n" +
							"<select id='searchFilter' name='filter'>" +"\n" +
								"<option>All</option>" +"\n" +
								"<option>Keyboard</option>" +"\n" +
								"<option>Mouse</option>" +"\n" +
								"<option>Headset</option>" +"\n" +
								"<option>Mouse Mat</option>" +"\n" +
								"<option>Accessories</option>" +"\n" +
							"</select><input id='searchInput' type='text' name='search' placeholder='Search for products or brands'/>" +
								"<a id='searchButton' href='javascript:void(0)'>" +"\n" +
							"<img id='searchIco' src='./images/searchIco.png'></img></a></form>" +"\n" +
						"</div>" +"\n" +
						"<div id='headerRight'>" +"\n" +
							"<span id='accbtn' style='padding: 18px 0px'><a style='padding: 18px 30px' id='accountDrop' href='./account'>Account</a>" +"\n" +
							"<span class='dropdown-content2'>" +"\n" +
								"<a id='loginBtn' href='./login'>Login</a>" +"\n" +
								"<a id='registerBtn' href='./signup'>Register</a>" +"\n" +
								"<a id='logoutBtn' href='./account'>Logout</a>" +"\n" +
							"</span></span>" +"\n" +
							"<a id='cart' href='./cart'><img id='banner' src='./images/cartSmall.png'/>Cart</a>" +"\n" +
						"</div>" +"\n" +
					"</div>" +"\n" +
				"</div>" +"\n" +
				"<div class='topHeader'>" +"\n" +
					"<div id='container'>" +"\n" +
						"<div class='headerTitle'>" +"\n" +
							"Nano Hps Gaming Store :: Home" +"\n" +
							"</div>" +"\n" +
							"<div id='headerUser'>" +"\n" +
								"<a id='accountDrop' href='./aboutUs'>About Us</a>" +
								"<a id='accountDrop' href='./customerservice'>Customer Service</a>" +
								"<span id='accbtn'><a id='accountDrop' href='./account'>Account</a>" +"\n" +
								"<span class='dropdown-content'>" +"\n" +
								"<a id='loginBtn' href='./login'>Login</a>" +"\n" +
								"<a id='registerBtn' href='./signup'>Register</a>" +"\n" +
								"<a id='logoutBtn' href='./account'>Logout</a>" +"\n" +
						    "</span></span>" +"\n" +
						"</div>" +"\n" +
					"</div>" +"\n" +
				"</div>" +"\n" +
				"<div class='mainHeader'>" +"\n" +
					"<div id='container'>" +"\n" +
						"<div class='bannerPicture'>" +"\n" +
							"<a href='./main'><img id='banner' src='./images/HEADERbANNER.png'/></a>" +"\n" +
						"</div>" +"\n" +
						"<div class='searchPanel'><form id='searchForm2' method='get' action='./shop'>" +"\n" +
							"<select id='searchFilter' name='filter'>" +"\n" +
									"<option>All</option>" +"\n" +
									"<option>Keyboard</option>" +"\n" +
									"<option>Mouse</option>" +"\n" +
									"<option>Headset</option>" +"\n" +
									"<option>Mouse Mat</option>" +"\n" +
									"<option>Accessories</option>" +"\n" +
							"</select><input id='searchInput' type='text' name='search' placeholder='Search for products or brands'/>" +
							"<a id='searchButton' href='javascript:void(0)'>" +"\n" +
							"<img id='searchIco' src='./images/searchIco.png'></img></a></form>" +"\n" +
						"</div>" +"\n" +
						"<a id='cart' href='./cart'><img id='banner' src='./images/cart.png'/>Cart</a>" +"\n" +
					"</div>" +"\n" +
				"</div>" +"\n" +
				"<nav id='navigation'>" +"\n" +
					"<ul id='menu'>" +"\n" +
						"<li><a href='javascript:void(0)'>Keyboard</a></li>" +"\n" +
						"<li><a href='javascript:void(0)'>Mouse</a></li>" +"\n" +
						"<li><a href='javascript:void(0)'>Headset</a></li>" +"\n" +
						"<li><a href='javascript:void(0)'>Mouse Mat</a></li>" +"\n" +
						"<li><a href='javascript:void(0)'>Accessories</a></li>" +"\n" +
					"</ul>" +"\n" +
				"</nav>" +"\n" +
				"<div id='imageSlider'>" +"\n" +
					"<ul id='slides'>" +"\n" +
						"<li><img src='./images/slides/1.jpg'/></li>" +"\n" +
						"<li><img src='./images/slides/2.jpg'/></li>" +"\n" +
						"<li><img src='./images/slides/3.jpg'/></li>" +"\n" +
						"<li><img src='./images/slides/4.jpg'/></li>" +"\n" +
						"<li><img src='./images/slides/1.jpg'/></li>" +"\n" +
					"</ul>" +"\n" +
				"</div>" +"\n" +
				"<span id='back-to-top'><a href='javascript:void(0)'>Top</a></span>" +"\n" +
			"</header>" +"\n";
	}
	
	private String printFooter() {
		
		return "<footer>" +"\n" +
				"<div class='mainFooter'>" +"\n" +
				"&copy; Copyright 2016 Nano Hps Gaming Store<br>" +"\n" +
				"Semester project for Web Programming CSC 2103 Section 1<br>" +"\n" +
				"Developed By:<br>" +"\n" +
				"Abdul Hadi bin Noordin 1428711<br>" +"\n" +
				"Muhammad 'Atif bin Mustaffa 1429619<br>" +"\n" +
				"Muhammad Adil Fahim bin Zulkifli 1429609<br>" +"\n" +
				"Muhammad Azfar bin Hamzah 1426417<br>" +"\n" +
				"Muhammad Hazim Bin Muhammuddin 1428415<br>" +"\n" +
					"</div>" +"\n" +
				"</footer>" +"\n" +
		"</body>" +"\n" +
	"</html>";
	}
	
	private String printPage(HttpServletRequest request, HttpServletResponse response) {
		
		return "<div class='mainDiv'>" +"\n" +
				"<h2><b>Best Selling Gaming Gear</b></h2>" +"\n" +
				"<div class='products'>" +"\n" +
					"<h3 id='prodTitle'><b>Keyboard</b></h3>" +"\n" +
					"<table id='tableItems'>" +"\n" +
						printItems(request, response, 3) +"\n" +
					"</table>" +"\n" +
					"<br><br><h3 id='prodTitle'><b>Mouse</b></h3>" +"\n" +
					"<table id='tableItems'>" +"\n" +
						printItems(request, response, 6) +"\n" +
					"</table>" +"\n" +
					"<br><br><h3 id='prodTitle'><b>Head Set</b></h3>" +"\n" +
					"<table id='tableItems'>" +"\n" +
						printItems(request, response, 9) +"\n" +
					"</table>" +"\n" +
					"<br><br><h3 id='prodTitle'><b>Mouse Mat</b></h3>" +"\n" +
					"<table id='tableItems'>" +"\n" +
						printItems(request, response, 12) +"\n" +
					"</table>" +"\n" +
					"<br><br><h3 id='prodTitle'><b>Accessories</b></h3>" +"\n" +
					"<table id='tableItems'>" +"\n" +
						printItems(request, response, 15) +"\n" +
					"</table>" +"\n" +
					"<br><p>Hye guys. Name saye <b>fahim</b> harini saya nk buat raver. Jadi korang semua kene tengok eh</p>" +"\n" +
					"<br>" +"\n" +
					"<br>" +"\n" +
					"<br>" +"\n" +
					"<br>" +"\n" +
					"<br>" +"\n" +
					"<br><p>Mak secretly masuk...<b>Raver tengkorak raver</b><br>" +
					"Orang panggil x jawab<br>" +
					"X siap2 lagi<br> ah raver raver<br>raver sgt la.<br>Ini lagi sorg</p>" +"\n" +
					"<br>" +"\n" +
					"<br>" +"\n" +
				"</div>" +"\n" +
			"</div>" +"\n";
	}

	private String printItems(HttpServletRequest request, HttpServletResponse response, int index) {
		String text = "<tr>";
		for(int i = index - 3; i < index; i++) {
			if(items[i] == null)
				break;
			text += "<td>"
					+ "<form method='post' action=''>"
					+ "<input type='hidden' name='addedtocart' value='" + items[i].getProduct_id() +"'/>\n"
					+ "<div id='imgDiv'>\n"
					+ "<img id='productImg' src='" + items[i].getImgurl() + "'></img>\n"
					+ "</div><br><span id='itemName'>" + items[i].getName() +"</span>\n"
					+ "<br>" + items[i].getDescription() + "<br>\n"
					+ "<br><span id='price'>" + new DecimalFormat("#0.00").format(items[i].getPrice()) + "</span><br><br>\n"
					+ "<div><a id='addtocartBtn' href='javascript:void(0)'>Add to cart</a></div>"
					+ "</form></td>\n";
		}
		text += "</tr>";
		return text;
	}
	

}
