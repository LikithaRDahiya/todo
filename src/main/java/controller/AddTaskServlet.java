package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;

@WebServlet("/add-task")
public class AddTaskServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("user") != null) {
			TodoService service = new TodoService();
			service.addTask(req, resp);
		} else {
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid session</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
}
