<%@page import="com.gcit.jovi.library.dao.domain.Author"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<Author> authorList = (List<Author>) request.getAttribute("authorList");
	String result = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Author</title>
<script>
function deleteAuthor(authId)
{
	$.ajax({
		  type: "POST",
		  url: "admin?function=DELETE_AUTHOR",
		  data: { authorId: authId}
		})
		  .done(function( msg ) {
			  //alert('#auth_row_'+authId);
			  $('#auth_row_' + authId).html('');
		  });	
}

function updateAuthor()
{
	$.ajax({
		  type: "POST",
		  url: "admin?function=UPDATE_AUTHOR",
		  data: { authorId: $('#authorId').val(), authorName: $('#authorName').val() }
		})
		  .done(function( msg ) {
		    //alert('#auth_'+$('#authorId').val());
		    $('#auth_'+$('#authorId').val()).html($('#authorName').val());
		    $('#updateAuthorModal').modal('hide');
		  });	
}

function showUpdateModal(id, name)
{
	$('#authorId').val(id);
	$('#oldAuthorName').html(name);
	$('#updateAuthorModal').modal('show');
}
</script>
</head>
<body>
<%@ include file="menu.jsp"%>
	<div class="container " >
		<%
		if (result != null)
		{
	%>
		<p><%=result%></p>
		<%
			}
		%>
		<form action="admin" method="POST" name=authorListForm>
			<table class="table table-hover">
				<tr>
					<th>Author Name</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<%
					for (Author author : authorList)
					{
				%>
				<tr id="auth_row_<%=author.getAuthorId() %>">
					<td id="auth_<%=author.getAuthorId() %>"><%=author.getAuthorName()%></td>
					<td><button type="button" class="btn btn-info"
							onClick="javascript:showUpdateModal(<%=author.getAuthorId()%>, '<%=author.getAuthorName()%>');">Update</button></td>
					<td><button type="button" class="btn btn-warning"
							onClick="javascript:deleteAuthor(<%=author.getAuthorId()%>);">Delete
						</button></td>
				</tr>
				<%
					}
				%>
			</table>
		</form>

		<div class="modal fade" id="updateAuthorModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Update author</h4>
					</div>
					<div class="modal-body">
						<form action="admin?function=UPDATE_AUTHOR" method="post"
							role="form">
							<div class="form-group">
								<label for="authorName">You are about to update the
									Author: <mark><span id="oldAuthorName"></span></mark>
								</label> <input type="hidden" id="authorId" name="authorId" /> <input
									type="text" class="form-control" id="authorName"
									name="authorName" placeholder="Enter new author name" />
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:updateAuthor();">Save changes</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>