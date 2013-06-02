package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.BDException;
import bd.DataBase;
import bd.admin.BDMapReduce;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;

/**
 * Servlet implementation class MapReduce
 */
public class MapReduce extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String mapdf = "function mapdf() {var text = this.text;var mots = text.match(/\\w+/g);if (mots == null)return;var df = [];for ( var i = 0; i < mots.length; i++) {m =\"\\\"\" + mots[i] + \"\\\"\";df[m] = 1;}for ( var mot in df)emit(mot, {df : 1});}";
	private static String reducedf = "function reducedf(key, values) {var total = 0;for ( var i = 0; i < values.length; i++)total += values[i].df;return {df : total};}";

	private static String maptf = "function maptf() {var mots = this.text.match(/\\w+/g);var tf = [];for ( var i = 0; i < mots.length; i++) {m = \"\\\"\" + mots[i] + \"\\\"\";if (tf[m] != null)tf[m]++;else tf[m] = 1;}for ( var w in tf)emit(w, {document : this._id,tf : tf[w]});}";
	private static String reducetf = "function reducetf(key, values) {return {tfs : values};}";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MapReduce() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse rep)
			throws ServletException, IOException {
		rep.setContentType("text/plain");
		PrintWriter out2 = rep.getWriter();
		try {

			Mongo m = DataBase.getMongo();
			DBCollection coll = DataBase.getMongoCollection(m, "tweet");

			MapReduceOutput out = coll.mapReduce(mapdf, reducedf, null,
					MapReduceCommand.OutputType.INLINE, new BasicDBObject());

			BDMapReduce.viderDF();

			for (DBObject obj : out.results()) {
				BDMapReduce.insertdf(obj);
				out2.println(obj);
			}

			out = coll.mapReduce(maptf, reducetf, null,
					MapReduceCommand.OutputType.INLINE, new BasicDBObject());

			for (DBObject obj : out.results()) {
				out2.println(obj);
				BDMapReduce.inserttf(obj);
			}

			BDMapReduce.finish();

			m.close();
		} catch (BDException e) {
			e.printStackTrace();
			out2.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			out2.println(e.getMessage());
		}
	}

}
