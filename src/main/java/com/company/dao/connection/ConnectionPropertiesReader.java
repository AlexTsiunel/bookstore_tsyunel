package main.java.com.company.dao.connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPropertiesReader {
	private final String url;
	private final String password;
	private final String user;
	private static final String propertiesFile = "resources/aplication.properties";

	public ConnectionPropertiesReader() {
		Properties properties = new Properties();
		try (InputStream in = new FileInputStream(propertiesFile)) {
			properties.load(in);
			url = properties.getProperty("url");
			password = properties.getProperty("password");
			user = properties.getProperty("user");
		} catch (Exception e) {
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
