<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Return Book Login</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
		<%
			if (message != null)
			{
		%>
		<p style="color: red"><%=message%></p>
		<%
			}
		%>
		<h3>Enter Card No</h3>
		<form action="borrower?function=RETURN_BOOK_LOGIN" class="form-horizontal col-md-6"
			method="post" role="form">
			<div class="form-group">
				<label for="cardNo">Card No: </label>
				<input type="text" class="form-control" id="cardNo"
					name="cardNo" placeholder="Enter Card No">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</body>
</html>