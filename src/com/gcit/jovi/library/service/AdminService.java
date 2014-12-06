package com.gcit.jovi.library.service;


import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.controller.entityController.AuthorManager;
import com.gcit.jovi.library.controller.entityController.BookManager;
import com.gcit.jovi.library.controller.entityController.BorrowerManager;
import com.gcit.jovi.library.controller.entityController.LibraryBranchManager;
import com.gcit.jovi.library.controller.entityController.PublisherManager;

public class AdminService extends AbstractManager
{
	private AuthorManager			authorManager		= new AuthorManager(this);
	private PublisherManager		publisherManager	= new PublisherManager(this);
	private BookManager				bookManager			= new BookManager(this);
	private LibraryBranchManager	branchManager		= new LibraryBranchManager(this);
	private BorrowerManager			borrowerManager		= new BorrowerManager(this);
	
	public AdminService(AbstractManager parentManager)
	{
		super(parentManager);
	}

	public AuthorManager getAuthorManager()
	{
		return authorManager;
	}

	public PublisherManager getPublisherManager()
	{
		return publisherManager;
	}

	public BookManager getBookManager()
	{
		return bookManager;
	}

	public LibraryBranchManager getBranchManager()
	{
		return branchManager;
	}

	public BorrowerManager getBorrowerManager()
	{
		return borrowerManager;
	}
}
