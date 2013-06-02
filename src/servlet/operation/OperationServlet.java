package servlet.operation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.operation.Operation;
import servlet.ParametreException;

/**
 * Servlet pour faire une Operation
 * 
 * @author Kevin et Pietro
 * 
 */
public class OperationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse rep) {
		try {
			String as = req.getParameter("a"), bs = req.getParameter("b"), ops = req
					.getParameter("op");
			if (as == null || bs == null || ops == null)
				throw new ParametreException("Un parametre n'est pas affecte");

			Double a = Double.parseDouble(as), b = Double.parseDouble(bs);
			Operation o = new Operation(a, b, Operation.convert(ops));

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(a + " " + ops + " " + b + " = " + o.realiser());

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
