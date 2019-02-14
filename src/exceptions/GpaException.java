package exceptions;

/**
 * 
 * @author Corey Schneider
 *
 */

@SuppressWarnings("serial")
public class GpaException extends Exception {

	public GpaException() {
		super("Invalid GPA!");
	}
	
	public GpaException(String message) {
		super(message);
	}
}
