package com.company.app.controller;

import java.util.List;

import com.company.app.service.BookService;
import com.company.app.service.dto.BookDto;

import jakarta.servlet.http.HttpServletRequest;

public class BooksCommand implements Command {
    private final  BookService bookService;
    
    public BooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<BookDto> books = bookService.getAll();
        req.setAttribute("books", books);
        return "jsp/books.jsp";
    }
}
