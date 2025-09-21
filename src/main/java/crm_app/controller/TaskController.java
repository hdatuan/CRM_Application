package crm_app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app.service.TaskService;
import entity.Task;

@WebServlet(name = "taskController", urlPatterns = {"/task"})
public class TaskController extends HttpServlet {
	
	TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> taskList = taskService.getAllTasks();
		
		req.setAttribute("tasks", taskList);
		
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}
}
