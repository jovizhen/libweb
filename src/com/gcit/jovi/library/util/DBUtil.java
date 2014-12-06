package com.gcit.jovi.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil
{

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");

		Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "gcit");
		dbConnection.setAutoCommit(false);
		return dbConnection;
	}
}
