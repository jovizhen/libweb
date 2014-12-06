<%@page import="com.gcit.jovi.library.dao.domain.BookCopy"%>
<%@page import="com.gcit.jovi.library.dao.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	List<LibraryBranch> branchList = (List<LibraryBranch>) (request.getAttribute("branchList"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Branch List</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
	<form action="Librarian" method="POST" name=manageBranchForm>
		<table class="table table-hover">
			<tr>
				<td><b>Branch Name</b></td>
				<td><b>Branch Address</b></td>
				<td><b>Select Branch</b></td>
			</tr>
			<%
				for (LibraryBranch branch : branchList)
				{
			%>
				<tr>
					<td id='branch_name_<%=branch.getBranchId() %>'><%=branch.getBranchName()%></td>
					<td id='branch_address_<%=branch.getBranchId() %>'><%=branch.getBranchAddress()%></td>
					<td><button type="button" class="btn btn-info"
							onclick="javascript:document.location.href=
							'librarian?function=SHOW_BRANCH_COPIES&branchId=<%=branch.getBranchId() %>';">Select</button></td>
				</tr>
				<%
				}
			%>
		</table>
	</form>
</div>
</body>
</html>