package com.alexandriabms.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.alexandriabms.model.Book;

/**
 * Book DAO class.
 * 
 */
@Repository
public class BookDAO implements IBookDAO {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * Get List of bookss from database
	 * 
	 * @return list of all books
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks() {
		return hibernateTemplate.find("from Book");
	}

	/**
	 * Delete a book with the id passed as parameter
	 * 
	 * @param id
	 */
	@Override
	public void deleteBook(int id) {
		Object record = hibernateTemplate.load(Book.class, id);
		hibernateTemplate.delete(record);
	}

	/**
	 * Create a new Book on the database or Update book
	 * 
	 * @param book
	 * @return book added or updated in DB
	 */
	@Override
	public Book saveBook(Book book) {
		hibernateTemplate.saveOrUpdate(book);
		return book;
	}
}