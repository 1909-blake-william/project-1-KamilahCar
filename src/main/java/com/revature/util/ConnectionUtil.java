package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String url = System.getenv("EMPLOYEE_DATABASE");
		String username = System.getenv("EMPLOYEE_USER");
		String password = System.getenv("EMPLOYEE_PASS");
		return DriverManager.getConnection(url, username, password);
	}
}