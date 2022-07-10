package com.company.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.UserDao;
import com.company.dao.connection.DataSource;
import com.company.dao.entity.User;

public class UserDaoImpl implements UserDao {
	private static final String SELECT_ALL = "SELECT u.first_name , u.last_name , u.email , u.password FROM users u;";
	private static final String SELECT_BY_ID = "SELECT u.first_name , u.last_name , u.email , u.password FROM users u WHERE  u.user_id = ?;";
	private static final String INSERT = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE users SET first_name = ?, last_name = ? , email = ?, password = ?  WHERE  user_id = ?";
	private static final String DELETE = "DELETE FROM users u WHERE u.user_id = ?;";
	private static final String SELECT_BY_EMAIL = "SELECT u.first_name , u.last_name , u.email , u.password FROM users u WHERE  u.user_email = ?;";
//	private static final String SELECT_BY_ISBN = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b WHERE b.isbn = ?;";
//	private static final String SELECT_BY_AUTHOR = "SELECT b.book_id , b.title , b.author , b.isbn, b.pages , b.price FROM books b WHERE b.author = ?;";
//	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM books b";

	private final DataSource dataSource;

	public UserDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public User getById(long id) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return getUser(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		try {
			List<User> list = new ArrayList<>();
			Statement statement = dataSource.getConnection().createStatement();
			ResultSet result = statement.executeQuery(SELECT_ALL);
			while (result.next()) {
				list.add(getUser(result));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User create(User user) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(3, user.getPassword());

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
	public User update(User user) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(UPDATE);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());

			statement.executeUpdate();

			return getById(user.getId());

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
	public User getUserByEmail(String email) {
		try {
			PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return getUser(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private User getUser(ResultSet result) throws SQLException {
		User user = new User();
		user.setId(result.getLong(1));
		user.setFirstName(result.getString(2));
		user.setLastName(result.getString(3));
		user.setEmail(result.getString(4));
		user.setPassword(result.getString(5));
		return user;
	}
}
