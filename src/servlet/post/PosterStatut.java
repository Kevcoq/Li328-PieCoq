package servlet.post;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.post.PostSend;
import servlet.ParametreException;

/**
 * Servlet pour poster un message
 * 
 * @author Kevin et Pietro
 * 
 */
public class PosterStatut extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), message = req
					.getParameter("message"), url = req.getParameter("url");
			if (clef == null )
				throw new ParametreException("Le parametre clef n'est pas affecte");
			if(message == null)
				throw new ParametreException("Le parametre message n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			if (url != null)
				out.println(PostSend.postStatut(clef, message, url));
			else {
				out.println(PostSend.postStatut(clef, message));
			}

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
