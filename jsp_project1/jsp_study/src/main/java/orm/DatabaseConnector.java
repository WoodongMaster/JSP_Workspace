package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private static DatabaseConnector dbc = new DatabaseConnector();
	private Connection conn = null; // JDBC url, user, password
	//JDBC driver
	private String jdbcdriver = "com.mysql.cj.jdbc.Driver";
	//mysql URL
	private String jdbcdUrl = "jdbc:mysql://localhost:3306/javadb";
	
	private DatabaseConnector() {
		try {
			Class.forName(jdbcdriver);
			conn = DriverManager.getConnection(jdbcdUrl,"javauser","mysql");
		} catch (ClassNotFoundException e) {
			System.out.println("driver Err");
		} catch (SQLException e) {
			System.out.println("URL Err");
		}
	}
	
	public static DatabaseConnector getInstance() {
		return dbc;
	}

	public Connection getConnection() {
		return conn;
	}
	
}
