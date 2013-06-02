package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.user.UserAuthentification;
import servlet.ParametreException;

/**
 * Servlet de login
 * 
 * @author Kevin et Pietro
 * 
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String login = req.getParameter("login"), mdp = req
					.getParameter("mdp");
			if (login == null || mdp == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.print(UserAuthentification.login(login, mdp));
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			rep.setContentType("text/plain");
			PrintWriter out;
			try {
				out = rep.getWriter();
				out.print(e.getMessage());
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
