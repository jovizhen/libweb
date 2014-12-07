<%@page import="com.gcit.jovi.library.dao.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	List<LibraryBranch> branchList = (List<LibraryBranch>) request.getAttribute("branchList");
	String result = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Branch</title>
<script >
function showBranchModal(id,name,address)
{
	$('#branchId').val(id);
	$('#oldBranchName').html(name);
	$('#oldBranchAddress').html(address);
	$('#updateBranchModal').modal('show');
}

function updateBranch()
{
	$.ajax({
		type:"POST",
	    url:"admin?function=UPDATE_BRANCH",
	    data:{branchId:$('#branchId').val(), branchName:$('#branchName').val(), 
	    	branchAddress:$('#branchAddress').val()}
	}).done(function(msg){
		$('#branch_name_'+$('#branchId').val()).html($('#branchName').val());
		$('#branch_address_'+$('#branchId').val()).html($('#branchAddress').val());
		$('#updateBranchModal').modal('hide');
	});
	
}

function deleteBranch(id)
{
	$.ajax({
		type:"POST",
		url:"admin?function=DELETE_BRANCH",
		data:{branchId:id}		
	}).done(function(msg){
		$('#branch_row_'+id).html('');
	});
}
</script>
<%@ include file="menu.jsp"%>
</head>
<body>
<div class="container">
<h3>Branch Management</h3>
	<%
		if (result != null)
		{
	%>
	<p><%=result%></p>
	<%
		}
	%>
	<form action="admin" method="POST" name=branchListForm>
	<table class="table table-hover">
		<tr>
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>Delete</th>
			<th>Edit</th>
		</tr>
		<%
			for(LibraryBranch branch : branchList)
			{
		%>
		<tr id='branch_row_<%=branch.getBranchId() %>'>
		<td id='branch_name_<%=branch.getBranchId() %>'><%=branch.getBranchName() %></td>
		<td id='branch_address_<%=branch.getBranchId() %>'><%=branch.getBranchAddress() %></td>
		<td><button type="button" class="btn btn-info" onclick="javascript:showBranchModal(<%=branch.getBranchId() %>, 
		'<%=branch.getBranchName() %>', '<%=branch.getBranchAddress() %>');">Update</button></td>
		<td><button type="button" class="btn btn-warning" onClick="javascript:deleteBranch(<%=branch.getBranchId()%>);">Delete
						</button></td>
		</tr>
		<%
			}
		%>
	</table>
	</form>
	<div class="modal fade" id="updateBranchModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Update Branch</h4>
					</div>
					<div class="modal-body">
						<form action="admin?function=UPDATE_BRANCH" method="post"
							role="form">
							<div class="form-group">
								<label for="branchName">Update branch name: <mark><span id="oldBranchName"></span></mark>
								</label> 
								<input type="hidden" id="branchId" name="branchId" />
								<input type="text" class="form-control" id="branchName"
									name="branchName" placeholder="Enter new branch name" /> 
							</div>
							<div class="form-group">
								<label for="branchAddress">Update branch address: <mark><span id="oldBranchAddress"></span></mark>
								</label> 
								<input type="text" class="form-control"
									id="branchAddress" name="branchAddress" placeholder="Enter new branch address"/> 
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:updateBranch();">Save changes</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>