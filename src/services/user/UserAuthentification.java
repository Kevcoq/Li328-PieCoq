package services.user;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.user.BDUserAuthentification;
import bd.user.BDUserTools;

/**
 * Authentification des utilisateurs
 * 
 * @author Kevin et Pietro
 * 
 */
public class UserAuthentification {
	/**
	 * 
	 * @return une clef aléatoire de 32 caractere
	 */
	private static String genereClef() {
		String s = "";
		char tab[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < 32; i++)
			// if(Math.random()<9./35)
			// s+=(char)(Math.random()*10+48);
			// else
			// s+=(char)(Math.random()*26+97);
			s += tab[(int) (Math.random() * tab.length)];
		return s;
	}

	/**
	 * 
	 * @param login
	 * @param mdp
	 * @return un json de login ou d'erreur
	 */
	public static JSONObject login(String login, String mdp) {
		try {
			if (!BDUserTools.loginExiste(login))
				return ServicesTools.JSONerreur("login inexistant", 1);
			if (!BDUserTools.checkMDP(login, mdp))
				return ServicesTools.JSONerreur("Mdp incorrecte", 2);

			int id = BDUserTools.getId(login, mdp);
			String clef = genereClef();
			BDUserAuthentification.addClef(id, clef);
			return ServicesTools.JSONLogin(id, login, clef);

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * 
	 * @param clef
	 * @return un json ok ou d'erreur
	 */
	public static JSONObject logout(String clef) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);

			BDUserAuthentification.removeClef(clef);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
