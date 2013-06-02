package bd.comments;

import org.json.JSONArray;
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
 * Gere la lecture des commentaire cote BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDCommentsRead {
	private static String bd = "comments";

	/**
	 * Affiche tous les commentaires
	 * 
	 * @return Tout les commentaires existant
	 * @throws JSONException
	 */
	public static JSONArray afficherComments() throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find();

		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			js.put(new JSONObject(c.next().toString()));
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche les commentaires d'id
	 * 
	 * @param id
	 * @return Tout les commentaires d'id
	 * @throws JSONException
	 */
	public static JSONArray afficherCommentsDe(int id) throws JSONException {
		Mongo m = DataBase.getMongo();
		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

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

	/**
	 * Affiche les commentaires de login
	 * 
	 * @param login
	 * @return Tous les commentaires de login
	 * @throws JSONException
	 */
	public static JSONArray afficherCommentsDe(String login)
			throws JSONException {
		Mongo m = DataBase.getMongo();
		DBObject obj = new BasicDBObject();
		obj.put("author_login", login);

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

	/**
	 * Lit le commentaire id_message
	 * 
	 * @param id_message
	 * @return un JSON du commentaire
	 * @throws JSONException
	 */
	public static JSONObject lireComments(String id_message)
			throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		JSONObject json = new JSONObject(BDTools.findOne(coll, id_message)
				.toString());
		m.close();
		return json;
	}

	/**
	 * Lit les commentaire du tweet
	 * 
	 * @param id_tweet
	 *            l'id du tweet
	 * @return un JSON contenant les commentairs du tweet
	 */
	public static Object lisCommentsDuTWEET(String id_tweet) {
		try {
			Mongo m = DataBase.getMongo();
			DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
			DBObject obj = BDTools.findOne(coll_tweet, id_tweet);

			// On verifie que le tweet à des commentaires
			if (!obj.containsField("comments"))
				return ServicesTools.JSONerreur(
						"Pas de commentaires pour ce tweet", 5);

			String id_Commments = obj.get("comments").toString();
			String[] boucle = id_Commments.split(",");

			JSONArray js = new JSONArray();

			for (int i = 0; i < boucle.length; i++) {
				js.put(lireComments(boucle[i]));
			}
			m.close();
			return js;
		} catch (JSONException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
