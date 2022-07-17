package com.company.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.dao.BookDao;
import com.company.dao.connection.DataSource;
import com.company.dao.entity.Book;
import com.company.dao.entity.Book.Cover;

public class BookDaoImpl implements BookDao {
    private static final String SELECT_ALL = "SELECT b.id , b.title , b.author , b.isbn, b.pages , b.price, c.name AS cover, b.deleted FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT b.id , b.title , b.author , b.isbn, b.pages , b.price, c.name AS cover, b.deleted FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.id = ? AND b.deleted = FALSE";
    private static final String INSERT = "INSERT INTO books (title, author, isbn, pages, price, cover_id, deleted) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE books SET title = ?, author = ? , isbn = ?, pages = ? , price = ?, cover_id = ?, deleted = ? WHERE id = ? AND b.deleted = FALSE";
    private static final String DELETE = "UPDATE books SET deleted = TRUE WHERE id = ? AND b.deleted = FALSE";
    private static final String SELECT_BY_ISBN = "SELECT b.id , b.title , b.author , b.isbn, b.pages , b.price, c.name AS cover, b.deleted FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.isbn = ? AND b.deleted = FALSE";
    private static final String SELECT_BY_AUTHOR = "SELECT b.id , b.title , b.author , b.isbn, b.pages , b.price, b.cover_id, b.deleted FROM books b WHERE b.author = ? AND b.deleted = FALSE";
    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM books b WHERE b.deleted = FALSE";

    private final DataSource dataSource;
    private static Logger logger = LogManager.getLogger();

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Book getById(long id) {
    	logger.log(Level.DEBUG, "Datadase query. Table books.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getBook(result);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, "Failed to query the database. Table books.");
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
    	logger.log(Level.DEBUG, "Datadase query. Table books.");
        try {
            List<Book> list = new ArrayList<>();
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);
            while (result.next()) {
                list.add(getBook(result));
            }
            return list;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, "Failed to query the database. Table books.");
        }
        return null;
    }

    @Override
    public Book create(Book book) {
    	logger.log(Level.DEBUG, "Datadase query. Table books.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getPages());
            statement.setBigDecimal(5, book.getPrice());
            statement.setString(6, book.getCover().toString());
            statement.setBoolean(7, false);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong(1);
                return getById(id);
            }

        } catch (SQLException e) {
        	logger.log(Level.ERROR, "Failed to query the database. Table books.");
        }
        return null;
    }

    @Override
    public Book update(Book book) {
    	logger.log(Level.DEBUG, "Datadase query. Table books.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(UPDATE);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getPages());
            statement.setBigDecimal(5, book.getPrice());
            statement.setString(6, book.getCover().toString());
            statement.setBoolean(7, false);
            statement.setLong(8, book.getId());

            statement.executeUpdate();

            return getById(book.getId());

        } catch (SQLException e) {
        	logger.log(Level.ERROR, "Failed to query the database. Table books.");
        }
        return null;
    }

  @Override
  public boolean delete(long id) {
	  logger.log(Level.DEBUG, "Datadase query. Table books.");
      try {
          PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE);

          statement.setLong(2, id);


          int rowsDeleted = statement.executeUpdate();
          return rowsDeleted == 1;

      } catch (SQLException e) {
    	  logger.log(Level.ERROR, "Failed to query the database. Table books.");
      }
      return false;
  }

    @Override
    public Book getBookByIsbn(String isbn) {
    	logger.log(Level.DEBUG, "Datadase query. Table books.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ISBN);
            statement.setString(1, isbn);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getBook(result);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, "Failed to query the database. Table books.");
        }
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
    	logger.log(Level.DEBUG, "Datadase query. Table books.");
        List<Book> booksList = new ArrayList<>();
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_AUTHOR);
            statement.setString(1, author);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                booksList.add(getBook(result));
            }
            return booksList;
        } catch (SQLException e) {
        	logger.log(Level.ERROR, "Failed to query the database. Table books.");
        }
        return null;
    }

    @Override
    public int getNumberOfBooks() {
    	logger.log(Level.DEBUG, "Datadase query. Table books.");
        int numberOfBooks = 0;
        try {
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet result = statement.executeQuery(SELECT_COUNT);
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
        	logger.log(Level.ERROR, "Failed to query the database. Table books.");
        }
        return numberOfBooks;
    }

    private Book getBook(ResultSet result) throws SQLException {
        Book book = new Book();
        book.setId(result.getLong("id"));
        book.setTitle(result.getString("title"));
        book.setAuthor(result.getString("author"));
        book.setIsbn(result.getString("isbn"));
        book.setPages(result.getInt("pages"));
        book.setPrice(result.getBigDecimal("price"));
        book.setCover(Cover.valueOf(result.getString("cover")));
        book.setDeleted(result.getBoolean("deleted"));
        return book;
    }
}
