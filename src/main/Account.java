package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Account
 */
@WebServlet("/accountLegit")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
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
	
	private String printHeader() {
		
		String title = "Nano Hps Gaming Store :: Account";
		
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
				"<div id='registerDiv'>" +"\n" +
					"<h3 id='secTitle'><b>Register New User</b></h3>" +"\n" +
					"<form>" +"\n" +
						"<table id='regTable'>" +"\n" +
							"<tr><td>First Name: *</td><td><input type='text' name='fName' size='20'/></td></tr>" +"\n" +
							"<tr><td>Last Name: *</td><td><input type='text' name='lName' size='20'/></td></tr>" +"\n" +
							"<tr><td>Email: *</td><td><input type='email' name='email' size='30' placeholder='eg: myname@mymail.com'/></td></tr>" +"\n" +
							"<tr><td>Password: *</td><td><input type='password' name='pass' size='12'/></td></tr>" +"\n" +
							"<tr><td>Re-type Password: *</td><td><input type='password' name='rePass' size='12'/></td></tr>" +"\n" +
						"</table>" +"\n" +
					"<input type='checkbox' name='agreement' style='vertical-align: middle'><span style='font-size: 13px'>" +
					"I have read and agree to the <u>Terms of Use</u> and <u>Privacy Policy</u></span><br>" +"\n" +
					"</form><br>" +"\n" +
					"<a id='btn' href='javascript:void(0)'>Register</a><br><br>" +"\n" +
					"<span id='signin'><a href='javascript:void(0)'>Already a member? Log in now</a></span>" +"\n" +
				"</div><br>" +"\n" +
				"<div id='loginDiv'>" +"\n" +
				"<h3 id='secTitle'><b>User Login</b></h3>" +"\n" +
					"<form>" +"\n" +
						"<table id='logTable'>" +"\n" +
							"<tr><td>Email: </td><td><input type='email' name='email' size='30' placeholder='eg: myname@mymail.com'></td></tr>" +"\n" +
							"<tr><td>Password: </td><td><input type='password' name='pass' size='12'></td></tr>" +"\n" +
						"</table>" +"\n" +
					"</form><br>" +"\n" +
					"<a id='btn' href='javascript:void(0)'>Login</a><br><br>" +"\n" +
					"<span id='signup'><a href='javascript:void(0)'>Not a member? Sign up now</a></span>" +"\n" +
				"</div><br>" +"\n" +
				"<div id='viewDiv'>" +"\n" +
				"<h3 id='secTitle'><b>Account Details</b></h3>" +"\n" +
					"<table id='viewTable'>" +"\n" +
						"<tr><td>Name:</td><td>Muhammad Atif Mustaffa</td></tr>" +"\n" +
						"<tr><td>Email:</td><td>aretif95@gmail.com</td></tr>" +"\n" +
					"</table><br>" +"\n" +
					"<a id='btn' href='javascript:void(0)'>View Cart</a>" +"\n" +
					"<a id='btn' href='javascript:void(0)'>Logout</a><br><br>" +"\n" +
				"</div><br>" +"\n" +
			"</div>" +"\n" + printFooter();
	}

}
