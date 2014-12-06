package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gcit.jovi.library.dao.domain.Publisher;

public class PublisherDao extends BaseDao<Publisher>
{
	public PublisherDao()
	{
		
	}
	
	public PublisherDao(Connection connection)
	{
		super(connection);
	}

	@Override
	public void create(Publisher publisher) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)", 
				new Object[]
				{ 
					publisher.getPublisherName() != null ? 
						publisher.getPublisherName() : null,
					publisher.getPublisherAddress() !=null ?
						publisher.getPublisherAddress() : null,
					publisher.getPublisherPhone() != null ?
						publisher.getPublisherPhone() : null
				});
	}

	@Override
	public void delete(Publisher publisher) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"delete from tbl_publisher where publisherId = (?)", 
				new Object[]{ publisher.getPublisherId() });
	}

	@Override
	public void update(Publisher publisher) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"update tbl_publisher set publisherName = ?, publisherAddress = ?, "
				+ "publisherPhone = ? where publisherId = ?", 
				new Object[]
						{ 
							publisher.getPublisherName() != null ? 
								publisher.getPublisherName() : null,
							publisher.getPublisherAddress() !=null ?
								publisher.getPublisherAddress() : null,
							publisher.getPublisherPhone() != null ?
								publisher.getPublisherPhone() : null,
							publisher.getPublisherId()
						});
	}

	@Override
	public ArrayList<Publisher> readAll() throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), "select * from tbl_publisher", null);
	}


	public Publisher read(int id) throws ClassNotFoundException, SQLException
	{
		ArrayList<Publisher> result = executeQuery(getConnection(), 
				"select * from tbl_publisher where publisherId = ?", new Object[]{id});
		if (result != null && result.size() != 0)
		{
			return result.get(0);
		}
		return null;
	}

	@Override
	public ArrayList<Publisher> parseResult(ResultSet resultSet) throws SQLException
	{
		ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
		while (resultSet.next())
		{
			Publisher publisher = new Publisher();
			publisher.setPublisherId(resultSet.getInt("publisherId"));
			publisher.setPublisherName(resultSet.getString("publisherName"));
			publisher.setPublisherAddress(resultSet.getString("publisherAddress"));
			publisher.setPublisherPhone(resultSet.getString("publisherPhone"));
			publisherList.add(publisher);
		}
		return publisherList;
	}
	
	public ArrayList<Publisher> readByName(String name) throws Exception 
	{
		return executeQuery(getConnection(),
				"select * from tbl_publisher where publisherName like %?%", 
				new Object[]{name});
	}
}
