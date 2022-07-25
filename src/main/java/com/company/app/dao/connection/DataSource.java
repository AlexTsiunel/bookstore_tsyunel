package com.company.app.dao.connection;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSource implements Closeable {
    private Connection connection;
    private static Logger logger = LogManager.getLogger(DataSource.class);

    public Connection getConnection() {
        if (connection == null) {
            init();
        }
        return connection;
    }

    private void init() {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                logger.error("Loading error DB driver in JVM memory", ex);
            }
            ConnectionPropertiesReader connectionPropertiesReader = new ConnectionPropertiesReader();
            connection = DriverManager.getConnection(connectionPropertiesReader.getUrl(),
                    connectionPropertiesReader.getUser(), connectionPropertiesReader.getPassword());

            logger.info("Datadase connection.");
        } catch (SQLException e) {
            logger.error("Failed to connect to database.", e);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Failed to closed connect to database.", e);
            }
        }
    }
}
