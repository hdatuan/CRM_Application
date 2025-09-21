package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter( filterName="authenFilter", urlPatterns = {"/user"})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	/*
	 * Check xem nguoi dung da log in chua, neu chua -> /login , neu roi -> /user
	 */
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		boolean isLoggedIn = false;
		
		
		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies ) {
			String name = c.getName();
			if ( name.equals("role") ) {
				isLoggedIn = true;
			}
		}
			
		if ( isLoggedIn )  {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect( req.getContextPath() + "/login");
		}
		
		
	}
	
}
