package bd.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bd.BDException;
import bd.DataBase;

/**
 * Gestion cote BD de l'authentification
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDUserAuthentification {
	/**
	 * Ajoute la clef donnée pour la session de l'utilisateur et lui active sa
	 * session
	 * 
	 * @param id
	 *            id de l'utilisateur
	 * @param clef
	 *            sa clef
	 * @throws BDException
	 */
	public static void addClef(int id, String clef) throws BDException {
		try {
			String string = "UPDATE session SET clef=\"" + clef + "\", perime="
					+ false + " WHERE id=" + id;

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new BDException("add CLEF" + e.getMessage());
		}

	}

	/**
	 * Desactive la session de l'utilisateur
	 * 
	 * @param clef
	 *            sa clef
	 * @throws BDException
	 */
	public static void removeClef(String clef) throws BDException {
		try {
			String string = "UPDATE session SET perime=" + true
					+ " WHERE clef=\"" + clef + "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);
			
			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new BDException("remove CLEF" + e.getMessage());
		}
	}
}
