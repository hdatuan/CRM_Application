package crm_app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app.service.JobService;
import entity.Job;

@WebServlet(name="jobController", urlPatterns= {"/groupwork"})
public class JobController extends HttpServlet {
	
	JobService jobService = new JobService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Job> jobList = jobService.getAllJobs();
		
		req.setAttribute("jobs", jobList);
		
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}
}
