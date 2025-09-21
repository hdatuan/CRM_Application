package crm_app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app.service.RoleService;
import entity.Role;

@WebServlet(name = "roleController", urlPatterns = {"/role"})
public class RoleController extends HttpServlet {
	
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> roleList = roleService.getAllRoles();

		req.setAttribute("roles", roleList);
		
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}
	

}