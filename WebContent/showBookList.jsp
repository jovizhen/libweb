<%@page import="com.gcit.jovi.library.dao.domain.Publisher"%>
<%@page import="com.gcit.jovi.library.dao.domain.Author"%>
<%@page import="com.gcit.jovi.library.dao.domain.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	List<Book> bookList = (List<Book>) (request.getAttribute("bookList"));
	String result = (String) request.getAttribute("message");
	List<Author> authorList = (List<Author>)request.getAttribute("authorList");
	List<Publisher> publisherList = (List<Publisher>)request.getAttribute("pubList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Book</title>
<script>
function showBookModal(bookId, authorName, pubName, title)
{
	$('#bookId').val(bookId);
	$('#oldAuthorName').html(authorName);
	$('#oldPublisherName').html(pubName);
	$('#oldBookTitle').html(title);
	$('#updateBookModal').modal('show');
}

function updateBook()
{
	$.ajax({
		type:"POST",
		url:"admin?function=UPDATE_BOOK",
		data:{bookId:$('#bookId').val(), title: $('#title').val(), authorId:$('#authorSelect').val()
			,publisherId:$('#pubSelect').val()}
	}).done(function(msg){
		$('#book_title_'+$('#bookId').val()).html($('#title').val());
		$('#book_auth_'+$('#bookId').val()).html($('#authorSelect option[value='+$('#authorSelect').val()+']').text());
		$('#book_pub_'+$('#bookId').val()).html($('#pubSelect option[value='+$('#pubSelect').val()+']').text());
		$('#updateBookModal').modal('hide');
			});
}

function deleteBook(id)
{
	$.ajax({
		type:"POST",
		url:"admin?function=DELETE_BOOK",
	    data:{bookId:id}
	}).done(function(msg){
		$('#book_row_'+id).html('');
	});
}
</script>
<%@ include file="menu.jsp"%>
</head>
<body>
	<div class="container">
		<%
		if (result != null)
		{
	%>
		<p><%=result%></p>
		<%
		}
	%>
	<h3>Book Management</h3>
		<form action="admin" method="POST" name=bookListForm>
			<table class="table table-hover">
				<tr>
					<th>Book Name</th>
					<th>Author</th>
					<th>Publisher</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<%
				for (Book book : bookList)
				{
			%>
				<tr id="book_row_<%=book.getBookId() %>">
					<td id="book_title_<%=book.getBookId() %>"><%=book.getTitle()%></td>
					<td id="book_auth_<%=book.getBookId() %>"><%=book.getAuthor() != null ? book.getAuthor().getAuthorName() : ""%></td>
					<td id="book_pub_<%=book.getBookId()%>"><%=book.getPublisher() != null ? book.getPublisher().getPublisherName() : ""%></td>
					<td><button type="button" class="btn btn-info"
							onClick="javascript:showBookModal(<%=book.getBookId()%>, '<%=book.getAuthor()!=null?book.getAuthor().getAuthorName():""%>',
							 '<%=book.getPublisher()!=null?book.getPublisher().getPublisherName():""%>',
							 '<%=book.getTitle()%>');">Update</button></td>
					<td><button type="button" class="btn btn-warning" onClick="javascript:deleteBook(<%=book.getBookId()%>);">Delete</button></td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
		<div class="modal fade" id="updateBookModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Update Book</h4>
					</div>
					<div class="modal-body">
						<form action="admin?function=ADD_BOOK" method="post" role="form">
							<div class="form-group">
								<label for="title">Update title: <mark>
									<span id="oldBookTitle"></span></mark></label> 
									<input
									type="hidden" id="bookId" name="bookId" /> <input type="text"
									class="form-control" id="title" name="title"
									placeholder="Enter Title">
							</div>
							<div class="form-group">
								<label for="author">Update author: 
								<mark><span id="oldAuthorName"></span></mark></label> 
								<select id="authorSelect"
									class="form-control" name="authorId">
									<option value=-1>--Select Author--</option>
									<%
										for (Author a : authorList)
										{
									%>
									<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
									<%
										}
									%>
								</select>
							</div>
							<div class="form-group">
								<label for="publisher">Publisher: Update publisher: 
								<mark><span id="oldPublisherName"></span></mark></label> 
								<select id="pubSelect"
									class="form-control" name="publisherId">
									<option value=-1>--Select Publisher--</option>
									<%
										for (Publisher p : publisherList)
										{
									%>
									<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
									<% } %>
								</select>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:updateBook();">Save changes</button>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>