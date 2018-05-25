<!DOCTYPE html>
<html>
	<head>
		<meta charset='ISO-8859-1'>
		<link href='https://fonts.googleapis.com/css?family=Poppins|Rubik' rel='stylesheet'>
		<link href='../shopStyles.css' rel='stylesheet'>
		<script src='../jquery.min.js'></script>
		<script src='../mainJavascript.js'></script>
		<title>Nano Hps Gaming Store :: Account</title>
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
					Nano Hps Gaming Store :: Account
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
			<div id='viewDiv'>
				<h3 id='secTitle'><b>Account Details</b></h3>
					<table id='viewTable'>
						<tr><td>Name:</td><td>Muhammad Atif Mustaffa</td></tr>
						<tr><td>Email:</td><td>aretif95@gmail.com</td></tr>
					</table><br>
				<a id='btn' href='javascript:void(0)'>View Cart</a>
				<a id='btn' href='javascript:void(0)'>Logout</a><br><br>
			</div><br>
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