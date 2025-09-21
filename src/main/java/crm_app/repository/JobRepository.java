package crm_app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Job;


public class JobRepository {
	public List<Job> findAll() {
		List<Job> jobList = new ArrayList<>();
		
		
		String query = "SELECT * FROM jobs";
		
		Connection connection =  MySQLConfig.getConnection();
		if ( connection == null ) {
			throw new RuntimeException("Database Disconnected");
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while( resultSet.next() ) {
				Job job = new Job();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getString("name"));
				job.setStart_date(resultSet.getDate("start_date"));
				job.setEnd_date(resultSet.getDate("end_date"));
				
				jobList.add(job);
			}
		} catch ( Exception e ) {
			System.out.println("Error " + e.getMessage());
		}
		
		return jobList;
	}
}
