package bd.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bd.BDException;
import bd.DataBase;

/**
 * Gestion cote de BD des amis
 * 
 * @author Kevin & Pietro
 * 
 */
public class BDUserFriend {
	/**
	 * Ajoute un ami
	 * 
	 * @param id
	 *            son id
	 * @param id_friend
	 *            id de l'ami
	 * @throws BDException
	 */
	public static void addFriend(int id, int id_friend) throws BDException {
		try {
			String string = "INSERT INTO friend VALUES(" + id + ", "
					+ id_friend + ", null)";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);
			
			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new BDException("add FRIEND" + e.getMessage());
		}
	}

	/**
	 * Supprime un ami
	 * 
	 * @param id
	 *            son id
	 * @param id_friend
	 *            id de l'ami qu'on souhaite supprimé
	 * @throws BDException
	 */
	public static void deleteFriend(int id, int id_friend) throws BDException {
		try {
			String string = "DELETE FROM friend WHERE id=" + id + " and ami="
					+ id_friend;

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);
			
			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new BDException("delete FRIEND" + e.getMessage());
		}
	}
}
