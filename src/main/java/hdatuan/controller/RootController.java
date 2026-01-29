package hdatuan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="rootController", urlPatterns= {"/root"})
public class RootController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if ( session != null && session.getAttribute("user") != null ){
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}



