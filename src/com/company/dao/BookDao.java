package com.company.dao;

import java.util.List;

import com.company.dao.entity.Book;

public interface BookDao {

	Book getById(long id);

	List<Book> getAll();

	Book create(Book book);

	Book update(Book book);

	boolean delete(long id);

}