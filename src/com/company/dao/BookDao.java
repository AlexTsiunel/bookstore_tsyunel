package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.Properties;
import com.company.entity.BookEntity;

public class BookDao {
	private static Connection connection;

	static {
		try {
			connection = DriverManager.getConnection(Properties.getUrl(), Properties.getUser(),
					Properties.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<BookEntity> getFilteredBookList(final String SQL) throws SQLException {

		List<BookEntity> list = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(SQL);
		while (result.next()) {
			list.add(getBookEntityFromDB(result));
		}
		return list;
	}

	private BookEntity getBookEntityFromDB(final ResultSet result) throws SQLException {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(result.getLong(1));
		bookEntity.setTitle(result.getString(2));
		bookEntity.setAuthor(result.getString(3));
		bookEntity.setIsbn(result.getString(4));
		bookEntity.setPages(result.getInt(5));
		bookEntity.setPrice(result.getBigDecimal(6));
		return bookEntity;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
