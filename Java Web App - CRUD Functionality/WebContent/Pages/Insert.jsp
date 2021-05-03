<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert User</title>
</head>
<body>
	<h1>Insert User</h1>
	<%
	String error_message = "";
	Object error = request.getAttribute("error");
	if (error != null)
		error_message = error.toString();
	%>
	<form action="../InsertServlet" METHOD=POST>
		<table Style="border-collapse: separate; border-spacing: 4px;">
			<tr>
				<td>Jersey Number:</td>
				<td><input name="id" type="text" size="2"></td>
				<td style="color: green"><%=error_message%></td>
			</tr>
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
				<td>First Name:</td>
				<td><input name="fname" type="text" size="20"></td>
				<td style="color: green"><%=error_message%></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input name="lname" type="text" size="20"></td>
				<td style="color: green"><%=error_message%></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Add User"></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>