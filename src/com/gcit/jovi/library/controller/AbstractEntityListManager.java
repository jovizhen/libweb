package com.gcit.jovi.library.controller;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityListManager<T> extends AbstractManager
{
	private ArrayList<T> dataSourceList;
	
	public AbstractEntityListManager(AbstractManager parentManager)
	{
		super(parentManager);
	}
	
	public ArrayList<T> getDataSourceList()
	{
		return dataSourceList;
	}

	public void setDataSourceList(ArrayList<T> dataSourceList)
	{
		this.dataSourceList = dataSourceList;
	}
	
	public abstract AbstractEntityManager<T> getEntityManager();
	
	public void refresh()
	{
		setDataSourceList((ArrayList<T>) findAll());
	}
	
	public abstract List<T> findAll();
}
