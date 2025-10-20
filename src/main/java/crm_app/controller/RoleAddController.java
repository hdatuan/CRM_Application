package crm_app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm_app.service.RoleService;
import entity.User;

@WebServlet(name = "roleAddServlet", urlPatterns = {"/role-add"} )
public class RoleAddController extends HttpServlet{

	private RoleService roleService = new RoleService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String roleName = req.getParameter("roleName");
		String roleDescription = req.getParameter("roleDesc");
		
		boolean isDone = true;
		
        
		if (roleName == null || roleName.trim().isEmpty() ) {
            
			req.setAttribute("message", "Tên quyền không được để trống!");
            req.setAttribute("isDone", isDone);
            req.setAttribute("isSuccess", false);
            req.getRequestDispatcher("role-add.jsp").forward(req, resp);
            return;
        }

		boolean isSuccess = roleService.insertRole(roleName, roleDescription);

        if (isSuccess) {
            req.setAttribute("message", "Thêm quyền thành công!");
        } else {
            req.setAttribute("message", "Quyền đã tồn tại, vui lòng thử lại!");
        }
        req.setAttribute("isDone", isDone);
        req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		int roleId = user.getRoleID();
		if ( roleId != 1 ) {
			resp.sendRedirect("404.jsp");
			return;
		}
		req.setAttribute("isDone", false);
		req.setAttribute("isSuccess", false);
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
}
