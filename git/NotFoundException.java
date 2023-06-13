package DreamFactory.Exception;

public class NotFoundException extends Exception{

	public NotFoundException() {
		super();
	}
	@Override
	public String getMessage() {
		return "未找到该用户";
	}
}
