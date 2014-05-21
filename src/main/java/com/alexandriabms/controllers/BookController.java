package com.alexandriabms.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alexandriabms.model.Book;
import com.alexandriabms.service.BookService;

/**
 * Controller - Spring
 * 
 */
@Controller
public class BookController {

	private BookService bookService;

	@RequestMapping(value = "/book/view.action")
	public @ResponseBody
	Map<String, ? extends Object> view() throws Exception {
		try {
			List<Book> books = bookService.getBookList();
			return getMap(books);
		} catch (Exception e) {
			return getModelMapError("Error retrieving Books from database.");
		}
	}

	@RequestMapping(value = "/book/create.action")
	public @ResponseBody
	Map<String, ? extends Object> create(@RequestParam Object data)
			throws Exception {
		try {
			List<Book> books = bookService.create(data);
			return getMap(books);
		} catch (Exception e) {
			return getModelMapError("Error trying to create book.");
		}
	}

	@RequestMapping(value = "/book/update.action")
	public @ResponseBody
	Map<String, ? extends Object> update(@RequestParam Object data)
			throws Exception {
		try {
			List<Book> books = bookService.update(data);
			return getMap(books);
		} catch (Exception e) {
			return getModelMapError("Error trying to update book.");
		}
	}

	@RequestMapping(value = "/book/delete.action")
	public @ResponseBody
	Map<String, ? extends Object> delete(@RequestParam Object data)
			throws Exception {
		try {
			bookService.delete(data);
			Map<String, Object> modelMap = new HashMap<String, Object>(1);
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			return getModelMapError("Error trying to delete book.");
		}
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * 
	 * @param books
	 * @return
	 */
	private Map<String, Object> getMap(List<Book> books) {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", books.size());
		modelMap.put("data", books);
		modelMap.put("success", true);
		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView in case of exception
	 * 
	 * @param msg
	 *            message
	 * @return
	 */
	private Map<String, Object> getModelMapError(String msg) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return modelMap;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
}