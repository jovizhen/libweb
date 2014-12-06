<%@page import="com.gcit.jovi.library.dao.domain.Borrower"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<Borrower> borrowerList = (List<Borrower>)request.getAttribute("borrowerList");
	String result = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Borrower</title>
<script >
function showBorrowerModal(id, name, address, phone)
{
	$('#cardNo').val(id);
	$('#oldBorrowerName').html(name);
	$('#oldBorrowerAddress').html(address);
	$('#oldBorrowerPhone').html(phone);
	$('#updateBorrowerModal').modal('show');
}

function updateBorrower()
{
	$.ajax({
		type:"POST",
		url:"admin?function=UPDATE_BORROWER",
	    data:{cardNo:$('#cardNo').val(), borrowerName:$('#borrowerName').val(), 
	    	borrowerAddress:$('#borrowerAddress').val(), borrowerPhone:$('#borrowerPhone').val()}
	}).done(function(msg){
		$('#borrower_name_'+$('#cardNo').val()).html($('#borrowerName').val());
		$('#borrower_add_'+$('#cardNo').val()).html($('#borrowerAddress').val());
		$('#borrower_phone_'+$('#cardNo').val()).html($('#borrowerPhone').val());
		$('#updateBorrowerModal').modal('hide');
	});
}
	
function deleteBorrower(cardNo)
{
	$.ajax({
		type:"POST",
		url:"admin?function=DELETE_BORROWER",
		data:{cardNo:cardNo}
	}).done(function(msg){
		$('#borrower_row_'+cardNo).html('');
	});
}
</script>
<%@include file="menu.jsp" %>
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
	<form action="admin" method="POST" name=borrowerListForm>
	<table class="table table-hover">
		<tr>
			<th>Borrower Name</th>
			<th>Borrower Address</th>
            <th>Borrower Phone</th>
			<th>Delete</th>
			<th>Edit</th>
		</tr>
		<%
			for(Borrower borrower : borrowerList)
			{
		%>
		<tr id="borrower_row_<%=borrower.getCardNo() %>">
		<td id="borrower_name_<%=borrower.getCardNo() %>"><%=borrower.getName() %></td>
		<td id="borrower_add_<%=borrower.getCardNo() %>"><%=borrower.getAddress() %></td>
		<td id="borrower_phone_<%=borrower.getCardNo() %>"><%=borrower.getPhone() %></td>
		<td><button type="button" class="btn btn-info" onClick="javascript:showBorrowerModal(<%=borrower.getCardNo() %>, 
		'<%=borrower.getName() %>', '<%=borrower.getAddress() %>', <%=borrower.getPhone() %>);">Update</button></td>
		<td><button type="button" class="btn btn-warning"
							onClick="javascript:deleteBorrower(<%=borrower.getCardNo()%>);">Delete
						</button></td>
		</tr>
		<%
			}
		%>
	</table>
	</form>
	
	<div class="modal fade" id="updateBorrowerModal" tabindex="-1"
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
						<form action="admin?function=UPDATE_BORROWER" method="post"
							role="form">
							<div class="form-group">
								<label for="borrowerName">Update borrower name: <mark><span id="oldBorrowerName"></span></mark>
								</label> 
								<input type="hidden" id="cardNo" name="cardNo" />
								<input type="text" class="form-control" id="borrowerName"
									name="borrowerName" placeholder="Enter new borrower name" /> 
							</div>
							<div class="form-group">
								<label for="borrowerAddress">Update borrower address: <mark><span id="oldBorrowerAddress"></span></mark>
								</label> 
								<input type="text" class="form-control"
									id="borrowerAddress" name="borrowerAddress" placeholder="Enter new borrower address"/> 
							</div>
							<div class="form-group">
								<label for="borrowerPhone">Update borrower phone: <mark><span id="oldBorrowerPhone"></span></mark>
								</label> 
								<input type="text"
									class="form-control" id="borrowerPhone" name="borrowerPhone" placeholder="Enter new borrower phone"/>
							</div>
							
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:updateBorrower();">Save changes</button>
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>