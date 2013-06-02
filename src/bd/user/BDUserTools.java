package bd.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.BDException;
import bd.DataBase;

/**
 * Classe qui possede les outils de v�rification sur les utilisateurs
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDUserTools {

	/**
	 * Test si le login existe dans la BD
	 * 
	 * @param login
	 * @return vrai si le login existe
	 * @throws BDException
	 */
	public static boolean loginExiste(String login) throws BDException {
		try {
			String string = "SELECT * FROM users WHERE login=\"" + login + "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();
			
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return bool;
		} catch (SQLException e) {
			throw new BDException("login existe" + e.getMessage());
		}
	}

	/**
	 * Test si le couple nom/prenom est unique
	 * 
	 * @param nom
	 * @param prenom
	 * @return vrai si le couple est unique
	 * @throws BDException
	 */
	public static boolean EstUniqueNomPrenom(String nom, String prenom)
			throws BDException {
		try {
			String string = "SELECT * FROM users WHERE nom=\"" + nom
					+ "\" and PRENOM=\"" + prenom + "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();

			boolean bool = !rs.next();

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return bool;
		} catch (SQLException e) {
			throw new BDException("Unique nom-prenom" + e.getMessage());
		}
	}

	/**
	 * Test si le mdp correspond au login
	 * 
	 * @param login
	 * @param mdp
	 * @return vrai si sa correspond
	 * @throws BDException
	 */
	public static boolean checkMDP(String login, String mdp) throws BDException {
		try {
			boolean bool = false;
			String string = "SELECT mdp FROM users WHERE login=\"" + login
					+ "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if (rs.next() && rs.getString("mdp").equals(mdp))
				bool = true;

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return bool;

		} catch (SQLException e) {
			throw new BDException("check MDP" + e.getMessage());
		}
	}

	/**
	 * Test si la clef est valide
	 * 
	 * @param clef
	 * @return vrai si la clef correspond � une entr�e non perim�
	 * @throws BDException
	 */
	public static boolean checkClef(String clef) throws BDException {
		try {
			boolean bool = false;
			String string = "SELECT * FROM session WHERE clef=\"" + clef
					+ "\" and perime=false";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if (rs.next())
				bool = true;

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return bool;

		} catch (SQLException e) {
			throw new BDException("check CLEF" + e.getMessage());
		}
	}

	/**
	 * Trouve l'id en fonction de la clef
	 * 
	 * @param clef
	 * @return id
	 * @throws BDException
	 */
	public static int getId(String clef) throws BDException {
		int id = 0;
		try {
			String string = "SELECT id FROM session WHERE clef=\"" + clef
					+ "\" and perime=false";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if (rs.next())
				id = rs.getInt("id");
			
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new BDException("getID" + e.getMessage());
		}
		return id;
	}

	/**
	 * Retourne l'id en fonction du nom et du pr�nom
	 * 
	 * @param nom
	 * @param prenom
	 * @return id
	 * @throws BDException
	 */
	public static int getId(String login, String mdp) throws BDException {
		int id = 0;
		try {
			String string = "SELECT id FROM users WHERE login=\"" + login
					+ "\" and mdp=\"" + mdp + "\"";

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if (rs.next())
				id = rs.getInt("id");

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new BDException("getID" + e.getMessage());
		}
		return id;
	}

	/**
	 * Test si l'id existe
	 * 
	 * @param id
	 * @return vrai si l'id existe
	 * @throws BDException
	 */
	public static boolean checkID(int id) throws BDException {
		try {
			boolean bool = false;
			String string = "SELECT * FROM users WHERE id=" + id;

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if (rs.next())
				bool = true;

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return bool;

		} catch (SQLException e) {
			throw new BDException("check ID" + e.getMessage());
		}
	}

	/**
	 * Test si l'utilisateur est d�j� ami avec id_friend
	 * 
	 * @param clef
	 * @param id_friend
	 * @return vrai si ils sont d�j� ami
	 * @throws BDException
	 */
	public static boolean checkAmi(String clef, int id_friend)
			throws BDException {
		try {
			boolean bool = false;
			if (checkClef(clef)) {
				int id = getId(clef);
				String string = "SELECT ami FROM friend WHERE id=" + id;

				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();
				s.executeQuery(string);
				ResultSet rs = s.getResultSet();

				while (rs.next())
					if (rs.getInt("ami") == id_friend) {
						bool = true;
						break;
					}
				
				// Fermeture de la connexion
				rs.close();
				s.close();
				c.close();
			}
			return bool;

		} catch (SQLException e) {
			throw new BDException("check AMI" + e.getMessage());
		}
	}

	/**
	 * Recupere le login � partir de la clef
	 * 
	 * @param clef
	 * @return login
	 * @throws BDException
	 */
	public static String getLogin(String clef) throws BDException {
		String login = "";
		try {
			int id = getId(clef);
			String string = "SELECT login FROM users WHERE id=" + id;

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if (rs.next())
				login = rs.getString(1);
			
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			throw new BDException("getLogin" + e.getMessage());
		}
		return login;
	}

	
	/**
	 * Verifie si l'id existe
	 * @param id
	 * @return true si il existe
	 * @throws BDException
	 */
	public static boolean idExiste(int id) throws BDException {
		try {
			String string = "SELECT * FROM users WHERE id=" + id;

			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();

			boolean bool = rs.next();
			
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return bool;
		} catch (SQLException e) {
			throw new BDException("login existe" + e.getMessage());
		}
	}
}
