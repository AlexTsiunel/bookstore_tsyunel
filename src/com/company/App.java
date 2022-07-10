package com.company;

import java.util.Scanner;

import com.company.controller.BookController;
import com.company.dao.connection.DataSource;
import com.company.dao.impl.BookDaoImpl;
import com.company.service.BookService;
import com.company.service.impl.BookServiceImpl;

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
