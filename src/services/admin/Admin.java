package services.admin;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.admin.BDAdmin;
import bd.user.BDUserTools;

/**
 * Services d'administration
 * 
 * @author Kevin & Pietro
 * 
 */
public class Admin {

	/**
	 * Vide les bases MongoDB
	 * 
	 * @param clef
	 * @return
	 */
	public static JSONObject viderMongoDB(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDAdmin.viderMongoDB();
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
