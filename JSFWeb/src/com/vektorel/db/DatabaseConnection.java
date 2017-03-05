package com.vektorel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@db.maozturk.com:1521:ORCL", 
				"java15",
				"java15");
	}
	
	public void executeUpdate(String SQL) throws SQLException, ClassNotFoundException {
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL);
		}
	}

}
