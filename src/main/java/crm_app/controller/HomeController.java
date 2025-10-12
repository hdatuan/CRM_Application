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

@WebServlet(name="homeController", urlPatterns= {"/home"})
public class HomeController extends HttpServlet {
	
	TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> taskList = taskService.getAllTasks();
		int totalTask = taskList.size();
		int notStartedTask = 0;
		int inProgressTask = 0;
		int doneTask = 0;
		for(Task task : taskList) {
			String status = task.getStatus_name();
			if ( "Chưa thực hiện".equals(status) ) notStartedTask++;
			else if ( "Đang thực hiện".equals(status) ) inProgressTask++;
			else if ( "Đã thực hiện".equals(status) ) doneTask++;
		}
		
		
	    double notStartedPercent = totalTask > 0 ? (notStartedTask * 100.0 / totalTask) : 0;
	    double inProgressPercent = totalTask > 0 ? (inProgressTask * 100.0 / totalTask) : 0;
	    double donePercent = totalTask > 0 ? (doneTask * 100.0 / totalTask) : 0;
		
		req.setAttribute("totalTask", totalTask);
		req.setAttribute("notStartedTask", notStartedTask);
		req.setAttribute("inProgressTask", inProgressTask);
		req.setAttribute("doneTask", doneTask);
	    req.setAttribute("notStartedPercent", notStartedPercent);
	    req.setAttribute("inProgressPercent", inProgressPercent);
	    req.setAttribute("donePercent", donePercent);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
