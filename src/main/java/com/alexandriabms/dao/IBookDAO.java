package com.alexandriabms.dao;

import java.util.List;

import com.alexandriabms.model.Book;

public interface IBookDAO {

	List<Book> getBooks();

	void deleteBook(int id);

	Book saveBook(Book book);
}