package com.company.app;

import java.util.Scanner;

import com.company.app.controller.BookController;
import com.company.app.dao.connection.DataSource;
import com.company.app.dao.impl.BookDaoImpl;
import com.company.app.service.BookService;
import com.company.app.service.impl.BookServiceImpl;


//The App class launches an application without a server (FOR APP TESTING)
//Use the class to start the server com.company.server.AppServer
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
