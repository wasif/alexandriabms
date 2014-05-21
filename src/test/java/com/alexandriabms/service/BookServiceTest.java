package com.alexandriabms.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.alexandriabms.dao.BookDAO;
import com.alexandriabms.model.Book;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookDAO bookDAO;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetBookList() {
		List<Book> books = new ArrayList<Book>(3);
		books.add(new Book());
		books.add(new Book());
		books.add(new Book());
		when(bookDAO.getBooks()).thenReturn(books);

		Assert.assertNotNull(bookService);

		List<Book> bookList = bookService.getBookList();
		
		assertEquals(books.size(), bookList.size());
	}

	@Test
	@Ignore
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testDelete() {
		fail("Not yet implemented");
	}

}
