package bd.image;

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
 * Contient les operations pour la BD pour poster une image
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDImageSend {
	private static String bd = "image";

	/**
	 * Poste une image dans la BD
	 * 
	 * @param clef
	 * @param message
	 * @throws BDException
	 */
	public static void posterImage(String clef, String url, byte[] bytes)
			throws BDException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = new BasicDBObject();

		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("url", url);
		obj.put("image", bytes);

		coll.insert(obj);
		m.close();
	}
}