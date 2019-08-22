/**
 * 
 */
package exceptions;

import beans.MedikamentProTagBean;

/**
 * @author Noah Ruben Marvin Jakob
 *         <p>
 * Diese Exception tritt auf wenn die auszulesende Zeile der CSV-Datein nicht in ein {@link MedikamentProTagBean} umwandelbar ist.
 */
@SuppressWarnings("serial")
public class LineCanNotBeParsedException extends Exception {


	/**
	 *  Default-Konstruktor
	 */
	public LineCanNotBeParsedException() {
	}

	/**
	 * @param message die Message der Exception
	 */
	public LineCanNotBeParsedException(String message) {
		super(message);
	}

	/**
	 * @param throwable
	 */
	public LineCanNotBeParsedException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * @param message die Message der Exception
	 * @param throwable
	 */
	public LineCanNotBeParsedException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
