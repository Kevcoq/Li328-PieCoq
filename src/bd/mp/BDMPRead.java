package bd.mp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Gere cote BD la lecture et l'affichage de MP
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDMPRead {
	private static String bd = "mp";

	/**
	 * Renvoie le mp desire
	 * 
	 * @param clef
	 * @param id_mp
	 * @return Un JSON contenant le mp d'_id id_mp
	 * @throws JSONException
	 */
	public static JSONObject lireMP(String clef, String id_message)
			throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = BDTools.findOne(coll, id_message);
		obj.put("statut", "Lu");
		coll.findAndModify(BDTools.findOne(coll, id_message), obj);
		m.close();
		return new JSONObject(obj.toString());
	}

	/**
	 * Affiche les mp recus
	 * 
	 * @param clef
	 * @param non_lu
	 *            Si vrai on affiche que les messages non lu
	 * @return Tous ces mp (ou tous ces mp non lu)
	 * @throws JSONException
	 * @throws BDException
	 */
	public static JSONArray afficherMP(String clef, boolean non_lu)
			throws JSONException, BDException {
		DBObject obj = new BasicDBObject();
		obj.put("author_id", BDUserTools.getId(clef));
		if (non_lu)
			obj.put("statut", "Non lu");

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);

		JSONArray tab = new JSONArray();
		while (c.hasNext()) {
			tab.put(new JSONObject(c.next().toString()));
		}
		c.close();
		m.close();
		return tab;
	}

	public static JSONArray listeMP(String clef) throws BDException,
			JSONException {
		DBObject obj = new BasicDBObject();
		obj.put("id_dest", BDUserTools.getId(clef));

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);

		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			js.put(new JSONObject(c.next().toString()));
		}
		c.close();
		m.close();
		return js;
	}
}