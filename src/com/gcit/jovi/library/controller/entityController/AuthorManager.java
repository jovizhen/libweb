package com.gcit.jovi.library.controller.entityController;


import com.gcit.jovi.library.controller.AbstractEntityManager;
import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.dao.AuthorDao;
import com.gcit.jovi.library.dao.domain.Author;

public class AuthorManager extends AbstractEntityManager<Author>
{
	public AuthorManager(AbstractManager parentManager)
	{
		super(parentManager);
		setBaseDao(new AuthorDao());
	}
	
	
}
