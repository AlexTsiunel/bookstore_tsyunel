package com.company.app.controller;


import com.company.app.service.BookService;
import com.company.app.service.dto.BookDto;

import jakarta.servlet.http.HttpServletRequest;


public class BookCommand implements Command {
    private final BookService bookService;
    
    

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }



    @Override
    public String execute(HttpServletRequest req){
      Long id = Long.parseLong(req.getParameter("id"));
      BookDto book = bookService.getById(id);
      req.setAttribute("book", book);
      return "jsp/book.jsp";
    }
}