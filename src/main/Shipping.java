package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class payment
 */
@WebServlet("/shipping")
public class Shipping extends HttpServlet {
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
	
	static int user_id = 1110;
	static ShippingDetails[] shipping;
	
    public Shipping() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		accessDb();
		//accessDbInsert(request, response, 0, user_id, , null, null, null);
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
	

	
	public static void accessDbInsert(HttpServletRequest request, HttpServletResponse response, String order_id, String user_id, String product_id, String quantity, String total, String order_date) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "insert into shipping (order_id, user_id, product_id, quantity, total, order_date) values\n"
					+ "("+order_id+", "+user_id+", '"+product_id+"', '"+quantity+"', "+total+", NOW())" +";";
							
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
		
		String title = "Nano Hps Gaming Store :: Shipping";
		
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
				"<div id='shippingDiv'>" +"\n" +
					"<h3 id='secTitle'><b>Checkout: Shipping Information</b></h3>" +"\n" +
					"<h4 style='text-align: left;'>Select shipping address</h4><hr>" +"\n" +
					"<form method='post' name='newAddForm' action='./payment'>" +"\n" +
						"<table id='addressTable'>" +"\n" + printShipping() +
							"<tr><td style='padding-top: 12px; vertical-align: top;'><input type='radio' name='shipping' value='new'/></td><td>" +
							"<span id='newAddrLabel'>New shipping address</span><span id='newAddrForm'>" +
								"<table id='regAddrTable'>" +"\n" +
									"<tr><td>Full Name: </td><td><input type='text' name='name' size='30'/></td></tr>" +"\n" +
									"<tr><td style='vertical-align: top;'>New Address: </td><td><textarea name='newAddress' rows='4'></textarea></td></tr>" +"\n" +
									"<tr><td>Postcode: </td><td><input type='text' name='postcode' size='5'/></td></tr>" +"\n" +
									"<tr><td>City: </td><td><input type='text' name='city' size='17'/></td></tr>" +"\n" +
									"<tr><td>Province: </td><td><select id='provinceOption' name='province'>" +"\n" +
										"<option value='Wp Kuala Lumpur' selected>Wp Kuala Lumpur</option>" +"\n" +
										"<option value='Johor'>Johor</option>" +"\n" +
										"<option value='Kedah'>Kedah</option>" +"\n" +
										"<option value='Kelantan'>Kelantan</option>" +"\n" +
										"<option value='Melaka'>Melaka</option>" +"\n" +
										"<option value='Negeri Sembilan'>Negeri Sembilan</option>" +"\n" +
										"<option value='Pahang'>Pahang</option>" +"\n" +
										"<option value='Penang'>Penang</option>" +"\n" +
										"<option value='Perak'>Perak</option>" +"\n" +
										"<option value='Perlis'>Perlis</option>" +"\n" +
										"<option value='Sabah'>Sabah</option>" +"\n" +
										"<option value='Sarawak'>Sarawak</option>" +"\n" +
										"<option value='Selangor'>Selangor</option>" +"\n" +
										"<option value='Terengganu'>Terengganu</option>" +"\n" +
										"<option value='Wp Labuan'>Wp Labuan</option>" +"\n" +
										"<option value='Wp Putrajaya'>Wp Putrajaya</option>" +"\n" +
									"</select></td></tr>" +"\n" +
									"<tr><td>Phone Number: </td><td><input type='text' name='phoneNumber' size='12'/></td></tr>" +"\n" +
								"</table>" +"\n</span>" +
							"</td></tr>" +
						"</table>" +"\n" +
					"</form><hr>" +"\n" +
					"<p style='text-align: left;padding-left: 30px;'>Standard Delivery:<br>2-5 working days<br>RM 5.00</p><hr><br>" +"\n" +
					"<a id='btn' class='gotoPayment' href='javascript:void(0)'>Proceed to Payment</a><br><br>" +"\n" +
				"</div><br>" +"\n" +
			"</div>" +"\n" + printFooter();
	}

	private String printShipping() {
		String text = "";
		for(int i = 0; i < shipping.length; i++)
			text += "<tr><td><input type='radio' name='shipping'/></td><td>" +
					"<input type='hidden' name='shipping_id' value='"+ shipping[i].getShipping_id() + "'/>" +
					"<span id='shippingName'>" + shipping[i].getFullname() + "</span><br>" +
					shipping[i].getAddress() + "<br>" +
					shipping[i].getPostcode() + ", " + shipping[i].getCity() + ", " + shipping[i].getProvince() + "<br>" +
					"Phone Number: " + shipping[i].getPhone_num() + "</td></tr>";
		return text;
	}

}
