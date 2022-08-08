package com.company;

import java.util.Scanner;

import com.company.console.BookConsole;

public class App {

	public static void main(String[] args) {
//		try (DataSource dataSource = new DataSource()) {
//			BookDaoImpl bookDao = new BookDaoImpl(dataSource);
//
//			bookDao.getAll().forEach(System.out::println);
//			System.out.println("------------------------");
//			System.out.println(bookDao.getNumberOfBooks());
//			System.out.println("------------------------");
//			bookDao.getBooksByAuthor("Taylor Jenkins Reid").forEach(System.out::println);
//			System.out.println("------------------------");
//			System.out.println(bookDao.getBookByIsbn("9781538732182"));
//
//		} catch (Exception e) {
//			e.getStackTrace();
//		}

		try (DataSource dataSource = new DataSource()) {
			Scanner scanner = new Scanner(System.in);
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
			while (true) {
				BookConsole bookConsole = new BookConsole(dataSource);
				String command = bookConsole.getValidCommand(scanner);
				System.out.println("Command execution result:");
				bookConsole.executeCommand(command, scanner);
			}
		}
	}
}
