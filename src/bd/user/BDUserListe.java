package bd.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.ServicesTools;
import bd.BDException;
import bd.DataBase;

public class BDUserListe {

	public static JSONArray liste(String clef) throws JSONException {
		try {
			String string = "SELECT id, login from users";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);

			ResultSet rs = s.getResultSet();
			JSONArray js = new JSONArray();

			while (rs.next()) {
				String id = rs.getString("id");
				String login = rs.getString("login");
				JSONObject tmp = new JSONObject();
				tmp.put("id", id);
				tmp.put("login", login);
				if (clef != null)
					tmp.put("contact",
							BDUserTools.checkAmi(clef, Integer.parseInt(id)));
				else
					tmp.put("contact", false);

				js.put(tmp);
			}
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return js;

		} catch (NumberFormatException | BDException e) {
			e.printStackTrace();
			return new JSONArray(
					ServicesTools.JSONBDerreur("erreur JSON dans BDUserListe"));
		} catch (SQLException e) {
			e.printStackTrace();
			return new JSONArray(
					ServicesTools.JSONBDerreur("BDUserListe (pb SQL) -> "
							+ e.getMessage()));
		}
	}
}
