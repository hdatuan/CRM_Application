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

@WebFilter( filterName="authenFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // ✅ Cho phép đi qua nếu là trang login 
        if (path.startsWith("/login") 
        	    || path.startsWith("/css") 
        	    || path.startsWith("/js") 
        	    || path.startsWith("/images")) {
        	    chain.doFilter(request, response);
        	    return;
        	}

		
        boolean isLoggedIn = false;
        
		Cookie[] cookies = req.getCookies();
		if ( cookies != null ) {
			for(Cookie c : cookies ) {
				if ( "role".equals(c.getName()) ) {
					isLoggedIn = true;
					break;
				}
			}
		}
		
			
		if ( isLoggedIn )  {
			chain.doFilter(request, response);
		} else {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
		
	}
	
}
