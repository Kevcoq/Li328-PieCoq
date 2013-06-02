package bd.humeur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.DataBase;

/**
 * Lecture des humeurs niveau BD
 * 
 * @author Kevin
 * 
 */
public class BDHumeurRead {

	/**
	 * Lit l'humeur d'un user dans la BD
	 * 
	 * @param login
	 *            Son login
	 * @return
	 * @throws SQLException
	 * @throws BDException
	 */

	public static JSONObject lireHumeur(String login) throws JSONException,
			SQLException {
		String requete = "SELECT * FROM humeur WHERE login=\"" + login + "\"";

		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		s.executeQuery(requete);
		ResultSet rs = s.getResultSet();

		JSONObject js = new JSONObject();
		js.put("login", login);
		if (rs.next())
			js.put("humeur", rs.getString("humeur"));
		else
			js.put("humeur", "RAS");

		return js;
	}

}
