package crm_app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.MySQLConfig;

public class LoginRepository {

	public boolean findUser(String email, String password) {
		
		String query = "SELECT 1 FROM users WHERE email = ? AND password = ?";
	
		try (
				Connection connection = MySQLConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				
				){
			
			statement.setString(1, email);
			statement.setString(2, password);
			
			try ( ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}
			
	
		} catch ( Exception e ) {
			System.out.println("Error while finding user" + e.getMessage());
		}
		return false;
		
	}
	
}
