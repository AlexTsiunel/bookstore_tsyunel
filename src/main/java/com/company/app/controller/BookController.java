package com.company.app.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.company.app.service.BookService;
import com.company.app.service.dto.BookDto;



public class BookController {
	private final String REGEX_VALID_COMMAND = "(isbn)|(author)|(count)|(update)|(add)|(exit)|(all)|(get[\\s][1-9][\\d]*)|(delete[\\s][1-9][\\d]*)";
	private Scanner scanner;
	private BookService bookService;
	{
		System.out.println("Please use commands:\n" //
				+ "  'all' - to get a list of all books in the repository;\n"//
				+ "  'get {id}' - to get book from repository by id;\n"//
				+ "  'delete {id}' - to remove book from repository by id;\n"//
				+ "  'add' - to create a book in the repository;\n"//
				+ "  'update' - to update a book in the repository;\n"//
				+ "  'count' - get the number of books in the repository;\n"//
				+ "  'author' - to get book from repository by author;\n"//
				+ "  'isbn' - to get book from repository by isbn;\n"//
				+ "  'exit' - to exit from application; ");
	}

	public BookController(Scanner scanner, BookService bookService) {
		this.scanner = scanner;
		this.bookService = bookService;
	}

	public void executeCommand(String command) {
		String[] method = command.split(" ");
		String methodName = method[0];
		Integer methodArgument = null;
		if (method.length > 1) {
			methodArgument = Integer.parseInt(method[1]);
		}
		switch (methodName) {
		case "all": {
			bookService.getAll().forEach(System.out::println);
			break;
		}
		case "get": {
			System.out.println(bookService.getById(methodArgument));
			break;
		}
		case "delete": {
			System.out.println(bookService.delete(methodArgument));
			break;
		}
		case "add": {
			create(scanner, bookService);
			break;
		}
		case "update": {
			update(scanner, bookService);
			break;
		}
		case "count": {
			System.out.printf("The repository contains %d books.\n", bookService.getNumberOfBooks());
			break;
		}
		case "author": {
			System.out.print("Please enter the author of book: ");
			String author = scanner.nextLine();
			List<BookDto> books = bookService.getBooksByAuthor(author);
			if (books.size() == 0) {
				System.out.printf("'%s' author's books are not in the repository.\n", author);
				break;
			}
			for (BookDto book : books) {
				System.out.println(book.toString());
			}
			break;
		}
		case "isbn": {
			System.out.print("Please enter the isbn of book: ");
			String isbn = scanner.nextLine();
			BookDto book = bookService.getBookByIsbn(isbn);
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

	private void create(Scanner scanner, BookService bookService) {
		BookDto book = new BookDto();

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

		System.out.print("Please enter a cover of the book /n (0 - 'Soft cover', 1 - 'Hard cover', 2 - 'Cpetial cover' : ");
		// isValidValue();!!!!
		Integer coverInt = scanner.nextInt();
		BookDto.Cover[] covers = BookDto.Cover.values();
		BookDto.Cover cover = covers[coverInt];
		book.setCover(cover);

		bookService.create(book);
	}

	private void update(Scanner scanner, BookService bookService) {
		System.out.print("Please enter a book id for update: ");
		Long id = scanner.nextLong();
		BookDto bookUpdate = bookService.getById(id);
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

		System.out.print("Update a caver of the book? Press key 'y' to update or another key to not update:  ");
		if (isUpdate(scanner)) {
			System.out.print("Please enter a caver of the book /n (0 - 'Soft cover', 1 - 'Hard cover', 2 - 'Cpetial cover' : ");
			// isValidValue();!!!!
			Integer coverInt = scanner.nextInt();
			BookDto.Cover[] covers = BookDto.Cover.values();
			BookDto.Cover cover = covers[coverInt];
			bookUpdate.setCover(cover);
		}

		bookService.update(bookUpdate);
	}

	private boolean isUpdate(Scanner scanner) {
		String command = scanner.nextLine();
		boolean result = command.matches("Y|y");
		return result;
	}
}
