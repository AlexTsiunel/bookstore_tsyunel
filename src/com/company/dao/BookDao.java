package com.company.dao;

import java.util.List;

import com.company.entity.BookEntity;

public interface BookDao {

	BookEntity getById(long id);

	List<BookEntity> getAll();

	BookEntity create(BookEntity book);

	BookEntity update(BookEntity book);

	boolean delete(long id);

}