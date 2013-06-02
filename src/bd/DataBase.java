package bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

/**
 * Classe qui possede les fonctions de connection aux base de donn�es.
 * 
 * @author Kevin et Pietro
 * 
 */
public class DataBase {
	private static DataBase database = null;
	private DataSource dataSource;

	/**
	 * Constructeur de la base de donnee SQL
	 * 
	 * @param jndiname
	 * @throws SQLException
	 */
	public DataBase(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			// Handle error that it’s not configured in JNDI.
			throw new SQLException(jndiname + " is missing in JNDI! : "
					+ e.getMessage());
		}
	}

	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * 
	 * @return une connection sur la base SQL
	 * @throws SQLException
	 */
	public static Connection getMySQLConnection() throws SQLException {
		if (DBStatic.mysql_pooling == false) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				return (DriverManager.getConnection("jdbc:mysql://"
						+ DBStatic.mysql_host + "/" + DBStatic.mysql_db,
						DBStatic.mysql_username, DBStatic.mysql_password));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			if (database == null) {
				database = new DataBase("jdbc/db");
			}
			return (database.getConnection());
		}
	}

	/**
	 * 
	 * @return la base mongo
	 */
	public static Mongo getMongo() {
		try {
			Mongo m = new Mongo("li328.lip6.fr", 27130);
			return m;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param m
	 *            : mongo
	 * @param base
	 *            : le nom de la collection sur laquelle on souhaite la
	 *            connexion
	 * @return une connexion sur la collection base de mongo
	 */
	public static DBCollection getMongoCollection(Mongo m, String base) {
		DB db = m.getDB("coquart");
		DBCollection coll = db.getCollection(base);
		return coll;
	}
}
