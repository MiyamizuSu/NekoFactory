package Main;

import java.io.Serializable;

public class Product implements Serializable{
	private int id;
	private String code;
	private String name;
	private String catagory;
	private String size;
	private String description;
	public Product(int id,String name,String catagory,String code,String size,String Des) {
		this.id=id;
		this.setCode(code);
		this.setName(name);
		this.setCatagory(catagory);
		this.setSize(size);
		this.setDescription(Des);
	}
	public String getMessage() {
		return id+"_"+code+"_"+name+"_"+catagory+"_"+size+"_"+description;
	}
	public int getId() {
		return id;
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
	public String getName() {
		return name;
	}
	public String getSize() {
		return size;
	}
	public String getDescription() {
		return description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
