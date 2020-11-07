package customExceptions;

public class NotCompleteMandatoryFieldsException extends Exception{


	private static final long serialVersionUID = 1L;

	public NotCompleteMandatoryFieldsException() {
		super("Some mandatory filds are empty, please complete fields");
	}
}
