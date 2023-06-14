package Main;

import java.io.Serializable;

public class Adminstrator implements Serializable{
	private String code;
	private String key;
	private int judgeNumber=1;
	public Adminstrator(String code,String key) {
		this.code=code;
		this.key=key;
	}
	public String getCode() {
		return code;
	}
	public String getKey() {
		return key;
	}
	

}
