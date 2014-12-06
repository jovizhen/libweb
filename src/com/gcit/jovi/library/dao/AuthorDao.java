package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gcit.jovi.library.dao.domain.Author;

public class AuthorDao extends BaseDao<Author>
{
	public AuthorDao()
	{
		
	}
	
	public AuthorDao(Connection connection)
	{
		super(connection);
	}

	@Override
	public void create(Author author) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(),  
				"insert into tbl_author (authorName) values (?)", 
				new Object[]{author.getAuthorName()});
	}

	@Override
	public void delete(Author author) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"delete from tbl_author where authorId = (?)", 
				new Object[]{ author.getAuthorId() });
	}

	@Override
	public void update(Author author) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"update tbl_author set authorName = (?) where authorId = (?)", 
				new Object[]{author.getAuthorName(), author.getAuthorId()});
	}

	@Override
	public ArrayList<Author> readAll() throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), "select * from tbl_author", null);
	}


	public Author read(int id) throws ClassNotFoundException, SQLException
	{
		ArrayList<Author> result = executeQuery(getConnection(),
				"select * from tbl_author where authorId = ?", 
				new Object[] { id });
		if (result != null && result.size() != 0)
		{
			return result.get(0);
		}
		return null;
	}
	
	public ArrayList<Author> readByName(String name) throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), 
				"select * from tbl_author where authorName like %?%", 
				new Object[]{name});
	}

	@Override
	public ArrayList<Author> parseResult(ResultSet resultSet) throws SQLException
	{
		ArrayList<Author> authorList = new ArrayList<Author>();
		while (resultSet.next())
		{
			Author author = new Author();
			author.setAuthorId(resultSet.getInt("authorId"));
			author.setAuthorName(resultSet.getString("authorName"));
			authorList.add(author);
		}
		return authorList;
	}
}
