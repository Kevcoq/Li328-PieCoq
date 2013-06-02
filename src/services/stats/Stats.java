package services.stats;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.stats.BDStats;

/**
 * Services de stats
 * @author Kevin & Pietro
 *
 */
public class Stats {
	/**
	 * Obtient les stats
	 * @return
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public static JSONObject getStats() throws JSONException, SQLException {
		return BDStats.getStats();
	}

}
