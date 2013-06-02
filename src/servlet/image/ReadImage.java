package servlet.image;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.image.ImageRead;
import servlet.ParametreException;

public class ReadImage extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String clef = req.getParameter("clef"), id_obj = req
					.getParameter("id_obj");
			if (clef == null || id_obj == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			Object o = ImageRead.lireImage(clef, id_obj);
			if (!(o instanceof JSONObject)) {

				byte[] bytes = (byte[]) o;
				rep.setContentType("image/jpeg");
				rep.getOutputStream().write(bytes);

			} else {
				JSONObject json = (JSONObject) o;
				rep.setContentType("text/plain");
				rep.getWriter().println(json);
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