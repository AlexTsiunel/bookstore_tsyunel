package com.company.dao.connection;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSource implements Closeable {
	private Connection connection;
	private static Logger logger = LogManager.getLogger();

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
			logger.log(Level.INFO, "Datadase connection.");
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Failed to connect to database.");
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "Failed to closed connect to database.");
			}
		}
	}
}
