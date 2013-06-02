package bd.comments;

import java.util.Date;

import bd.BDTools;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Boite à outils pour les commentaires
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDCommentsTools {
	private static String bd = "comments";

	/**
	 * 
	 * @param author_id
	 * @param date
	 * @return l'id du commentaire correspondant
	 */
	// Normalement l'id et la date est censé consitué une clef puisque la date
	// est précise
	public static String getObjID(int author_id, Date date) {
		DBObject obj = new BasicDBObject();
		obj.put("author_id", author_id);
		obj.put("date", date);

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);
		String s = c.next().get("_id").toString();
		c.close();
		m.close();
		return s;
	}

	/**
	 * Verifie si le commentaire appartient a id
	 * 
	 * @param id
	 * @param id_message
	 * @return vrai si id est l'auteur
	 */
	public static boolean SonComments(int id, String id_message) {
		DBObject obj = BDTools.ObjectID(id_message);
		obj.put("author_id", id);

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);
		boolean rep = c.hasNext();
		c.close();
		m.close();
		if (rep)
			return true;
		return false;
	}

}
