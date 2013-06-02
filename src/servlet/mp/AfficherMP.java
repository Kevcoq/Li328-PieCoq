package servlet.mp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.mp.MPRead;
import servlet.ParametreException;

/**
 * Servlet d'affichage des mp
 * 
 * @author Kevin et Pietro
 * 
 */
public class AfficherMP extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), non_lu = req
					.getParameter("non_lu");

			if (clef == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();

			if (non_lu != null && non_lu.equals("true"))
				out.println(MPRead.afficherMp(clef, true));
			else
				out.println(MPRead.afficherMp(clef, false));

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
