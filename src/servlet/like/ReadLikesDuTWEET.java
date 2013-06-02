package servlet.like;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.like.LikeRead;
import servlet.ParametreException;

/**
 * Servlet pour la lecture des commentaires d'un TWEET
 * 
 * @author Kevin & Pietro
 * 
 */
public class ReadLikesDuTWEET extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), id_tweet = req
					.getParameter("id_tweet");
			if (clef == null || id_tweet == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(LikeRead.lisLikeDuTWEET(clef, id_tweet));

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