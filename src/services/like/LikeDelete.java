package services.like;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.like.BDLikeDelete;
import bd.like.BDLikeTools;
import bd.post.BDPostTools;
import bd.user.BDUserTools;

/**
 * Service pour supprimer les likes
 * 
 * @author Kevin & Pietro
 * 
 */
public class LikeDelete {
	/**
	 * Supprime les likes de clef
	 * 
	 * @param clef
	 * @return un JSON ok
	 */
	public static JSONObject supprimerCesLikes(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDLikeDelete.supprimerCesLikes(clef);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * supprime le like id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON ok
	 */
	public static JSONObject supprimerLike(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("like", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);
			int id = BDUserTools.getId(clef);
			if (!BDLikeTools.SonLike(id, id_obj))
				return ServicesTools.JSONerreur(
						"vous ne pouvez supprimer ce message", 4);

			BDLikeDelete.supprimerLike(id_obj);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur("Supprimer un commentaire"
					+ e.getMessage());
		}
	}

	/**
	 * supprime les likes du statut id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON ok
	 */
	public static JSONObject supprimerLikeDuTWEET(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);
			int id = BDUserTools.getId(clef);
			if (!BDPostTools.SonStatut(id, id_obj))
				return ServicesTools.JSONerreur(
						"vous ne pouvez supprimer ce message", 4);

			BDLikeDelete.supprimerLikeDuTWEET(id_obj);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
