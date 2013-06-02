package services.humeur;

import org.json.JSONObject;

import services.ServicesTools;
import bd.humeur.BDHumeurAdd;

/**
 * Services d'ajout d'humeur
 * 
 * @author Kevin
 * 
 */
public class HumeurAddHumeur {
	/**
	 * Ajoute une humeur
	 * 
	 * @param login
	 * @param humeur
	 * @return un JSON ok ou d'erreur
	 */

	public static JSONObject ajoutHumeur(String login, String humeur)
			throws Exception {
		try {

			BDHumeurAdd.ajouterHumeur(login, humeur);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
