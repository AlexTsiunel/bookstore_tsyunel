package com.company.app.controller;

import java.io.IOException;
import java.util.List;

import com.company.app.dao.connection.DataSource;
import com.company.app.dao.impl.BookDaoImpl;
import com.company.app.service.BookService;
import com.company.app.service.dto.BookDto;
import com.company.app.service.impl.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class BooksController extends HttpServlet {
    private final BookService bookService = new BookServiceImpl(new BookDaoImpl(new DataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<BookDto> books = bookService.getAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("jsp/books.jsp").forward(req, resp);
    }
}
