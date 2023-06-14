package Main;

import java.io.Serializable;

public class Equipment implements Serializable{
	private int id;
	private String netural1;//开关机
	private String netural2;//租用
	private String name;
	private String code;
	private String catagory;
	private String size;
	private String description;
	private String belong;
	public Equipment(int id,String name,String code,String catagory,String size,String description,String belong) {
		this.id=id;
		this.setNetural1("关机");
		this.setNetural2("工厂设备");
		this.setName(name);
		this.setCode(code);
		this.setCatagory(catagory);
		this.setSize(size);
		this.setDescription(description);
		this.setBelong(belong);
	}
	public Equipment(int id,String name,String code,String catagory,String size,String description) {
		this.id=id;
		this.setNetural1("关机");
		this.setNetural2("未被租用");
		this.setName(name);
		this.setCode(code);
		this.setCatagory(catagory);
		this.setSize(size);
		this.setDescription(description);
		belong="无";
	}
//	public String getMessage() {
//		return id+"_"+name+"_"+code+"_"+catagory+"_"+size+"_"+description+"_"+belong;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNetural1() {
		return netural1;
	}

	public void setNetural1(String netural1) {
		this.netural1 = netural1;
	}

	public String getNetural2() {
		return netural2;
	}

	public void setNetural2(String netural2) {
		this.netural2 = netural2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}
}
