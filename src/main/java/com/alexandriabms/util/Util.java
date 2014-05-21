package com.alexandriabms.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.alexandriabms.model.Book;

/**
 * Util class. Contains utility methods.
 * 
 */
@Component
public class Util {

	/**
	 * Get list of Books from request.
	 * 
	 * @param data
	 *            - json data from request
	 * @return list of Books
	 */
	public List<Book> getBooksFromRequest(Object data) {
		List<Book> list;
		// if it is an array of books, convert to list of book objects
		if (data.toString().indexOf('[') > -1) {
			list = getListBooksFromJSON(data);
		} else { // it is only one book, convert to book object
			Book book = getBookFromJSON(data);
			list = new ArrayList<Book>();
			list.add(book);
		}
		return list;
	}

	/**
	 * Transform json data format into Book object
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	private Book getBookFromJSON(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
		Book newBook = (Book) JSONObject.toBean(jsonObject, Book.class);
		return newBook;
	}

	/**
	 * Transform json data format into list of Book objects
	 * 
	 * @param data
	 *            - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Book> getListBooksFromJSON(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Book> newBooks = (List<Book>) JSONArray.toCollection(jsonArray,
				Book.class);
		return newBooks;
	}

	/**
	 * Tranform array of numbers in json data format into list of Integer
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListIdFromJSON(Object data) {
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Integer> idBooks = (List<Integer>) JSONArray.toCollection(
				jsonArray, Integer.class);
		return idBooks;
	}
}