package bd.post;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.comments.BDCommentsDelete;
import bd.like.BDLikeDelete;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Supprime les statuts de la base
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDPostDelete {
	private static String bd = "tweet";

	/**
	 * Suppimer les statuts de clef
	 * 
	 * @param clef
	 * @throws BDException
	 */
	public static void supprimerCesStatut(String clef) throws BDException {
		int id = BDUserTools.getId(clef);
		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor cursor = coll.find(obj);

		// Suppresion des commentaires associé
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			if (o.containsField("comments") && !o.get("comments").equals(""))
				BDCommentsDelete.supprimerCommentsDuTWEET(o.get("_id")
						.toString());
			if (o.containsField("like") && !o.get("like").equals(""))
				BDLikeDelete.supprimerLikeDuTWEET(o.get("_id").toString());
			coll.remove(o);
		}
		cursor.close();
		m.close();
	}

	/**
	 * Supprimer le statut id_message
	 * 
	 * @param id_message
	 */
	public static void supprimerStatut(String id_message) {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject o = BDTools.findOne(coll, id_message);

		// Supression des commentaires
		if (o.containsField("comments") && !o.get("comments").equals(""))
			BDCommentsDelete.supprimerCommentsDuTWEET(id_message);
		if (o.containsField("like") && !o.get("like").equals(""))
			BDLikeDelete.supprimerLikeDuTWEET(id_message);

		coll.remove(o);
		m.close();
	}

}
