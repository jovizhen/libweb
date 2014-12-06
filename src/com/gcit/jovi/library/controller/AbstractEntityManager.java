package com.gcit.jovi.library.controller;

import java.sql.Connection;
import java.util.List;

import com.gcit.jovi.library.controller.entityController.LibDbException;
import com.gcit.jovi.library.dao.BaseDao;
import com.gcit.jovi.library.util.DBUtil;

public abstract class AbstractEntityManager<T> extends AbstractManager
{

	private T			dataSourceObject;
	private BaseDao<T>	baseDao;

	public AbstractEntityManager(AbstractManager parentManager)
	{
		super(parentManager);
	}

	public T getDataSourceObject()
	{
		return dataSourceObject;
	}

	public void setDataSourceObject(T dataSourceObject)
	{
		this.dataSourceObject = dataSourceObject;
	}

	public BaseDao<T> getBaseDao()
	{
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao)
	{
		this.baseDao = baseDao;
	}

	public void saveImp(T object) throws LibDbException, Exception
	{
		Connection connection = DBUtil.getDBConnection();
		baseDao.setConnection(connection);
		try
		{
			baseDao.create(object);
			connection.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			connection.rollback();
			throw new LibDbException("Save New Entity Failed");
		}
		finally
		{
			if (connection != null)
			{
				connection.close();
			}
		}
	}

	public void deleteImp(T object) throws Exception
	{
		Connection connection = DBUtil.getDBConnection();
		baseDao.setConnection(connection);
		try
		{
			baseDao.delete(object);
			connection.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			connection.rollback();
			throw new LibDbException("Delete Entity Failed");
		}
		finally
		{
			if (connection != null)
				connection.close();
		}
	}

	public void updateImp(T object) throws Exception
	{
		Connection connection = DBUtil.getDBConnection();
		baseDao.setConnection(connection);
		try
		{
			baseDao.update(object);
			connection.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			connection.rollback();
			throw new LibDbException("Update Failed");
		}
		finally
		{
			if (connection != null)
				connection.rollback();
		}
	}
	
	public List<T> findAll() throws Exception
	{
		Connection connection = DBUtil.getDBConnection();
		baseDao.setConnection(connection);
		try
		{
			return getBaseDao().readAll();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new LibDbException("read all query fialed");
		}
		finally
		{
			if(getBaseDao().getConnection()!=null)
				getBaseDao().getConnection().close();
		}
	}
	
	public T getEntityById(int id) throws Exception
	{
		getBaseDao().setConnection(DBUtil.getDBConnection());
		return getBaseDao().read(id);
	}
}
