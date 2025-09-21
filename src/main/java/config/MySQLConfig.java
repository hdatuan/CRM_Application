package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConfig {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			String url = "jdbc:mysql://localhost:3307/crm_app";
			String username = "root";
			String password = "tuan2006";
														
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Lỗi kết nối : " + e.getMessage());
		}
		return connection;
	}
	
}
