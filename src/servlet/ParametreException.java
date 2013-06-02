package servlet;

/**
 * Exception si un parametre est vide
 * 
 * @author Kevin et Pietro
 * 
 */
public class ParametreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParametreException(String message) {
		super(message);
		System.out.println(message);
	}
}
