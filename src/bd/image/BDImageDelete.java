package bd.image;

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
 * Supprime les images dans la BD
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDImageDelete {
	private static String bd = "image";

	/**
	 * Supprime l'image id_image
	 * 
	 * @param id_image
	 */
	public static void supprimerImage(String id_image) {
		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		coll.remove(BDTools.findOne(coll, id_image));

		// MAJ de la BD tweet
		DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
		DBObject obj = new BasicDBObject();
		obj.put("photo", id_image);

		DBCursor cursor = coll_tweet.find(obj);
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			DBObject modif = cursor.curr();
			modif.removeField("photo");
			coll_tweet.findAndModify(o, modif);
		}
		cursor.close();
		m.close();
	}

	/**
	 * Supprime les images de clef
	 * 
	 * @param clef
	 * @throws BDException
	 */
	public static void supprimerCesImages(String clef) throws BDException {
		int id = BDUserTools.getId(clef);
		DBObject obj = new BasicDBObject();
		obj.put("author_id", id);

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor curs = coll.find(obj);

		while (curs.hasNext()) {
			DBObject image = curs.next();

			// MAJ de la BD tweet
			DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
			DBObject o_tweet = new BasicDBObject();
			o_tweet.put("photo", image.get("_id").toString());

			DBCursor cursor = coll_tweet.find(o_tweet);
			while (cursor.hasNext()) {
				DBObject o = cursor.next();
				DBObject modif = cursor.curr();
				modif.removeField("photo");
				coll_tweet.findAndModify(o, modif);
			}
			coll.remove(image);
		}
		curs.close();
		m.close();
	}

}