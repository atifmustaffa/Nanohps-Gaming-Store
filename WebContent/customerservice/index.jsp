<!DOCTYPE html>
<html>
	<head>
		<meta charset='ISO-8859-1'>
		<link href='https://fonts.googleapis.com/css?family=Poppins|Rubik' rel='stylesheet'>
		<link href='../shopStyles.css' rel='stylesheet'>
		<script src='../jquery.min.js'></script>
		<script src='../mainJavascript.js'></script>
		<title>Nano Hps Gaming Store :: Customer Service</title>
	</head>
	<body>
		<header>
			<div class='hiddenTopBar'>
				<div id='container'>
					<div class='headerLeft'>
						<a href='../main'><img id='banner' src='../images/HEADERbANNERsmall.png'/></a>
					</div>
					<div class='searchPanel'><form id='searchForm1' method='get' action='../shop'>
						<select id='searchFilter' name='filter'>
							<option>All</option>
							<option>Keyboard</option>
							<option>Mouse</option>
							<option>Headset</option>
							<option>Mouse Mat</option>
							<option>Accessories</option>
						</select><input id='searchInput' type='text' name='search' placeholder='Search for products or brands'/><a id='searchButton' href='javascript:document.getElementById("searchForm1").submit()'>
						<img id='searchIco' src='../images/searchIco.png'></img></a></form>
					</div>
					<div id='headerRight'>
						<span id='accbtn' style='padding: 18px 0px'><a style='padding: 18px 30px' id='accountDrop' href='../account'>Account</a>
							<span class='dropdown-content2'>
							<a id='loginBtn' href='../login'>Login</a>
							<a id='registerBtn' href='../signup'>Register</a>
							<a id='logoutBtn' href='../account'>Logout</a>
						</span></span>
						<a id='cart' href='../cart'><img id='banner' src='../images/cartSmall.png'/>Cart</a>
					</div>
				</div>
			</div>
			<div class='topHeader'>
				<div id='container'>
					<div class='headerTitle'>
					Nano Hps Gaming Store :: Customer Service
					</div>
						<div id='headerUser'>
							<a id='accountDrop' href='../aboutUs'>About Us</a><!--
							  --><a id='accountDrop' href='../customerservice'>Customer Service</a><!--
							  --><span id='accbtn'><a id='accountDrop' href='../account'>Account</a>
							<span class='dropdown-content'>
							<a id='loginBtn' href='../login'>Login</a>
							<a id='registerBtn' href='../signup'>Register</a>
							<a id='logoutBtn' href='../account'>Logout</a>
					    </span></span>
					</div>
				</div>
			</div>
			<div class='mainHeader'>
				<div id='container'>
					<div class='bannerPicture'>
						<a href='../main'><img id='banner' src='../images/HEADERbANNER.png'/></a>
					</div>
					<div class='searchPanel'><form id='searchForm2' method='get' action='../shop'>
						<select id='searchFilter' name='filter'>
								<option>All</option>
								<option>Keyboard</option>
								<option>Mouse</option>
								<option>Headset</option>
								<option>Mouse Mat</option>
								<option>Accessories</option>
						</select><input id='searchInput' type='text' name='search' placeholder='Search for products or brands'/><a id='searchButton' href='javascript:document.getElementById("searchForm2").submit()'>
						<img id='searchIco' src='../images/searchIco.png'></img></a></form>
					</div>
					<a id='cart' href='../cart'><img id='banner' src='../images/cart.png'/>Cart</a>
				</div>
			</div>
			<nav id='navigation'>
				<ul id='menu'>
					<li><a href='javascript:void(0)'>Keyboard</a></li>
					<li><a href='javascript:void(0)'>Mouse</a></li>
					<li><a href='javascript:void(0)'>Headset</a></li>
					<li><a href='javascript:void(0)'>Mouse Mat</a></li>
					<li><a href='javascript:void(0)'>Accessories</a></li>
				</ul>
			</nav>
		</header>
		<div class='mainDiv'>
			<div class='contact-us'>
				<h3 id='secTitle'><b>Contact Us</b></h3>
				<form id='contact-us-form' name='contact-us-form'>
					<table id='regTable'>
						<tr><td>* <span style='font-size: 12px;'>indicates required field</span></td><td></td></tr>
						<tr><td>Name: *</td><td><input type='text' name='name'/></td></tr>
						<tr><td>Email: *</td><td><input type='email' name='email' placeholder='eg: myname@mymail.com'/></td></tr>
						<tr><td>Phone Number: *</td><td><input type='text' name='phoneNum'/></td></tr>
						<tr><td>Reason: *</td><td><select id='reasonOption' name='reason'>
								<option>Please select</option>
								<option>Feedback</option>
								<option>Problem with order</option>
								<option>Problem with payment</option>
								<option>Problem with shipping</option>
								<option>Problem with product information</option>
						</select></td></tr>
						<tr><td>Question: *</td><td><input type='text' name='question'/></td></tr>
						<tr><td style='vertical-align: top;'>Details: *</td><td><textarea name='details' rows='4'></textarea></td></tr>
					</table>
				</form><br>
				<a id='btn' href='javascript:void(0)'>Submit</a><br><br>
			</div>
		</div>
		<footer>
			<div class='mainFooter'>
			&copy; Copyright 2016 Nano Hps Gaming Store<br>
			Semester project for Web Programming CSC 2103 Section 1<br>
			Developed By:<br>
			Abdul Hadi bin Noordin 1428711<br>
			Muhammad 'Atif bin Mustaffa 1429619<br>
			Muhammad Adil Fahim bin Zulkifli 1429609<br>
			Muhammad Azfar bin Hamzah 1426417<br>
			Muhammad Hazim Bin Muhammuddin 1428415<br>
			</div>
		</footer>
	</body>
</html>