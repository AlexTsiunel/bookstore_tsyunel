package main.java.com.company.service;

import java.util.List;

import main.java.com.company.service.dto.BookDto;

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
