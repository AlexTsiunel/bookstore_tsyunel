package com.company.console;

import java.util.Scanner;

import com.company.DataSource;
import com.company.dao.BookDao;
import com.company.dao.BookDaoImpl;

public class BookConsole {
	private static final String REGEX_VALID_COMMAND = "(exit)|(all)|(get[\\s][1-9][\\d]*)|(delete[\\s][1-9][\\d]*)";

	public static void executeCommand(String command, DataSource dataSource) {
		BookDao bookDao = new BookDaoImpl(dataSource);
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
		case "exit": {
			System.out.println("Application completed");
			System.exit(0);
			break;
		}
		}
	}

	public static String getValidCommand(Scanner scanner) {
		String value = null;
		if (scanner != null) {
			do {
				System.out.print("Please enter the valid command: ");
				value = scanner.nextLine();
			} while (!isValidCommand(value));
		}
		return value;
	}

	private static boolean isValidCommand(String command) {
		return command.matches(REGEX_VALID_COMMAND);
	}
}
