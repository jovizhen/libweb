<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Publisher</title>
<%@ include file="menu.jsp"%>
</head>
<body>
	<div class="container">
		<h3>Add Publisher</h3>
		<form action="admin?function=ADD_PUBLISHER" class="form-horizontal col-md-6"
			method="post" role="form">
			<div class="form-group">
			<label for="publisherName">Name: </label>
				<input type="text" class="form-control" name="publisherName" placeholder="Enter Publisher Name">
			</div>
			<div class="form-group">
			<label for="publisherAddress">Address: </label>
				<input type="text" class="form-control" name="publisherAddress" placeholder="Enter Publisher Address">
			</div>
			<div class="form-group">
			<label for="publisherPhone">Phone: </label>
				<input type="text" class="form-control" name="publisherPhone" placeholder="Enter Publisher Phone">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</body>
</html>