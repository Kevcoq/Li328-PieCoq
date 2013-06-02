package services.user;

import org.json.JSONArray;
import org.json.JSONException;

import bd.user.BDUserListe;

public class UserListe {

	public static JSONArray liste(String clef) throws JSONException {
		return BDUserListe.liste(clef);
	}

}
