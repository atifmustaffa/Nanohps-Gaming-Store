package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class payment
 */
@WebServlet("/payment")
public class Payment extends HttpServlet {
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
	
	static ShippingDetails[] shipping;
	static int user_id = 1110;
	
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		accessDbInsert(request, response, request.getParameter("name"), request.getParameter("newAddress"), 
				request.getParameter("postcode"), request.getParameter("city"), request.getParameter("province"), request.getParameter("phoneNumber"));
		accessDb();
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
	
	public static void accessDb() {
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "select * from shipping where user_id = " + user_id +";";
							
			// Execute a query
			System.out.println("Executing SQL query...\n" +sql);
			stmt = conn.createStatement();

			stmt.executeQuery(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			int numberOfRecords = 0;
			if(resultSet.last())
			    numberOfRecords = resultSet.getRow();
			shipping = new ShippingDetails[numberOfRecords];
			
			int count = 0;
			resultSet.beforeFirst();	
			while(resultSet.next()) {
					shipping[count] = new ShippingDetails(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
							resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
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

	public static void accessDbInsert(HttpServletRequest request, HttpServletResponse response, String name, String newAddress, String postcode, String city, String province, String phoneNumber) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "insert into shipping values\n"
					+ "(0, "+user_id+", '"+name+"', '"+newAddress+"', "+postcode+", '"+city+"', '"+province+"', '"+phoneNumber+"')" +";";
							
			// Execute a query
			System.out.println("Executing SQL query...\n" +sql);
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
			//ResultSet resultSet = stmt.executeQuery(sql);
			

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
		
		String title = "Nano Hps Gaming Store :: Payment";
		
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
			"<div class='mainDiv' style='text-align: center'>" +"\n" +
				"<h3 id='secTitle'><b>Checkout: Payment Information</b></h3>" +"\n" +
				"<div id='paymentDiv'>" +"\n" +
					"<h4 style='text-align: left;'>Select payment type</h4><hr>" +"\n" +
					"<form>" +"\n" +
						"<table id='paymentTable'>" +"\n" +
							"<tr><td style='vertical-align: top;'>Payment Type: </td><td>" +"\n" +
							"<input type='radio' name='paymentType'/> Credit or Debit Card<br>" +"\n" +
							"<input type='radio' name='paymentType'/> Online Banking<br>" +"\n" +
							"<input type='radio' name='paymentType'/> PayPal<br>" +"\n" +
							"<input type='radio' name='paymentType'/> Cash on Delivery<br>" +"\n" +
							"</td></tr>" +"\n" +
						"</table>" +"\n" +
						"<div id='creditdebit'>" +"\n" +
							"<img src='./images/payment/creditdebit.jpg'/><br>" +"\n" +
							"<a id='btn' href='javascript:alert(\"Order has been paid\")'>Pay</a><br><br>" +"\n" +
						"</div><br>" +"\n" +
						"<div id='bank'>" +"\n" +
							"<select id='bankOption' name='bank'>" +"\n" +
								"<option value='None' selected>Select bank</option>" +"\n" +
								"<option value='Maybank'>Maybank</option>" +"\n" +
								"<option value='CIMB Clicks'>CIMB Clicks</option>" +"\n" +
							"</select><br><img src=''/>" +"\n" +
							"<br><a id='btn' href='javascript:alert(\"Order has been paid\")'>Pay</a><br><br>" +"\n" +
						"</div>" +"\n" +
						"<div id='paypal'>" +"\n" +
							"<img src='./images/payment/paypal.jpg'/><br>" +"\n" +
							"<a id='btn' href='javascript:alert(\"Order has been paid\")'>Pay</a><br><br>" +"\n" +
						"</div>" +"\n" +
						"<div id='cod'>" +"\n" +
							"You can pay in cash to us when you receive the goods.<br>" +"\n" +
							"(Only available for Gombak area)" +"\n" +
						"</div>" +"\n" +
					"</form><br>" +"\n" +
				"</div>" +"\n" +
				"<div id='orderDiv' style='text-align: left'>" +"\n" +
					"<h4>Shipping and Delivery Details</h4><hr>" +"\n" +
					"<table id='summaryTable'>" +"\n" +
						"<tr><td>"+shipping[0].getFullname()+"</span><br>" +
						shipping[0].getAddress()+"<br>" +
						shipping[0].getPostcode()+", "+shipping[0].getCity()+", "+shipping[0].getProvince()+"<br>" +
							"Phone Number: "+shipping[0].getPhone_num()+"</td></tr>" +"\n" +
						"<tr><td><span id='shippingName'>Standard Delivery</span>:<br>2-5 working days<br>RM 5.00</td></tr>" +"\n" +
					"</table>" +"\n" +
					"<h4>Order Summary</h4><hr>" +"\n" +
					"<table id='paymentProducts'><tr>" +"\n" +
					    "<th>Product</th>" +"\n" +
					    "<th>Quantity</th>" +"\n" +
					    "<th>Price</th>" +"\n" +
					  "</tr><tr>" +"\n" +
					  	"<td>Razer Razer Blackwidow X Tournament Chroma RGB Mechanical Keyboard </td>" +"\n" +
					    "<td>2</td>" +"\n" +
					    "<td>RM 1030.00</td>" +"\n" +
					    "</tr><tr>" +"\n" +
					    "<td>Steelseries backpack</td>" +"\n" +
					    "<td>1</td>" +"\n" +
					    "<td>RM 115.00</td></tr>" +"\n" +
					"</table>" +"\n" +
					"<table id='summaryTable'>" +"\n" +
						"<tr><td>Subtotal:</td><td>RM 1145.00</td></tr>" +"\n" +
						"<tr><td>Shipping Fees:</td><td>RM 5.00</td></tr>" +"\n" +
						"<tr><td><b>Total</b><span style='font-size: 12px'>(6% GST applied where applicable)</span>:</td><td style='font-weight: bold'>RM 1150.00</td></tr>" +"\n" +
					"</table><br>" +"\n" +
				"</div>" +"\n" +
				"<div style='clear: both'><hr><br><a id='btn' href='./successful'>Place Order</a></div><br><br>" +"\n" +
			"</div>" +"\n" + printFooter();
	}

}
