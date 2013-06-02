package test.operation;

import services.operation.Operateur;
import services.operation.Operation;

/**
 * Permet de tester les Opérations en locale
 * 
 * @author Kevin et Pietro
 * 
 */
public class MainOperation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Operation op = new Operation(4, 1, Operateur.Div);
			System.out.println(op.realiser());

			Operation op2 = new Operation(4, 2, null);
			System.out.println(op2.realiser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
