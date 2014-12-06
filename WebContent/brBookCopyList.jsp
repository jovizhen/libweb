<%@page import="com.gcit.jovi.library.dao.domain.BookCopy"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    	int cardNo = (Integer) request.getAttribute("cardNo");
    	int branchId = (Integer) request.getAttribute("branchId");
    	List<BookCopy> bookCopyList = (List<BookCopy>) request.getAttribute("bookCopyList");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Copy List</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
<table class="table table-hover">
			<tr>
				<td><b>Book Name</b></td>
				<td><b>Author</b></td>
				<td><b>Check Out</b></td>
			</tr>
			<%
				for (BookCopy bookCopy : bookCopyList)
				{
			%>
			<tr>
				<td><%=bookCopy.getBook().getTitle() %></td>
				<td><%=bookCopy.getBook().getAuthor().getAuthorName() %></td>
				<td><button type="button" class="btn btn-info"
						onclick="javascript:document.location.href='borrower?function=CHECK_OUT_BOOK&cardNo=<%=cardNo%>&branchId=<%=branchId%>&bookId=<%=bookCopy.getBook().getBookId()%>';">
						Check Out</button></td>
			</tr>
			<%
				}
			%>
		</table>
		</div>
</body>
</html>