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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class Cart extends HttpServlet {
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
		
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getCookies();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String html = printPage(request, response);
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
	
	public static void accessDb(ArrayList<String> cart) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "select * from products where ";
			for(int i = 0; i < cart.size(); i++) {
				sql += "product_id = " + cart.get(i);
				if(i < cart.size()-1)
					sql += " or ";
			}
				
			// Execute a query
			System.out.println("Executing SQL query...\n" +sql);
			stmt = conn.createStatement();

			stmt.executeQuery(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			
			int count = 0;
			resultSet.beforeFirst();	
			while(resultSet.next()) {
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
		
		String title = "Nano Hps Gaming Store :: Cart";

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
		
		return printHeader() +
			"<div class='mainDiv'>" +"\n" +
			"<h3 id='secTitle' style='text-align:center'><b>Cart Details</b></h3>" +"\n" +
			"<div class='cartItems'>" +"\n" +
			printItems(request, response) +
			"</div>" +"\n" +
			"<div class='orderSummary'>" +"\n" +
			"<h4>Order Summary</h4><hr>" +"\n" +
			"<table id='summaryTable'>" +"\n" +
			"<tr><td>Subtotal:</td><td><span id='price-cart'>" + new DecimalFormat("#0.00").format(calculateSubtotal(request, response)) + "</span></td></tr>" +"\n" +
			"<tr><td>Shipping Fees:</td><td><span id='price-cart'>" + new DecimalFormat("#0.00").format(shipping(request, response)) + "</span></td></tr>" +"\n" +
			"<tr><td><b>Total</b><span style='font-size: 12px'>(6% GST applied where applicable)</span>:</td><td style='font-weight: bold'>" +
			"<span id='price-cart'>" + new DecimalFormat("#0.00").format(calculateSubtotal(request, response) + shipping(request, response)) + "</span></td></tr>" +"\n" +
			"</table><br>" +"\n" +
			"<span id='checkoutButton' style='text-align:center'><a href='./shipping'>Proceed to Checkout</a></span>" +"\n" +
			"</div>" +"\n" +
			"</div>" +"\n" + printFooter();
	}
	
	private String printItems(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(request.getParameter("removeAll") != null && request.getParameter("removeAll").equals("yes")) {
			session.removeAttribute("cart");
			try {
				clearCookies(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayList<String> cart = (ArrayList<String>)session.getAttribute("cart");

		if(request.getParameter("removeItem") != null) {
			if(cart != null)
				for(int x = 0; x < cart.size(); x++)
					if(cart.get(x).equals(request.getParameter("removeItem"))) {
						cart.remove(x);
						if(cart.size() == 0) {
							session.removeAttribute("cart");
							try {
								delCookie(request, response, "quantity" +request.getParameter("removeItem"));
							} catch (ServletException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
		}
		
		String text = "";
		if(cart == null || cart.size() == 0)
			text = "<h4>0 Item</h4><hr>" +"\n" +
			"<table id='itemTable'>" +"\n" +
			"Your shopping cart is empty" +"\n" +
			"</table>" +"\n";
		
		else {
			items =  new Item[cart.size()];
			accessDb(cart);
			text = "<h4>" + items.length + " Item(s)<span style='float: right; font-weight: normal; position: relative; right: 8px; bottom: -20px'>" +
					"<form method='post'><input type='hidden' name='removeAll' value='yes'/><a id='removeAllItems' href='javascript:void(0)'>Remove all items</a></form></span></h4><hr>" +"\n" +
					"<table id='itemTable'>" +"\n";
			for(int i = 0; i < items.length; i++) {
				text += "<tr><td>" +"\n" +
						"<div id='itemImg'>" +"\n" +
						"<img id='productImg-cart' src='" + items[i].getImgurl() + "'></img>" +"\n" +
						"</div></td>" +"\n" +
						"<td><span id='itemName-cart'>" + items[i].getName() + "</span><br>" +"\n" +
						"<span id='brand-cart'>" + items[i].getBrand() + "</span><br>" +"\n" +
						"<span id='price-cart'>" + new DecimalFormat("#0.00").format(items[i].getPrice()) + "</span><br>" +"\n" +
						"<span id='quantity-cart'><form method='post'>Quantity: <input type='number' value='" + getQuantity(request, response, items[i].getProduct_id()) + "' name='quantity"+ items[i].getProduct_id() +"' min='1' value='1'/>" +
						"<a id='updateCart' href='javascript:void(0)'>Update Cart</a></form></span><br>" +"\n" +
						"<form method='post'><input type='hidden' name='removeItem' value='" + items[i].getProduct_id() + "'/><a id='removeItem' href='javascript:void(0)'>Remove item</a></form></td></tr>" +"\n";
						
			}
			text += "</table>" +"\n";
			
		}
		return text;
	}
	
	private int getQuantity(HttpServletRequest request, HttpServletResponse response, int id) {
		
		int quantity = 1;
		if(request.getParameter("quantity"+id) != null) {
			quantity = Integer.parseInt(request.getParameter("quantity"+id));
			Cookie c1 = new Cookie("quantity"+id, ""+quantity);
			response.addCookie(c1);
		}
		try {
			if(Integer.parseInt(getCookieVal(request, response, "quantity"+id)) != 1)
				return Integer.parseInt(getCookieVal(request, response, "quantity"+id));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quantity;
	}
	
	protected String getCookieVal(HttpServletRequest request, HttpServletResponse response, String cName) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(cName.equals(cookie.getName()))
						return cookie.getValue();
			}
		}
		return "";
	}
	
	protected void delCookie(HttpServletRequest request, HttpServletResponse response, String cName) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for(int i = 0; i < cookies.length; i++) {
	            Cookie cookie = cookies[i];
	            if(cookie.getName().equals(cName)) {
		            cookie.setMaxAge(0);
		            response.addCookie(cookie);
	            }
	        }
	    }
	}
	
	protected void clearCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for(int i = 0; i < cookies.length; i++) {
	            Cookie cookie = cookies[i];
	            cookie.setMaxAge(0);
	            response.addCookie(cookie);
	        }
	    }
	}
	
	private double calculateSubtotal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		double subtotal = 0.00;
		if(session.getAttribute("cart") == null)
			return 0.00;
		else {
			for(Item items: items) {
				try {
					if(getCookieVal(request, response, "quantity"+items.getProduct_id()) != null) {
						int quantity = Integer.parseInt(getCookieVal(request, response, "quantity"+items.getProduct_id()));
						subtotal += items.getPrice()*quantity;
					}
					else
						subtotal += items.getPrice();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return subtotal;
		}
	}
	
	private double shipping(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("cart") == null)
			return 0.00;
		else
			return 5.00;
	}

}
