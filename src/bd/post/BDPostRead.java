package bd.post;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.like.BDLikeTools;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Contient les op�rations pour la BD pour lire un/des message
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDPostRead {
	private static String bd = "tweet";

	/**
	 * Service search
	 * 
	 * @param recherche
	 * @return
	 * @throws JSONException
	 * @throws SQLException
	 */
	public static JSONArray Search(String recherche) throws JSONException,
			SQLException {
		// Ouverture des bases
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCollection coll_search = DataBase.getMongoCollection(m, "search");
		// Suppresion de l'ancien contenu dans search
		coll_search.drop();

		// Obtention de la collection de base trié par date
		DBObject trie_date = new BasicDBObject("$natural", -1);
		DBCursor c = coll.find().sort(trie_date);

		// Pour chaque tweet on calcule son score et on l'insert dans la base
		// search
		while (c.hasNext()) {
			DBObject o = c.next();
			o.put("score",
					BDPostTools.getScore(recherche, o.get("_id").toString()));
			coll_search.insert(o);
		}

		// On trie par score ce coup si
		DBObject trie_score = new BasicDBObject("score", -1);
		c = coll_search.find().sort(trie_score);

		// On introduit par ordre de score les tweet dans le js de reponse
		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			DBObject o = c.next();
			// Si le score est à 0, on le passe
			if (Double.parseDouble(o.get("score").toString()) > 0) {
				int[] like = BDLikeTools.count_like(o.get("_id").toString());

				JSONObject tmp = new JSONObject(o.toString());
				tmp.put("nb_like", like[0]);
				tmp.put("nb_unlike", like[1]);
				js.put(tmp);
			}
		}

		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche tous les statuts
	 * 
	 * @return Tout les statuts existant
	 * @throws JSONException
	 */
	public static JSONArray afficherStatut() throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject trie = new BasicDBObject("$natural", -1);
		DBCursor c = coll.find().sort(trie);

		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			DBObject o = c.next();
			int[] like = BDLikeTools.count_like(o.get("_id").toString());

			JSONObject tmp = new JSONObject(o.toString());
			tmp.put("nb_like", like[0]);
			tmp.put("nb_unlike", like[1]);
			js.put(tmp);
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche les statuts des amis
	 * 
	 * @param clef
	 * @return
	 * @throws JSONException
	 * @throws BDException
	 */
	public static JSONArray afficherStatutAmi(String clef)
			throws JSONException, BDException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject trie = new BasicDBObject("$natural", -1);
		DBCursor c = coll.find().sort(trie);

		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			DBObject o = c.next();
			int auteur_id = (int) o.get("author_id");
			if (BDUserTools.checkAmi(clef, auteur_id)
					|| BDUserTools.getId(clef) == auteur_id) {
				int[] like = BDLikeTools.count_like(o.get("_id").toString());

				JSONObject tmp = new JSONObject(o.toString());
				tmp.put("nb_like", like[0]);
				tmp.put("nb_unlike", like[1]);
				js.put(tmp);
			}
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche les statuts d'id
	 * 
	 * @param id
	 * @return Tout les statuts d'id
	 * @throws JSONException
	 */
	public static JSONArray afficherStatutDe(int id) throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);

		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);
		DBCursor c = coll.find(obj);

		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			DBObject o = c.next();
			int[] like = BDLikeTools.count_like(((JSONObject) o.get("_id"))
					.get("$oid").toString());

			JSONObject tmp = new JSONObject(o.toString());
			tmp.put("nb_like", like[0]);
			tmp.put("nb_unlike", like[1]);
			js.put(tmp);
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Affiche les statuts de login
	 * 
	 * @param login
	 * @return Tous les statuts de login
	 * @throws JSONException
	 */
	public static JSONArray afficherStatutDe(String login) throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);

		DBObject obj = new BasicDBObject();
		obj.put("author_login", login);
		DBCursor c = coll.find(obj);

		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			DBObject o = c.next();
			int[] like = BDLikeTools.count_like(((JSONObject) o.get("_id"))
					.get("$oid").toString());

			JSONObject tmp = new JSONObject(o.toString());
			tmp.put("nb_like", like[0]);
			tmp.put("nb_unlike", like[1]);
			js.put(tmp);
		}
		c.close();
		m.close();
		return js;
	}

	/**
	 * Lit le statut id_message
	 * 
	 * @param id_message
	 * @return un JSON dut message
	 * @throws JSONException
	 */
	public static JSONObject lireStatut(String id_message) throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		JSONObject json = new JSONObject(BDTools.findOne(coll, id_message)
				.toString());
		m.close();
		return json;
	}

	/**
	 * Affiche les statuts de login
	 * 
	 * @param login
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray afficherCesStatut(String login)
			throws JSONException {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject trie = new BasicDBObject("$natural", -1);

		DBObject obj = new BasicDBObject();
		obj.put("author_login", login);

		DBCursor c = coll.find(obj).sort(trie);

		JSONArray js = new JSONArray();
		while (c.hasNext()) {
			DBObject o = c.next();
			int[] like = BDLikeTools.count_like(o.get("_id").toString());

			JSONObject tmp = new JSONObject(o.toString());
			tmp.put("nb_like", like[0]);
			tmp.put("nb_unlike", like[1]);
			js.put(tmp);
		}
		c.close();
		m.close();
		return js;
	}
}
