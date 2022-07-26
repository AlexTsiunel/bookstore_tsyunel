package com.company.app.service;

import java.util.List;

import com.company.app.service.dto.BookDto;



public interface BookService {
	BookDto getById(long id);

	List<BookDto> getAll();

	BookDto create(BookDto book);

	BookDto update(BookDto book);

	boolean delete(long id);

	BookDto getBookByIsbn(String isbn);

	List<BookDto> getBooksByAuthor(String author);

	public int getNumberOfBooks();
}
