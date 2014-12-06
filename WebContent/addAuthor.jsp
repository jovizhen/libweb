<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Author</title>
<%@ include file="menu.jsp"%>
</head>
<body>
	<div class="container">
	<h3>Add Author</h3>
		<form action="admin?function=ADD_AUTHOR" class="form-horizontal col-md-6"
			method="post" role="form">
			<div class="form-group">
				<label for="authorName">Name: </label>
				<input type="text" class="form-control" id="authorName"
					name="authorName" placeholder="Enter Author Name">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</body>
</html>