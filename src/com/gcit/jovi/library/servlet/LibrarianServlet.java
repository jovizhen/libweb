package com.gcit.jovi.library.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.jovi.library.controller.entityController.LibDbException;
import com.gcit.jovi.library.dao.domain.Book;
import com.gcit.jovi.library.dao.domain.BookCopy;
import com.gcit.jovi.library.dao.domain.LibraryBranch;
import com.gcit.jovi.library.service.LibrarianService;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet("/librarian")
public class LibrarianServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
	private LibrarianService service = new LibrarianService(null);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		LibrarianFunction function = LibrarianFunction.valueOf(request.getParameter("function"));
		String resultPage = null;
		try
		{
			switch(function)
			{
				case SHOW_BRANCH_LIST:
				{
					getBranchList(request, response);
					resultPage= "/libBranchList.jsp";
					break;
				}
				case SHOW_BRANCH_COPIES:
				{
					showBranchCopies(request, response);
					resultPage = "/libBookCopyList.jsp";
					break;
				}
				case UPDATE_BRANCH:
				{
					updateBranch(request, response);
					resultPage=null;
					break;
				}
				case UPDATE_BOOK_COPY:
				{
					updateBookCopy(request, response);
					resultPage = null;
					break;
				}
				case NEW_BOOK_COPY:
				{
					newBookCopy(request, response);
					resultPage="/addBookCopy.jsp";
					break;
				}
				case ADD_BOOK_COPY:
				{
					addCopies(request, response);
					resultPage="/librarian?function=SHOW_BRANCH_LIST";
					break;
				}
			default:
				break;
			}
			request.setAttribute("message", "succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "failed!");
		}
		if(resultPage!=null)
		{
			RequestDispatcher rd= getServletContext().getRequestDispatcher(resultPage);
			rd.forward(request, response);
		}
	}

	private void updateBookCopy(HttpServletRequest request, HttpServletResponse response)
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));
		BookCopy bookCopy = new BookCopy(new Book(bookId), new LibraryBranch(branchId), noOfCopies);
		try
		{
			service.getBookCopyManager().updateImp(bookCopy);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private void newBookCopy(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Book> bookList = service.getBookManager().findAll();
			List<LibraryBranch> branchList= service.getBranchManager().findAll();
			request.setAttribute("bookList", bookList);
			request.setAttribute("branchList", branchList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	private void getBranchList(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ArrayList<LibraryBranch> branchList=(ArrayList<LibraryBranch>) service.getBranchManager().findAll();
		request.setAttribute("branchList", branchList);
	}

	private void updateBranch(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String branchName = request.getParameter("branchName");
		String branchAddress=request.getParameter("branchAddress");
		LibraryBranch branch = new LibraryBranch(branchId, branchName,  branchAddress);
		service.getBranchManager().updateImp(branch);
	}
	
	private void showBranchCopies(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		List<BookCopy> bookCopyList=service.getBookCopyManager().getBookCopyByBranch(branchId);
		request.setAttribute("bookCopyList", bookCopyList);
	}
	
	private void addCopies(HttpServletRequest request, HttpServletResponse response) throws LibDbException, Exception
	{
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int bookId   = Integer.parseInt(request.getParameter("bookId"));
		int noOfCopies = Integer.parseInt(request.getParameter("noOfCopies"));
		BookCopy bookCopy = new BookCopy(new Book(bookId), new LibraryBranch(branchId),noOfCopies);
		service.getBookCopyManager().saveImp(bookCopy);
	}
}
