package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gcit.jovi.library.dao.domain.BookCopy;

public class BookCopyDao extends BaseDao<BookCopy>
{
	public BookCopyDao()
	{
		
	}
	
	public BookCopyDao(Connection connection)
	{
		super(connection);
	}

	@Override
	public void create(BookCopy copy) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(),
				"insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?)", 
				new Object[]{copy.getBook().getBookId(), copy.getBranch().getBranchId(), copy.getNoOfCopies()});
	}

	@Override
	public void delete(BookCopy copy) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"delete from tbl_book_copies where bookId = (?) and branchId = (?)",
				new Object[]{ copy.getBook().getBookId(), copy.getBranch().getBranchId() });
	}

	@Override
	public void update(BookCopy copy) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"update tbl_book_copies set noOfCopies = (?) where bookId = (?) and branchId = (?)", 
				new Object[]{ copy.getNoOfCopies(), copy.getBook().getBookId(), copy.getBranch().getBranchId() });
	}

	@Override
	public ArrayList<BookCopy> readAll() throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), "select * from tbl_book_copies", null);
	}

	public BookCopy read(int bookId, int branchId) throws ClassNotFoundException, SQLException
	{
		ArrayList<BookCopy> result = executeQuery(getConnection(), 
				"select * from tbl_book_copies where bookId = ? and branchId = ?", 
				new Object[]{ bookId, branchId });
		if (result != null && result.size() != 0)
		{
			return result.get(0);
		}
		return null;
	}
	
	public ArrayList<BookCopy> readByBranch(int branchId) throws ClassNotFoundException, SQLException
	{
		ArrayList<BookCopy> result = executeQuery(getConnection(), 
				"select * from tbl_book_copies where branchId = ?", 
				new Object[]{ branchId });
		return result;
	}

	@Override
	public ArrayList<BookCopy> parseResult(ResultSet resultSet) throws ClassNotFoundException, SQLException
	{
		ArrayList<BookCopy> copyList = new ArrayList<BookCopy>();
		while (resultSet.next())
		{
			BookCopy copy = new BookCopy();
			copy.setBook(new BookDao(getConnection()).read(resultSet.getInt("bookId")));
			copy.setBranch(new LibraryBranchDao(getConnection()).read(resultSet.getInt("branchId")));
			copy.setNoOfCopies(resultSet.getInt("noOfCopies"));
			copyList.add(copy);
		}
		return copyList;
	}

	@Override
	public BookCopy read(int id) throws ClassNotFoundException, SQLException
	{
		return null;
	}
}
