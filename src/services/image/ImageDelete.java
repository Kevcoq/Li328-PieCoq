package services.image;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.comments.BDCommentsTools;
import bd.image.BDImageDelete;
import bd.user.BDUserTools;

/**
 * Services de suppression des images
 * 
 * @author Kevin & Pietro
 * 
 */
public class ImageDelete {
	/**
	 * Supprime les images de clef
	 * 
	 * @param clef
	 * @return un JSON ok
	 */
	public static JSONObject supprimerCesImage(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDImageDelete.supprimerCesImages(clef);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * supprime l'image id_image
	 * 
	 * @param clef
	 * @param id_obj
	 * @return un JSON ok
	 */
	public static JSONObject supprimerImage(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("comments", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);
			int id = BDUserTools.getId(clef);
			if (!BDCommentsTools.SonComments(id, id_obj))
				return ServicesTools.JSONerreur(
						"vous ne pouvez supprimer ce message", 4);

			BDImageDelete.supprimerImage(id_obj);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
