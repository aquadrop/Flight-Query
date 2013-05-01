<%--
    Document    : details.jsp
    Created on  : 2013/04/11
    Author      : Jeff Lee
    Description : Detail modal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Collection" %>
<%@page import="ece1779.appengine.models.Airline" %>
<%@page import="ece1779.appengine.models.Airport" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>ECE 1779 - Assignment 2</title>
		
		<link rel="stylesheet" type="text/css" href="https://code.leejefon.com/cdn/jquery/twitter-bootstrap/v2.3.1/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="https://code.leejefon.com/cdn/jquery/twitter-bootstrap/v2.3.1/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" type="text/css" href="https://code.leejefon.com/font-awesome/css/font-awesome.min.css" />
	</head>
	
	<body>
		<table class="table table-striped">
		<% if (request.getParameter("type").equals("airline")) {
			Airline airline = (Airline) request.getAttribute("airline");
		%>
			<tr><th>Name</th><td><%=airline.getName() %></td></tr>
			<tr><th>Country</th><td><%=airline.getCountry() %></td></tr>
			<tr><th>Call Assign</th><td><%=airline.getCallAssign() %></td></tr>
			<tr><th>IATA</th><td><%=airline.getIATA() %></td></tr>
			<tr><th>ICAO</th><td><%=airline.getICAO() %></td></tr>

		<% } else if (request.getParameter("type").equals("airport")) {
			Airport airport = (Airport) request.getAttribute("airport");
		%>
			<tr><th>Name</th><td><%=airport.getName() %></td></tr>
			<tr><th>Country</th><td><%=airport.getCountry() %></td></tr>
			<tr><th>City</th><td><%=airport.getCity() %></td></tr>
			<tr><th>IATA</th><td><%=airport.getIATA() %></td></tr>
			<tr><th>ICAO</th><td><%=airport.getICAO() %></td></tr>
		<% } %>
		</table>
	</body>
</html>