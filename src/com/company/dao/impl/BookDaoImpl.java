package com.company.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.BookDao;
import com.company.dao.connection.DataSource;
import com.company.dao.entity.Book;

public class BookDaoImpl implements BookDao {
	private static final String SELECT_ALL = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b;";
	private static final String SELECT_BY_ID = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b WHERE b.book_id = ?;";
	private static final String INSERT = "INSERT INTO books (title, author, isbn, pages, price) VALUES (?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE books SET title = ?, author = ? , isbn = ?, pages = ? , price = ? WHERE book_id = ?;";
	private static final String DELETE = "DELETE FROM books b WHERE b.book_id = ?;";
	private static final String SELECT_BY_ISBN = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b WHERE b.isbn = ?;";
	private static final String SELECT_BY_AUTHOR = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b WHERE b.author = ?;";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM books b";

	private final DataSource dataSource;

	public BookDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Book getById(long id) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return getBook(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getAll() {
		try {
			List<Book> list = new ArrayList<>();
			Statement statement = dataSource.getConnection().createStatement();
			ResultSet result = statement.executeQuery(SELECT_ALL);
			while (result.next()) {
				list.add(getBook(result));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book create(Book book) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setString(3, book.getIsbn());
			statement.setInt(4, book.getPages());
			statement.setBigDecimal(5, book.getPrice());
			statement.executeUpdate();

			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next()) {
				Long id = keys.getLong(1);
				return getById(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book update(Book book) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(UPDATE);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setString(3, book.getIsbn());
			statement.setInt(4, book.getPages());
			statement.setBigDecimal(5, book.getPrice());
			statement.setLong(6, book.getId());

			statement.executeUpdate();

			return getById(book.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE);
			statement.setLong(1, id);
			int rowsDeleted = statement.executeUpdate();
			return rowsDeleted == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Book getBookByIsbn(String isbn) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ISBN);
			statement.setString(1, isbn);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return getBook(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Book> getBooksByAuthor(String author) {
		List<Book> booksList = new ArrayList<>();
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_AUTHOR);
			statement.setString(1, author);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				booksList.add(getBook(result));
			}
			return booksList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int getNumberOfBooks() {
		int numberOfBooks = 0;
		try {
			Statement statement = dataSource.getConnection().createStatement();
			ResultSet result = statement.executeQuery(SELECT_COUNT);
			if (result.next()) {
				return result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberOfBooks;
	}

	private Book getBook(ResultSet result) throws SQLException {
		Book book = new Book();
		book.setId(result.getLong(1));
		book.setTitle(result.getString(2));
		book.setAuthor(result.getString(3));
		book.setIsbn(result.getString(4));
		book.setPages(result.getInt(5));
		book.setPrice(result.getBigDecimal(6));
		return book;
	}
}
