package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.user.UserCreateUser;
import servlet.ParametreException;

/**
 * Servlet de creation d'utilisateur
 * 
 * @author Kevin et Pietro
 * 
 */
public class CreateUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String login = req.getParameter("login"), mdp = req
					.getParameter("mdp"), nom = req.getParameter("nom"), prenom = req
					.getParameter("prenom");
			if (login == null || mdp == null || nom == null || prenom == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(UserCreateUser.creeUtilisateur(login, mdp, nom, prenom));

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
