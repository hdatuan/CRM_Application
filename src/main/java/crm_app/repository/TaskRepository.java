package crm_app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Task;

public class TaskRepository {
	public List<Task> findAll() {
		List<Task> taskList = new ArrayList<>();
		
		
		String query = "SELECT t.id AS id,\r\n"
				+ "		t.name AS name,\r\n"
				+ "		j.name AS job_name,\r\n"
				+ "		u.fullname AS user_name,\r\n"
				+ "		t.start_date AS start_date,\r\n"
				+ "		t.end_date AS end_date,\r\n"
				+ "		s.name AS status_name\r\n"
				+ "FROM tasks t\r\n"
				+ "JOIN users u\r\n"
				+ "ON t.user_id = u.id\r\n"
				+ "JOIN jobs j\r\n"
				+ "ON t.job_id = j.id\r\n"
				+ "JOIN status s\r\n"
				+ "ON t.status_id = s.id";
		
		Connection connection =  MySQLConfig.getConnection();
		if ( connection == null ) {
			throw new RuntimeException("Database Disconnected");
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next() ) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setJob_name(resultSet.getString("job_name"));
				task.setUser_name(resultSet.getString("user_name"));
				task.setStart_date(resultSet.getDate("start_date"));
				task.setEnd_date(resultSet.getDate("end_date"));
				task.setStatus_name(resultSet.getString("status_name"));
				taskList.add(task);
			}
		} catch ( Exception e ) {
			System.out.println("Error " + e.getMessage());
		}
		
		return taskList;
	}
}
