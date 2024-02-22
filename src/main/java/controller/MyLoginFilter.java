package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter({ "/add-task", "/complete", "/delete", "/logout", "/home.jsp", "/edit.jsp","/edit-task"})
public class MyLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession() == null) {
			response.getWriter().print("<h1 align='center' style='color:red'>Invalid session</h1>");
			req.getRequestDispatcher("login.html").forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}

}
