package bd.admin;

import bd.DataBase;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;

/**
 * Outils administrateur niveau BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDAdmin {
	private static String bd = "tweet,image,comments,mp,like";

	/**
	 * Supprime toutes les MongoDB
	 */
	public static void viderMongoDB() {
		String[] tmp = bd.split(",");
		Mongo m = DataBase.getMongo();
		for (int i = 0; i < tmp.length; i++) {
			DBCollection coll = DataBase.getMongoCollection(m, tmp[i]);
			coll.drop();
		}
		m.close();
	}
}
