package ece1779.appengine.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Welcome extends HttpServlet {

	private static final long serialVersionUID = -6085045202556946766L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			if (request.getUserPrincipal() != null) {
				RequestDispatcher view = request.getRequestDispatcher("searchFlight.jsp");
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
