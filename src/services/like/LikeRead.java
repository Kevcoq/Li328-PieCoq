package services.like;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.like.BDLikeRead;
import bd.user.BDUserTools;

/**
 * Service de lecture et affichage des likes
 * 
 * @author Kevin & Pietro
 * 
 */
public class LikeRead {
	/**
	 * 
	 * @param clef
	 * @return un JSON contenant tout les likes de la BD
	 */
	public static JSONObject afficherLike(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDLikeRead.afficherLike();
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @param id
	 * @return un JSON contenant tout les likes d'id
	 */
	public static JSONObject afficherLikeDe(String clef, int id) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDLikeRead.afficherLikeDe(id);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @param login
	 * @return un JSON contenant tout les likes de login
	 */
	public static JSONObject afficherLikeDe(String clef, String login) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDLikeRead.afficherLikeDe(login);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Lis le like id_like
	 * 
	 * @param clef
	 * @param id_like
	 * @return
	 */
	public static JSONObject lireLike(String clef, String id_like) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("like", id_like))
				return ServicesTools.JSONerreur("message introuvable", 3);

			return BDLikeRead.lireLike(id_like);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Lis tout les likes du tweet id
	 * 
	 * @param clef
	 * @param id_tweet
	 * @return Un JSON contenant tout les likes du tweet
	 */
	public static JSONObject lisLikeDuTWEET(String clef, String id_tweet) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_tweet))
				return ServicesTools.JSONerreur("message introuvable", 3);

			return BDLikeRead.lisLikeDuTWEET(id_tweet);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
