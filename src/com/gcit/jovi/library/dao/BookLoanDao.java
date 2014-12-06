package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.gcit.jovi.library.dao.domain.BookLoan;

public class BookLoanDao extends BaseDao<BookLoan>
{
	public BookLoanDao()
	{
		
	}
	
	public BookLoanDao(Connection connection)
	{
		super(connection);
	}

	@Override
	public void create(BookLoan loan) throws ClassNotFoundException, SQLException
	{
		Timestamp outDate=new Timestamp(new Date().getTime());
		long theFuture = System.currentTimeMillis() + (86400 * 7 * 1000);
		Timestamp dueDate=new Timestamp(theFuture);
		executeUpdate(getConnection(), 
				"insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?,?,?,?,?)", 
				new Object[]{ loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo(),
			outDate, dueDate});
	}

	@Override
	public void delete(BookLoan loan) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"delete from tbl_book_loans where bookId = (?) and branchId = (?) and cardNo = (?)", 
				new Object[]{ loan.getBook().getBookId(), loan.getBranch().getBranchId(), loan.getBorrower().getCardNo() });
	}

	@Override
	public void update(BookLoan loan) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"update tbl_book_loans set dueDate = (?) where bookId = (?) and branchId = (?) and cardNo =(?)", 
				new Object[]{ loan.getDueDate(), loan.getBook().getBookId(), 
			loan.getBranch().getBranchId(), loan.getBorrower().getCardNo() });
	}

	@Override
	public ArrayList<BookLoan> readAll() throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), "select * from tbl_book_loans", null);
	}

	public BookLoan read(int bookId, int branchId, int cardNo) throws ClassNotFoundException, SQLException
	{
		ArrayList<BookLoan> result = executeQuery(getConnection(), 
				"select * from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?", 
				new Object[]{ bookId, branchId, cardNo });

		if (result != null && result.size() != 0)
		{
			return result.get(0);
		}
		return null;
	}
	
	public ArrayList<BookLoan> getBookLoanByBorrower(int cardNo) throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), "select * from tbl_book_loans where cardNo = ?", 
				new Object[]{cardNo});
	}

	@Override
	public ArrayList<BookLoan> parseResult(ResultSet resultSet) throws SQLException, ClassNotFoundException
	{
		ArrayList<BookLoan> loanList = new ArrayList<BookLoan>();
		while (resultSet.next())
		{
			BookLoan loan=new BookLoan();
			loan.setBook(new BookDao(getConnection()).read(resultSet.getInt("bookId")));
			loan.setBranch(new LibraryBranchDao(getConnection()).read(resultSet.getInt("branchId")));
			loan.setBorrower(new BorrowerDao(getConnection()).read(resultSet.getInt("cardNo")));
			loan.setDateOut(resultSet.getDate("dateOut"));
			loan.setDueDate(resultSet.getDate("dueDate"));
			loanList.add(loan);
		}
		return loanList;
	}


	@Override
	public BookLoan read(int id) throws ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}
}
