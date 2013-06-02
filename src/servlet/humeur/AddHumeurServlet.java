package servlet.humeur;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.humeur.HumeurAddHumeur;
import servlet.ParametreException;

/**
 * Servlet d'ajout d'humeur
 * 
 * @author Kevin
 * 
 */
public class AddHumeurServlet extends HttpServlet {

	/**
         * 
         */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String login = req.getParameter("login"), humeur = req
					.getParameter("humeur");
			if (login == null || humeur == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(HumeurAddHumeur.ajoutHumeur(login, humeur));

		} catch (Exception e) {
			e.printStackTrace();
			rep.setContentType("text/plain");
			PrintWriter out;
			try {
				out = rep.getWriter();
				out.println(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
