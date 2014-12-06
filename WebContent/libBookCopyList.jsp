<%@page import="com.gcit.jovi.library.dao.domain.BookCopy"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<BookCopy> bookCopyList = (List<BookCopy>) request.getAttribute("bookCopyList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Copy List</title>
<script >
function showBookCopyModal(bookId, branchId, bookName, branchName, noOfCopy)
{
	$('#bookId').val(bookId);
	$('#branchId').val(branchId);
	$('#bookName').html(bookName);
	$('#branchName').html(branchName);
	$('#updateBookCopyModal').modal('show');
}

function updateBookCopy()
{
	$.ajax({
		type:"POST",
		url:"librarian?function=UPDATE_BOOK_COPY",
		data:{bookId:$('#bookId').val(), branchId:$('#branchId').val(), noOfCopies:$('#noOfCopies').val()}
		
	}).done(function(msg){
		$('#bc_'+$('#bookId').val()+'_'+$('#branchId').val()).html($('#noOfCopies').val());
		$('#updateBookCopyModal').modal('hide');
	});
}

</script>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
<table class="table table-hover">
			<tr>
				<td><b>Book Name</b></td>
				<td><b>Branch Name</b></td>
				<td><b>No of copies</b></td>
				<td><b>Update No of Copies</b></td>
			</tr>
			<%
				for (BookCopy bookCopy : bookCopyList)
				{
			%>
				<tr>
					<td><%=bookCopy.getBook().getTitle() %></td>
					<td ><%=bookCopy.getBranch().getBranchName() %></td>
					<td id="bc_<%=bookCopy.getBook().getBookId() %>_<%=bookCopy.getBranch().getBranchId() %>">
					<%=bookCopy.getNoOfCopies() %></td>
					<td><button type="button" class="btn btn-info"
							onclick="javascript:showBookCopyModal(<%=bookCopy.getBook().getBookId() %>, 
							<%=bookCopy.getBranch().getBranchId() %>, '<%=bookCopy.getBook().getTitle() %>', 
							'<%=bookCopy.getBranch().getBranchName() %>', <%=bookCopy.getNoOfCopies() %>);">Update</button></td>
				</tr>
				<%
				}
			%>
		</table>
		<div class="modal fade" id="updateBookCopyModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Update No Of Copies</h4>
					</div>
					<div class="modal-body">
						<form action="librarian?function=ADD_COPIES_TO_BRANCH" method="post"
							role="form">
							<div class="form-group">
								<label >Book: <span id="bookName"></span><br><br>
								<label >Branch: <span id="branchName"></span>
								</label> 
								<input type="hidden" id="bookId" name="bookId" /> 
								<input type="hidden" id="branchId" name="branchId" /> 
								<input
									type="text" class="form-control" id="noOfCopies"
									name="noOfCopies" placeholder="Enter No of copies" />
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:updateBookCopy();">Save changes</button>
					</div>
				</div>
			</div>
		</div>
</div>

</body>
</html>