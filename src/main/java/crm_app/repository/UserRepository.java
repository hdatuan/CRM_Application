package crm_app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.User;

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
		
		/*	1, Chuẩn bị câu truy vấn
		 * 	2, Mở kết nối DB 
		 * 	3, Truyền query vào DB
		 * 	4, Thực hiện
		 */
		
		// 1
		String query = "SELECT * FROM users u JOIN roles r ON u.role_id = r.id";
		
		// 2
		Connection connection = MySQLConfig.getConnection();
		if (connection == null) {
		    throw new RuntimeException("Không thể kết nối tới Database");
		}
		
		try {
			// 3
			PreparedStatement statement = connection.prepareStatement(query);
			
			// 4 
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
