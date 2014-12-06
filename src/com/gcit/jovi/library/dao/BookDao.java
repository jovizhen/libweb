package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gcit.jovi.library.dao.domain.Book;

public class BookDao extends BaseDao<Book>
{
	public BookDao()
	{
		
	}
	
	public BookDao(Connection connection)
	{
		super(connection);
	}

	@Override
	public void create(Book book) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"insert into tbl_book (title, authId, pubId) values (?,?,?)", 
				new Object[] 
				{
				  book.getTitle(), 
				  (book.getAuthor() != null) ? 
						  book.getAuthor().getAuthorId() : null,
				  book.getPublisher() != null ? 
						  book.getPublisher().getPublisherId() : null 
				});
	}

	@Override
	public void delete(Book book) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"delete from tbl_book where bookId = (?)", 
				new Object[] { book.getBookId() });
	}

	@Override
	public void update(Book book) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(),
				"update tbl_book set title = ?, authId = ?, pubId = ? where bookId = ?",
				new Object[] 
				{
					book.getTitle(),
					(book.getAuthor() != null) ? 
							book.getAuthor().getAuthorId() : null,
					book.getPublisher() != null ? 
							book.getPublisher().getPublisherId() : null,
					book.getBookId()
				});
	}

	@Override
	public ArrayList<Book> readAll() throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), "select * from tbl_book", null);
	}
	
	public Book read(int id) throws ClassNotFoundException, SQLException
	{
		ArrayList<Book> result = executeQuery(getConnection(), 
				"select * from tbl_book where bookId = ?", new Object[]{ id });
		
		if (result != null && result.size() != 0)
		{
			return result.get(0);
		}
		return null;
	}
	
	public ArrayList<Book> readByName(String name) throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), 
					"select * from tbl_book where title like %?%", new Object[]{ name });
	}

	@Override
	public ArrayList<Book> parseResult(ResultSet resultSet) throws SQLException, ClassNotFoundException
	{
		ArrayList<Book> bookList = new ArrayList<Book>();
		while (resultSet.next())
		{
			Book book=new Book();
			book.setBookId(resultSet.getInt("bookId"));
			book.setAuthor(new AuthorDao(getConnection()).read(resultSet.getInt("authId")));
			book.setPublisher(new PublisherDao(getConnection()).read(resultSet.getInt("pubId")));
			book.setTitle(resultSet.getString("title"));
			bookList.add(book);
		}
		return bookList;
	}
}
