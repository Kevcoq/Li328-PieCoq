package bd.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.BDException;
import bd.DataBase;
import bd.comments.BDCommentsDelete;
import bd.image.BDImageDelete;
import bd.like.BDLikeDelete;
import bd.mp.BDMPDelete;
import bd.post.BDPostDelete;

/**
 * Classe qui posede les méthodes pour effectuer les opérations principales sur
 * la base de données SQL à propos des utilisateurs
 * 
 * @author Kevin et Pietro
 * 
 */
public class BDUser {
	/**
	 * Creer un utilisateur dans la BD
	 * 
	 * @param login
	 *            Son login
	 * @param mdp
	 *            Son mot de passe
	 * @param nom
	 *            Son nom
	 * @param prenom
	 *            Son prénom
	 * @throws BDException
	 */
	public static void createUser(String login, String mdp, String nom,
			String prenom) throws BDException {
		try {
			String stUsers = "INSERT INTO users VALUES(null, \"" + login
					+ "\", \"" + mdp + "\", \"" + nom + "\", \"" + prenom
					+ "\", null)";
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(stUsers);

			String stSession = "INSERT INTO session VALUES(null, \""
					+ BDUserTools.getId(login, mdp) + "\", 1, null)";
			s.executeUpdate(stSession);

			// Fermeture des connexions
			s.close();
			c.close();

		} catch (SQLException e) {
			throw new BDException("create USER" + e.getMessage());
		}

	}

	/**
	 * Supprime l'utilisateur
	 * 
	 * @param id
	 * @param clef
	 * @throws BDException
	 */
	public static void deleteUser(int id, String clef) throws BDException {
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();

			// Suppresion des amies
			String stFriend = "SELECT ami FROM friend WHERE id=" + id;
			s.executeQuery(stFriend);
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				BDUserFriend.deleteFriend(rs.getInt("ami"), id);
				BDUserFriend.deleteFriend(id, rs.getInt("ami"));
			}

			stFriend = "SELECT id FROM friend WHERE ami=" + id;
			s.executeQuery(stFriend);
			rs = s.getResultSet();
			while (rs.next()) {
				BDUserFriend.deleteFriend(rs.getInt("id"), id);
				BDUserFriend.deleteFriend(id, rs.getInt("id"));
			}

			// Supression tweet, mp, comments, image, ...
			BDMPDelete.supprimerCesMP(clef);
			BDPostDelete.supprimerCesStatut(clef);
			BDImageDelete.supprimerCesImages(clef);
			BDCommentsDelete.supprimerCesComments(clef);
			BDLikeDelete.supprimerCesLikes(clef);

			// Suppression de l'utilisateur
			String stUsers = "DELETE FROM users where id=" + id;
			s.executeUpdate(stUsers);

			String stSession = "DELETE FROM session where id=" + id;
			s.executeUpdate(stSession);

			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();

		} catch (SQLException e) {
			throw new BDException("create USER" + e.getMessage());
		}
	}
}
