package ece1779.appengine.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import ece1779.appengine.datastore.CacheUtil;
import ece1779.appengine.models.Airport;
import ece1779.appengine.models.Route;

public class SearchFlight extends HttpServlet {

	private static final long serialVersionUID = 8067286395342650514L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String dept = request.getParameter("dept");
		String dest = request.getParameter("dest");
		String q = request.getParameter("q");

		response.setContentType("application/json");

		try {
			PrintWriter out = response.getWriter();

			if (request.getUserPrincipal() != null) {
				int count = 0;

				out.println("{");
				if (type.equals("airport")) {
					List<Airport> airports = queryAirports(q);
					out.println("\t\"status\": \"OK\",");
					out.println("\t\"airports\" : [");
					for (Airport airport : airports) {
						if (count++ != 0) out.println(",");
						out.println("\t\t\"" + airport.getCity() + " - " + airport.getName() + " (" + airport.getIATA() + ")\"");
					}
					out.println("\t]");
				} else if (type.equals("route")) {
					List<Route> routes = queryRoutes(dept, dest);
					out.println("\t\"status\": \"OK\",");
					out.println("\t\"routes\" : [");
					for (Route route : routes) {
						if (count++ != 0) out.println(",");
						out.println("\t\t{");
						out.println("\t\t\t\"Airline\" : \"" + route.getAirline() + "\",");
						out.println("\t\t\t\"DestAirport\" : \"" + route.getDestAirport() + "\",");
						out.println("\t\t\t\"DeptAirport\" : \"" + route.getDeptAirport() + "\",");
						out.println("\t\t\t\"Equipment\" : \"" + route.getEquipment() + "\"");
						out.println("\t\t}");
					}
					out.println("\t]");
				} else {
					out.println("\t\"status\": \"Error: No such type found\"");
				}

				out.println("}");
			} else {
				out.println("{");
				out.println("\t\"status\": \"Error: Not authenticated\"");
				out.println("}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Airport> queryAirports(String q) {
		DatastoreService datastore =  DatastoreServiceFactory.getDatastoreService();

		List<Airport> airports = new ArrayList<Airport>();

		// Query City
		Query query = new Query("airports")
			.addFilter("city", FilterOperator.GREATER_THAN_OR_EQUAL, q)
			.addFilter("city", FilterOperator.LESS_THAN, q + "\ufffd");
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			Airport airport = new Airport();

	        airport.setCity((String) result.getProperty("city"));
	        airport.setCountry((String) result.getProperty("country"));
	        airport.setIATA((String) result.getProperty("iata"));
	        airport.setICAO((String) result.getProperty("icao"));
	        airport.setName((String) result.getProperty("name"));

	        airports.add(airport);
	        CacheUtil.addToCache(airport.getIATA(), result);
	    }

		// Query IATA
		Query query2 = new Query("airports")
			.addFilter("iata", FilterOperator.GREATER_THAN_OR_EQUAL, q)
			.addFilter("iata", FilterOperator.LESS_THAN, q + "\ufffd");
		PreparedQuery pq2 = datastore.prepare(query2);

		for (Entity result : pq2.asIterable()) {
			Airport airport = new Airport();

	        airport.setCity((String) result.getProperty("city"));
	        airport.setCountry((String) result.getProperty("country"));
	        airport.setIATA((String) result.getProperty("iata"));
	        airport.setICAO((String) result.getProperty("icao"));
	        airport.setName((String) result.getProperty("name"));

	        airports.add(airport);
	        CacheUtil.addToCache(airport.getIATA(), result);
	    }

		return airports;
	}

	public List<Route> queryRoutes(String dept, String dest) {
		DatastoreService datastore =  DatastoreServiceFactory.getDatastoreService();

		List<Route> routes = new ArrayList<Route>();

		Query query = new Query("routes")
			.addFilter("sourceairport", FilterOperator.EQUAL, dept)
			.addFilter("destinationairport", FilterOperator.EQUAL, dest);
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			Route route = new Route();

			route.setAirline((String) result.getProperty("airline"));
			route.setDeptAirport((String) result.getProperty("sourceairport"));
			route.setDestAirport((String) result.getProperty("destinationairport"));
			route.setEquipment((String) result.getProperty("equipment"));

			routes.add(route);
			//CacheUtil.addToCache(route.getDeptAirport() + "-" + route.getDestAirport(), route);
	    }

		return routes;
	}
}
