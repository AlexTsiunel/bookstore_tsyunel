package com.company.app.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.app.service.BookService;
import com.company.app.service.dto.BookDto;
import com.company.server.HttpRequest;
import com.company.server.HttpResponse;
import com.company.server.Servlet;

public class BookSimpleController implements Servlet {
    private final BookService bookService;
    private static Logger logger = LogManager.getLogger(BookSimpleController.class);

    public BookSimpleController(BookService bookService) {
        super();
        this.bookService = bookService;
    }

    @Override
    public void processRequest(HttpRequest httpRequest, HttpResponse httpResponse) {
        switch (httpRequest.getURL()) {
        case "books": {
            List<BookDto> books = bookService.getAll();
            StringBuilder response = new StringBuilder("""
                    <html>
                        <head>
                            <title>Books</title>
                        </head>
                        <body>
                            <table>
                                <th>Id</th><th>Title</th>
                    """);
            for (BookDto book : books) {
                response.append("<tr><td>")//
                        .append(book.getId())//
                        .append("</td><td>")//
                        .append(book.getTitle())//
                        .append("</td></tr>");
            }
            response.append("</table></body></html>");
            httpResponse.setBody(response.toString());
            logger.info("Response generated for URL: books");
            break;
        }

        case "book": {
            Map<String, String> parameters = httpRequest.getParameters();
            if (parameters.containsKey("id")) {
                BookDto book = bookService.getById(Integer.parseInt(parameters.get("id")));
                if (book == null) {
                    StringBuilder response = new StringBuilder("""
                            <html>
                                <head>
                                    <title>Book</title>
                                </head>
                                <body>
                                Book not found!!!
                                </body>
                             </html>
                            """);
                    httpResponse.setBody(response.toString());
                    logger.info("Response generated for URL: book");
                    break;
                }
                StringBuilder response = new StringBuilder("""
                        <html>
                            <head>
                                <title>Book</title>
                            </head>
                            <body>
                                <table>
                                    <th>Id</th><th>Title</th>
                        """);
                response.append("<tr><td>")//
                        .append(book.getId())//
                        .append("</td><td>")//
                        .append(book.getTitle())//
                        .append("</td></tr>");
                response.append("</table></body></html>");
                httpResponse.setBody(response.toString());
                logger.info("Response generated for URL: book");
            }
            break;
        }
        }
    }
}
