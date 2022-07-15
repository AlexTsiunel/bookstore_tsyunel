package main.java.com.company;

import java.util.Scanner;

import main.java.com.company.controller.BookController;
import main.java.com.company.dao.connection.DataSource;
import main.java.com.company.dao.impl.BookDaoImpl;
import main.java.com.company.service.BookService;
import main.java.com.company.service.impl.BookServiceImpl;

public class App {
	public static void main(String[] args) {
		try (DataSource dataSource = new DataSource()) {
			Scanner scanner = new Scanner(System.in);
			BookService bookService = new BookServiceImpl(new BookDaoImpl(dataSource));
			while (true) {
				BookController bookController = new BookController(scanner, bookService);
				String command = bookController.getValidCommand(scanner);
				System.out.println("Command execution result:");
				bookController.executeCommand(command);
			}
		}
	}
}
