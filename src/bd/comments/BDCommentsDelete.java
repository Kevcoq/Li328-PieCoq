package bd.comments;

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
 * Gere la suppresion des commentaires au niveau BD
 * 
 * @author Kevin & Pieteo
 * 
 */
public class BDCommentsDelete {
	private static String bd = "comments";

	/**
	 * Suppimer les commentaires de clef
	 * 
	 * @param clef
	 * @throws BDException
	 */
	public static void supprimerCesComments(String clef) throws BDException {
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
			supprimerComments(o.get("_id").toString());
		}
		cursor.close();
		m.close();
	}

	/**
	 * Supprimer le commentaire id_message
	 * 
	 * @param id_comments
	 */
	public static void supprimerComments(String id_comments) {
		// Suppresion dans la bd comments
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = BDTools.findOne(coll, id_comments);
		coll.remove(obj);

		// Suppresion de la reference dans le tweet pere
		DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
		DBObject tweet = BDTools.findOne(coll_tweet, obj.get("id_tweet")
				.toString());

		String lesCommments = tweet.get("comments").toString(), nouv = "";
		String[] boucle = lesCommments.split(",");
		for (int i = 0; i < boucle.length; i++)
			if (!boucle[i].equals(id_comments))
				if (nouv.equals(""))
					nouv += boucle[i];
				else
					nouv += "," + boucle[i];

		tweet.put("comments", nouv);
		coll_tweet.findAndModify(
				BDTools.findOne(coll_tweet, obj.get("id_tweet").toString()),
				tweet);
		m.close();
	}

	/**
	 * Supprime tout les commentaires associé au tweet
	 * 
	 * @param id_tweet
	 */
	public static void supprimerCommentsDuTWEET(String id_tweet) {
		Mongo m = DataBase.getMongo();
		DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
		DBObject tweet = BDTools.findOne(coll_tweet, id_tweet);

		// Suppresion dans la BD comments
		if (!tweet.containsField("comments")) {
			m.close();
			return;
		}
		String id_Commments = tweet.get("comments").toString();
		String[] boucle = id_Commments.split(",");
		for (int i = 0; i < boucle.length; i++)
			supprimerComments(boucle[i]);

		// MAJ dans le tweet
		tweet.put("comments", "");
		coll_tweet.findAndModify(BDTools.findOne(coll_tweet, id_tweet), tweet);

		m.close();
	}

}
