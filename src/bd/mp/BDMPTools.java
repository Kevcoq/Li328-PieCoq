package bd.mp;

import bd.BDTools;
import bd.DataBase;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Gestion mp cote BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDMPTools {
	private static String bd = "mp";

	/**
	 * Verifie si le mp appartient a id
	 * 
	 * @param id
	 * @param id_message
	 * @return vrai si id est le destinataire
	 */
	public static boolean SonMP(int id, String id_message) {
		DBObject obj = BDTools.ObjectID(id_message);
		obj.put("id_dest", id);

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);
		boolean rep = c.hasNext();
		c.close();
		m.close();
		return rep;
	}
}
