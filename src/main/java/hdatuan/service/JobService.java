package hdatuan.service;

import java.util.List;

import hdatuan.repository.JobRepository;
import hdatuan.entity.Job;

public class JobService {
	private JobRepository jobRepository = new JobRepository();
	
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}
	
	public List<Job> getJobById(int user_id){
		return jobRepository.findById(user_id);
	}
}

