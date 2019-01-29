package com.livetechstudy.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectFactory {
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/library?useSSL=false";
	static final String USER = "root";
	static final String PASS = "password";

	public static Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
