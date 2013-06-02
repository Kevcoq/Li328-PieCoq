package services.mp;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.mp.BDMPDelete;
import bd.mp.BDMPTools;
import bd.user.BDUserTools;

/**
 * Service de suppresion de MP
 * 
 * @author Kevin & Pietro
 * 
 */
public class MPDelete {
	/**
	 * Supprime le mp id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON ok
	 */
	public static JSONObject supprimerMP(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("mp", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);
			int id = BDUserTools.getId(clef);
			if (!BDMPTools.SonMP(id, id_obj))
				return ServicesTools.JSONerreur(
						"vous ne pouvez supprimer ce message", 4);

			BDMPDelete.supprimerMP(id_obj);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Vide sa boite de mp
	 * 
	 * @param clef
	 * @return un JSON ok
	 */
	public static JSONObject supprimerTousMP(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDMPDelete.supprimerCesMP(clef);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

}
