package crm_app.service;

import java.util.List;

import crm_app.repository.JobRepository;
import entity.Job;

public class JobService {
	private JobRepository jobRepository = new JobRepository();
	
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}
}
