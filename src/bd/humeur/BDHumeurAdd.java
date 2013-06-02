package bd.humeur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.BDException;
import bd.DataBase;

/**
 * Gestion de l'ajout d'humeur niveau Mongo
 * 
 * @author Kevin
 * 
 */
public class BDHumeurAdd {

	/**
	 * Ajouter une humeur a un user dans la BD
	 * 
	 * @param clef
	 *            Sa clef
	 * @param humeur
	 *            Son humeur
	 * @throws BDException
	 * @throws SQLException
	 */
	public static void ajouterHumeur(String login, String humeur)
			throws SQLException {
		String requete = "SELECT * FROM humeur WHERE login=\"" + login + "\"";
		String update = "UPDATE humeur SET humeur=\"" + humeur
				+ "\" WHERE login=\"" + login + "\"";
		String insert = "INSERT INTO humeur VALUES(\"" + login + "\", \""
				+ humeur + "\")";

		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		s.executeQuery(requete);
		ResultSet rs = s.getResultSet();

		if (rs.next())
			s.executeUpdate(update);
		else
			s.executeUpdate(insert);
	}
}