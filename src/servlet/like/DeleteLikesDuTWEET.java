package servlet.like;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.like.LikeDelete;
import servlet.ParametreException;

/**
 * Servlet supprimant les commentaites associé à un tweet
 * 
 * @author Kevin & Pietro
 * 
 */
public class DeleteLikesDuTWEET extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), id_obj = req
					.getParameter("id_tweet");
			if (clef == null || id_obj == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();

			out.println(LikeDelete.supprimerLikeDuTWEET(clef, id_obj));

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
