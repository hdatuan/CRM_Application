package hdatuan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hdatuan.service.RoleService;
import hdatuan.entity.Role;

@WebServlet(name = "roleController", urlPatterns = {"/role"})
public class RoleController extends HttpServlet {
	
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    List<Role> roleList = roleService.getAllRoles();
	    req.setAttribute("roles", roleList);

	    HttpSession session = req.getSession(false);
	    if (session != null) {
	        Object msg = session.getAttribute("deleteMessage");
	        Object success = session.getAttribute("isSuccess");

	        if (msg != null) {
	            req.setAttribute("deleteMessage", msg);
	            req.setAttribute("isSuccess", success);

	            session.removeAttribute("deleteMessage");
	            session.removeAttribute("isSuccess");
	        }
	    }

	    req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}


}
