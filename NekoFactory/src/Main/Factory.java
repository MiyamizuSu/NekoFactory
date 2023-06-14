package Main;

import java.io.Serializable;

public class Factory implements Serializable{
	private int id;
	private String name;
	private String description;
	private String netural;
	private String ownerName;
	private String ownerTel;
	private String ownerZ;
	public Factory(int id,User user) {
		this.id=id;
		description=user.getFaDes();
		name=user.getFaname();
		netural=user.getFaN();
		ownerName=user.getName();
		ownerTel=user.getTel();
		ownerZ=user.getCode();
	}
//	public String getMessage() {
////		return id+"_"+name+"_"+description+"_"+ownerName+"_"+ownerTel+"_"+ownerZ+"_"+netural;
//	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public String getOwnerTel() {
		return ownerTel;
	}
	public String getOwnerZ() {
		return ownerZ;
	}
	public String getNetural() {
		return netural;
	}
	public void setNetural(String netural) {
		this.netural = netural;
	}
	
}
