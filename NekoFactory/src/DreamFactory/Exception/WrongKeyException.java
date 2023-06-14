package DreamFactory.Exception;

public class WrongKeyException extends Exception{
	public WrongKeyException() {
		super();
	}
	@Override
	public String getMessage() {
		return "密码或者账号错误";
	}
}
