package com.gcit.jovi.library.controller.entityController;

import java.sql.SQLException;
import java.util.List;

import com.gcit.jovi.library.controller.AbstractEntityManager;
import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.dao.BookLoanDao;
import com.gcit.jovi.library.dao.domain.BookLoan;
import com.gcit.jovi.library.util.DBUtil;

public class BookLoanManager extends AbstractEntityManager<BookLoan>
{
	public BookLoanManager(AbstractManager parentManager)
	{
		super(parentManager);
		setBaseDao(new BookLoanDao());
	}
	
	public List<BookLoan> getLoanByBorrower(int cardNo)
	{
		try
		{
			getBaseDao().setConnection(DBUtil.getDBConnection());
			return ((BookLoanDao)getBaseDao()).getBookLoanByBorrower(cardNo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public BookLoan getBookLoan(int bookId, int branchId, int cardNo) throws Exception 
	{
		try
		{
			getBaseDao().setConnection(DBUtil.getDBConnection());
			return ((BookLoanDao)getBaseDao()).read(bookId, branchId, cardNo);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			throw new LibDbException("Read book Loan fialed");
		}
		finally
		{
			if(getBaseDao().getConnection()!=null)
			{
				try
				{
					getBaseDao().getConnection().close();
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		}
	}
}
