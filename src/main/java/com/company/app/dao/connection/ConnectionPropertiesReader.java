package com.company.app.dao.connection;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPropertiesReader {

    private final String url;
    private final String password;
    private final String user;
//    private static final String propertiesFile = "C:/Users/user/git/bookstore_tsyunel/src/main/resources/aplication.properties";
    
    private static Logger logger = LogManager.getLogger(ConnectionPropertiesReader.class);

    public ConnectionPropertiesReader() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("Loading error DB driver in JVM memory", ex);
        }
        Properties properties = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/aplication.properties")) {
            properties.load(in);
            url = properties.getProperty("db.url");
            password = properties.getProperty("db.password");
            user = properties.getProperty("db.user");
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
