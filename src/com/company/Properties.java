package com.company;

public class Properties {
	private static final String URL = "jdbc:postgresql://localhost:5432/bookstore";
	private static final String PASSWORD = "root";
	private static final String USER = "postgres";

	public static String getUrl() {
		return URL;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getUser() {
		return USER;
	}
}
