package servlet.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.comments.CommentsRead;

/**
 * Servlet pour afficher les commentaires globaux ou bien ceux d'un utilisateur
 * donné
 * 
 * @author Kevin & Pietro
 * 
 */
public class AfficherComments extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String idS = req.getParameter("id"), login = req
					.getParameter("login");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();

			if (idS != null) {
				int id = Integer.parseInt(idS);
				out.println(CommentsRead.afficherCommentsDe(id));
			} else if (login != null)
				out.println(CommentsRead.afficherCommentsDe(login));
			else
				out.println(CommentsRead.afficherComments());

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
