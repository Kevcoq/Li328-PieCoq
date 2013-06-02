package bd.mp;

import java.util.Date;
import java.util.GregorianCalendar;

import bd.BDException;
import bd.DataBase;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Gere cote BD l'envoie de MP
 * 
 * @author Kevin &Pietro
 * 
 */
public class BDMPSend {
	private static String bd = "mp";

	/**
	 * Creer le mp dans la BD
	 * 
	 * @param clef
	 *            sa clef
	 * @param id_dest
	 * @param message
	 * @throws BDException
	 */
	public static void sendMP(String clef, int id_dest, String message)
			throws BDException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = new BasicDBObject();

		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("id_dest", id_dest);
		obj.put("date", date);
		obj.put("statut", "Non lu");
		obj.put("text", message);

		coll.insert(obj);
		m.close();
	}
}
