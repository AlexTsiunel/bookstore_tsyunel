package com.company.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.app.dao.BookDao;
import com.company.app.dao.entity.Book;
import com.company.app.service.BookService;
import com.company.app.service.dto.BookDto;




public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private static Logger logger = LogManager.getLogger();

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public BookDto getById(long id) {
    	logger.log(Level.DEBUG, "Method call: BookService.getById.");
        Book book = bookDao.getById(id);
        return toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
    	logger.log(Level.DEBUG, "Method call: BookService.getAll.");
        return bookDao.getAll().stream().map(this::toDto).toList();
    }

    @Override
    public BookDto create(BookDto dto) {
    	logger.log(Level.DEBUG, "Method call: BookService.create.");
        Book existing = bookDao.getBookByIsbn(dto.getIsbn());
        if (existing != null) {
        	logger.log(Level.ERROR, "Failed to get book by isbn.");
            throw new RuntimeException();
        }
        return toDto(bookDao.create(toEntity(dto)));
    }

    @Override
    public BookDto update(BookDto dto) {
    	logger.log(Level.DEBUG, "Method call: BookService.update.");
        Book existing = bookDao.getBookByIsbn(dto.getIsbn());
        if (existing != null && existing.getId() != dto.getId()) {
        	logger.log(Level.ERROR, "Failed to update book.");
            throw new RuntimeException();
        }
        return toDto(bookDao.update(toEntity(dto)));
    }

    @Override
    public boolean delete(long id) {
    	logger.log(Level.DEBUG, "Method call: BookService.delete.");
        return bookDao.delete(id);
    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
    	logger.log(Level.DEBUG, "Method call: BookService.getBookByIsbn.");
        return toDto(bookDao.getBookByIsbn(isbn));
    }

    @Override
    public List<BookDto> getBooksByAuthor(String author) {
    	logger.log(Level.DEBUG, "Method call: BookService.getBooksByAuthor.");
        List<BookDto> dtoList = new ArrayList<>();
        List<Book> entityList = bookDao.getBooksByAuthor(author);
        for (Book entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    @Override
    public int getNumberOfBooks() {
    	logger.log(Level.DEBUG, "Method call: BookService.getNumberOfBooks.");
        return bookDao.getNumberOfBooks();
    }

    private BookDto toDto(Book entity) {
        if(entity == null) {
            return null;
        }
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setPages(entity.getPages());
        dto.setPrice(entity.getPrice());
        dto.setCover(toCoverDto(entity.getCover()));
        return dto;
    }

    private Book toEntity(BookDto dto) {
        if(dto == null) {
            return null;
        }
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setPages(dto.getPages());
        entity.setPrice(dto.getPrice());
        entity.setCover(toCoverEntity(dto.getCover()));
        return entity;
    }

    private Book.Cover toCoverEntity(BookDto.Cover dtoCover) {
        int ordinal = dtoCover.ordinal();
        Book.Cover[] entityCovers = Book.Cover.values();
        return entityCovers[ordinal];
    }

    private BookDto.Cover toCoverDto(Book.Cover entityCover) {
        int ordinal = entityCover.ordinal();
        BookDto.Cover[] dtoCovers = BookDto.Cover.values();
        return dtoCovers[ordinal];
    }
}
