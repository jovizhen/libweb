<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- saved from url=(0040)http://getbootstrap.com/examples/navbar/ -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://getbootstrap.com/favicon.ico">

<title>Navbar Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="http://getbootstrap.com/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="http://getbootstrap.com/examples/navbar/navbar.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="./script/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<!-- Static navbar -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="index.jsp">Library
					Management</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Author
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="addAuthor.jsp">Create</a></li>
							<li><a href="admin?function=SHOW_AUTHOR_LIST">Manage
									Author</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Publisher
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="addPublisher.jsp">Create</a></li>
							<li><a href="admin?function=SHOW_PUBLISHER_LIST">Manage
									Publisher</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Book
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="admin?function=OPEN_NEW_BOOK">Create</a></li>
							<li><a href="admin?function=SHOW_BOOK_LIST">Manage Book</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Branch
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="addBranch.jsp">Create</a></li>
							<li><a href="admin?function=SHOW_BRANCH_LIST">Manage
									Branch</a></li>
						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Borrower
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="addBorrower.jsp">Create</a></li>
							<li><a href="admin?function=SHOW_BORROWER_LIST">Manage
									Borrower</a></li>
						</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Librarian Management
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="librarian?function=NEW_BOOK_COPY">Add copy</a></li>
							<li><a href="librarian?function=SHOW_BRANCH_LIST">Manage Branch / Copy</a></li>
						</ul></li>
						
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Borrower Management
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="brCheckoutLogin.jsp">Check Out</a></li>
							<li><a href="brReturnBookLogin.jsp">Return Book</a></li>
						</ul></li>

				</ul>
			</div>
			<!--/.nav-collapse -->

		</div>
	</nav>
	<!-- /container -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./script/jquery.min.js"></script>
	<script src="./script/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="./script/ie10-viewport-bug-workaround.js"></script>
</body>
</html>