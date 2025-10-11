package crm_app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app.service.TaskService;
import entity.Task;

@WebServlet(name="profileController", urlPatterns= {"/profile"})
public class ProfileController extends HttpServlet{
	
	private TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = taskService.getAllTasks();
		
		req.setAttribute("tasks", tasks);
		
		
		req.getRequestDispatcher("profile.jsp").forward(req, resp);
	}
}
