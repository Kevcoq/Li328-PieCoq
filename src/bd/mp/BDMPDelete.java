package bd.mp;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class BDMPDelete {
	private static String bd = "mp";

	/**
	 * Supprime le mp id_message
	 * 
	 * @param id_message
	 */
	public static void supprimerMP(String id_message) {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		coll.remove(BDTools.findOne(coll, id_message));
		m.close();
	}

	/**
	 * Supprime les mp de clef
	 * 
	 * @param clef
	 * @throws BDException
	 */
	public static void supprimerCesMP(String clef) throws BDException {
		int id = BDUserTools.getId(clef);
		DBObject obj = new BasicDBObject();
		obj.put("id_dest", id);

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		coll.remove(obj);
		m.close();
	}

}