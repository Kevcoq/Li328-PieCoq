package services.like;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.BDTools;
import bd.like.BDLikeSend;
import bd.like.BDLikeTools;
import bd.user.BDUserTools;

/**
 * Service pour l'envoie de like
 * 
 * @author Kevin & Pietro
 * 
 */
public class LikeSend {
	/**
	 * Ajoute un like
	 * 
	 * @param clef
	 * @param id_message
	 * @param like
	 * @return un JSON ok ou d'erreur
	 */
	public static JSONObject postLike(String clef, String id_message,
			String like) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_message))
				return ServicesTools.JSONerreur("Tweet inexistant", 3);
			if (BDLikeTools.like_deja(BDUserTools.getId(clef), id_message))
				return ServicesTools.JSONerreur("Avis déjà donné", 4);

			if (like.equals("like"))
				BDLikeSend.ajouterLike(clef, id_message);
			else if (like.equals("unlike"))
				BDLikeSend.ajouterUnlike(clef, id_message);
			else
				return ServicesTools.JSONerreur(
						"like / unlike mais pas n'importe quoi", 5);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
