package com.gcit.jovi.library.service;


import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.controller.entityController.BookCopyManager;
import com.gcit.jovi.library.controller.entityController.BookManager;
import com.gcit.jovi.library.controller.entityController.LibraryBranchManager;

public class LibrarianService extends AbstractManager
{
	private LibraryBranchManager	branchManager	= new LibraryBranchManager(this);
	private BookManager				bookManager		= new BookManager(this);
	private BookCopyManager			bookCopyManager	= new BookCopyManager(this);
	public int						selectedBranchId;

	public LibrarianService(AbstractManager parentManager)
	{
		super(parentManager);
	}

	public LibraryBranchManager getBranchManager()
	{
		return branchManager;
	}

	public BookManager getBookManager()
	{
		return bookManager;
	}

	public BookCopyManager getBookCopyManager()
	{
		return bookCopyManager;
	}

	public int getSelectedBranchId()
	{
		return selectedBranchId;
	}
}
