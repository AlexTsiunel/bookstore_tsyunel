package com.company.app.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.app.dao.UserDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.dao.entity.User;



public class UserDaoImpl implements UserDao {
    private static final String SELECT_ALL = "SELECT u.id , u.first_name , u.last_name , u.email , u.password, r.name AS role, u.deleted FROM users u JOIN roles r ON u.role_id = r.id WHERE u.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT u.id , u.first_name , u.last_name , u.email , u.password, r.name AS role, u.deleted FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ? AND u.deleted = FALSE";
    private static final String INSERT = "INSERT INTO users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, (SELECT id FROM roles WHERE name = ?))";
    private static final String UPDATE = "UPDATE users SET first_name = ?, last_name = ? , email = ?, password = ?, role = ? WHERE  user_id = ? AND u.deleted = FALSE";
    private static final String DELETE = "UPDATE users SET u.deleted = TRUE WHERE  user_id = ? AND u.deleted = FALSE";
    private static final String SELECT_BY_EMAIL = "SELECT u.id , u.first_name , u.last_name , u.email , u.password, r.name AS role, u.deleted FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ? AND u.deleted = FALSE";

    private final DataSource dataSource;
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getById(long id) {
    	logger.debug("Datadase query. Table users.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getUser(result);
            }
        } catch (SQLException e) {
        	logger.error("Failed to query the database. Table users.", e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
    	logger.debug("Datadase query. Table users.");
        try {
            List<User> list = new ArrayList<>();
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);
            while (result.next()) {
                list.add(getUser(result));
            }
            return list;
        } catch (SQLException e) {
        	logger.error("Failed to query the database. Table users.");
        }
        return null;
    }

    @Override
    public User create(User user) {
    	logger.debug("Datadase query. Table users.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().toString());

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong(1);
                return getById(id);
            }

        } catch (SQLException e) {
        	logger.error("Failed to query the database. Table users.", e);
        }
        return null;
    }

    @Override
    public User update(User user) {
    	logger.debug("Datadase query. Table users.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(UPDATE);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().toString());

            statement.executeUpdate();

            return getById(user.getId());

        } catch (SQLException e) {
        	logger.error("Failed to query the database. Table users.", e);
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
    	logger.debug("Datadase query. Table users.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted == 1;

        } catch (SQLException e) {
        	logger.error("Failed to query the database. Table users.",e);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
    	logger.debug("Datadase query. Table users.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_EMAIL);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getUser(result);
            }
        } catch (SQLException e) {
        	logger.error("Failed to query the database. Table users.", e);
        }
        return null;
    }

    private User getUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getLong("id"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
        user.setRole(User.Role.valueOf(result.getString("role")));
        user.setDeleted(result.getBoolean("deleted"));

        return user;
    }
}
