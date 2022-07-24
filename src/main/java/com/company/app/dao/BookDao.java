package com.company.app.dao;

import java.util.List;

import com.company.app.dao.entity.Book;



public interface BookDao {

	Book getById(long id);

	List<Book> getAll();

	Book create(Book book);

	Book update(Book book);

	boolean delete(long id);

	Book getBookByIsbn(String isbn);

	List<Book> getBooksByAuthor(String author);

	public int getNumberOfBooks();

}