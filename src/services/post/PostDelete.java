package services.post;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.post.BDPostDelete;
import bd.post.BDPostTools;
import bd.user.BDUserTools;

/**
 * Supprimer un statut
 * 
 * @author Kevin& Pietro
 * 
 */
public class PostDelete {
	/**
	 * Supprime les statuts de clef
	 * 
	 * @param clef
	 * @return un JSON ok
	 */
	public static JSONObject supprimerCesStatut(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDPostDelete.supprimerCesStatut(clef);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * supprime le message id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON ok
	 */
	public static JSONObject supprimerStatut(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);
			int id = BDUserTools.getId(clef);
			if (!BDPostTools.SonStatut(id, id_obj))
				return ServicesTools.JSONerreur(
						"vous ne pouvez supprimer ce message", 4);

			BDPostDelete.supprimerStatut(id_obj);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
