package crm_app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm_app.service.UserService;
import entity.User;
@WebServlet(name="userAddController", urlPatterns= {"/user-add"})
public class UserAddController extends HttpServlet {
	
	UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		int roleId = user.getRoleID();
		if ( roleId != 1 ) {
			resp.sendRedirect("404.jsp");
			return;
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
		
		boolean isSuccess = userService.insertUser(fullName, email, password, roleId);
		
		if (isSuccess) {
            req.setAttribute("message", "Thêm người dùng thành công!");
        } else {
            req.setAttribute("message", "Người dùng đã tồn tại, vui lòng thử lại!");
        }
        req.setAttribute("isDone", isDone);
        req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
}
