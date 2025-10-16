package crm_app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Task;

public class TaskRepository {
	
	public boolean updateTask(Task task) {
		String query = "UPDATE tasks\r\n"
				+ "SET start_date = ?,\r\n"
				+ "end_date = ?,\r\n"
				+ "status_id = ?\r\n"
				+ "where id = ?;\r\n"
				+ "";
		try ( 	Connection connection = MySQLConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setDate(1, task.getStart_date());
			statement.setDate(2, task.getEnd_date());
			statement.setInt(3, task.getStatus_id());
			statement.setInt(4, task.getId());
			int row = statement.executeUpdate();
			return row > 0;
		} catch ( Exception e ) {
			System.out.println(e);
		}
		
		return false;
	}
	
	public Task findTask(int taskId) {
		Task task = new Task();
		String query = "SELECT * FROM tasks WHERE tasks.id = ?";
		try (
				Connection connection =  MySQLConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
			) {
				statement.setInt(1, taskId);
				ResultSet resultSet = statement.executeQuery();
				
				while( resultSet.next() ) {
					task.setId(resultSet.getInt("id"));
					task.setName(resultSet.getString("name"));
					task.setJob_id(resultSet.getInt("job_id"));
					task.setUser_id(resultSet.getInt("user_id"));
					task.setStart_date(resultSet.getDate("start_date"));
					task.setEnd_date(resultSet.getDate("end_date"));
					task.setStatus_id(resultSet.getInt("status_id"));
				}
			} catch ( Exception e ) {
				System.out.println("Error " + e.getMessage());
		}
		return task;
	}
	
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
		try (
			Connection connection =  MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
		) {
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
	
	public List<Task> findByUserAndJob(int userId, int jobId){
		List<Task> taskList = new ArrayList<Task>();
		String query = "SELECT t.*\r\n"
				+ "FROM tasks t\r\n"
				+ "JOIN users u\r\n"
				+ "ON t.user_id = u.id \r\n"
				+ "JOIN jobs j\r\n"
				+ "ON j.id = t.job_id\r\n"
				+ "WHERE user_id = ?\r\n"
				+ "AND job_id = ?";
		try (
				Connection connection = MySQLConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setInt(1, userId);
			statement.setInt(2, jobId);
			
			try ( ResultSet resultSet = statement.executeQuery() ){
				while(resultSet.next()) {
					Task task = new Task();
					task.setId(resultSet.getInt("id"));
					task.setName(resultSet.getString("name"));
					task.setStart_date(resultSet.getDate("start_date"));
					task.setEnd_date(resultSet.getDate("end_date"));
					task.setStatus_id(resultSet.getInt("status_id"));
					taskList.add(task);
				}
			}
			
			return taskList;
		} catch ( Exception e ) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<Task> findByUserId(int userId){
		List<Task> userTasks = new ArrayList<Task>();
		String query = "SELECT t.id, t.name, j.name AS job_name, t.start_date, t.end_date, s.name AS status_name\r\n"
				+ "FROM tasks t\r\n"
				+ "JOIN status s ON t.status_id = s.id\r\n"
				+ "JOIN jobs j ON t.job_id = j.id\r\n"
				+ "WHERE t.user_id = ?\r\n";
		


		try (	
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
		)	{
			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();
			
			
			while( resultSet.next() ) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setJob_name(resultSet.getString("job_name"));
				task.setStart_date(resultSet.getDate("start_date"));
				task.setEnd_date(resultSet.getDate("end_date"));
				task.setStatus_name(resultSet.getString("status_name"));
				userTasks.add(task);
			}
			
		} catch ( Exception e ) {
			System.out.println("Error " + e.getMessage());
		}
		
		return userTasks;
		}
}