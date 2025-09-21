package crm_app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.MySQLConfig;
import crm_app.service.LoginService;
import crm_app.service.UserService;
import entity.User;

@WebServlet( name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie[] cookieList = req.getCookies();
		
		String email = "";
		String password = "";
		
		for (Cookie cookie : cookieList) {
			String name = cookie.getName();
			String value = cookie.getValue();
			
			if ( name.equals("email") ) email = value;
			else if ( name.equals("password")) password = value;
			
			
		}
		
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password  = req. getParameter("password");
		String remember = req.getParameter("remember");
		System.out.println(remember);
		
		if ( remember != null ) {
			// Tạo cookie lưu thông tin người dùng đăng nhập
			Cookie cEmail = new Cookie("email", email);
			Cookie cPassword = new Cookie("password", password);
			cEmail.setMaxAge(300);
			cPassword.setMaxAge(300);
			resp.addCookie(cEmail);
			resp.addCookie(cPassword);
		}
		
		
		LoginService loginService = new LoginService();
		
		String loginResult = "Sai email hoặc mật khẩu";
		
		if ( loginService.findUser(email, password) ) {
			loginResult = "Đăng nhập thành công";
			Cookie cRole = new Cookie("role", "logged_in");
			cRole.setMaxAge(8 * 60 * 60);
			resp.addCookie(cRole);
			
			HttpSession session = req.getSession();
			session.setAttribute("currentUser", email); 
			
			req.setAttribute("loginResult", loginResult);
			resp.sendRedirect( req.getContextPath() + "/index.html");
			return;
		} else {
			req.setAttribute("loginResult", loginResult);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
	
	
}
