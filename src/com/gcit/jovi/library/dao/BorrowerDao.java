package com.gcit.jovi.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gcit.jovi.library.dao.domain.Borrower;

public class BorrowerDao extends BaseDao<Borrower>
{
	public BorrowerDao()
	{
		
	}
	
	public BorrowerDao(Connection connection)
	{
		super(connection);
	}

	@Override
	public void create(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"insert into tbl_borrower (name, address, phone) values (?,?,?)", 
				new Object[]
				{
			       (borrower.getName()==null)?
			    		   null : borrower.getName(),
			       (borrower.getAddress()==null)?
			    		   null : borrower.getAddress(),
			       (borrower.getPhone()==null)?
			    		   null : borrower.getPhone()
				});
	}

	@Override
	public void delete(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from tbl_borrower where cardNo = (?)";
		Object[] vals = { borrower.getCardNo() };
		executeUpdate(getConnection(), sql, vals);
	}

	@Override
	public void update(Borrower borrower) throws ClassNotFoundException, SQLException
	{
		executeUpdate(getConnection(), 
				"update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", 
				new Object[]
				{
			 		(borrower.getName()==null)?
		    		   null : borrower.getName(),
		    		(borrower.getAddress()==null)?
		    		   null : borrower.getAddress(),
		    		(borrower.getPhone()==null)?
		    		   null : borrower.getPhone(),
		    		borrower.getCardNo()
				});
		
	}

	@Override
	public ArrayList<Borrower> readAll() throws ClassNotFoundException, SQLException
	{
		return executeQuery(getConnection(), 
				"select * from tbl_borrower", null);
	}


	public Borrower read(int id) throws ClassNotFoundException, SQLException
	{
		ArrayList<Borrower> result = executeQuery(getConnection(), 
				"select * from tbl_borrower where cardNo = ?", 
				new Object[]
				{
					id
				});
		if (result != null && result.size() != 0)
		{
			return result.get(0);
		}
		return null;
	}
	
	public ArrayList<Borrower> readByName(String name) throws ClassNotFoundException, SQLException
	{
		ArrayList<Borrower> result = executeQuery(getConnection(), 
				"select * from tbl_borrower where name = ?", 
				new Object[]
				{
					name
				});
		return result;
	}

	@Override
	public ArrayList<Borrower> parseResult(ResultSet resultSet) throws SQLException
	{
		ArrayList<Borrower> borrowerList = new ArrayList<Borrower>();
		while (resultSet.next())
		{
			Borrower borrower=new Borrower();
			borrower.setCardNo(resultSet.getInt("cardNo"));
			borrower.setName(resultSet.getString("name"));
			borrower.setAddress(resultSet.getString("address"));
			borrower.setPhone(resultSet.getString("phone"));
			borrowerList.add(borrower);
		}
		return borrowerList;
	}
	
}
