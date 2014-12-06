package com.gcit.jovi.library.dao.domain;

public class BookCopy
{
	private Book			book;
	private LibraryBranch	branch;
	private Integer			noOfCopies;

	public BookCopy()
	{
		
	}
	
	public BookCopy(Book book, LibraryBranch branch, Integer noOfCopies)
	{
		super();
		this.book = book;
		this.branch = branch;
		this.noOfCopies = noOfCopies;
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

	public Integer getNoOfCopies()
	{
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies)
	{
		this.noOfCopies = noOfCopies;
	}
}
