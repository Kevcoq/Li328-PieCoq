package bd.image;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDTools;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Contient les operations pour la BD pour poster une image
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDImageRead {

	private static String bd = "image";

	/**
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON contenant le binaire de l'image
	 * @throws JSONException
	 */
	public static byte[] lireImage(String clef, String id_obj)
			throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(BDTools.findOne(coll, id_obj));
		DBObject obj = c.next();
		c.close();
		m.close();
		return ((byte[]) (obj.get("image")));
	}

	/**
	 * Affiche toutes les images
	 * 
	 * @return Tout les statuts existant
	 * @throws JSONException
	 */
	public static JSONObject afficherImage() throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find();

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			// o.put("image", "");
			js.put("image " + i, new JSONObject(o.toString()));
			i++;
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche toutes les images de id
	 * 
	 * @param id
	 * @return Tout les statuts existant
	 * @throws JSONException
	 */
	public static JSONObject afficherImageDe(int id) throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);

		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			o.put("image", "");
			js.put("image " + i, new JSONObject(o.toString()));
			i++;
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche toutes les images de login
	 * 
	 * @param login
	 * @return Tout les statuts existant
	 * @throws JSONException
	 */
	public static JSONObject afficherImageDe(String login) throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);

		DBObject obj = new BasicDBObject();
		obj.put("author_login", login);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			o.put("image", "");
			js.put("image " + i, new JSONObject(o.toString()));
			i++;
		}
		c.close();
		m.close();
		return js;
	}

}
