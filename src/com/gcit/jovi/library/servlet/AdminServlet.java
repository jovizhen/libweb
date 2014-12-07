package com.gcit.jovi.library.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.jovi.library.dao.domain.Author;
import com.gcit.jovi.library.dao.domain.Book;
import com.gcit.jovi.library.dao.domain.Borrower;
import com.gcit.jovi.library.dao.domain.LibraryBranch;
import com.gcit.jovi.library.dao.domain.Publisher;
import com.gcit.jovi.library.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
	AdminService				service				= new AdminService(null);

	public AdminServlet()
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
		AdminFunction function = AdminFunction.valueOf(request.getParameter("function"));
		String resultPage = null;
		switch (function)
		{
			case SHOW_AUTHOR_LIST:
			{
				showAuthorList(request, response);
				resultPage = "/showAuthorList.jsp";
				break;
			}
			case SHOW_PUBLISHER_LIST:
			{
				showPublisherList(request, response);
				resultPage="/showPublisherList.jsp";
				break;
			}
			case SHOW_BOOK_LIST :
			{
				showBookList(request, response);
				resultPage= "/showBookList.jsp";
				break;
			}
			case SHOW_BRANCH_LIST:
			{
				showBranchList(request, response);
				resultPage= "/showBranchList.jsp";
				break;
			}
			case SHOW_BORROWER_LIST:
			{
				showBorrowerList(request, response);
				resultPage="/showBorrowerList.jsp";
				break;
			}
			case DELETE_AUTHOR:
			{
				deleteAuthor(request, response);
				resultPage=null;
				break;
			}
			case DELETE_PUBLISHER:
			{
				deletePublisher(request, response);
				resultPage="/admin?function=SHOW_PUBLISHER_LIST";
				break;
			}
			case DELETE_BOOK:
			{
				deleteBook(request, response);
				resultPage="/admin?function=SHOW_BOOK_LIST";
				break;
			}
			case DELETE_BRANCH:
			{
				deleteBranch(request, response);
				resultPage="/admin?function=SHOW_BRANCH_LIST";
				break;
			}
			case DELETE_BORROWER:
			{
				deleteBorrower(request, response);
				resultPage="/admin?function=SHOW_BORROWER_LIST";
				break;
			}
			case UPDATE_AUTHOR:
			{
				updateAuthor(request, response);
				resultPage=null;
				break;
			}
			case UPDATE_PUBLISHER:
			{
				updatePublisher(request, response);
				resultPage=null;
				break;
			}
			case UPDATE_BOOK:
			{
				updateBook(request, response);
				resultPage=null;
				break;
			}
			case UPDATE_BRANCH:
			{
				updateBranch(request, response);
				resultPage=null;
				break;
			}
			case UPDATE_BORROWER:
			{
				updateBorrower(request, response);
				resultPage=null;
				break;
			}
			case ADD_AUTHOR:
			{
				addAuthor(request, response);
				resultPage = "/admin?function=SHOW_AUTHOR_LIST";
				break;
			}
			case ADD_PUBLISHER:
			{
				addPublisher(request, response);
				resultPage = "/admin?function=SHOW_PUBLISHER_LIST";
				break;
			}
			case ADD_BOOK:
			{
				addBook(request, response);
				resultPage = "/admin?function=SHOW_BOOK_LIST";
				break;
			}
			case ADD_BRANCH:
			{
				addBranch(request, response);
				resultPage = "/admin?function=SHOW_BRANCH_LIST";
				break;
			}
			case ADD_BORROWER:
			{
				addBorrower(request, response);
				resultPage = "/admin?function=SHOW_BORROWER_LIST";
				break;
			}
			case OPEN_NEW_BOOK:
			{
				openNewBook(request, response);
				resultPage = "/addBook.jsp";
				break;
			}
			default:
				break;
		}
		if(resultPage!=null)
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(resultPage);
			rd.forward(request, response);
		}
	}

	private void updateAuthor(HttpServletRequest request, HttpServletResponse response)
	{
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		String authorName = request.getParameter("authorName");
		Author author = new Author(authorId, authorName);
		try
		{
			service.getAuthorManager().updateImp(author);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void updatePublisher(HttpServletRequest request, HttpServletResponse response)
	{
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		Publisher pub = new Publisher(publisherId, publisherName, publisherAddress, publisherPhone);
		try
		{
			service.getPublisherManager().updateImp(pub);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Book book = new Book(bookId);
		book.setTitle(title);
		book.setAuthor(authorId!=-1?new Author(authorId):null);
		book.setPublisher(publisherId!=-1?new Publisher(publisherId):null);
		try
		{
			service.getBookManager().updateImp(book);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void updateBranch(HttpServletRequest request, HttpServletResponse response)
	{
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		LibraryBranch branch = new LibraryBranch(branchId, branchName, branchAddress);
		try
		{
			service.getBranchManager().updateImp(branch);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void updateBorrower(HttpServletRequest request, HttpServletResponse response)
	{
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		String name = request.getParameter("borrowerName");
		String address = request.getParameter("borrowerAddress");
		String phone = request.getParameter("borrowerPhone");
		Borrower borrower = new Borrower(cardNo, name, address, phone);
		try
		{
			service.getBorrowerManager().updateImp(borrower);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response)
	{
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		try
		{
			service.getAuthorManager().deleteImp(new Author(authorId));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void deletePublisher(HttpServletRequest request, HttpServletResponse response)
	{
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		try
		{
			service.getPublisherManager().deleteImp(new Publisher(publisherId));
			request.setAttribute("message", "Delete publisher succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "Delete publisher failed!");
		}
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response)
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		try
		{
			service.getBookManager().deleteImp(new Book(bookId));
			request.setAttribute("message", "Delete book succeeded!");
		}
		catch (Exception e)
		{
			request.setAttribute("message", "Delete book failed!");
		}
	}

	private void deleteBranch(HttpServletRequest request, HttpServletResponse response)
	{
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		try
		{
			service.getBranchManager().deleteImp(new LibraryBranch(branchId));
			request.setAttribute("message", "Delete branch succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "Delete branch failed");
		}
	}

	private void deleteBorrower(HttpServletRequest request, HttpServletResponse response)
	{
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		try
		{
			service.getBorrowerManager().deleteImp(new Borrower(cardNo));
			request.setAttribute("message", "Delete borrower succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "Delete borrower failed!");
		}
	}

	private void showAuthorList(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Author> authorList = service.getAuthorManager().findAll();
			request.setAttribute("authorList", authorList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showPublisherList(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Publisher> publisherList = service.getPublisherManager().findAll();
			request.setAttribute("publisherList", publisherList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showBookList(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Book> bookList = service.getBookManager().findAll();
			List<Author> authorList = service.getAuthorManager().findAll();
			List<Publisher> publisherList = service.getPublisherManager().findAll();
			request.setAttribute("bookList", bookList);
			request.setAttribute("authorList", authorList);
			request.setAttribute("pubList", publisherList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showBranchList(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<LibraryBranch> branchList = service.getBranchManager().findAll();
			request.setAttribute("branchList", branchList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showBorrowerList(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Borrower> borrowerList = service.getBorrowerManager().findAll();
			request.setAttribute("borrowerList", borrowerList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void addAuthor(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String authorName = request.getParameter("authorName");
			Author author = new Author(authorName);
			service.getAuthorManager().saveImp(author);
			request.setAttribute("message", "Save author succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "Save author failed!");
		}
	}

	private void addPublisher(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String publisherName = request.getParameter("publisherName");
			String publisherAddress = request.getParameter("publisherAddress");
			String publisherPhone = request.getParameter("publisherPhone");
			Publisher publisher = new Publisher(publisherName, publisherAddress, publisherPhone);
			service.getPublisherManager().saveImp(publisher);
			request.setAttribute("message", "Save publisher succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "Save publisher failed!");
		}
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int authorId = Integer.parseInt(request.getParameter("authorId"));
			int publisherId = Integer.parseInt(request.getParameter("publisherId"));
			String title = request.getParameter("title");
			Book book=new Book();
			book.setAuthor(authorId!=-1?new Author(authorId):null);
			book.setPublisher(publisherId!=-1?new Publisher(publisherId):null);
			book.setTitle(title);
			service.getBookManager().saveImp(book);
			request.setAttribute("message", "Save book succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "Save book failed!");
		}
	}

	private void addBranch(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String branchName = request.getParameter("branchName");
			String branchAddress = request.getParameter("branchAddress");
			LibraryBranch branch = new LibraryBranch(branchName, branchAddress);
			service.getBranchManager().saveImp(branch);
			request.setAttribute("message", "Add new branch succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "Add new branch failed!");
		}
	}

	private void addBorrower(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String name = request.getParameter("borrowerName");
			String address = request.getParameter("borrowerAddress");
			String phone = request.getParameter("borrowerPhone");
			Borrower borrower = new Borrower(name, address, phone);
			service.getBorrowerManager().saveImp(borrower);
			request.setAttribute("message", "Save new borrower succeeded!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("message", "save new borrower failed!");
		}
	}

	private void openNewBook(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List<Author> authorList = service.getAuthorManager().findAll();
			List<Publisher> publisherList = service.getPublisherManager().findAll();
			request.setAttribute("message", "open new book succeed");
			request.setAttribute("authorList", authorList);
			request.setAttribute("publisherList", publisherList);
		}
		catch (Exception e)
		{
			request.setAttribute("message", "open new book failed!");
			System.out.println(e.getMessage());
		}
	}
}
