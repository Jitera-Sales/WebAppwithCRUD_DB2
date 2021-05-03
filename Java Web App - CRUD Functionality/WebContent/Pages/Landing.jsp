<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Landing Page</title>
</head>
<body>
	<h1>You successfully logged in.</h1>
	<%
	    String person = "?";
	    String user = request.getParameter("user");
	    if (user != null)
	        person = user;
	    Object fullname = request.getAttribute("fullname");
	    if (fullname != null)
	        person = fullname.toString();
	    
	    Object users = request.getAttribute("users");
	    Object batting = request.getAttribute("batting");
	    Object fielding = request.getAttribute("fielding");
	%>
	
	Welcome to the landing page,
	<%=person%>!
	
	<h3>Here is a list of users:</h3>
	<p>
		<%=users%>
	</p>
	
	<h3>Your Batting Stats:</h3>
	<p>
		<%=batting%>
	</p>
	
	<h3>Your Fielding Stats:</h3>
	<p>
		<%=fielding%>
	</p>
	
	<div>
		<a href="./Pages/Insert.jsp"><button>Add User</button></a>
		<a href="./Pages/Delete.jsp"><button>Delete User</button></a>
		<a href="./Pages/Update.jsp"><button>Update User</button></a>
	</div>
</body>
</html>