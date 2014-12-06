<%@page import="com.gcit.jovi.library.dao.domain.BookCopy"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.jovi.library.dao.domain.Book"%>
<%@page import="com.gcit.jovi.library.dao.domain.LibraryBranch"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<Book> bookList = (List<Book>) request.getAttribute("bookList");
	List<LibraryBranch> branchList = (List<LibraryBranch>) request.getAttribute("branchList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book Copy</title>
</head>
<body>
	<%@ include file="menu.jsp"%>

	<div class="container">
		<h3>Add Book Copy</h3>
		<form action="librarian?function=ADD_BOOK_COPY" method="post" class="form-horizontal col-md-6" role="form">
			
			<div class="form-group">
				<label for="book">Book: </label> <select class="form-control"
					name="bookId">
					<option value=-1>--Select Book--</option>
					<%
						for (Book book : bookList)
						{
					%>
					<option value="<%=book.getBookId()%>"><%=book.getTitle()%></option>
					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="branch">Branch: </label> <select class="form-control"
					name="branchId">
					<option value=-1>--Select Branch--</option>
					<%
						for (LibraryBranch branch : branchList)
						{
					%>
					<option value="<%=branch.getBranchId()%>"><%=branch.getBranchName()%></option>
					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="noOfCopies">No of copies: </label>
				<input type="text" class="form-control" id="noOfCopies"
				name="noOfCopies" placeholder="Enter No of copies">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>

	</div>

</body>
</html>