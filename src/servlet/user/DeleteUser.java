package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.user.UserCreateUser;
import servlet.ParametreException;

/**
 * Servlet supprimant un utilisateur
 * @author Kevin & Pietro
 *
 */
public class DeleteUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), login = req
					.getParameter("login"), mdp = req.getParameter("mdp");
			if (clef == null || login == null || mdp == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(UserCreateUser.supprimerUtilisateur(clef, login, mdp));
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
