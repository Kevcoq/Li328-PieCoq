package bd.like;

import java.util.Date;
import java.util.GregorianCalendar;

import bd.BDException;
import bd.BDTools;
import bd.DataBase;
import bd.user.BDUserTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Gere l'envoie de commentaire à la bd
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDLikeSend {
	private static String bd = "like";

	/**
	 * Ajoute un like à un message donné
	 * 
	 * @param clef
	 * @param id_tweet
	 * @throws BDException
	 */
	public static void ajouterLike(String clef, String id_tweet)
			throws BDException {
		// Ajout du commentaire dans la base comments
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = new BasicDBObject();

		obj.put("id_tweet", id_tweet);
		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("like", "+1");

		coll.insert(obj);

		// ***
		// ***
		// Ajout de l'id du commentaire au message pere
		String id_like = BDLikeTools.getObjID(BDUserTools.getId(clef), date);

		DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
		DBObject o_tweet = BDTools.findOne(coll_tweet, id_tweet);
		if (o_tweet.containsField("like")
				&& !o_tweet.get("like").toString().equals(""))
			o_tweet.put("like", o_tweet.get("like").toString() + "," + id_like);
		else
			o_tweet.put("like", id_like);

		coll_tweet
				.findAndModify(BDTools.findOne(coll_tweet, id_tweet), o_tweet);
		m.close();
	}

	/**
	 * Ajoute un unlike à un message donné
	 * 
	 * @param clef
	 * @param id_tweet
	 * @throws BDException
	 */
	public static void ajouterUnlike(String clef, String id_tweet)
			throws BDException {
		// Ajout du commentaire dans la base comments
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBObject obj = new BasicDBObject();

		obj.put("id_tweet", id_tweet);
		obj.put("author_id", BDUserTools.getId(clef));
		obj.put("author_login", BDUserTools.getLogin(clef));
		obj.put("date", date);
		obj.put("like", "-1");

		coll.insert(obj);

		// ***
		// ***
		// Ajout de l'id du commentaire au message pere
		String id_like = BDLikeTools.getObjID(BDUserTools.getId(clef), date);

		DBCollection coll_tweet = DataBase.getMongoCollection(m, "tweet");
		DBObject o_tweet = BDTools.findOne(coll_tweet, id_tweet);
		if (o_tweet.containsField("like"))
			o_tweet.put("like", o_tweet.get("like").toString() + "," + id_like);
		else
			o_tweet.put("like", id_like);

		coll_tweet
				.findAndModify(BDTools.findOne(coll_tweet, id_tweet), o_tweet);
		m.close();
	}
}
