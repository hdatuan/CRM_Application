package crm_app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Role;

public class RoleRepository {

	public List<Role> findAll() {
		List<Role> roleList = new ArrayList<>();
		
		
		/*	1, Chuẩn bị câu truy vấn
		 * 	2, Mở kết nối DB 
		 * 	3, Truyền query vào DB
		 * 	4, Thực hiện
		 */
		
		// 1
		String query = "SELECT * FROM roles";
		
		//2
		Connection connection =  MySQLConfig.getConnection();
		if ( connection == null ) {
			throw new RuntimeException("Database Disconnected");
		}
		
		try {
			// 3
			PreparedStatement statement = connection.prepareStatement(query);
			
			// 4 : execute query
			ResultSet resultSet = statement.executeQuery();
			
			while ( resultSet.next() ) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				roleList.add(role);
			}
			
			
		} catch ( Exception e ) {
			System.out.println("Error " + e.getMessage());
		}
		
		return roleList;
	}
	
	public int insertRole(String name, String description ) {
		int rowCount = 0;
		
		String query = "INSERT INTO roles( name, description ) VALUES (? , ?)";
		
		Connection connection = MySQLConfig.getConnection();
		
		if ( connection == null ) {
			throw new RuntimeException("Database disconnected");
		}
	 
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, name);
			statement.setString(2, description);
			
			// Check if role is existed
			List<Role> insertedRole = this.findAll();
			for(Role role : insertedRole) {
				if ( role.getName().equals(name) ) return 0;
			}
			
			
			rowCount = statement.executeUpdate();
			
		} catch ( Exception e ) {
			System.out.println("Error : " + e.getMessage());
		}
		
		return rowCount;
	}
}
