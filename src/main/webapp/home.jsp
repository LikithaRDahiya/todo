<%@page import="dto.TodoTask"%>
<%@page import="service.TodoService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style>
body {
	background: radial-gradient(circle farthest-corner at 10% 20%, #7F7FD5 2.5%, #91EAE4
		42%, #31B7C2 97.2%);
}

* {
	font-family: cursive;
}

table {
	margin-left: 300px;
	font-size: 1.3em;
	background: radial-gradient(circle farthest-corner at 10% 20%, #ee9ca7 2.5%, #ffdde1
		42%, #ef629f 97.2%);
	border-radius: 20px;
	background: radial-gradient(circle farthest-corner at 10% 20%, #ee9ca7 2.5%, #ffdde1
		42%, #ef629f 97.2%);
}

.b1 {
	margin-left: 10%;
	padding: 10px;
	width: 100px;
}

.buttons {
	margin-left: 30%;
	margin-top: 10px;
	font-size: 1.5em;
}
/* .buttons button{
background-color: pink;
} */
button {
	font-size: 0.7em;
	border-radius: 7px;
	border: 1px solid gray;
}
</style>
</head>
<body>
	<%
	List<TodoTask> tasks = (List<TodoTask>) request.getAttribute("tasks");
	%>
	<table border=2 cellpadding=10 cellspacing=7>
		<%
		if (!tasks.isEmpty()) {
		%>
		<tr>
			<th>Task name</th>
			<th>Task Description</th>
			<th>Created Time</th>
			<th>Status</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
		for (TodoTask task : tasks) {
		%>
		<tr>
			<td><%=task.getName()%></td>
			<td><%=task.getDescription()%></td>
			<td><%=task.getCreatedTime()%></td>
			<%-- 	<%
			if (!task.isStatus()) {
			%>
			<td><button>complete</button></td>
			<%
			} else {
			%>
			<td>Completed</td>
			<%
			}
			%> --%>
			<%
			if (task.isStatus()) {
			%><td style="color: green">Complete</td>
			<%
			} else {
			%>
			<td><a href="complete?id=<%=task.getId()%>"><button>complete</button></a></td>
			<%
			}
			%>
			<td><a href="edit-task.jsp?id=<%=task.getId()%>&name=<%=task.getName()%>&desp=<%=task.getDescription()%>"><button>edit</button></td></a>
			<td><a href="delete?id=<%=task.getId()%>"><button>delete</button></a></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	}
	%>
	<div class=buttons>
		<a href="./add-task.html"><button class=b1>Add Task</button></a> <a
			href=logout><button class=b1>Logout</button></a>
	</div>
</body>
</html>