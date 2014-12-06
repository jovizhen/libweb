package com.gcit.jovi.library.dao.domain;

import java.sql.Date;

public class BookLoan
{
	private Book			book;
	private LibraryBranch	branch;
	private Borrower		borrower;
	private Date			dateOut;
	private Date			dueDate;

	public BookLoan()
	{
		
	}
	
	public BookLoan(Book book, LibraryBranch branch, Borrower borrower)
	{
		super();
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
	}

	public Book getBook()
	{
		return book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}

	public LibraryBranch getBranch()
	{
		return branch;
	}

	public void setBranch(LibraryBranch branch)
	{
		this.branch = branch;
	}

	public Borrower getBorrower()
	{
		return borrower;
	}

	public void setBorrower(Borrower borrower)
	{
		this.borrower = borrower;
	}

	public Date getDateOut()
	{
		return dateOut;
	}

	public void setDateOut(Date dateOut)
	{
		this.dateOut = dateOut;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}
}
