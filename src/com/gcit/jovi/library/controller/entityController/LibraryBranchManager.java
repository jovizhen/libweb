 package com.gcit.jovi.library.controller.entityController;


import com.gcit.jovi.library.controller.AbstractEntityManager;
import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.dao.LibraryBranchDao;
import com.gcit.jovi.library.dao.domain.LibraryBranch;

public class LibraryBranchManager extends AbstractEntityManager<LibraryBranch>
{
	public LibraryBranchManager(AbstractManager parentManager)
	{
		super(parentManager);
		setBaseDao(new LibraryBranchDao());
	}
}
