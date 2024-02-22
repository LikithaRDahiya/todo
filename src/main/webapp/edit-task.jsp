<%@page import="dao.TodoDao"%>
<%@page import="dto.TodoTask"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Edit task</h1>
	<form action="edit-task">
		<label>Task Name:</label> <br> <input type="text" name=tname
			value=<%=request.getParameter("name")%>><br> <br> <label>
			Description:</label> <br> <input type="text" name=tdesp
			value=<%=request.getParameter("desp")%>> <br> <br>
		<input type=hidden name=id value=<%=request.getParameter("id")%>>
		<button>edit</button>
	</form>
</body>
</html>