package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import dao.TodoDao;
import dto.TodoTask;
import dto.TodoUser;

public class TodoService {
	TodoDao dao = new TodoDao();

	public void signup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		TodoUser user = new TodoUser();
		user.setName(req.getParameter("name"));
		user.setDob(LocalDate.parse(req.getParameter("dob")));
		user.setEmail(req.getParameter("email"));
		user.setGender(req.getParameter("gender"));
		user.setNumber(Long.parseLong(req.getParameter("number")));
		user.setPassword(req.getParameter("password"));

		List<TodoUser> list = dao.findByEmail(req.getParameter("email"));
		if (list.isEmpty()) {
			dao.saveUser(user);
			resp.getWriter().print("<h1 align=center style='color:green'>Account created success</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1 align=center style='color:red'>Email should be unique</h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		List<TodoUser> list = dao.findByEmail(email);

		if (list.isEmpty()) {
			resp.getWriter().print("<h1 align=center style='color:red'>Invalid email</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			TodoUser user = list.get(0);
			if (password.equals(user.getPassword())) {
				// setting the session with logged in user
				req.getSession().setAttribute("user", user);
				List<TodoTask> tasks = dao.fetchTaskByUser(user.getId());
				req.setAttribute("tasks", tasks);
				resp.getWriter().print("<h1 align=center style='color:green'>Login success</h1>");
				req.getRequestDispatcher("home.jsp").include(req, resp);
			} else {
				resp.getWriter().print("<h1 align=center style='color:red'>Invalid password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}
	}

	public void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tname = req.getParameter("tname");
		String tdescription = req.getParameter("tdesp");

		TodoTask task = new TodoTask();
		task.setName(tname);
		task.setDescription(tdescription);
		task.setStatus(false);
		task.setCreatedTime(LocalDateTime.now());
		// getting the user from the session
		TodoUser user = (TodoUser) req.getSession().getAttribute("user");
		task.setUser(user);

		dao.addTaskindb(task);
		List<TodoTask> tasks = dao.fetchTaskByUser(user.getId());
		req.setAttribute("tasks", tasks);
		resp.getWriter().print("<h1 align=center style='color:green'>Task added successfully</h1>");
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getSession().removeAttribute("user");
		resp.getWriter().print("<h1 align='center' style='color:green;font-size:2rem;'>Logout success</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		List<TodoTask> task = dao.fetchTaskById(id);
		TodoTask task1 = task.get(0);
		dao.deletetaskById(task1);
		TodoUser user = (TodoUser) req.getSession().getAttribute("user");
		List<TodoTask> tasks = dao.fetchTaskByUser(user.getId());
		req.setAttribute("tasks", tasks);
		resp.getWriter().print("<h1 align='center' style='color:green;font-size:2rem;'>Task deleted</h1>");
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	public void completetask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		List<TodoTask> task = dao.fetchTaskById(id);
		TodoTask task1 = task.get(0);
		task1.setStatus(true);
		dao.updateTask(task1);
		TodoUser user = (TodoUser) req.getSession().getAttribute("user");
		List<TodoTask> tasks = dao.fetchTaskByUser(user.getId());
		req.setAttribute("tasks", tasks);
		resp.getWriter().print("<h1 align='center' style='color:green;font-size:2rem;'>Task completed</h1>");
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	public void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name=req.getParameter("tname");
		String description=req.getParameter("tdesp");
		int id = Integer.parseInt(req.getParameter("id"));
		TodoTask task1 = new TodoTask();
		task1.setId(id);
		task1.setName(name);
		task1.setDescription(description);
		task1.setStatus(false);
		task1.setCreatedTime(LocalDateTime.now());
		TodoUser user = (TodoUser) req.getSession().getAttribute("user");
		task1.setUser(user);
		dao.updateTask(task1);
		List<TodoTask> tasks = dao.fetchTaskByUser(user.getId());
		req.setAttribute("tasks", tasks);
		resp.getWriter().print("<h1 align='center' style='color:green;font-size:2rem;'>Task edited</h1>");
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
}
