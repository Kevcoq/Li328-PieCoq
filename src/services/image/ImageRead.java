package services.image;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.image.BDImageRead;
import bd.user.BDUserTools;

/**
 * Services de lecture sur les images
 * 
 * @author Kevin et Pietro
 * 
 */
public class ImageRead {
	/**
	 * Lis l'image id_obj
	 * 
	 * @param clef
	 * @param id_obj
	 * @return
	 */
	public static Object lireImage(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("image", id_obj))
				return ServicesTools.JSONerreur("Image introuvable", 3);

			return BDImageRead.lireImage(clef, id_obj);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());

		}

	}

	/**
	 * Affiche les images
	 * 
	 * @param clef
	 * @return
	 */
	public static JSONObject afficheImage(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			return BDImageRead.afficherImage();
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());

		}
	}

	/**
	 * Affiche les images de l'id
	 * 
	 * @param clef
	 * @return
	 */
	public static JSONObject afficheImageDe(String clef, int id) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDUserTools.idExiste(id))
				return ServicesTools.JSONerreur("Id inexistant", 3);

			return BDImageRead.afficherImageDe(id);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Affiche les images de le login
	 * 
	 * @param clef
	 * @return
	 */
	public static JSONObject afficheImageDe(String clef, String login) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDUserTools.loginExiste(login))
				return ServicesTools.JSONerreur("Id inexistant", 3);

			return BDImageRead.afficherImageDe(login);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}