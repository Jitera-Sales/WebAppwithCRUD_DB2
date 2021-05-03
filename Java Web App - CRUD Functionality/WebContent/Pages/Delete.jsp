<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete User</title>
</head>
<body>
	<h1>Delete User</h1>
	<%
	String error_message = "";
	Object error = request.getAttribute("error");
	if (error != null)
		error_message = error.toString();
	%>
	<form action="../DeleteServlet" METHOD=POST>
		<table Style="border-collapse: separate; border-spacing: 4px;">
			<tr>
				<td>Username:</td>
				<td><input name="username" type="text" size="20"></td>
				<td style="color: green"><%=error_message%></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="password" type="password" size="20"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Delete User"></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>