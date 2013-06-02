package services.post;

import org.json.JSONObject;

import services.ServicesTools;
import services.image.ImageSend;
import bd.BDException;
import bd.BDTools;
import bd.image.BDImageTools;
import bd.post.BDPostSend;
import bd.post.BDPostTools;
import bd.user.BDUserTools;

/**
 * Services pour poster un message
 * 
 * @author Kevin et Pietro
 * 
 */
public class PostSend {
	/**
	 * Poste un statut dans la BD
	 * 
	 * @param clef
	 * @param message
	 * @return un JSON ok ou d'erreur
	 */
	public static JSONObject postStatut(String clef, String message) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDPostSend.posterStatut(clef, message);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	public static JSONObject postStatut(String clef, String message, String url) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			ImageSend.postImage(clef, url);
			BDPostSend.posterStatut(clef, message, BDImageTools.getObjId(url));
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	public static JSONObject ajouterPhoto(String clef, String id_message,
			String id_photo) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_message))
				return ServicesTools.JSONerreur("message introuvable", 3);
			if (!BDPostTools.SonStatut(BDUserTools.getId(clef), id_message))
				return ServicesTools.JSONerreur("ceci n'est pas votre message",
						5);
			if (!BDTools.ObjetExiste("image", id_photo))
				return ServicesTools.JSONerreur("photo introuvable", 4);

			BDPostSend.ajouterPhoto(id_message, id_photo);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}

	}

}
