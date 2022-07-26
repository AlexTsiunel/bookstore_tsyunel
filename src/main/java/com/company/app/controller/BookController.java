package com.company.app.controller;

import java.io.IOException;

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

@WebServlet("/book")
public class BookController extends HttpServlet {
    private final BookService bookService = new BookServiceImpl(new BookDaoImpl(new DataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        BookDto book = bookService.getById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("jsp/book.jsp").forward(req, resp);
    }

    private String renderHtml(BookDto book) {
        if (book == null) {

            return """
                        <html>
                        <head>
                            <title>Book</title>
                        </head>
                        <body>
                        Book not found!!!
                        </body>
                     </html>
                    """;
        }

        return String.format("""
                  <html>
                  <head>
                    <title>Book</title>
                  </head>
                  <body>
                    <table>
                      <th>Id</th>
                      <th>Title</th>
                      <tr>
                        <td>%s</td>
                        <td>%s</td>
                      </tr>
                    </table>
                  </body>
                </html>
                                """, //
                book.getId(), book.getTitle());
    }
}