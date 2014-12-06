<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Borrower</title>
<%@ include file="menu.jsp" %>
</head>
<body>
<div class="container">
<h3>Add Borrower</h3>
		<form action="admin?function=ADD_BORROWER" class="form-horizontal col-md-6"
			method="post" role="form">
			<div class="form-group">
			<label for="borrowerName">Name: </label>
				<input type="text" class="form-control" name="borrowerName" placeholder="Enter Borrower Name">
			</div>
			<div class="form-group">
			<label for="borrowerAddress">Address: </label>
				<input type="text" class="form-control" name="borrowerAddress" placeholder="Enter Borrower Address">
			</div>
			<div class="form-group">
			<label for="borrowerPhone">Phone: </label>
				<input type="text" class="form-control" name="borrowerPhone" placeholder="Enter Borrower Phone">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
</div>
</body>
</html>