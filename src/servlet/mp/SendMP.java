package servlet.mp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.mp.MPSend;
import servlet.ParametreException;

/**
 * Servlet pour envoyer un MP
 * 
 * @author Kevin et Pietro
 * 
 */
public class SendMP extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), message = req
					.getParameter("message"), id_dest_S = req
					.getParameter("id_dest");
			if (clef == null || message == null || id_dest_S == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			int id_dest = Integer.parseInt(id_dest_S);
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(MPSend.sendMp(clef, id_dest, message));

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
