package Main;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String key;
	private String code;
	private String name;
	private String faname;
	private String Tel;
	private String Z;
	private String faDes;
	private String faN;
	public User(String code,String key,String name,String Tel,String Z) {
		this.key=key;
		this.code=code;
		this.name=name;
		this.Tel=Tel;
		this.Z=Z;
		setFaname(null);
		setFaDes(null);
	}
	public User(int id,String code,String key,String Ownername,String Tel,String Z,String faName,String des) {
		this.id=id;
		this.code=code;
		this.setKey(key);
		this.name=Ownername;
		this.Tel=Tel;
		this.Z=Z;
		this.setFaname(faName);
		this.setFaDes(des);
	}
	public User(int id,String code,String key,String Ownername,String Tel,String Z,String faName,String des,String fan) {
		this.id=id;
		this.code=code;
		this.setKey(key);
		this.name=Ownername;
		this.Tel=Tel;
		this.Z=Z;
		this.setFaname(faName);
		this.setFaDes(des);
		this.faN=fan;//工厂状态
	}
	public String getMessage() {
		return id+"_"+code+"_"+name+"_"+Tel+"_"+Z;}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getTel() {
		return Tel;
	}
	public String getZ() {
		return Z;
	}
	public int getId() {
		return id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setCode(String code) {
		this.code=code;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setTel(String Tel) {
		this.Tel=Tel;
	}
	public String getFaDes() {
		return faDes;
	}
	public void setFaDes(String faDes) {
		this.faDes = faDes;
	}
	public String getFaname() {
		return faname;
	}
	public void setFaname(String faname) {
		this.faname = faname;
	}
	public String getFaN() {
		return faN;
	}
	public void setFaN(String faN) {
		this.faN = faN;
	}
}
