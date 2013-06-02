package services.user;

import org.json.JSONObject;

import services.ServicesTools;
import bd.user.BDUser;
import bd.user.BDUserTools;

/**
 * Creer les utilisateurs
 * 
 * @author Kevin et Pietro
 * 
 */
public class UserCreateUser {
	/**
	 * Effectue divers test puis ajoute l'utilisateur dans la BD
	 * 
	 * @param login
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @return un JSON ok ou d'erreur
	 */
	public static JSONObject creeUtilisateur(String login, String mdp,
			String nom, String prenom) {
		try {
			if (BDUserTools.loginExiste(login))
				return ServicesTools.JSONerreur("le login existe deja", 1);
			if (!BDUserTools.EstUniqueNomPrenom(nom, prenom))
				return ServicesTools.JSONerreur("utilisateur deja  present", 2);

			BDUser.createUser(login, mdp, nom, prenom);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}

	}

	/**
	 * Supprime l'utilisateur en entrée
	 * 
	 * @param clef
	 * @param login
	 * @param mdp
	 * @return un JSON ok
	 */
	public static JSONObject supprimerUtilisateur(String clef, String login,
			String mdp) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 3);
			if (!BDUserTools.loginExiste(login))
				return ServicesTools.JSONerreur("login inexistant", 1);
			if (!BDUserTools.checkMDP(login, mdp))
				return ServicesTools.JSONerreur("Mdp incorrecte", 2);

			int id = BDUserTools.getId(clef);
			BDUser.deleteUser(id, clef);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

}
