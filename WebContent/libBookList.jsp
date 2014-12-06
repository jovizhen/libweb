<%@page import="com.gcit.jovi.library.dao.domain.LibraryBranch"%>
<%@page import="com.gcit.jovi.library.dao.domain.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<Book> bookList = (List<Book>) (request.getAttribute("bookList"));
	LibraryBranch branch = (LibraryBranch)(request.getAttribute("branch"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function selectBook(bookId)
{
	document.bookListForm.action = "librarian?function=SHOW_BRANCH_COPIES&bookId="+bookId;
	document.bookListForm.submit();
}

</script>
</head>
<body>
	<form action="Librarian" method="POST" name=bookListForm>
		<input type="hidden"  name="branchId" value="<%=branch.getBranchId()%>"/>
		<table border=1>
			<tr>
				<td><b>Book Name</b></td>
				<td><b>Author</b></td>
				<td><b>Publisher</b></td>
				<td><b>Select</b></td>
			</tr>
			<%
				for (Book book : bookList)
				{
			%>
			<tr>
				<td><%=book.getTitle()%></td>
				<td><%=book.getAuthor()!=null?book.getAuthor().getAuthorName():"" %></td>
				<td><%=book.getPublisher()!=null? book.getPublisher().getPublisherName():"" %></td>
				<td><input type="button" value="Select"
					onclick="javascript:selectBook(<%=book.getBookId()%>);"></td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</body>
</html>