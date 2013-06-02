package bd.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.BDException;
import bd.DataBase;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class BDMapReduce {
	/**
	 * Permet de vider les 2 tables SQL pour search
	 * 
	 * @throws SQLException
	 */
	public static void viderDF() throws SQLException {
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		s.executeUpdate("DELETE FROM dfs");
		s.executeUpdate("DELETE FROM tfs");

		s.close();
		c.close();
	}

	/**
	 * Insere l'objet provenant de Map/Reduce dans la base SQL dfs
	 * 
	 * @param obj
	 * @throws BDException
	 * @throws SQLException
	 */
	public static void insertdf(DBObject obj) throws BDException, SQLException {
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		String stDf = "INSERT INTO dfs VALUES(" + obj.get("_id") + ", "
				+ ((DBObject) obj.get("value")).get("df") + ")";
		s.executeUpdate(stDf);

		// Fermeture des connexions
		s.close();
		c.close();
	}

	/**
	 * Renvoie le total des df
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static int getTotalDf() throws SQLException {
		int cpt = 0;

		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		s.executeQuery("SELECT df FROM dfs");
		ResultSet rs = s.getResultSet();

		while (rs.next())
			cpt += rs.getDouble("df");

		s.close();
		c.close();

		return cpt;
	}

	/**
	 * Insere l'objet provenant de Map/Reduce dans la base SQL tfs
	 * 
	 * @param obj
	 * @throws BDException
	 * @throws SQLException
	 */
	public static void inserttf(DBObject obj) throws BDException, SQLException {
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		String getDf = "SELECT df FROM dfs WHERE mot=" + obj.get("_id");
		int nbDf = 0;
		s.executeQuery(getDf);
		ResultSet rs = s.getResultSet();
		if (rs.next())
			nbDf = rs.getInt("df");

		if (nbDf == 0)
			return;
		else if (nbDf == 1) {// ((DBObject) obj.get("value")).get("document")
			String sttf = "INSERT INTO tfs VALUES(" + obj.get("_id") + ", \""
					+ ((DBObject) obj.get("value")).get("document") + "\", "
					+ ((DBObject) obj.get("value")).get("tf") + ")";

			s.executeUpdate(sttf);
		} else {
			BasicDBList o_list = (BasicDBList) ((DBObject) obj.get("value"))
					.get("tfs");
			for (Object tmp : o_list) {
				DBObject o = (DBObject) tmp;
				String sttfs = "INSERT INTO tfs VALUES(" + obj.get("_id")
						+ ", \"" + o.get("document") + "\", " + o.get("tf")
						+ ")";
				s.executeUpdate(sttfs);
			}
		}

		// Fermeture des connexions
		s.close();
		c.close();
	}

	/**
	 * Renvoie le total des tf
	 * 
	 * @return
	 * @throws SQLException
	 */
	private static int getTotalTf() throws SQLException {
		int cpt = 0;

		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		s.executeQuery("SELECT tf FROM tfs");
		ResultSet rs = s.getResultSet();

		while (rs.next())
			cpt += rs.getDouble("tf");

		s.close();
		c.close();

		return cpt;
	}

	/**
	 * Permet de finir le Map/Reduce en y inserant les totaux
	 * 
	 * @throws SQLException
	 */
	public static void finish() throws SQLException {
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		s.executeUpdate("INSERT INTO dfs VALUES(\"_TOTAL\", " + getTotalDf()
				+ ")");
		s.executeUpdate("INSERT INTO tfs VALUES(\"_TOTAL\", 0, " + getTotalTf()
				+ ")");

		s.close();
		c.close();
	}

	/**
	 * Renvoie le df d'un mot
	 * 
	 * @param mot
	 * @return
	 * @throws SQLException
	 */
	public static int getDfScore(String mot) throws SQLException {
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		int df = 0;

		s.executeQuery("SELECT df FROM dfs WHERE mot=\"" + mot + "\"");
		ResultSet rs = s.getResultSet();

		if (rs.next())
			df = rs.getInt("df");

		s.close();
		c.close();

		return df;
	}

	/**
	 * Renvoie le df total
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static int getDfTOTAL() throws SQLException {
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		int df;

		s.executeQuery("SELECT df FROM dfs WHERE mot=\"_TOTAL\"");
		ResultSet rs = s.getResultSet();
		if (rs.next())
			df = rs.getInt("df");
		else
			throw new SQLException("Le maximum _TOTAL n'a pas été trouvé. ");

		s.close();
		c.close();

		return df;
	}

	/**
	 * Renvoie le tf d'un mot
	 * 
	 * @param mot
	 * @param id_tweet
	 * @return
	 * @throws SQLException
	 */
	public static int getTfScore(String mot, String id_tweet)
			throws SQLException {
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		int tf = 0;

		s.executeQuery("SELECT tf FROM tfs WHERE mot=\"" + mot
				+ "\" and doc=\"" + id_tweet + "\"");
		ResultSet rs = s.getResultSet();

		if (rs.next())
			tf = rs.getInt("tf");

		s.close();
		c.close();

		return tf;
	}
}
