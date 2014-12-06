<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String result = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Welcome to Library Management System!</h2>
	<%
		if (result != null)
		{
	%>
	<p><%=result%></p>
	<%
		}
	%>
	<a href="librarian.html">Librarian</a><BR>
	<a href="admin.jsp">Administrator</a><BR>
	<a href="borrower.html">Borrower</a>
</body>
</html>

