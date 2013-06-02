package services;

import org.json.JSONObject;

/**
 * Definit les JSON de base
 * 
 * @author Kevin et Pietro
 * 
 */
public class ServicesTools {
	public static JSONObject JSONok() {
		return new JSONObject();
	}

	/**
	 * JSON d'erreur
	 * 
	 * @param message
	 * @param code_erreur
	 * @return un JSON avec le message d'erreur et son code
	 */
	public static JSONObject JSONerreur(String message, int code_erreur) {
		try {
			JSONObject jo = new JSONObject();
			jo.put("error", message);
			jo.put("error_code", code_erreur);
			return jo;
		} catch (Exception e) {
			return (null);
		}
	}

	/**
	 * Le JSON de login
	 * 
	 * @param id
	 * @param login
	 * @param clef
	 * @return un JSON contenant id, login et clef
	 */
	public static JSONObject JSONLogin(int id, String login, String clef) {
		try {
			JSONObject jo = new JSONObject();
			jo.put("id", id);
			jo.put("login", login);
			jo.put("clef", clef);
			return jo;
		} catch (Exception e) {
			return (null);
		}
	}

	/**
	 * JSON d'erreur dans la BD
	 * 
	 * @param string
	 * @return un JSON avec le message provenant de la BD et le code d'erreur
	 *         999
	 */
	public static JSONObject JSONBDerreur(String string) {
		try {
			JSONObject jo = new JSONObject();
			jo.put("error", "Erreur dans la BD -> " + string);
			jo.put("error_code", 999);
			return jo;
		} catch (Exception e) {
			return (null);
		}
	}
}
