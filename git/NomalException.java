package DreamFactory.Exception;

public class NomalException extends Exception{
	public NomalException() {
		super();
	}
	@Override
	public String getMessage() {
		return "发生了未知的错误";
	}
}
