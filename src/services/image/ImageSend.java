package services.image;

import java.io.InputStream;
import java.net.URL;

import org.json.JSONObject;

import services.ServicesTools;
import bd.image.BDImageSend;
import bd.user.BDUserTools;

/**
 * Services pour poster une image
 * 
 * @author Kevin et Pietro
 * 
 */
public class ImageSend {
	/**
	 * Poste une image dans la BD
	 * 
	 * @param clef
	 * @param url
	 * @return un JSON ok ou d'erreur
	 */
	public static JSONObject postImage(String clef, String url) {
		try {
			if (!BDUserTools.checkClef(clef))
				return ServicesTools.JSONerreur("Clef incorrecte", 2);
		
			 URL oracle = new URL(url);
			 InputStream in=oracle.openStream();
			 long length = in.available();
			 byte[] bytes = new byte[(int) length];
			 in.read(bytes);
			 
			 
			BDImageSend.posterImage(clef, url, bytes);
			return ServicesTools.JSONok();

		} catch (Exception e) {
			return ServicesTools.JSONBDerreur(e.getMessage());
		}
	}
}
