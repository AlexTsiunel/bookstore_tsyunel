package com.company;

import java.sql.SQLException;
import java.util.List;

import com.company.dao.BookDao;
import com.company.entity.BookEntity;

public class App {
	public static void main(final String[] args) throws SQLException {
		BookDao bookDao = new BookDao();
		List<BookEntity> list = bookDao.getFilteredBookList("SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b;");
		list.forEach(System.out::println);
	}
}
