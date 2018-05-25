package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/shop")
public class Shop extends HttpServlet {
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
	
	static Item[] items;
		
    public Shop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String html = "", query = request.getParameter("search"), category = request.getParameter("filter");
		if(query == null)
			query = "";
		
		if(category == null)
			category = "";
		
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
		
		// print pages accordingly
		if(query.equals("") && category.equals(""))
			accessDb("", "");
		else
			accessDb(query.toLowerCase(), category.toLowerCase());
		
		String searchInput =  query;
		String filterInput =  category;
		html =  printPage(request, response, searchInput, filterInput);
		
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
	
	public static void accessDb(String query, String category) {
		
		String sql = "";
		if((query == "" && category == "")||(query == "" && category.equals("all"))) {
			sql = "select * from products;";
		}
		else if(query == "" && !category.equals("all")) {
			if(category.equals("accessories"))
				sql = "select * from products where name not like ('%headset') and name not like ('%keyboard') and name not like ('%mouse%');";
			else
				sql = "select * from products where name like ('%" + category + "');";
		}
		else {
			if(category.equals("accessories"))
				sql = "select * from products where name not like ('%headset') and name not like ('%keyboard') and name not like ('%mouse%') and name like ('%" + query + "%');";
			else if(category.equals("all"))
				sql = "select * from products where name like ('%" + query + "%');";
			else
				sql = "select * from products where name like ('%" + category + "') and name like ('%" + query + "%');";
		}
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Executing SQL query...\n" +sql);
			stmt = conn.createStatement();

			stmt.executeQuery(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			
			int numberOfRecords = 0;
			if(resultSet.last())
			    numberOfRecords = resultSet.getRow();
			items = new Item[numberOfRecords];
			
			resultSet.beforeFirst();
			while(resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				String name = resultSet.getString(2);
				String desc = resultSet.getString(3);
				int stock = Integer.parseInt(resultSet.getString(4));
				double price = Double.parseDouble(resultSet.getString(5));
				String imgurl = resultSet.getString(6);
				String brand = resultSet.getString(7);
				items[resultSet.getRow()-1] = new Item(id, stock, price, name, desc, brand, imgurl);
				
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
	
	private String printHeader(HttpServletRequest request, HttpServletResponse response, String query, String category) throws ServletException, IOException {
		String title = "Nano Hps Gaming Store :: Shop";
		System.out.println("filter : " + category);
		String[] radio = {"", "", "", "", "", ""};
		if(category.equals("All") || category.equals(""))
			radio[0] = "selected";
		else if(category.equals("Keyboard"))
			radio[1] = "selected";
		else if(category.equals("Mouse"))
			radio[2] = "selected";
		else if(category.equals("Headset"))
			radio[3] = "selected";
		else if(category.equals("Mouse Mat"))
			radio[4] = "selected";
		else if(category.equals("Accessories"))
			radio[5] = "selected";
		
		return "<!DOCTYPE html>" +"\n" +
		"<html>" +"\n" +
		"<head>" +"\n" +
			"<meta charset='ISO-8859-1'>" +"\n" +
			"<link href='https://fonts.googleapis.com/css?family=Poppins|Rubik' rel='stylesheet'>" +"\n" +
			"<link href='./shopStyles.css' rel='stylesheet'>" +"\n" +
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
								"<option " + radio[0] + ">All</option>" +"\n" +
								"<option " + radio[1] + ">Keyboard</option>" +"\n" +
								"<option " + radio[2] + ">Mouse</option>" +"\n" +
								"<option " + radio[3] + ">Headset</option>" +"\n" +
								"<option " + radio[4] + ">Mouse Mat</option>" +"\n" +
								"<option " + radio[5] + ">Accessories</option>" +"\n" +
							"</select><input id='searchInput' type='text' name='search' value='" + query + "' placeholder='Search for products or brands'/>" +
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
							title +"\n" +
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
								"<option " + radio[0] + ">All</option>" +"\n" +
								"<option " + radio[1] + ">Keyboard</option>" +"\n" +
								"<option " + radio[2] + ">Mouse</option>" +"\n" +
								"<option " + radio[3] + ">Headset</option>" +"\n" +
								"<option " + radio[4] + ">Mouse Mat</option>" +"\n" +
								"<option " + radio[5] + ">Accessories</option>" +"\n" +
							"</select><input id='searchInput' type='text' name='search' value='" + query + "' placeholder='Search for products or brands'/>" +
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
	
	private String printPage(HttpServletRequest request, HttpServletResponse response, String query, String category) throws ServletException, IOException {
		
		return printHeader(request, response, query, category) + "<div class='mainDiv'>" +"\n" +
				"<div class='products'>" +"\n" +
					"<table id='tableItems'>" +"\n" +
						printItems(request, response) +"\n" +
					"</table>" +"\n" +
					"<br><p>Hye guys. Name saye <b>azfar</b> harini saya nk buat raver. Jadi korang semua kene tengok eh</p>" +"\n" +
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
			"</div>" +"\n" + printFooter();
	}
		
	private String printItems(HttpServletRequest request, HttpServletResponse response) {
		
		String query = request.getParameter("search"), category = request.getParameter("filter");
		if((query == null && category == null)) {
			query = ""; category = "All";
		}
		String text = "";
		int count = 0;
		int row = (int) Math.ceil((double)items.length/3);
		for(int x = 0; x <= row; x++) {
			text += "<tr>";
			for(int i = 0; i < 3; i++) {
				if(count < items.length) {
					text += "<td>"
							+ "<form method='post' action=''>"
							+ "<input type='hidden' name='filter' value='" + category +"'/>\n"
							+ "<input type='hidden' name='search' value='" + query +"'/>\n"
							+ "<input type='hidden' name='addedtocart' value='" + items[count].getProduct_id() +"'/>\n"
							+ "<div id='imgDiv'>\n"
							+ "<img id='productImg' src='" + items[count].getImgurl() +"'></img>\n"
							+ "</div><br><span id='itemName'>" + items[count].getName() + "</span>\n"
							+ "<br>" + items[count].getDescription()+ "<br>\n"
							+ "<br><span id='price'>" + new DecimalFormat("#0.00").format(items[count].getPrice()) + "</span><br><br>\n"
							+ "<div><a id='addtocartBtn' href='javascript:void(0)'>Add to cart</a></div>"
							+ "</form></td>\n";
					count++;
				}
				else
					break;
			}
			text += "</tr>\n";
		}
		return text + "Total item(s) found: " + (count);
	}

}
