package services.operation;

/**
 * Services qui propose de faire des Opérations basique
 * @author Kevin et Pietro
 *
 */
public class Operation {
	private double a, b;
	private Operateur op;

	/**
	 * 
	 * @param a nombre 1
	 * @param b nombre 2
	 * @param op un opérateur
	 */
	public Operation(double a, double b, Operateur op) {
		super();
		this.a = a;
		this.b = b;
		this.op = op;
	}

	/**
	 * 
	 * @return le résultat de l'opération
	 * @throws OperateurException
	 */
	public double realiser() throws OperateurException {
		switch (op) {
		case Plus:
			return a + b;
		case Moins:
			return a - b;
		case Mul:
			return a * b;
		case Div:
			if(b==0)
				throw new ArithmeticException("Division par 0");
			return a / b;
		default:
			throw new OperateurException("Operateur non reconnue");
		}
	}

	/**
	 * Convertit un opérateur sous forme de chaine de caractere en object
	 * @param ops
	 * @return un object Operateur
	 * @throws OperateurException
	 */
	public static Operateur convert(String ops) throws OperateurException {
		if (ops.equals("+"))
			return Operateur.Plus;
		if (ops.equals("-"))
			return Operateur.Moins;
		if (ops.equals("*"))
			return Operateur.Mul;
		if (ops.equals("/"))
			return Operateur.Div;
		throw new OperateurException("Operateur non reconnue");
	}

}
