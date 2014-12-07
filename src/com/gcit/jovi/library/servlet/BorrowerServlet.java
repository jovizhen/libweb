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

import com.gcit.jovi.library.dao.domain.Book;
import com.gcit.jovi.library.dao.domain.BookCopy;
import com.gcit.jovi.library.dao.domain.BookLoan;
import com.gcit.jovi.library.dao.domain.Borrower;
import com.gcit.jovi.library.dao.domain.LibraryBranch;
import com.gcit.jovi.library.service.BorrowerService;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet("/borrower")
public class BorrowerServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
	private BorrowerService		service				= new BorrowerService(null);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		BorrowerFunction function = BorrowerFunction.valueOf(request.getParameter("function"));
		String resultPage = null;
		switch (function)
		{
			case CHECK_OUT_LOGIN:
			{
				resultPage = checkOutLogin(request, response);
				break;
			}
			case RETURN_BOOK_LOGIN:
			{
				resultPage = returnBookLogin(request, response);
				break;
			}
			case SHOW_BRANCH_LIST:
			{
				showBranchList(request, response);
				resultPage = "/brBranchList.jsp";
				break;
			}
			case SHOW_BOOK_COPY_LIST:
			{
				showBookCopyList(request, response);
				resultPage = "/brBookCopyList.jsp";
				break;
			}
			case SHOW_BOOK_LOAN_LIST:
			{
				showBookLoanList(request, response);
				resultPage = "/brBookLoanList.jsp";
				break;
			}
			case CHECK_OUT_BOOK:
			{
				checkOutBook(request, response);
				resultPage = "/borrower?function=SHOW_BRANCH_LIST";
				break;
			}
			case RETURN_BOOK:
			{
				returnBook(request, response);
				resultPage = null;
				break;
			}
			default:
				break;
		}
		if (resultPage != null)
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(resultPage);
			rd.forward(request, response);
		}
	}

	private String checkOutLogin(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Borrower borrower = service.getBorrowerManager().getEntityById(cardNo);
			if (borrower == null)
			{
				request.setAttribute("message", "Wrong ID, please enter your Card No again");
				return "/brCheckoutLogin.jsp";
			}
			else
			{
				List<LibraryBranch> branchList = service.getBranchManager().findAll();
				request.setAttribute("branchList", branchList);
				request.setAttribute("borrower", borrower);
				return "/brBranchList.jsp";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "/brCheckoutLogin.jsp";
		}
	}

	private String returnBookLogin(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Borrower borrower = service.getBorrowerManager().getEntityById(cardNo);
			if (borrower == null)
			{
				request.setAttribute("message", "Wrong ID, please enter your Card No again");
				return "/brReturnBookLogin.jsp";
			}
			else
			{
				request.setAttribute("cardNo", cardNo);
				return "/borrower?function=SHOW_BOOK_LOAN_LIST";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "/brReturnBookLogin.jsp";
		}
	}

	private void showBranchList(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Borrower borrower = service.getBorrowerManager().getEntityById(cardNo);
			List<LibraryBranch> branchList = service.getBranchManager().findAll();
			request.setAttribute("branchList", branchList);
			request.setAttribute("borrower", borrower);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showBookCopyList(HttpServletRequest request, HttpServletResponse response)
	{
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		List<BookCopy> bookCopyList = service.getBookCopyManager().getBookCopyByBranch(branchId);
		List<BookCopy> newCopyList = new ArrayList<>();
		for (BookCopy bc : bookCopyList)
		{
			if (bc.getNoOfCopies() != 0)
				newCopyList.add(bc);
		}
		request.setAttribute("cardNo", cardNo);
		request.setAttribute("branchId", branchId);
		request.setAttribute("bookCopyList", newCopyList);
	}

	private void showBookLoanList(HttpServletRequest request, HttpServletResponse response)
	{
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		List<BookLoan> bookLoanList = service.getLoanManager().getLoanByBorrower(cardNo);
		request.setAttribute("bookLoanList", bookLoanList);
		request.setAttribute("cardNo", cardNo);
	}

	private void checkOutBook(HttpServletRequest request, HttpServletResponse response)
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		BookCopy bc = service.getBookCopyManager().getBookCopy(bookId, branchId);
		bc.setNoOfCopies(bc.getNoOfCopies() - 1);
		BookLoan bl = new BookLoan(new Book(bookId), new LibraryBranch(branchId), new Borrower(cardNo));
		try
		{
			service.getBookCopyManager().updateImp(bc);
			service.getLoanManager().saveImp(bl);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void returnBook(HttpServletRequest request, HttpServletResponse response)
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));

		try
		{
			BookCopy bc = service.getBookCopyManager().getBookCopy(bookId, branchId);
			bc.setNoOfCopies(bc.getNoOfCopies() + 1);
			BookLoan bl = service.getLoanManager().getBookLoan(bookId, branchId, cardNo);
			service.getLoanManager().deleteImp(bl);
			service.getBookCopyManager().updateImp(bc);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
