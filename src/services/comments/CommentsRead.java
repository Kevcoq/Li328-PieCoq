package services.comments;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDTools;
import bd.comments.BDCommentsRead;
import bd.user.BDUserTools;

/**
 * Service de lecture et affichage des commentaires
 * 
 * @author Kevin & Pietro
 * 
 */
public class CommentsRead {
	/**
	 * 
	 * @param clef
	 * @return un JSON contenant tout les commentaires de la BD
	 */
	public static Object afficherComments() {
		try {
			return BDCommentsRead.afficherComments();
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @param id
	 * @return un JSON contenant tout les commentaires d'id
	 */
	public static Object afficherCommentsDe(int id) {
		try {
			return BDCommentsRead.afficherCommentsDe(id);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @param login
	 * @return un JSON contenant tout les commentaires de login
	 */
	public static Object afficherCommentsDe(String login) {
		try {
			return BDCommentsRead.afficherCommentsDe(login);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Lis le commentaire id_comments
	 * 
	 * @param clef
	 * @param id_comments
	 * @return
	 */
	public static JSONObject lireComments(String clef, String id_comments) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("comments", id_comments))
				return ServicesTools.JSONerreur("message introuvable", 3);

			return BDCommentsRead.lireComments(id_comments);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Lis tout les commentaires du tweet id
	 * 
	 * @param clef
	 * @param id_tweet
	 * @return Un JSON contenant tout les commentaires du tweet
	 */
	public static Object lisCommentsDuTWEET(String id_tweet) {
		try {
			if (!BDTools.ObjetExiste("tweet", id_tweet))
				return ServicesTools.JSONerreur("message introuvable", 3);

			return BDCommentsRead.lisCommentsDuTWEET(id_tweet);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
