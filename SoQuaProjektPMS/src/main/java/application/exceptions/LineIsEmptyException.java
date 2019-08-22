/**
 * 
 */
package application.exceptions;

/**
 * @author Noah Ruben Marvin Jakob
 *         <p>
 * Diese Exception tritt auf wenn die auszulesende Zeile der CSV-Datein leer ist.
 */
@SuppressWarnings("serial")
public class LineIsEmptyException extends Exception {

	/**
	 *  Default-Konstruktor
	 */
	public LineIsEmptyException() {
	}

	/**
	 * @param message die Message der Exception
	 */
	public LineIsEmptyException(String message) {
		super(message);
	}

	/**
	 * @param throwable
	 */
	public LineIsEmptyException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * @param message die Message der Exception
	 * @param throwable 
	 */
	public LineIsEmptyException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
