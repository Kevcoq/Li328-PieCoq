package services.user;

import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.user.BDUserFriend;
import bd.user.BDUserTools;

/**
 * Services de gestion d'ami
 * 
 * @author Kevin et Pietro
 * 
 */
public class UserFriend {
	/**
	 * Divers test puis ajoute l'ami
	 * 
	 * @param clef
	 * @param id_friend
	 * @return un json ok ou d'erreur
	 */
	public static JSONObject addFriend(String clef, int id_friend) {
		try {
			int id = BDUserTools.getId(clef);
			if (id == id_friend)
				return ServicesTools.JSONerreur("Impossible", 5);
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDUserTools.checkID(id_friend))
				return ServicesTools.JSONerreur("Ami inexistant", 3);
			if (BDUserTools.checkAmi(clef, id_friend))
				return ServicesTools.JSONerreur("Deja ami", 4);

			BDUserFriend.addFriend(id, id_friend);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}

	/**
	 * Divers test puis supprime l'ami
	 * 
	 * @param clef
	 * @param id_friend
	 * @return json ok ou d'erreur
	 */
	public static JSONObject deleteFriend(String clef, int id_friend) {
		try {
			int id = BDUserTools.getId(clef);
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
			if (!BDUserTools.checkID(id_friend))
				return ServicesTools.JSONerreur("Ami inexistant", 3);
			if (!BDUserTools.checkAmi(clef, id_friend))
				return ServicesTools.JSONerreur("Pas ami", 4);

			BDUserFriend.deleteFriend(id, id_friend);
			return ServicesTools.JSONok();

		} catch (BDException e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
