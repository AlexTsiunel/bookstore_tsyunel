package main.java.com.company.dao.connection;

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
			ConnectionPropertiesReader connectionPropertiesReader = new ConnectionPropertiesReader();
			connection = DriverManager.getConnection(connectionPropertiesReader.getUrl(),
					connectionPropertiesReader.getUser(), connectionPropertiesReader.getPassword());
		} catch (SQLException e) {
			e.getStackTrace();
//			throw new RuntimeException();
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
