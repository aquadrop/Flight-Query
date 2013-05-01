package ece1779.appengine.datastore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class ClearDatabase extends HttpServlet {

	private static final long serialVersionUID = 4526326820715860280L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String table = request.getParameter("table");

		try {
			PrintWriter out = response.getWriter();

			if (table.equals("airlines") || table.equals("airports") || table.equals("routes")) {
				clearTable(table);
				out.println("Table " + table + " Cleared!");
			} else {
				out.println("Table " + table + " not found!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearTable(String table) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query(table);
		PreparedQuery pq = datastore.prepare(q);

		for (Entity result : pq.asIterable()) {
			datastore.delete(result.getKey());
		}
	}
}
