package com.alexandriabms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexandriabms.dao.BookDAO;
import com.alexandriabms.model.Book;
import com.alexandriabms.util.Util;

/**
 * Book Service
 * 
 */
@Service
public class BookService {

	private BookDAO bookDAO;
	private Util util;

	/**
	 * Get all books
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Book> getBookList() {
		return bookDAO.getBooks();
	}

	/**
	 * Create new Book
	 * 
	 * @param data
	 *            - json data from request
	 * @return created book
	 */
	@Transactional
	public List<Book> create(Object data) {
		List<Book> newBooks = new ArrayList<Book>();
		List<Book> list = util.getBooksFromRequest(data);
		for (Book book : list) {
			newBooks.add(bookDAO.saveBook(book));
		}
		return newBooks;
	}

	/**
	 * Update book/books
	 * 
	 * @param data
	 *            - json data from request
	 * @return updated books
	 */
	@Transactional
	public List<Book> update(Object data) {
		List<Book> returnBooks = new ArrayList<Book>();
		List<Book> updatedBooks = util.getBooksFromRequest(data);
		for (Book book : updatedBooks) {
			returnBooks.add(bookDAO.saveBook(book));
		}
		return returnBooks;
	}

	/**
	 * Delete book/books
	 * 
	 * @param data
	 *            - json data from request
	 */
	@Transactional
	public void delete(Object data) {
		// if it is an array of deleted books, convert to list of integers
		if (data.toString().indexOf('[') > -1) {
			List<Integer> deleteBooks = util.getListIdFromJSON(data);
			for (Integer id : deleteBooks) {
				bookDAO.deleteBook(id);
			}
		} else { // it is only one book, convert to integer
			Integer id = Integer.parseInt(data.toString());
			bookDAO.deleteBook(id);
		}
	}

	/**
	 * @param bookDAO
	 */
	@Autowired
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	/**
	 * @param util
	 */
	@Autowired
	public void setUtil(Util util) {
		this.util = util;
	}
}