package exceptions;

/**
 * 
 * @author Corey Schneider
 *
 */

@SuppressWarnings("serial")
public class ItemNotFoundException extends Exception {

	public ItemNotFoundException(String message) {
		super(message);
	}
	
}
