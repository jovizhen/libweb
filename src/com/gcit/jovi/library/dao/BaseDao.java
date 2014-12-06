package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseDao<T>
{
	private Connection connection;
	
	public BaseDao()
	{
	}
	
	public BaseDao(Connection connection)
	{
		setConnection(connection);
	}
	
	protected void executeUpdate(Connection connection, String sql, Object[] vals) throws SQLException
	{
		PreparedStatement stmt = connection.prepareStatement(sql);
		int count = 1;
		for (Object obj : vals)
		{
			stmt.setObject(count, obj);
			count++;
		}
		stmt.executeUpdate();
	}

	protected ArrayList<T> executeQuery(Connection connection, String sql, Object[] vals) throws SQLException, ClassNotFoundException
	{
		PreparedStatement stmt = connection.prepareStatement(sql);
		int count = 1;
		if (vals != null)
		{
			for (Object obj : vals)
			{
				stmt.setObject(count, obj);
				count++;
			}
		}
		return parseResult(stmt.executeQuery());
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
	
	public Connection getConnection()
	{
		return connection;
	}

	public abstract void create(T object) throws ClassNotFoundException, SQLException;

	public abstract void delete(T object) throws ClassNotFoundException, SQLException;

	public abstract void update(T object) throws ClassNotFoundException, SQLException;

	public abstract ArrayList<T> readAll() throws ClassNotFoundException, SQLException;
	
	public abstract T read(int id) throws ClassNotFoundException, SQLException;

	public abstract ArrayList<T> parseResult(ResultSet resultSet) throws SQLException, ClassNotFoundException;
}
