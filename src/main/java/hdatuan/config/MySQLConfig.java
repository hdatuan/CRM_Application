package hdatuan.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConfig {

	public static Connection getConnection() {
		Connection connection = null;
		Properties prop = new Properties();
		try ( InputStream input = MySQLConfig.class.getClassLoader().getResourceAsStream("db.properties"))
		{
			if ( input == null ) {
				System.out.println("Không tìm thấy file db.resources");
				return null;
			}
			
			prop.load(input);
			String url = prop.getProperty("db.url");
			String username = prop.getProperty("db.user");
			String password = prop.getProperty("db.pass");
														
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Lỗi kết nối : " + e.getMessage());
		}
		return connection;
	}
}

