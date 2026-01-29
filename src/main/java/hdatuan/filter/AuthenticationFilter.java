package hdatuan.filter;

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
        HttpSession session = req.getSession(false);

        boolean isLoginRequest = path.startsWith("/login");
        boolean isStaticResource = path.startsWith("/css") || path.startsWith("/js") || path.startsWith("/images") 
                                || path.startsWith("/bootstrap") || path.startsWith("/plugins") || path.startsWith("/less")
                                || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") 
                                || path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".gif")
                                || path.endsWith(".ico") || path.endsWith(".woff") || path.endsWith(".woff2");

        // Kiem tra da dang nhap chua : session != null va da co user
        boolean isLoggedIn = ( session != null && session.getAttribute("user") != null);

		if ( isLoggedIn || isLoginRequest || isStaticResource )  {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}


