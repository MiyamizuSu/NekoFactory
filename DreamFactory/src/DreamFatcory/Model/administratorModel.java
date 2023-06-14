package DreamFatcory.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import DreamFactory.Exception.CatagoryNotFoundException;
import DreamFactory.Exception.NomalException;
import DreamFactory.Exception.NotFoundException;
import DreamFactory.Exception.WrongKeyException;
import Main.*;

public  class administratorModel implements Model{
	//登陆操作
	public boolean Log_in(String code,String key,ArrayList<Adminstrator> list) throws WrongKeyException,FileNotFoundException,IOException,ClassNotFoundException{
		try {
		for(Adminstrator k:list) {
			if(k.getCode().equals(code)) {
				if(k.getKey().equals(key)) {
					return true;
				}
				else {
					throw new WrongKeyException();
					
				}
			}
		}
	     	throw new WrongKeyException();
	}
		catch(WrongKeyException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
	}
	public User getAUser(String name,ArrayList<User> list) throws NotFoundException{
		try {
		for(User k:list) {
			if(k.getName().equals(name)) {
				return k;
			}
		}
		throw new NotFoundException();
		}
		catch(NotFoundException e) {
			throw e;
		}
	}
	public ArrayList<User> deleteUser(ArrayList<User> listData,ArrayList<User> list){
		Iterator<User> iterator=list.iterator();
		while(iterator.hasNext()) {
			User h=iterator.next();
			Iterator<User> iterator1=listData.iterator();
			while(iterator1.hasNext()) {
				User k=iterator1.next();
				if(k.getName().equals(h.getName())) {
					iterator1.remove();
				}
			}
		}
		return listData;
	}
	//修改用户信息
	public ArrayList<User> changeMessage(ArrayList<User>listData,ArrayList<User>list,String key,String name,String Tel,String faName,String des) {
		Iterator<User> iterator1=listData.iterator();
		Iterator<User> iterator=list.iterator();
		while(iterator.hasNext()) {
			User h=iterator.next();
			while(iterator1.hasNext()) {
				User k=iterator1.next();
				if(k.getCode().equals(h.getCode())) {
					k.setName(name);
					k.setKey(key);
					k.setTel(Tel);
					k.setFaname(faName);
					k.setFaDes(des);
				}
			}
		}
		return listData;
	}
	//
	public Factory getAFactory(String name,ArrayList<Factory>list)throws NotFoundException {
		try {
		for(Factory k:list) {
			if(k.getName().equals(name)) {
				return k;
			}
		}
		throw new NotFoundException();
		}
		catch(NotFoundException e) {
			throw e;
		}
	}
	//改变工厂状态
	public ArrayList<Factory> changeNetural(ArrayList<Factory>list,ArrayList<Factory>listData){
		Iterator<Factory> iterator=list.iterator();
		while(iterator.hasNext()) {
			Iterator<Factory> iteratorData=listData.iterator();
			Factory k=iterator.next();
			while(iteratorData.hasNext()) {
				Factory h=iteratorData.next();
				if(h.getName().equals(k.getName())){
					if(h.getNetural().equals("关停")) {
						h.setNetural("正常");
						}
						break;
					}
					if(h.getNetural().equals("正常")) {
						h.setNetural("关停");
						break;
					}
				}
			}
		return listData;
		}
	//对应的用户也要更改
	public ArrayList<User> changeNetural1(ArrayList<User>listData,ArrayList<Factory>list){
		Iterator<Factory> iteratorF=list.iterator(); 
		while(iteratorF.hasNext()) {
		Factory h=iteratorF.next();
		Iterator<User>iteratorU=listData.iterator();
		while(iteratorU.hasNext()) {
			User u=iteratorU.next();
			if(u.getFaname().equals(h.getName())) {
				if(u.getFaN().equals("关停")) {
					u.setFaN("正常");
					break;
				}
				if(u.getFaN().equals("正常")) {
					u.setFaN("关停");
				}
			}
	}
	}
		return listData;
}
	//新增类别
	public ArrayList<Catagory> addCatagory(ArrayList<Catagory> list,int id,String name){
		Catagory catagory=new Catagory(id,name);
		list.add(catagory);
		return list;
	}
	//查找产品类
	public Catagory getCatagory(ArrayList<Catagory> list,String name) throws NotFoundException{
		try {
		Iterator<Catagory> iterator=list.iterator();
		while(iterator.hasNext()){
			Catagory k=iterator.next();
			if(k.getName().equals(name)) {
				return k;
			}		
		}
		throw new NotFoundException();
	}
		catch(NotFoundException e) {
			throw e;
		}
	}
	//删除类别
	public ArrayList<Catagory> removeProductCatagory(ArrayList<Catagory> list,ArrayList<Catagory>datalist){
		Iterator<Catagory>iterator=list.iterator();
		while(iterator.hasNext()) {
			Catagory k=iterator.next();
			Iterator<Catagory> iteratorData=datalist.iterator();
			while(iteratorData.hasNext()) {
				Catagory h=iteratorData.next();
				if(h.getName().equals(k.getName())) {
					iteratorData.remove();
				}
			}
		}
		return datalist;
	}
	//新建产品
	public ArrayList<Product> addNewProduct(ArrayList<Product> list,int productId,String name,String catagory,String code,String size,String Des){
		Product ans=new Product(productId,name,catagory,code,size,Des);
		list.add(ans);
		return list;
	}
	//按类别查找产品
	public ArrayList<Product> getProduct(ArrayList<Product> list,String name)throws NotFoundException{
		try {
		ArrayList<Product> ans=new ArrayList<Product>();
		Iterator<Product> iterator=list.iterator();
		while(iterator.hasNext()) {
			Product k=iterator.next();
			if(k.getCatagory().equals(name)) {
				ans.add(k);
			}
		}
		if(ans.size()==0) {
			throw new NotFoundException();
		}
		return ans;
		}
		catch(NotFoundException e) {
			throw e;
		}
	}
	//删除产品
	public ArrayList<Product> removeProduct(ArrayList<Product>listdata,ArrayList<Product>list){
		Iterator<Product> iterator=list.iterator();
		while(iterator.hasNext()) {
			Product k=iterator.next();
			Iterator<Product> iteratorData=listdata.iterator();
			while(iteratorData.hasNext()) {
				Product h=iteratorData.next();
				if(k.getCode().equals(h.getCode())) {
					iteratorData.remove();
				}
			}
		}
		return listdata;
	}
	//修改产品
	public ArrayList<Product> changeProduct(ArrayList<Product> listdata,ArrayList<Product>list,String name,String catagory,String size,String des){
		Iterator<Product> iterator=list.iterator();
		while(iterator.hasNext()) {
			Iterator<Product> iteratorData=listdata.iterator();
			Product k=iterator.next();
			while(iteratorData.hasNext()) {
				Product h=iteratorData.next();
				if(h.getCode().equals(k.getCode())) {
					h.setName(name);
					h.setCatagory(catagory);
					h.setSize(size);
					h.setDescription(des);
				}
			}
		}
		return listdata;
	}
	//新建设备类
	public ArrayList<Catagory> addNewEquipmentCatagory(ArrayList<Catagory>eCatagory,int equipmentCatagoryId,String name){
		Catagory ans=new Catagory(equipmentCatagoryId,name);
		eCatagory.add(ans);
		return eCatagory;
	}
	//查找设备类
	public Catagory getEquipmentCatagory(ArrayList<Catagory> list,String name) throws NotFoundException{
		try {
		Iterator<Catagory> iterator=list.iterator();
		while(iterator.hasNext()){
			Catagory k=iterator.next();
			if(k.getName().equals(name)) {
				return k;
			}
	      }
		throw new NotFoundException();
		}
		catch(NotFoundException e) {
			throw e;
		}
	}
	//新建设备
	public ArrayList<Equipment> addNewEquipment(ArrayList<Equipment>list,int equipmentId,String name,String code,String catagory,String size,String description) {
		Equipment ans=new Equipment(equipmentId,name, code, catagory, size, description);
		list.add(ans);
		return list;
	}
	//查找设备
	public ArrayList<Equipment> getEquipment(ArrayList<Equipment>list,String catagoryName)throws NotFoundException{
		try {
		Iterator<Equipment> iterator=list.iterator();
		ArrayList<Equipment> ans=new ArrayList<Equipment>();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			if(k.getCatagory().equals(catagoryName)) {
				ans.add(k);
			}
		}
		if(ans.size()==0) {
			throw new NotFoundException();
		}
		return ans;
		}
		catch(NotFoundException e) {
			throw e;
		}
	}
	//删除设备
	public ArrayList<Equipment> removeEquipment(ArrayList<Equipment>listdata,ArrayList<Equipment>list){
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
	//修改设备信息
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
	//展示所有空闲设备
	public ArrayList<Equipment> showAllFreeEquipment(ArrayList<Equipment> list){
		Iterator<Equipment> iterator=list.iterator();
		ArrayList<Equipment> ans=new ArrayList<Equipment>();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			if(k.getBelong().equals("无")&&k.getNetural2().equals("未被租用")) {
				ans.add(k);
			}
		}
		return ans;
	}
	//租用设备
	public ArrayList<Equipment> lendEquipment(ArrayList<Equipment>listdata,ArrayList<Equipment>list,String belong){
		Iterator<Equipment>iterator=list.iterator();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			Iterator<Equipment> iteratorData=listdata.iterator();
			while(iteratorData.hasNext()) {
				Equipment h=iteratorData.next();
				if(k.getCode().equals(h.getCode())) {
					h.setBelong(belong);
					h.setNetural2("已被租用");
					if(h.getNetural1().equals("关机")) {
						h.setNetural1("开机");
					}
				}
			}
		}
		return listdata;
	}
}
