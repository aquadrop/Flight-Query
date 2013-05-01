package ece1779.appengine.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
import ece1779.appengine.models.Airline;
import ece1779.appengine.models.Airport;

public class Details extends HttpServlet {

	private static final long serialVersionUID = 8067286395342650514L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String iata = request.getParameter("iata");

		if (type.equals("airline")) {
			request.setAttribute("airline", queryAirline(iata));
		} else if (type.equals("airport")) {
			request.setAttribute("airport", queryAirport(iata));
		}

		try {
			RequestDispatcher view = request.getRequestDispatcher("details.jsp");
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Airline queryAirline(String iata) {
		DatastoreService datastore =  DatastoreServiceFactory.getDatastoreService();

		Entity entity = (Entity) CacheUtil.getFromCache(iata);

		if (entity != null) {
			return new Airline(entity);
		}

		Query query = new Query("airlines").addFilter("iata", FilterOperator.EQUAL, iata);
		PreparedQuery pq = datastore.prepare(query);
		Entity result = pq.asSingleEntity();

		Airline airline = new Airline();
		airline.setName((String) result.getProperty("name"));
		airline.setCountry((String) result.getProperty("country"));
		airline.setCallAssign((String) result.getProperty("callAssign"));
		airline.setIATA((String) result.getProperty("iata"));
		airline.setICAO((String) result.getProperty("icao"));

		return airline;
	}

	public Airport queryAirport(String iata) {
		DatastoreService datastore =  DatastoreServiceFactory.getDatastoreService();

		Entity entity = (Entity) CacheUtil.getFromCache(iata);

		if (entity != null) {
			return new Airport(entity);
		}

		Query query = new Query("airports").addFilter("iata", FilterOperator.EQUAL, iata);
		PreparedQuery pq = datastore.prepare(query);
		Entity result = pq.asSingleEntity();

		Airport airport = new Airport();
		airport.setName((String) result.getProperty("name"));
		airport.setCity((String) result.getProperty("city"));
		airport.setCountry((String) result.getProperty("country"));
		airport.setIATA((String) result.getProperty("iata"));
		airport.setICAO((String) result.getProperty("icao"));

		return airport;
	}
}
