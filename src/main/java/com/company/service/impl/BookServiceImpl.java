package main.java.com.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import main.java.com.company.dao.BookDao;
import main.java.com.company.dao.entity.Book;
import main.java.com.company.service.BookService;
import main.java.com.company.service.dto.BookDto;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public BookDto getById(long id) {
        Book book = bookDao.getById(id);
        return toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll().stream().map(this::toDto).toList();
    }

    @Override
    public BookDto create(BookDto dto) {
        Book existing = bookDao.getBookByIsbn(dto.getIsbn());
        if (existing != null) {
            throw new RuntimeException();
        }
        return toDto(bookDao.create(toEntity(dto)));
    }

    @Override
    public BookDto update(BookDto dto) {
        Book existing = bookDao.getBookByIsbn(dto.getIsbn());
        if (existing != null && existing.getId() != dto.getId()) {
            throw new RuntimeException();
        }
        return toDto(bookDao.update(toEntity(dto)));
    }

    @Override
    public boolean delete(long id) {
        return bookDao.delete(id);
    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
        return toDto(bookDao.getBookByIsbn(isbn));
    }

    @Override
    public List<BookDto> getBooksByAuthor(String author) {
        List<BookDto> dtoList = new ArrayList<>();
        List<Book> entityList = bookDao.getBooksByAuthor(author);
        for (Book entity : entityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    @Override
    public int getNumberOfBooks() {
        return bookDao.getNumberOfBooks();
    }

    private BookDto toDto(Book entity) {
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
