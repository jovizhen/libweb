<%@page import="com.gcit.jovi.library.dao.domain.BookLoan"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<BookLoan> bookLoanList = (List<BookLoan>) request.getAttribute("bookLoanList");
	int cardNo = (Integer) request.getAttribute("cardNo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Loan List</title>
<script>
function returnBook(bookId, branchId, cardNo)
{
	$.ajax({
		type:"POST",
		url:"borrower?function=RETURN_BOOK",
		data:{bookId:bookId, branchId:branchId, cardNo:cardNo}
	}).done(function(msg){
		$('#loan_row_'+bookId+'_'+branchId+'_'+cardNo).html('');
	});
}
</script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container">
	<h4>Please choose the Book you want to return:</h4>
		<table class="table table-hover">
			<tr>
				<th>Book</th>
				<th>Branch</th>
				<th>check out date</th>
				<th>due date</th>
				<th>Return Book</th>
			</tr>
			<%
				for (BookLoan loan : bookLoanList)
				{
			%>
			<tr
				id='loan_row_<%=loan.getBook().getBookId()%>_<%=loan.getBranch().getBranchId()%>_<%=loan.getBorrower().getCardNo()%>'>
				<td><%=loan.getBook().getTitle()%></td>
				<td><%=loan.getBranch().getBranchName()%></td>
				<td><%=loan.getDateOut()%></td>
				<td><%=loan.getDueDate()%></td>
				<td><button type="button" class="btn btn-info"
						onclick="javascript:returnBook(<%=loan.getBook().getBookId() %>,<%=loan.getBranch().getBranchId() %>, <%=loan.getBorrower().getCardNo() %>);">Return</button></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>