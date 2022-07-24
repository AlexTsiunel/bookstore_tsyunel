package com.company.app.dao.connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPropertiesReader {
	private final String url;
	private final String password;
	private final String user;
	private static final String propertiesFile = "resources/aplication.properties";
	private static Logger logger = LogManager.getLogger(ConnectionPropertiesReader.class);

	public ConnectionPropertiesReader() {
		Properties properties = new Properties();
		try (InputStream in = new FileInputStream(propertiesFile)) {
			properties.load(in);
			url = properties.getProperty("url");
			password = properties.getProperty("password");
			user = properties.getProperty("user");
		} catch (Exception e) {
			logger.error("Failed to reade the properties file.", e);
			throw new RuntimeException();
		}
	}

	public String getUrl() {
		return url;
	}

	public String getPassword() {
		return password;
	}

	public String getUser() {
		return user;
	}
}
