package services.mp;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.mp.BDMPSend;
import bd.user.BDUserTools;

/**
 * Service de gestion d'envoie de MP
 * 
 * @author Kevin & Pietro
 * 
 */
public class MPSend {
	/**
	 * 
	 * @param clef
	 *            la clef
	 * @param id_dest
	 *            l'id du destinataire
	 * @param message
	 *            le message
	 * @return
	 */
	public static JSONObject sendMp(String clef, int id_dest, String message) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDUserTools.idExiste(id_dest))
				return ServicesTools.JSONerreur("dest introuvable", 3);

			BDMPSend.sendMP(clef, id_dest, message);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
