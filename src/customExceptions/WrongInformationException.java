package customExceptions;

public class WrongInformationException extends Exception{

	private static final long serialVersionUID = 383312834684256627L;
	
	public WrongInformationException(String id,String password) {
		super("The user or password are incorret, please check "+ id+" or "+ password);
	}

}
