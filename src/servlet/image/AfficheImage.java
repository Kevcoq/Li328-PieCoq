package servlet.image;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.image.ImageRead;
import servlet.ParametreException;

public class AfficheImage extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), sid = req
					.getParameter("id"), login = req.getParameter("login");
			if (clef == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();

			if (sid != null)
				out.println(ImageRead.afficheImageDe(clef,
						Integer.parseInt(sid)));
			else if (login != null)
				out.println(ImageRead.afficheImageDe(clef, login));
			else
				out.println(ImageRead.afficheImage(clef));

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