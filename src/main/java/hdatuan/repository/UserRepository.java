package hdatuan.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hdatuan.config.MySQLConfig;
import hdatuan.entity.Role;
import hdatuan.entity.User;

public class UserRepository {
	
	/*
	 * Cách đặt tên hàm trong repo để gọi tới query
	 * 
	 * Ví dụ : 	SELECT * 
	 * 			FROM users u
	 * 			WHERE u.email = '' AND u.password = '' 
	 *			 	-> findByEmailAndPassword
	 * 
	 * 
	 * 			SELECT * 
	 * 			FROM roles r
	 * 			WHERE name='' OR id=''
	 * 				-> findByNameOrId
	 */
	
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
	
		String query = "SELECT * FROM users u JOIN roles r ON u.role_id = r.id";
		
		Connection connection = MySQLConfig.getConnection();
		if (connection == null) {
		    throw new RuntimeException("Không thể kết nối tới Database");
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while ( resultSet.next() ) {
				User user = new User();
				user.setFullname(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setId(resultSet.getInt("id"));
				user.setRoleDescription(resultSet.getString("description"));
				userList.add(user);
			}
			
		} catch ( Exception e ) {
			System.out.println("Error : " + e.getMessage());
		}
		return userList;
	}	
	
	
	public User findById(int id) {
	    User user = null;
	    String query = "SELECT u.*, r.description FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ?";
	    Connection connection = MySQLConfig.getConnection();

	    try {
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, id);
	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {
	            user = new User();
	            user.setId(rs.getInt("id"));
	            user.setFullname(rs.getString("fullname"));
	            user.setEmail(rs.getString("email"));
	            user.setRoleID(rs.getInt("role_id"));
	            user.setRoleDescription(rs.getString("description"));
	        }
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    }

	    return user;
	}

	
	public int updateUser(int id, String fullName, String email, String password, int roleId) {
	    int row = 0;

	    String query = "UPDATE users "
	                 + "SET fullname = ?, email = ?, password = ?, role_id = ? "
	                 + "WHERE id = ?";

	    Connection connection = MySQLConfig.getConnection();

	    if (connection == null) {
	        throw new RuntimeException("Database disconnected");
	    }

	    try {
	        PreparedStatement statement = connection.prepareStatement(query);

	        statement.setString(1, fullName);
	        statement.setString(2, email);
	        statement.setString(3, password);
	        statement.setInt(4, roleId);
	        statement.setInt(5, id);

	        row = statement.executeUpdate();

	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    }

	    return row;
	}

	
	public int insertUser(String fullName, String email, String password, int roleId) {
		int row = 0;
		String query = "INSERT INTO users ( email, password, fullname, role_id) "
				+ "VALUES ( ?, ?, ?, ?)";
		Connection connection = MySQLConfig.getConnection();
		
		if ( connection == null ) {
			throw new RuntimeException("Database disconnected");
		}
	 
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, fullName);
			statement.setInt(4, roleId);
			
			// Check if user is existed
			List<User> insertedUser = this.findAll();
			for(User user : insertedUser) {
				if ( user.getFullname().equals(fullName) ) return 0;
			}
			
			
			row = statement.executeUpdate();
			
		} catch ( Exception e ) {
			System.out.println("Error : " + e.getMessage());
		}
		return row;
	}
	
	
	public void deleteUser(int id) {
		
		String query = "DELETE FROM users WHERE id = ?";
		
		Connection connection = MySQLConfig.getConnection();
		
		if (connection == null) {
		    throw new RuntimeException("Database disconnected");
		}
		
		try {

			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch ( Exception e ) {
			System.out.println("Error : " + e.getMessage());
		}

	}
}

