package com.gcit.jovi.library.controller.entityController;

import java.sql.SQLException;
import java.util.List;

import com.gcit.jovi.library.controller.AbstractEntityManager;
import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.dao.BookCopyDao;
import com.gcit.jovi.library.dao.domain.BookCopy;
import com.gcit.jovi.library.util.DBUtil;

public class BookCopyManager extends AbstractEntityManager<BookCopy>
{
	public BookCopyManager(AbstractManager parentManager)
	{
		super(parentManager);
		try
		{
			setBaseDao(new BookCopyDao(DBUtil.getDBConnection()));
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<BookCopy> getBookCopyByBranch(int branchId)
	{
		try
		{
			getBaseDao().setConnection(DBUtil.getDBConnection());
			return ((BookCopyDao)getBaseDao()).readByBranch(branchId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public BookCopy getBookCopy(int bookId, int branchId) 
	{
		try
		{
			getBaseDao().setConnection(DBUtil.getDBConnection());
			return ((BookCopyDao)getBaseDao()).read(bookId, branchId);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
				try
				{
					if(getBaseDao().getConnection()!=null)
					{
						getBaseDao().getConnection().close();
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}
}
