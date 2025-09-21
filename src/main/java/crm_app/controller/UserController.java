package crm_app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app.service.UserService;
import entity.User;

@WebServlet( name = "userController", urlPatterns = {"/user", "/user-delete"})
public class UserController extends HttpServlet {
	
	UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if ( req.getServletPath().equals("/user-delete") ) {
			int user_id =  Integer.parseInt(req.getParameter("id"));
			
			userService.deleteUser(user_id);
			
			resp.sendRedirect(req.getContextPath() + "/user");
			return;
		}
		
		List<User> userList = userService.getAllUsers();		
		
		req.setAttribute("users", userList);
		
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
	
	
	
}
