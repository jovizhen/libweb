package com.gcit.jovi.library.controller.entityController;


import com.gcit.jovi.library.controller.AbstractEntityManager;
import com.gcit.jovi.library.controller.AbstractManager;
import com.gcit.jovi.library.dao.BookDao;
import com.gcit.jovi.library.dao.domain.Book;

public class BookManager extends AbstractEntityManager<Book>
{

	public BookManager(AbstractManager parentManager)
	{
		super(parentManager);
		setBaseDao(new BookDao());
	}
}
