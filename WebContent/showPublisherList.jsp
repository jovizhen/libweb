<%@page import="com.gcit.jovi.library.dao.domain.Publisher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<Publisher> publisherList = (List<Publisher>) request.getAttribute("publisherList");
	String result = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Publisher</title>
<script >
function showPublisherModal(id, name, address, phone)
{
	$('#publisherId').val(id);
	$('#oldPublisherName').html(name);
	$('#oldPublisherAddress').html(address);
	$('#oldPublisherPhone').html(phone);
	$('#updatePublisherModal').modal('show');
}

function updatePublisher()
{
	console.log('im here');
	$.ajax({
		type:"POST",
		url:"admin?function=UPDATE_PUBLISHER",
		data:{publisherId:$('#publisherId').val(), publisherName:$('#publisherName').val(), 
			publisherAddress:$('#publisherAddress').val(), publisherPhone:$('#publisherPhone').val()}		
	}).done(function(msg){
		$('#pub_name_'+$('#publisherId').val()).html($('#publisherName').val());
		$('#pub_add_'+$('#publisherId').val()).html($('#publisherAddress').val());
		$('#pub_phone_'+$('#publisherId').val()).html($('#publisherPhone').val());
		$('#updatePublisherModal').modal('hide');
	});
}

function deletePublisher(pubId)
{
	$.ajax({
		  type: "POST",
		  url: "admin?function=DELETE_PUBLISHER",
		  data: { publisherId: pubId}
		})
		  .done(function( msg ) {
			  $('#pub_row_' + pubId).html('');
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
	<form action="admin" method="POST" name=publisherListForm>
	<table class="table table-hover">
		<tr>
			<th>Publisher Name</th>
			<th>Publisher Address</th>
			<th>Publisher Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			for(Publisher publisher : publisherList)
			{
		%>
		<tr id="pub_row_<%=publisher.getPublisherId() %>">
		<td id="pub_name_<%=publisher.getPublisherId() %>"><%=publisher.getPublisherName()!=null?publisher.getPublisherName():"" %></td>
		<td id="pub_add_<%=publisher.getPublisherId() %>"><%=publisher.getPublisherAddress()!=null?publisher.getPublisherAddress():""%></td>
		<td id="pub_phone_<%=publisher.getPublisherId() %>"><%=publisher.getPublisherPhone()!=null?publisher.getPublisherPhone():"" %></td>
					<td><button type="button" class="btn btn-info" 
					onClick="javascript:showPublisherModal(<%=publisher.getPublisherId() %>, '<%=publisher.getPublisherName() %>', 
					'<%=publisher.getPublisherAddress() %>', '<%=publisher.getPublisherPhone() %>');">Update</button></td>
		<td><button type="button" class="btn btn-warning"
							onClick="javascript:deletePublisher(<%=publisher.getPublisherId()%>);">Delete
						</button></td>
		</tr>
		<%
			}
		%>
	</table>
	</form>
			<div class="modal fade" id="updatePublisherModal" tabindex="-1"
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
						<form action="admin?function=UPDATE_PUBLISHER" method="post"
							role="form">
							<div class="form-group">
								<label for="publisherName">Update publisher name: <mark><span id="oldPublisherName"></span></mark>
								</label> 
								<input type="hidden" id="publisherId" name="publisherId" />
								<input type="text" class="form-control" id="publisherName"
									name="publisherName" placeholder="Enter new publisher name" /> 
							</div>
							<div class="form-group">
								<label for="publisherAddress">Update publisher address: <mark><span id="oldPublisherAddress"></span></mark>
								</label> 
								<input type="text" class="form-control"
									id="publisherAddress" name="publisherAddress" placeholder="Enter new publisher address"/> 
							</div>
							<div class="form-group">
								<label for="publisherPhone">Update publisher phone: <mark><span id="oldPublisherPhone"></span></mark>
								</label> 
								<input type="text"
									class="form-control" id="publisherPhone" name="publisherPhone" placeholder="Enter new publisher phone"/>
							</div>
							
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:updatePublisher();">Save changes</button>
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>