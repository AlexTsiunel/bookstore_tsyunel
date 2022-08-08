package com.company.console;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.company.DataSource;
import com.company.dao.BookDao;
import com.company.dao.BookDaoImpl;
import com.company.dao.entity.Book;

public class BookConsole {
	private final String REGEX_VALID_COMMAND = "(isbn)|(author)|(count)|(update)|(add)|(exit)|(all)|(get[\\s][1-9][\\d]*)|(delete[\\s][1-9][\\d]*)";
	private DataSource dataSource;

	public BookConsole(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void executeCommand(String command, Scanner scanner) {
		BookDaoImpl bookDao = new BookDaoImpl(dataSource);
		String[] method = command.split(" ");
		String methodName = method[0];
		Integer methodArgument = null;
		if (method.length > 1) {
			methodArgument = Integer.parseInt(method[1]);
		}
		switch (methodName) {
		case "all": {
			bookDao.getAll().forEach(System.out::println);
			break;
		}
		case "get": {
			System.out.println(bookDao.getById(methodArgument));
			break;
		}
		case "delete": {
			System.out.println(bookDao.delete(methodArgument));
			break;
		}
		case "add": {
			create(scanner, bookDao);
			break;
		}
		case "update": {
			update(scanner, bookDao);
			break;
		}
		case "count": {
			System.out.printf("The repository contains %d books.\n", bookDao.getNumberOfBooks());
			break;
		}
		case "author": {
			System.out.print("Please enter the author of book: ");
			String author = scanner.nextLine();
			List<Book> books = bookDao.getBooksByAuthor(author);
			if (books.size() == 0) {
				System.out.printf("'%s' author's books are not in the repository.\n", author);
				break;
			}
			for (Book book : books) {
				System.out.println(book.toString());
			}
			break;
		}
		case "isbn": {
			System.out.print("Please enter the isbn of book: ");
			String isbn = scanner.nextLine();
			Book book = bookDao.getBookByIsbn(isbn);
			if (book == null) {
				System.out.printf("Book with isbn '%s' are not in the repository.\n", isbn);
				break;
			}
			System.out.println(book.toString());
			break;
		}
		case "exit": {
			System.out.println("Application completed");
			System.exit(0);
			break;
		}
		}
	}

	public String getValidCommand(Scanner scanner) {
		String value = null;
		if (scanner != null) {
			do {
				System.out.print("Please enter the valid command: ");
				value = scanner.nextLine();
			} while (!isValidCommand(value));
		}
		return value;
	}

	private boolean isValidCommand(String command) {
		return command.matches(REGEX_VALID_COMMAND);
	}

	private void create(Scanner scanner, BookDao bookDao) {
		Book book = new Book();

		System.out.print("Please enter a book title: ");
		String titel = scanner.nextLine();
		book.setTitle(titel);

		System.out.print("Please enter a book author: ");
		String author = scanner.nextLine();
		book.setAuthor(author);

		System.out.print("Please enter a book isbn: ");
		String isbn = scanner.nextLine();
		book.setIsbn(isbn);

		System.out.print("Please enter a number of pages in book: ");
		// isValidValue();!!!!
		Integer pages = scanner.nextInt();
		book.setPages(pages);

		System.out.print("Please enter a price of the book: ");
		// isValidValue();!!!!
		BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
		book.setPrice(price);

		bookDao.create(book);
	}

	private void update(Scanner scanner, BookDao bookDao) {
		System.out.print("Please enter a book id for update: ");
		Long id = scanner.nextLong();
		scanner.next();
		Book bookUpdate = bookDao.getById(id);
		if (bookUpdate == null) {
			System.out.printf("Book with id %d does not exist in the database!!!", id);
			return;
		}

		System.out.print("Update the title of the book? Press key 'y' to update or another key to not update: ");
		if (isUpdate(scanner)) {
			System.out.print("Please enter a book title: ");
			String titel = scanner.nextLine();
			bookUpdate.setTitle(titel);
		}

		System.out.print("Update the author of the book? Press key 'y' to update or another key to not update: ");
		if (isUpdate(scanner)) {
			System.out.print("Please enter a book author: ");
			String author = scanner.nextLine();
			bookUpdate.setAuthor(author);
		}

		System.out.print("Update the book isbn? Press key 'y' to update or another key to not update: ");
		if (isUpdate(scanner)) {
			System.out.print("Please enter a book isbn: ");
			String isbn = scanner.nextLine();
			bookUpdate.setIsbn(isbn);
		}

		System.out.print("Update a number of pages in book? Press key 'y' to update or another key to not update:  ");
		if (isUpdate(scanner)) {
			// isValidValue();!!!!
			Integer pages = scanner.nextInt();
			bookUpdate.setPages(pages);
		}

		System.out.print("Update a price of the book? Press key 'y' to update or another key to not update:  ");
		if (isUpdate(scanner)) {
			System.out.print("Please enter a price of the book: ");
			// isValidValue();!!!!
			BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
			bookUpdate.setPrice(price);
		}

		bookDao.update(bookUpdate);
	}

	private boolean isUpdate(Scanner scanner) {
		String command = scanner.nextLine();
		boolean result = command.matches("Y|y");
		return result;
	}
}
