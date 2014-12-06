package com.gcit.jovi.library.service;


import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.controller.entityController.BookCopyManager;
import com.gcit.jovi.library.controller.entityController.BookLoanManager;
import com.gcit.jovi.library.controller.entityController.BookManager;
import com.gcit.jovi.library.controller.entityController.BorrowerManager;
import com.gcit.jovi.library.controller.entityController.LibraryBranchManager;

public class BorrowerService extends AbstractManager
{
	private BookLoanManager			loanManager		= new BookLoanManager(this);
	private BookManager				bookManager		= new BookManager(this);
	private BookCopyManager         bookCopyManager = new BookCopyManager(this);
	private LibraryBranchManager	branchManager	= new LibraryBranchManager(this);
	private BorrowerManager         borrowerManager = new BorrowerManager(this);

	public BorrowerService(AbstractManager parentManager)
	{
		super(parentManager);
	}

	public BookLoanManager getLoanManager()
	{
		return loanManager;
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
	
	public BookCopyManager getBookCopyManager()
	{
		return bookCopyManager;
	}
}
