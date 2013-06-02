package servlet.post;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.post.PostRead;
import servlet.ParametreException;

/**
 * Servlet d'affichage des messages de login "nom mal choisi"
 * 
 * @author Kevin
 * 
 */
public class AfficherCesStatut extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String login = req.getParameter("login");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();

			if (login == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			out.println(PostRead.afficherCesStatut(login));

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
