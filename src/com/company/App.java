package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.company.console.BookConsole;

public class App {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = DriverManager.getConnection(Properties.getUrl(), Properties.getUser(),
				Properties.getPassword());) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please use commands:\n" //
					+ "  'all' - to get a list of all books in the repository;\n"//
					+ "  'get {id} - to get book from repository by id;\n"//
					+ "  'delete {id} - to remove book from repository by id;\n"//
					+ "  'exit' - to exit from application; ");
			while (true) {
				String command = BookConsole.getValidCommand(scanner);
				System.out.println("Ñommand execution result:");
				BookConsole.executeCommand(command, connection);
			}

		}
	}
}
