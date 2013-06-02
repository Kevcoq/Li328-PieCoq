package services.comments;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.comments.BDCommentsDelete;
import bd.comments.BDCommentsTools;
import bd.post.BDPostTools;
import bd.user.BDUserTools;

/**
 * Service pour supprimer les commentaires
 * 
 * @author Kevin & Pietro
 * 
 */
public class CommentsDelete {
	/**
	 * Supprime les commentaires de clef
	 * 
	 * @param clef
	 * @return un JSON ok
	 */
	public static JSONObject supprimerCesComments(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDCommentsDelete.supprimerCesComments(clef);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * supprime le commentaire id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON ok
	 */
	public static JSONObject supprimerComments(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("comments", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);
			int id = BDUserTools.getId(clef);
			if (!BDCommentsTools.SonComments(id, id_obj))
				return ServicesTools.JSONerreur(
						"vous ne pouvez supprimer ce message", 4);

			BDCommentsDelete.supprimerComments(id_obj);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur("Supprimer un commentaire"
					+ e.getMessage());
		}
	}

	/**
	 * supprime les commentaires du statut id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON ok
	 */
	public static JSONObject supprimerCommentsDuTWEET(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);
			int id = BDUserTools.getId(clef);
			if (!BDPostTools.SonStatut(id, id_obj))
				return ServicesTools.JSONerreur(
						"vous ne pouvez supprimer ce message", 4);

			BDCommentsDelete.supprimerCommentsDuTWEET(id_obj);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
