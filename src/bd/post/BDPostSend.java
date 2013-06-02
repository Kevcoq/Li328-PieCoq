package bd.post;

import java.util.Date;
import java.util.GregorianCalendar;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Contient les operations pour la BD pour poster un message
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDPostSend {
	private static String bd = "tweet";

	/**
	 * Post un message dans la BD
	 * 
	 * @param clef
	 * @param message
	 * @throws BDException
	 */
	public static void posterStatut(String clef, String message)
			throws BDException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = new BasicDBObject();

		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("text", message);

		coll.insert(obj);
		m.close();
	}

	/**
	 * Post un message dans la BD
	 * 
	 * @param clef
	 * @param message
	 * @param image_id
	 * @throws BDException
	 */
	public static void posterStatut(String clef, String message, String image_id)
			throws BDException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = new BasicDBObject();

		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("text", message);
		obj.put("photo", image_id);

		coll.insert(obj);
		m.close();
	}

	public static void ajouterPhoto(String id_message, String id_photo) {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject o_message = BDTools.findOne(coll, id_message);
		o_message.put("photo", id_photo);

		coll.findAndModify(BDTools.findOne(coll, id_message), o_message);
		m.close();
	}
}
