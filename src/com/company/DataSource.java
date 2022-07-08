package com.company;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource implements Closeable {
	private Connection connection;

	public Connection getConnection() {
		if (connection == null) {
			init();
		}
		return connection;
	}

	private void init() {
		try {
			connection = DriverManager.getConnection(Properties.getUrl(), Properties.getUser(),
					Properties.getPassword());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}
}
