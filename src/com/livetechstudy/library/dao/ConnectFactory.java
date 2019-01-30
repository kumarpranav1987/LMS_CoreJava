package com.livetechstudy.library.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.livetechstudy.library.util.LibraryConstants;

public class ConnectFactory {
	// static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// static final String DB_URL = "jdbc:mysql://localhost/library?useSSL=false";
	// static final String USER = "root";
	// static final String PASS = "password";

	private static final Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(new FileReader(System.getProperty(LibraryConstants.DB_PROPERTIES)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			Class.forName(properties.getProperty(LibraryConstants.DB_JDBC_DRIVER_CLASS));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(properties.getProperty(LibraryConstants.DB_URL),
					properties.getProperty(LibraryConstants.DB_USER), properties.getProperty(LibraryConstants.DB_PASSWORD));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
