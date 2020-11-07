package customExceptions;

public class IncompatibleFieldsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public IncompatibleFieldsException() {
		super("Filled more fields than it's necessary.");
	}
	
}
