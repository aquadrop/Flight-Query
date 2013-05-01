package ece1779.appengine.datastore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class LoadDatabase extends HttpServlet {

	private static final long serialVersionUID = 6674820159083357865L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String table = request.getParameter("table");

		try {
			PrintWriter out = response.getWriter();

			if (table.equals("airlines")) {
				loadAirlines();
				out.println("Airline Data Imported!");
			} else if (table.equals("airports")) {
				loadAirports();
				out.println("Airport Data Imported!");
			} else if (table.equals("routes")) {
				loadRoutes();
				out.println("Route Data Imported!");
			} else {
				out.println("Table not found!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadAirlines() {
		String[] entries = { "airlineid", "name", "alias", "iata", "icao", "callassign", "country", "active" };

		String NullString = "\\N";

		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

			//URL airporturl = new URL("http://openflights.svn.sourceforge.net/viewvc/openflights/openflights/data/airlines.dat");
			URL airporturl = new URL("http://leejefon.com/openflights/airlines.csv");

			BufferedReader br = new BufferedReader(new InputStreamReader(airporturl.openStream()));

			String line;
			String container;
			while ((line = br.readLine()) != null) {
				Entity airline = new Entity("airlines");

				List<String> items = Arrays.asList(line.split(","));
				for (int i = 0; i < items.size(); i++) {
					container = items.get(i).replaceAll("^\"|\"$", "");
					if (container.equals(NullString))
						container = "";
					airline.setProperty(entries[i], container);
				}
				datastore.put(airline);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadAirports() {
		String[] entries = { "airportid", "name", "city", "country", "iata",
				"icao", "latitude", "longitude", "altitude", "timezone", "dst" };

		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

			//URL airporturl = new URL("http://openflights.svn.sourceforge.net/viewvc/openflights/openflights/data/airports.dat");
			URL airporturl = new URL("http://leejefon.com/openflights/airports.csv");

			BufferedReader br = new BufferedReader(new InputStreamReader(airporturl.openStream()));

			String line;
			String container;
			while ((line = br.readLine()) != null) {
				Entity airport = new Entity("airports");

				List<String> items = Arrays.asList(line.split(","));
				for (int i = 0; i < items.size(); i++) {
					container = items.get(i).replaceAll("^\"|\"$", "");
					airport.setProperty(entries[i], container);
				}
				datastore.put(airport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadRoutes() {
		String[] entries = { "airline", "airlineid", "sourceairport", "sourceairportid",
				"destinationairport", "destinationairportid", "codeshare", "stops", "equipment" };

		String NullString = "\\N";

		try {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

			//URL airporturl = new URL("http://openflights.svn.sourceforge.net/viewvc/openflights/openflights/data/routes.dat");
			URL airporturl = new URL("http://leejefon.com/openflights/routes.csv");

			BufferedReader br = new BufferedReader(new InputStreamReader(airporturl.openStream()));

			String line;
			String container;
			while ((line = br.readLine()) != null) {
				Entity routes = new Entity("routes");
				List<String> items = Arrays.asList(line.split(","));

				for (int i = 0; i < items.size(); i++) {
					container = items.get(i).replaceAll("^\"|\"$", "");
					if (container.equals(NullString))
						container = "";
					routes.setProperty(entries[i], container);
				}
				datastore.put(routes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
