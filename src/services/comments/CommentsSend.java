package services.comments;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.comments.BDCommentsSend;
import bd.user.BDUserTools;

/**
 * Service pour l'envoie de commentaire
 * 
 * @author Kevin & Pietro
 * 
 */
public class CommentsSend {
	/**
	 * Ajoute un commentaire
	 * 
	 * @param clef
	 * @param id_message
	 * @param message
	 * @return un JSON ok ou d'erreur
	 */
	public static JSONObject postComments(String clef, String id_message,
			String message) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDCommentsSend.ajouterCommentaire(clef, message, id_message);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
