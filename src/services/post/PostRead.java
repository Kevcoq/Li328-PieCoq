package services.post;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.BDTools;
import bd.post.BDPostRead;
import bd.user.BDUserTools;

/**
 * Services de lecture sur les posts
 * 
 * @author Kevin et Pietro
 * 
 */
public class PostRead {

	/**
	 * Affiche les statuts
	 * 
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray afficherStatut() throws JSONException {
		return BDPostRead.afficherStatut();
	}

	/**
	 * Affiche les statuts des amis
	 * 
	 * @param clef
	 * @return
	 * @throws JSONException
	 * @throws BDException
	 */
	public static JSONArray afficherStatutAmi(String clef)
			throws JSONException, BDException {
		return BDPostRead.afficherStatutAmi(clef);
	}

	/**
	 * 
	 * @param clef
	 * @param id
	 * @return un JSON contenant tout les messages d'id
	 * @throws JSONException
	 */
	public static JSONArray afficherStatutDe(int id) throws JSONException {
		return BDPostRead.afficherStatutDe(id);
	}

	/**
	 * 
	 * @param clef
	 * @param login
	 * @return un JSON contenant tout les messages de login
	 * @throws JSONException
	 */
	public static JSONArray afficherStatutDe(String login) throws JSONException {
		return BDPostRead.afficherStatutDe(login);
	}

	/**
	 * Lis le statut id_message
	 * 
	 * @param clef
	 * @param id_obj
	 * @return
	 */
	public static JSONObject lireStatut(String clef, String id_obj) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDTools.ObjetExiste("tweet", id_obj))
				return ServicesTools.JSONerreur("message introuvable", 3);

			return BDPostRead.lireStatut(id_obj);
		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Fonction search
	 * 
	 * @param recherche
	 * @return
	 * @throws JSONException
	 * @throws SQLException
	 */
	public static JSONArray Search(String recherche) throws JSONException,
			SQLException {
		return BDPostRead.Search(recherche);
	}

	/**
	 * Affiche les statuts de login
	 * 
	 * @param login
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray afficherCesStatut(String login)
			throws JSONException {
		return BDPostRead.afficherCesStatut(login);
	}
}
