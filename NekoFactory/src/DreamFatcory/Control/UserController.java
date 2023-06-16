package DreamFatcory.Control;

import java.io.File;
import java.io.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import DreamFactory.Exception.MultipleSelectException;
import DreamFactory.Exception.NomalException;
import DreamFactory.Exception.NothingContainException;
import DreamFactory.Exception.SameCodeException;
import DreamFactory.Exception.SomethingNullException;
import DreamFactory.Exception.WrongKeyException;
import DreamFatcory.Model.UserModel;
import Main.Database;
import Main.Equipment;
import Main.User;

public class UserController implements Controller{
	private UserModel model2;
	private static UserController instance;
	private Data_operations operation;
	private UserController() {
		model2=new UserModel();
		instance=null;
		operation=new Data_operations();
	}
	public static UserController getInstance() {
		if(instance==null) {
			return new UserController();
		}
		else {
			return instance;
		}
	}
	//注册
	public boolean registered(String filename,String code,String key,String name,String Tel,String Z,String faName,String des)throws SomethingNullException,SameCodeException{
		try {
			if(code.isEmpty()||key.isEmpty()||Tel.isEmpty()||Z.isEmpty()||faName.isEmpty()||des.isEmpty()) {
				throw new SomethingNullException();
			}
			Database database=operation.ReadData(filename);
			ArrayList<User> have=database.getUsers();
			Iterator<User>iterator=have.iterator();
			while(iterator.hasNext()) {
				User k=iterator.next();
				if(k.getCode().equals(code)) {
					throw new SameCodeException(); 
				}
			}
			int userId=database.getUserId();
			have=model2.registered(have,userId,code, key, name, Tel, Z, faName, des);
			userId++;
			database.setUsers(have);
			database.setUserId(userId);
			operation.updateData(filename, database);
			return true;
		}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(SameCodeException e) {
			throw e;
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	//用户登录操作
	public boolean Log_in(String code, String key,String filename) throws NomalException{
		try {
		File file=new File(filename);
		FileInputStream input=new FileInputStream(file);
		ObjectInputStream objectInput=new ObjectInputStream(input);
		Database database=(Database)objectInput.readObject();
		if(model2.Log_in(database, code, key)) {
			return true;
		                }
		}
		catch(NomalException e) {
			throw e;
		}
		catch(WrongKeyException e) {
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//
	public User KnowWho(String filename,String code) {
		try {
			Database database=operation.ReadData(filename);
			ArrayList<User>list=database.getUsers();
			Iterator<User> iterator=list.iterator();
			while(iterator.hasNext()) {
				User k=iterator.next();
				if(k.getCode().equals(code)) {
					return k;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//显示用户所拥有的设备
	public ArrayList<Equipment> ShowAllUserEquipment(String filename,String code){
		try {
			ArrayList<Equipment>ans=new ArrayList<Equipment>();
			Database database =operation.ReadData(filename);
			User now=this.KnowWho(filename, code);
			ans=model2.showAllUserEquipments(database.getEquiments(), now);
			return ans;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//用户注册设备
	public void addEquipment(String filename,String name,String code,String catagory,String size,String des,String userName) throws SameCodeException,SomethingNullException{
		try {
			if(catagory==null) {
				throw new SomethingNullException();
			}
			if(name.isEmpty()||code.isEmpty()||catagory.isEmpty()||size.isEmpty()||des.isEmpty()||userName.isEmpty()) {
				throw new SomethingNullException();
			}
		Database database=operation.ReadData(filename);
		ArrayList<Equipment> listdata=database.getEquiments();
		int equipmentId=database.getEquipmentId();
		User k=this.KnowWho(filename,userName);
		Iterator<Equipment>check=listdata.iterator();
		while(check.hasNext()) {
			Equipment h=check.next();
			if(h.getCode().equals(code)) {
				throw new SameCodeException();
			}
		}
		listdata=model2.addEquipment(listdata,equipmentId ,name, code, catagory, des,k.getFaname());
		equipmentId++;
		database.setEquipments(listdata);
		database.setEquipmentId(equipmentId);
		operation.updateData(filename, database);
		}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(SameCodeException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//检测是否为空
	public void checkNull(ArrayList<Equipment> equipments) throws NothingContainException{
		try {
		if(equipments.size()==0) {
			throw new NothingContainException();
		}
		}
		catch(NothingContainException e) {
			throw e;
		}
	}
	//检测是否多重选择
	public void checkMultiple(ArrayList<Equipment> equipments) throws MultipleSelectException{
		try {
		if(equipments.size()>1) {
			throw new MultipleSelectException();
		}
		}
		catch(MultipleSelectException e) {
			throw e;
		}	
	}
	//检测是否为共有设备
	public void checkPublicEquipment(ArrayList<Equipment>list) throws NomalException{
		try {
		Iterator<Equipment> iterator=list.iterator();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
				if(k.getNetural2().equals("已被租用")) {
					throw new NomalException();
			}
		}
		}
		catch(NomalException e) {
			throw e;
		}
	}
	//用户删除设备
	public void removeEquipment(String filename,ArrayList<Equipment>list) throws NomalException{
		try {
		Database database=operation.ReadData(filename);
		ArrayList<Equipment>datalist=database.getEquiments();
		Iterator<Equipment>iterator=list.iterator();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			if(k.getNetural2().equals("已被租用"))
			throw new NomalException();
		}
		datalist=model2.removeEquipment(datalist, list);
		database.setEquipments(datalist);
		operation.updateData(filename, database);
	}
		catch(NomalException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//用户修改设备
	public void changeEquipment(String filename,String code,String name,String Catagory,String size,String des,String belong)throws SomethingNullException{
		try {
			if(Catagory==null) {
				throw new SomethingNullException();
			}
			if(code.isEmpty()||name.isEmpty()||Catagory.isEmpty()||size.isEmpty()||des.isEmpty()||belong.isEmpty()) {
				throw new SomethingNullException();
			}
		Database database=operation.ReadData(filename);
		ArrayList<Equipment> listdata=database.getEquiments();
		listdata=model2.changeEquipment(listdata, code, name, Catagory, size, des, belong);
		database.setEquipments(listdata);
		operation.updateData(filename, database);
		}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//用户开/关机
	public void turnOn_Off(String filename,ArrayList<Equipment>list) {
		try {
			Database database=operation.ReadData(filename);
			ArrayList<Equipment> listData=database.getEquiments();
			listData=model2.turnOn_Off(list, listData);
			database.setEquipments(listData);
			operation.updateData(filename, database);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//用户归还设备
	public void returnEquipment(String filename,ArrayList<Equipment>list) throws NomalException,NothingContainException {
		try {
			if(list.size()==0) {
				throw new NothingContainException();
			}
			Iterator<Equipment> iterator=list.iterator();
			while(iterator.hasNext()) {
				Equipment k=iterator.next();
				if(k.getNetural2().equals("工厂设备"))
				{
					throw new NomalException();
				}
			}
			Database database=operation.ReadData(filename);
			ArrayList<Equipment>listdata= database.getEquiments();
			listdata=model2.returnEquipment(listdata, list);
			database.setEquipments(listdata);
			operation.updateData(filename, database);
		}
		catch(NothingContainException e) {
			throw e;
		}
		catch(NomalException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
