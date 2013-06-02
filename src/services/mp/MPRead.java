package services.mp;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.mp.BDMPRead;
import bd.user.BDUserTools;

/**
 * Service de lecture de MP
 * 
 * @author Kevin & Pietro
 * 
 */
public class MPRead {
	/**
	 * Affiche le mp voulu
	 * 
	 * @param clef
	 * @param id_obj
	 * @return Un JSON contenant le mp
	 */
	public static JSONObject lireMp(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("mp", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);

			return BDMPRead.lireMP(clef, id_obj);

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @param non_lu
	 *            Boolean vrai -> message non lu, faux -> tous
	 * @return Tous les mp ou tous les mp non lu
	 */
	public static Object afficherMp(String clef, boolean non_lu) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDMPRead.afficherMP(clef, non_lu);

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	public static Object listeMp(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDMPRead.listeMP(clef);

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
