package bd.post;

import java.sql.SQLException;

import bd.BDTools;
import bd.DataBase;
import bd.admin.BDMapReduce;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * Boite a outils pour BD.Post
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDPostTools {
	private static String bd = "tweet";

	/**
	 * Verifie si le statut appartient a id
	 * 
	 * @param id
	 * @param id_message
	 * @return vrai si id est l'auteur
	 */
	public static boolean SonStatut(int id, String id_message) {
		DBObject obj = BDTools.ObjectID(id_message);
		obj.put("author_id", id);

		Mongo m = DataBase.getMongo();
		DBCollection coll = DataBase.getMongoCollection(m, bd);
		DBCursor c = coll.find(obj);
		boolean rep = c.hasNext();
		c.close();
		m.close();
		return rep;
	}

	public static double getScore(String recherche, String id_tweet)
			throws SQLException {
		double cpt = 0;
		String[] boucle = recherche.split(" ");
		for (int i = 0; i < boucle.length; i++) {
			int df = BDMapReduce.getDfScore(boucle[i]), tf = BDMapReduce
					.getTfScore(boucle[i], id_tweet), dftot = BDMapReduce
					.getDfTOTAL();
			if (df != 0 && tf != 0)
				cpt += tf * Math.log(dftot / df);
		}
		return cpt;
	}
}
