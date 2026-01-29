package hdatuan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hdatuan.service.RoleService;
import hdatuan.service.UserService;
import hdatuan.entity.Role;
import hdatuan.entity.User;
@WebServlet(name="userAddController", urlPatterns= {"/user-add", "/user-edit"})
public class UserAddController extends HttpServlet {
	
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		int roleId = user.getRoleID();
		String servletPath = req.getServletPath();
	
		if ( roleId != 1 && (servletPath.equals("/user-edit") || servletPath.equals("/user-add") ) ) {
			resp.sendRedirect("404.jsp");
			return;
		}
		List<Role> roles = roleService.getAllRoles();
		req.setAttribute("roles", roles);
	    if (servletPath.equals("/user-edit")) {
	        String idStr = req.getParameter("id");
	        if (idStr != null) {
	            int id = Integer.parseInt(idStr);
	            User editUser = userService.findById(id);
	            req.setAttribute("editUser", editUser);
	            req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
	            return;
	        }
	    }
		
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullName = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int roleId =  Integer.parseInt(req.getParameter("role-id"));
		boolean isDone = true;
		
        
		if (fullName == null || fullName.trim().isEmpty() || email == null || email.trim().isEmpty()
				|| password == null || password.trim().isEmpty() ) {
			req.setAttribute("message", "Thông tin không được để trống!");
            req.setAttribute("isDone", isDone);
            req.setAttribute("isSuccess", false);
            req.getRequestDispatcher("user-add.jsp").forward(req, resp);
            return;
        }
		
		if ( req.getServletPath().equals("/user-add") ) {
			
			boolean isSuccess = userService.insertUser(fullName, email, password, roleId);
			
			if (isSuccess) {
				req.setAttribute("message", "Thêm người dùng thành công!");
			} else {
				req.setAttribute("message", "Người dùng đã tồn tại, vui lòng thử lại!");
			}
			req.setAttribute("isDone", isDone);
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);			
		} else if ( req.getServletPath().equals("/user-edit") ) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = userService.updateUser(id, fullName, email, password, roleId);
			User user = userService.findById(id);
			if (isSuccess) {
				req.setAttribute("message", "Chỉnh sửa người dùng thành công!");
			} else {
				req.setAttribute("message", "Thất bại, vui lòng thử lại!");
			}
			req.setAttribute("user", user);
			req.setAttribute("isDone", isDone);
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
		}
		
	}
	
}

