package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.entity.BookEntity;

public class BookDaoImpl implements BookDao {
	private static final String SELECT_ALL = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b;";
	private static final String SELECT_BY_ID = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b WHERE b.book_id = ?;";
	private static final String INSERT = "INSERT INTO books (title, author, isbn, pages, price) VALUES (?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE books SET title = ?, author = ? , isbn = ?, pages = ? , price = ? WHERE book_id = ?;";
	private static final String DELETE = "DELETE FROM books b WHERE b.book_id = ?;";

	private final Connection connection;

	public BookDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public BookEntity getById(long id) {
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return getBookEntityFromDB(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookEntity> getAll() {
		try {
			List<BookEntity> list = new ArrayList<>();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(SELECT_ALL);
			while (result.next()) {
				list.add(getBookEntityFromDB(result));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BookEntity create(BookEntity book) {
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
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
	public BookEntity update(BookEntity book) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
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
			PreparedStatement statement = connection.prepareStatement(DELETE);
			statement.setLong(1, id);
			int rowsDeleted = statement.executeUpdate();
			return rowsDeleted == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private BookEntity getBookEntityFromDB(ResultSet result) throws SQLException {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(result.getLong(1));
		bookEntity.setTitle(result.getString(2));
		bookEntity.setAuthor(result.getString(3));
		bookEntity.setIsbn(result.getString(4));
		bookEntity.setPages(result.getInt(5));
		bookEntity.setPrice(result.getBigDecimal(6));
		return bookEntity;
	}
}
