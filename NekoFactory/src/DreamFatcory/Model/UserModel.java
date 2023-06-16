package DreamFatcory.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import DreamFactory.Exception.NomalException;
import DreamFactory.Exception.WrongKeyException;
import Main.*;

public class UserModel implements Model{
	public UserModel() {
		
	}
	//注册
	public ArrayList<User> registered(ArrayList<User>list,int userId,String code,String key,String name,String Tel,String Z,String faName,String des){
			User ans=new User(userId,code,key,name,Tel,Z,faName,des,"关停");
			list.add(ans);
			return list;
	}
	//登录
	public boolean Log_in(Database database,String code,String key) throws WrongKeyException,FileNotFoundException,IOException,ClassNotFoundException,NomalException{
		try {
			for(User k:database.getUsers()) {
				if(code.equals(k.getCode())) {
					if(k.getZ().equals("经销商")) {
						throw new NomalException();
					}
					if(key.equals(k.getKey())){
						return true;
					}
				}
			}
			throw new WrongKeyException();
		}
		catch(Exception e) {
			throw e;
		}
	} 
	//展示用户所拥有的设备
	public ArrayList<Equipment> showAllUserEquipments(ArrayList<Equipment> list,User k){
		ArrayList<Equipment> ans=new ArrayList<Equipment>();
		Iterator<Equipment> iterator=list.iterator();
		while(iterator.hasNext()) {
			Equipment e=iterator.next();
			if(e.getBelong().equals(k.getFaname())) {
				ans.add(e);
			}
		}
		return ans;
	}
	//工厂新建设备
	public ArrayList<Equipment> addEquipment(ArrayList<Equipment>list,int equipmentId,String name,String code,String catagory,String des,String belong) {
		
		Equipment ans=new Equipment(equipmentId,name, code, catagory, catagory, des,belong);
		list.add(ans);
		return list;
	}
	public ArrayList<Equipment> removeEquipment(ArrayList<Equipment> listdata, ArrayList<Equipment> list) {
		Iterator<Equipment> iterator=list.iterator();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			Iterator<Equipment> iteratorData=listdata.iterator();
			while(iteratorData.hasNext()) {
				Equipment h=iteratorData.next();
				if(k.getCode().equals(h.getCode())) {
					iteratorData.remove();
				}
			}
		}
		return listdata;
	}
	//用户修改设备
	public ArrayList<Equipment> changeEquipment(ArrayList<Equipment> list,String code,String name,String catagory,String size,String des,String belong){
		Iterator<Equipment> iterator=list.iterator();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			if(k.getCode().equals(code)) {
				k.setName(name);
				k.setCatagory(catagory);
				k.setSize(size);
				k.setDescription(des);
				k.setBelong(belong);
			}
		}
		return list;
	}
	//用户开关机
	public ArrayList<Equipment> turnOn_Off(ArrayList<Equipment>list,ArrayList<Equipment>listdata){
		Iterator<Equipment>iterator=list.iterator();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			Iterator<Equipment> iteratorData=listdata.iterator();
			while(iteratorData.hasNext()) {
				Equipment h=iteratorData.next();
				if(k.getCode().equals(h.getCode())) {
					if(h.getNetural1().equals("关机")) {
						h.setNetural1("开机");
						break;
					}
					if(h.getNetural1().equals("开机")) {
						h.setNetural1("关机");
						break;
					}
				}
			}
		}
		return listdata;
	}
	//用户归还设备
	public ArrayList<Equipment> returnEquipment(ArrayList<Equipment>listdata,ArrayList<Equipment>list){
		Iterator<Equipment> iterator=list.iterator();
		while(iterator.hasNext()) {
			Iterator<Equipment> iteratorData=listdata.iterator();
			Equipment k=iterator.next();
			while(iteratorData.hasNext()) {
				Equipment h=iteratorData.next();
				if(k.getCode().equals(h.getCode())) {
					h.setBelong("无");
					h.setNetural2("未被租用");
					h.setNetural1("关机");
				}
			}
		}
		return listdata;
	}
}
