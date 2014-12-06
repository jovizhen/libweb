<%@page import="com.gcit.jovi.library.dao.domain.Publisher"%>
<%@page import="com.gcit.jovi.library.dao.domain.Author"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	List<Author> authorList = (List<Author>) request.getAttribute("authorList");
	List<Publisher> publisherList = (List<Publisher>) request.getAttribute("publisherList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book</title>
<%@ include file="menu.jsp"%>
</head>
<body>
<div class="container">
<h3>Add Book</h3>
<form action="admin?function=ADD_BOOK" method="post" class="form-horizontal col-md-6" role="form">
  <div class="form-group">
   <label for="title">Title: </label>
    <input type="text" class="form-control" id="title" name="title" placeholder="Enter Title">
  </div>
  <div class="form-group">
    <label for="author">Author: </label>
    <select class="form-control" name="authorId">
		<option value=-1>--Select Author--</option>
		<%for(Author a : authorList) { %>
			<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
		<% } %>
	</select>
  </div>
  <div class="form-group">
    <label for="publisher">Publisher: </label>
    <select class="form-control" name="publisherId">
		<option value=-1>--Select Publisher--</option>
		<%for(Publisher p : publisherList) { %>
			<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
		<% } %>
	</select>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>

</div>
</body>
</html>