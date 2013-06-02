package services.humeur;

import org.json.JSONObject;

import services.ServicesTools;
import bd.humeur.BDHumeurRead;

/**
 * Services de lecture d'humeur
 * 
 * @author Kevin
 * 
 */
public class HumeurReadHumeur {
	/**
	 * Affiche l'humeur de la personne
	 * 
	 * @param login
	 * @return un JSON ok ou d'erreur
	 */

	public static JSONObject lireHumeur(String login) throws Exception {
		try {
			return BDHumeurRead.lireHumeur(login);

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
