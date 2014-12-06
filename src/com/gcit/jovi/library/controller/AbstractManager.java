package com.gcit.jovi.library.controller;

public abstract class AbstractManager
{
	protected AbstractManager		parentManager;

	public AbstractManager(AbstractManager parentManager)
	{
		configure(parentManager);
	}

	protected void configure(AbstractManager parentManager)
	{
		this.parentManager = parentManager;
	}

	public AbstractManager getParentManager()
	{
		return parentManager;
	}
}
