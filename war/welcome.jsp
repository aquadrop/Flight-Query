<%--
    Document    : welcome.jsp
    Created on  : 2013/04/07
    Author      : Jeff Lee
    Description : Login page
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Collection" %>
<%@page import="com.google.appengine.api.users.UserService" %>
<%@page import="com.google.appengine.api.users.UserServiceFactory" %>

<%
	UserService userService = UserServiceFactory.getUserService();
	String thisURL = "";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>ECE 1779 - Assignment 2</title>
		
		<link rel="stylesheet" type="text/css" href="https://code.leejefon.com/cdn/jquery/twitter-bootstrap/v2.3.1/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
		<link rel="stylesheet" type="text/css" href="https://code.leejefon.com/cdn/jquery/twitter-bootstrap/v2.3.1/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" type="text/css" href="https://code.leejefon.com/font-awesome/css/font-awesome.min.css" />
		
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
		<script type="text/javascript" src="https://code.leejefon.com/jquery.bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container-narrow">

			<div class="masthead">
				<h3 class="muted">ECE 1779 - Assignment 2</h3>
			</div>

			<hr />

			<div class="jumbotron">
				<h1>Planning for a Trip?</h1>
				<p class="lead">Search the flights with our state-of-the-art searching system.</p>

				<br />

				<a class="btn btn-large btn-success" href="<%=userService.createLoginURL(thisURL) %>"><i class="icon-lock"></i> Sign in with Google</a>
			</div>

			<hr />

			<div class="footer">
				<p>&copy; Group 13</p>
			</div>

		</div> <!-- /container -->
	</body>
</html>