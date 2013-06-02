package bd.like;

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
 * Gere la suppresion des like au niveau BD
 * 
 * @author Kevin & Pieteo
 * 
 */
public class BDLikeDelete {
	private static String bd = "like";

	/**
	 * Suppimer les like de clef
	 * 
	 * @param clef
	 * @throws BDException
	 */
	public static void supprimerCesLikes(String clef) throws BDException {
		// Ouverture des collections
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);

		// Creation d'un DBObject avec l'id correspondant à la clef
		int id = BDUserTools.getId(clef);
		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

		// Un curseur sur tout les resultats de la recherche
		DBCursor cursor = coll.find(obj);

		// On supprime en itérant sur le curseur
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			supprimerLike(o.get("_id").toString());
		}
		cursor.close();
		m.close();
	}

	/**
	 * Supprimer le like id_like
	 * 
	 * @param id_like
	 */
	public static void supprimerLike(String id_like) {
		Mongo m = DataBase.getMongo();
		// Suppresion dans la bd like
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = BDTools.findOne(coll, id_like);
		coll.remove(obj);

		// Suppresion de la reference dans le tweet pere
		DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
		DBObject tweet = BDTools.findOne(coll_tweet, obj.get("id_tweet")
				.toString());

		String lesLikes = tweet.get("like").toString(), nouv = "";
		String[] boucle = lesLikes.split(",");
		for (int i = 0; i < boucle.length; i++)
			if (!boucle[i].equals(id_like))
				if (nouv.equals(""))
					nouv += boucle[i];
				else
					nouv += "," + boucle[i];

		tweet.put("like", nouv);
		coll_tweet.findAndModify(
				BDTools.findOne(coll_tweet, obj.get("id_tweet").toString()),
				tweet);
		m.close();
	}

	/**
	 * Supprime tout les like associé au tweet
	 * 
	 * @param id_tweet
	 */
	public static void supprimerLikeDuTWEET(String id_tweet) {
		Mongo m = DataBase.getMongo();
		DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
		DBObject tweet = BDTools.findOne(coll_tweet, id_tweet);

		// Suppresion dans la BD comments
		String id_like = tweet.get("like").toString();
		String[] boucle = id_like.split(",");
		for (int i = 0; i < boucle.length; i++)
			supprimerLike(boucle[i]);

		// MAJ dans le tweet
		tweet.put("like", "");
		coll_tweet.findAndModify(BDTools.findOne(coll_tweet, id_tweet), tweet);

		m.close();
	}
}
