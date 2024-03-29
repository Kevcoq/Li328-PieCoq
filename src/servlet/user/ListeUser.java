package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.ServicesTools;
import services.user.UserListe;

/**
 * Servlet implementation class ListeUser
 */
public class ListeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeUser() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse rep)
			throws ServletException, IOException {

		String clef = request.getParameter("clef");

		rep.setContentType("text/plain");
		PrintWriter out = rep.getWriter();

		try {
			if (clef == null)
				out.print(UserListe.liste(null));
			else
				out.print(UserListe.liste(clef));
		} catch (JSONException e) {
			e.printStackTrace();
			out.print(ServicesTools.JSONBDerreur(e.getMessage()));
		}
		out.flush();
	}
}
