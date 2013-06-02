package bd.stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.DataBase;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class BDStats {

	public static JSONObject getStats() throws JSONException, SQLException {
		JSONObject js = new JSONObject();

		Mongo m = DataBase.getMongo();

		// Tweet
		DBCollection c_tweet = DataBase.getMongoCollection(m, "tweet");
		js.put("nb_tweet", c_tweet.getCount());

		// Comments
		DBCollection c_comments = DataBase.getMongoCollection(m, "comments");
		js.put("nb_comments", c_comments.getCount());

		// Like | Unlike
		DBCollection c_like = DataBase.getMongoCollection(m, "like");
		js.put("nb_like", c_like.getCount());

		// User
		Connection co = DataBase.getMySQLConnection();
		Statement s = co.createStatement();
		ResultSet rs = s.executeQuery("SELECT count(*) FROM users");
		if (rs.next())
			js.put("nb_users", rs.getInt("count(*)"));

		return js;
	}
}
