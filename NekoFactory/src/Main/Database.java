package Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Database implements Serializable,Iterable<User>{
	private ArrayList<Adminstrator> adminstrators=new ArrayList<Adminstrator>();
	private ArrayList<Equipment> equiments=new ArrayList<Equipment>();
	private ArrayList<Factory> factorys=new ArrayList<Factory>();
	private ArrayList<Product> products=new ArrayList<Product>(); 
	private ArrayList<User> users=new ArrayList<User>();
	private ArrayList<Catagory> productCatagory;
	private ArrayList<Catagory> equipmentCatagory;
	private int userId;
	private int factoryId;
	private int productCatagoryId;
	private int productId;
	private int equipmentCatagoryId;
	private int equipmentId;
	public Database() {
		adminstrators=new ArrayList<Adminstrator>();
		equiments=new ArrayList<Equipment>();
		factorys=new ArrayList<Factory>();
		users=new ArrayList<User>();
		setProductCatagory(new ArrayList<Catagory>());
		setEquipmentCatagory(new ArrayList<Catagory>());
		adminstrators.add(new Adminstrator("庄昊天", "114514"));
		adminstrators.add(new Adminstrator("z", "z"));
		userId=0;
		factoryId=0;
		productCatagoryId=0;
		productId=0;
		equipmentCatagoryId=0;
		equipmentId=0;
	}
	public ArrayList<Adminstrator> getAdminstrators() {
		return adminstrators;
	}
	public ArrayList<Equipment> getEquiments() {
		return equiments;
	}
	public ArrayList<Factory> getFactorys() {
		return factorys;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers (ArrayList<User> users) {
		this.users=users;
	}
	public void setFactory(ArrayList<Factory> fac) {
		this.factorys=fac;
	}
	public void setProducts(ArrayList<Product> pro) {
		this.products=pro;
	}
	public void setEquipments(ArrayList<Equipment> eq) {
		this.equiments=eq;
	}
	////
	//数据的初始化
	public static void dataBaseZ() {
		try {
		File file=new File("Database.dat");
		FileOutputStream output=new FileOutputStream(file);
		ObjectOutputStream objectOutput=new ObjectOutputStream(output);
		objectOutput.writeObject(new Database());
		objectOutput.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public Iterator<User> iterator() {
		// TODO Auto-generated method stub
		return users.iterator();
	}
	public Iterator<Factory> iteratorF(){
		return factorys.iterator();
	}
	public Iterator<Product> iteratorP(){
		return products.iterator();
	}
	public Iterator<Equipment> iteratorE(){
		return equiments.iterator();
	}
	public ArrayList<Catagory> getEquipmentCatagory() {
		return equipmentCatagory;
	}
	public void setEquipmentCatagory(ArrayList<Catagory> equipmentCatagory) {
		this.equipmentCatagory = equipmentCatagory;
	}
	public ArrayList<Catagory> getProductCatagory() {
		return productCatagory;
	}
	public void setProductCatagory(ArrayList<Catagory> productCatagory) {
		this.productCatagory = productCatagory;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(int factoryId) {
		this.factoryId = factoryId;
	}
	public int getProductCatagoryId() {
		return productCatagoryId;
	}
	public void setProductCatagoryId(int productCatagoryId) {
		this.productCatagoryId = productCatagoryId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getEquipmentCatagoryId() {
		return equipmentCatagoryId;
	}
	public void setEquipmentCatagoryId(int equipmentCatagoryId) {
		this.equipmentCatagoryId = equipmentCatagoryId;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	
}
