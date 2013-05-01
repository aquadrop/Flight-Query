<%--
    Document    : searchFlight.jsp
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
		<script type="text/javascript" src="../js/airports.js"></script>
		<script type="text/javascript" src="../js/searchFlight.js"></script>
	</head>
	
	<body>
		<div class="container-narrow">

			<div class="masthead">
				<div class="pull-right">
					<div id="salute">Welcome, <a href="#"><%=request.getUserPrincipal().getName() %></a></div>
					<ul class="nav nav-pills pull-right">
						<li><a href="<%=userService.createLogoutURL(thisURL) %>"><i class="icon-off"></i> Sign Out</a></li>
					</ul>
				</div>
				
				<h3>ECE 1779 - Assignment 2</h3>
			</div>

			<hr />

			<div id="searchForm">
				<h2 class="muted">Flight Search</h2>

				<form action="SearchFlight" method="post" accept-charset="utf-8" class="form-inline" id="searchFlightForm">
					<input placeholder="Departure City: Toronto" type="text" id="deptCity" autocomplete="off" />
					<input placeholder="Arrival City: YVR" type="text" id="destCity" autocomplete="off" />
					
					<input name="deptCity" type="hidden" />
					<input name="destCity" type="hidden" />
					
					<input type="submit" value="Search" class="btn btn-large" />
				</form>
			</div>

			<div id="searchResult"></div>

			<div class="footer">
				<p>&copy; Group 13</p>
			</div>

		</div> <!-- /container -->
		
		<!-- Modal -->
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="modalHeader">Details</h3>
			</div>
			
			<div class="modal-body" id="modalBody"></div>
			
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			</div>
		</div>
	</body>
</html>