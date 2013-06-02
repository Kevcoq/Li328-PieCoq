package bd.like;

import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.DataBase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Gere la lecture des likes cote BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDLikeRead {
	private static String bd = "like";

	/**
	 * Affiche tous les likes
	 * 
	 * @return Tout les likes existant
	 * @throws JSONException
	 */
	public static JSONObject afficherLike() throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find();

		JSONObject js = new JSONObject();
		int i = 0, j = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			if (o.get("like").toString().equals("+1"))
				js.put("like " + ++i, new JSONObject(o.toString()));
			else
				js.put("unlike " + ++j, new JSONObject(o.toString()));
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche les likes d'id
	 * 
	 * @param id
	 * @return Tout les likes d'id
	 * @throws JSONException
	 */
	public static JSONObject afficherLikeDe(int id) throws JSONException {
		Mongo m = DataBase.getMongo();

		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0, j = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			if (o.get("like").toString().equals("+1"))
				js.put("like " + ++i, new JSONObject(o.toString()));
			else
				js.put("unlike " + ++j, new JSONObject(o.toString()));
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche les likes de login
	 * 
	 * @param login
	 * @return Tous les likes de login
	 * @throws JSONException
	 */
	public static JSONObject afficherLikeDe(String login) throws JSONException {
		Mongo m = DataBase.getMongo();

		DBObject obj = new BasicDBObject();
		obj.put("author_login", login);

		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);

		JSONObject js = new JSONObject();
		int i = 0, j = 0;
		while (c.hasNext()) {
			DBObject o = c.next();
			if (o.get("like").toString().equals("+1"))
				js.put("like " + ++i, new JSONObject(o.toString()));
			else
				js.put("unlike " + ++j, new JSONObject(o.toString()));
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Lit le like id_like
	 * 
	 * @param id_like
	 * @return un JSON du like
	 * @throws JSONException
	 */
	public static JSONObject lireLike(String id_like) throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		JSONObject json = new JSONObject(BDTools.findOne(coll, id_like)
				.toString());
		m.close();
		return json;
	}

	/**
	 * Lit les likes du tweet
	 * 
	 * @param id_tweet
	 *            l'id du tweet
	 * @return un JSON contenant les likes du tweet
	 */
	public static JSONObject lisLikeDuTWEET(String id_tweet) {
		try {
			Mongo m = DataBase.getMongo();
			DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
			DBObject obj = BDTools.findOne(coll_tweet, id_tweet);

			// On verifie que le tweet à des likes
			if (!obj.containsField("like"))
				return ServicesTools.JSONerreur("Pas de like pour ce tweet", 5);

			String id_Likes = obj.get("like").toString();
			String[] boucle = id_Likes.split(",");

			JSONObject js = new JSONObject();

			for (int i = 0; i < boucle.length; i++) {
				js.put("like/unlike " + i, lireLike(boucle[i]));
			}
			m.close();
			return js;
		} catch (JSONException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
