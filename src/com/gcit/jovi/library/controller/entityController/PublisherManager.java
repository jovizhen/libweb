package com.gcit.jovi.library.controller.entityController;

import com.gcit.jovi.library.controller.AbstractEntityManager;
import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.dao.PublisherDao;
import com.gcit.jovi.library.dao.domain.Publisher;

public class PublisherManager extends AbstractEntityManager<Publisher>
{
	public PublisherManager(AbstractManager parentManager)
	{
		super(parentManager);
		setBaseDao(new PublisherDao());
	}
}
