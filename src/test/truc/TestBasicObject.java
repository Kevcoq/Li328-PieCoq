package test.truc;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


/**
 * Permet de tester le texte envoy� � la bd pour une recherche d'un element
 * lorsque la methode objectid est mise publique
 * 
 * @author Kevin & Pietro
 * 
 */
public class TestBasicObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(BDTools.ObjectID("512cb43ee4b01cc6ec48afb5"));
		DBObject obj = new BasicDBObject();
		obj.put("truc", "1");
		obj.put("truc", "2");
		System.out.println(obj.toString());
	}
}
