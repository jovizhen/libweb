package com.gcit.jovi.library.controller.entityController;


import com.gcit.jovi.library.controller.AbstractEntityManager;
import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.dao.BorrowerDao;
import com.gcit.jovi.library.dao.domain.Borrower;

public class BorrowerManager extends AbstractEntityManager<Borrower>
{

	public BorrowerManager(AbstractManager parentManager)
	{
		super(parentManager);
		setBaseDao(new BorrowerDao());
	}
}
