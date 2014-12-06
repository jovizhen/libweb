<%@page import="com.gcit.jovi.library.dao.domain.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.jovi.library.dao.domain.Borrower"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	Borrower borrower = (Borrower)request.getAttribute("borrower");
	List<LibraryBranch> branchList = (List<LibraryBranch>)request.getAttribute("branchList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
<form action="admin" method="POST" name=branchListForm>
	<table class="table table-hover">
		<tr>
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>Select Branch</th>
		</tr>
		<%
			for(LibraryBranch branch : branchList)
			{
		%>
				<tr id='branch_row_<%=branch.getBranchId()%>'>
					<td id='branch_name_<%=branch.getBranchId()%>'><%=branch.getBranchName()%></td>
					<td id='branch_address_<%=branch.getBranchId()%>'><%=branch.getBranchAddress()%></td>
					<td><button type="button" class="btn btn-info"
							onclick="javascript:document.location.href='borrower?function=SHOW_BOOK_COPY_LIST&cardNo=<%=borrower.getCardNo()%>&branchId=<%=branch.getBranchId()%>';">
							Select</button></td>
				</tr>
				<%
			}
		%>
	</table>
	</form>

</div>
</body>
</html>