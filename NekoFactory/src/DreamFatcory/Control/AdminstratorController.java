package DreamFatcory.Control;

import java.util.ArrayList;
import java.util.Iterator;

import DreamFactory.Exception.MultipleSelectException;
import DreamFactory.Exception.NomalException;
import DreamFactory.Exception.NotFoundException;
import DreamFactory.Exception.NothingContainException;
import DreamFactory.Exception.SameCodeException;
import DreamFactory.Exception.SomethingNullException;
import DreamFactory.Exception.WasQuotedException;
import DreamFactory.Exception.WrongKeyException;
import DreamFatcory.Model.administratorModel;
import Main.*;

public class AdminstratorController implements Controller{
	private administratorModel model1;
	private static AdminstratorController instance;
	private Data_operations operations;
	//单例模式的实现
	private AdminstratorController() {
		model1=new administratorModel();
		instance=null;
		operations=new Data_operations();
	}	
	public static AdminstratorController getInstance () {
		if(instance==null) {
			return new AdminstratorController();
		}
		else
			return instance;
	}
	//登陆操作
	public boolean Log_in(String code,String key,int judgeNumber,String filename){
		ArrayList<Adminstrator> list;
		try {
		list=operations.ReadData(filename).getAdminstrators();
		if(model1.Log_in(code, key,list)) {
			return true;
		}
		}
		catch(WrongKeyException e) {
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	//
	public ArrayList<User> showAllUser(String filename) {
		try {
			Database database=operations.ReadData(filename);
			return database.getUsers();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//根据名字查找某位用户
	public User GetUser(String filename,String Name)throws NotFoundException{
		try {
			Database database=operations.ReadData(filename);
			return model1.getAUser(Name, database.getUsers());
		}
		catch(NotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//删除所选择的用户
	public void removeUser(String filename,ArrayList<User> list)throws NomalException{
		try {
			Database data=operations.ReadData(filename);
			this.checkUser(list, data.getEquiments());
			data.setUsers(model1.deleteUser(data.getUsers(),list));
			data.setEquipments(model1.deleteEquipment(data.getEquiments(),list));
			operations.updateData(filename, data);
		}
		catch(NomalException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	//检查用户信息
	public void checkUser(ArrayList<User>list,ArrayList<Equipment>listdata) throws NomalException{
		Iterator<User> iterator=list.iterator();
		while(iterator.hasNext()) {
			User k=iterator.next();
			Iterator<Equipment>iteratorData=listdata.iterator();
			while(iteratorData.hasNext()){
				Equipment h=iteratorData.next();
				if(k.getFaname().equals(h.getBelong())) {
					if(h.getNetural2().equals("已被租用")) {
						throw new NomalException();
					}
				}
			}
		}
	}
	//检查选择为空问题
	public void checkNullu(ArrayList<User> users) throws NothingContainException{
		try {
			if(users.size()==0) {
				throw new NothingContainException();
			}
		}
		catch(NothingContainException e) {
			throw e;
		}
	}
	//检查是否为多重选择
	public void checkMultipleu(ArrayList<User> users) throws MultipleSelectException{
		try {
		if(users.size()>1) {
			throw new MultipleSelectException();
		}
		}
		catch(MultipleSelectException e) {
			throw e;
		}	
	}
	//修改用户信息
	public void changeMessage(String filename,String key,String Ownername,String Tel,String faName,String des,ArrayList<User>list)throws SomethingNullException{     
		try {
			if(list.size()==0) {
				throw new NothingContainException();
			}
		if(key.isEmpty()||Ownername.isEmpty()||Tel.isEmpty()||faName.isEmpty()||des.isEmpty()) {
			throw new SomethingNullException();
		}
		Database database=operations.ReadData(filename);
		database.setUsers(model1.changeMessage(database.getUsers(),list,key,Ownername,Tel,faName,des));
		operations.updateData(filename, database);
	}
		catch(SomethingNullException e) {
			throw e;
		}
	catch(Exception e) {
		e.printStackTrace();
	}
    }
	//全部工厂的信息
	public ArrayList<Factory> showAllFactory(String filename){
		try {
			Database database=operations.ReadData(filename);
			ArrayList<Factory> factorys=database.getFactorys();
			ArrayList<User> users=database.getUsers();
			int factoryId=database.getFactoryId();
			factorys.clear();
			for(User k:users) {
				if(k.getZ().equals("云工厂")) {
				Factory want=new Factory(factoryId,k);
				factorys.add(want);
				factoryId++;
				}
			}
			database.setFactory(factorys);
			database.setFactoryId(factoryId);
			operations.updateData(filename, database);
			return factorys;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//查找工厂
	public Factory getFactory(String filename,String Name) throws NotFoundException{
		try {
			Database database=operations.ReadData(filename);
			Factory ans=model1.getAFactory(Name, database.getFactorys());
			return ans;
		}
		catch(NotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//改变工厂状态 
	public void changeNetural(ArrayList<Factory>list,String filename)throws NothingContainException{
		try {
			if(list.size()==0) {
				throw new NothingContainException();
			}
		Database database=operations.ReadData(filename);
		database.setFactory(model1.changeNetural(list,database.getFactorys()));
		database.setUsers(model1.changeNetural1(database.getUsers(), list));
		operations.updateData(filename, database);
		}
		catch(NothingContainException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//展示所有产品类别
	public ArrayList<Catagory> showAllProductCatagory(String filename) {
		try {
		Database database=operations.ReadData(filename);
		return database.getProductCatagory();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//新建产品类别
	public void addCatagory(String filename,String name) throws SomethingNullException,SameCodeException{
		try {
			if(name.isEmpty()) {
				throw new SomethingNullException();
			}
			
			Database database=operations.ReadData(filename);
			int productCatagoryId=database.getProductCatagoryId();
			ArrayList<Catagory> ans=database.getProductCatagory();
			Iterator<Catagory> iterator=ans.iterator();
			while((iterator.hasNext())) {
				Catagory k=iterator.next();
				if(k.getName().equals(name)) {
					throw new SameCodeException();
				}
			}
			ans=model1.addCatagory(ans,productCatagoryId, name);
			productCatagoryId++;
			database.setProductCatagory(ans);
			database.setProductCatagoryId(productCatagoryId);
			operations.updateData(filename, database);
		}
		catch(SameCodeException e) {
			throw e;
		}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//
	//查找产品类
	public Catagory getACatagory(String filename,String name) throws NotFoundException{
		try {
		Database database=operations.ReadData(filename);
		Catagory ans=model1.getCatagory(database.getProductCatagory(), name);
		return ans;
		}
		catch(NotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//检查是否为空
	public void checkNullc(ArrayList<Catagory> catagorys) throws NothingContainException{
		try {
			if(catagorys.size()==0) {
				throw new NothingContainException();
			}
		}
		catch(NothingContainException e) {
			throw e;
		}
	}
	//检查是否为多重选择
	public void checkMultiplec(ArrayList<Catagory> catagorys) throws MultipleSelectException{
		try {
		if(catagorys.size()>1) {
			throw new MultipleSelectException();
		}
		}
		catch(MultipleSelectException e) {
			throw e;
		}	
	}
	//删除产品类
	public void removeProductCatagory(ArrayList<Catagory>list,String filename) throws WasQuotedException{
		try {
		Database database=operations.ReadData(filename);
		ArrayList<Catagory>datalist=database.getProductCatagory();
		ArrayList<Product>check=database.getProducts();
		Iterator<Catagory> iterator=list.iterator();
		while(iterator.hasNext()) {
			Iterator<Product> iteratorC=check.iterator();
			Catagory k=iterator.next();
			while(iteratorC.hasNext()) {
				Product h=iteratorC.next();
				if(k.getName().equals(h.getCatagory())) {
					throw new WasQuotedException();
				}
			}
		}
		datalist=model1.removeProductCatagory(list, datalist);
		database.setProductCatagory(datalist);;
		operations.updateData(filename, database);
	}
		catch(WasQuotedException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//产品展示
	public ArrayList<Product> showAllProduct(String filename){
		try {
			Database database=operations.ReadData(filename);
			return database.getProducts();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//产品新增
	public void addNewProduct(String filename,String name,String catagory,String code,String size,String Des) throws SameCodeException,SomethingNullException{
		try {
			if(catagory==null) {
				throw new SomethingNullException();
			}
			if(name.isEmpty()||catagory.isEmpty()||code.isEmpty()||size.isEmpty()||Des.isEmpty()) {
				throw new SomethingNullException();
			}
			Database database=operations.ReadData(filename);
			ArrayList<Product> list=database.getProducts();
			int productId=database.getProductId();
			Iterator<Product> iterator=list.iterator();
			while(iterator.hasNext()) {
				Product k=iterator.next();
				if(k.getCode().equals(code)) {
					throw new SameCodeException();
				}
			}
			list=model1.addNewProduct(list,productId, name, catagory, code, size, Des);
			productId++;
			database.setProducts(list);
			database.setProductId(productId);
			operations.updateData(filename, database);
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
	//产品查找
	public ArrayList<Product> getProduct(String filename,String catagoryName)throws NotFoundException{
		try {
		Database database=operations.ReadData(filename);
		ArrayList<Product> list=database.getProducts();
		ArrayList<Product>ans=model1.getProduct(list, catagoryName);
		return ans;
		}
		catch(NotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//检查是否为空
	public void checkNullp(ArrayList<Product> products) throws NothingContainException{
		try {
			if(products.size()==0) {
				throw new NothingContainException();
			}
		}
		catch(NothingContainException e) {
			throw e;
		}
	}
	//检查是否为多重选择
	public void checkMultiplep(ArrayList<Product> products) throws MultipleSelectException{
		try {
		if(products.size()>1) {
			throw new MultipleSelectException();
		}
		}
		catch(MultipleSelectException e) {
			throw e;
		}	
	}
	//产品删除
	public void removeProduct(String filename,ArrayList<Product>list){
		try {
			Database database=operations.ReadData(filename);
			ArrayList<Product> datalist=database.getProducts();
			datalist=model1.removeProduct(datalist, list);
			database.setProducts(datalist);
			operations.updateData(filename, database);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//产品修改
	public void changeProduct(ArrayList<Product> list,String filename,String name,String catagory,String size,String des) throws SomethingNullException{
		try {
			if(name.isEmpty()||catagory.isEmpty()||size.isEmpty()||des.isEmpty()) {
				throw new SomethingNullException();
			}
		Database database=operations.ReadData(filename);
		ArrayList<Product> listdata=database.getProducts();
		listdata=model1.changeProduct(listdata, list,name,catagory,size,des);
		database.setProducts(listdata);	
		operations.updateData(filename, database);
	}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//设备类展示
	public ArrayList<Catagory> ShowAllEquipmentCatagory(String filename) {
		try {
			Database database=operations.ReadData(filename);
			ArrayList<Catagory> eCatagory=database.getEquipmentCatagory();
			return eCatagory;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//设备类的新建
	public void addEquipmentCatagory(String filename,String name) throws SameCodeException,SomethingNullException{
		try {
			if(name.isEmpty()) {
				throw new SomethingNullException();
			}
			Database database=operations.ReadData(filename);
			ArrayList<Catagory> eCatagory=database.getEquipmentCatagory();
			int equipmentCatagoryId=database.getEquipmentCatagoryId();
			Iterator<Catagory> iterator=eCatagory.iterator();
			while(iterator.hasNext()) {
				Catagory k=iterator.next();
				if(k.getName().equals(name)) {
					throw new SameCodeException();
				}
			}
			eCatagory=model1.addNewEquipmentCatagory(eCatagory,equipmentCatagoryId,name);
			equipmentCatagoryId++;
			database.setEquipmentCatagory(eCatagory);
			database.setEquipmentCatagoryId(equipmentCatagoryId);
			operations.updateData(filename, database);
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
	//查找设备类
	public Catagory getAEquipmentCatagory(String filename,String name) throws NotFoundException{
		try {
		Database database=operations.ReadData(filename);
		Catagory ans=model1.getEquipmentCatagory(database.getEquipmentCatagory(),name);
		return ans;
		}
		catch(NotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//删除设备类
	public void removeEquipmentCatagory(ArrayList<Catagory>list,String filename) throws WasQuotedException{
		try {
		Database database=operations.ReadData(filename);
		ArrayList<Catagory>datalist=database.getEquipmentCatagory();
		ArrayList<Equipment> check=database.getEquiments();
		Iterator<Catagory> iterator=list.iterator();
		while(iterator.hasNext()) {
			Catagory k=iterator.next();
			Iterator<Equipment> iteratorC=check.iterator();
			while(iteratorC.hasNext()) {
				Equipment h=iteratorC.next();
				if(h.getCatagory().equals(k.getName())) {
					throw new WasQuotedException();
				}
			}
		}
		datalist=model1.removeProductCatagory(list, datalist);
		database.setEquipmentCatagory(datalist);;
		operations.updateData(filename, database);
	}
		catch(WasQuotedException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//展示所有设备
	public ArrayList<Equipment> showAllEquipments(String filename){
		try {
		Database database=operations.ReadData(filename);
		ArrayList<Equipment> list=database.getEquiments();
		return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//新增设备
	public void addNewEquipment(String filename,String name,String code,String catagory,String size,String description)throws SameCodeException,SomethingNullException {
		try {
			if(catagory==null) {
				throw new SomethingNullException();
			}
			if(name.isEmpty()||code.isEmpty()||catagory.isEmpty()||size.isEmpty()||description.isEmpty()) {
				throw new SomethingNullException();
			}
			Database database=operations.ReadData(filename);
			ArrayList<Equipment> list=database.getEquiments();
			int equipmentId=database.getEquipmentId();
			Iterator<Equipment>iterator=list.iterator();
			while(iterator.hasNext()) {
				Equipment k=iterator.next();
				if(k.getCode().equals(code)) {
					throw new SameCodeException();
				}
			}
			list=model1.addNewEquipment(list,equipmentId, name, code, catagory, size, description);
			equipmentId++;
			database.setEquipments(list);
			database.setEquipmentId(equipmentId);
			operations.updateData(filename, database);
		}
		catch(SameCodeException e) {
			throw e;
		}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//查找设备
	public ArrayList<Equipment> getEquipment(String filename,String catagoryName)throws NotFoundException{
		try {
			Database database=operations.ReadData(filename);
			ArrayList<Equipment> ans=model1.getEquipment(database.getEquiments(), catagoryName);
			return ans;
		}
		catch(NotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//删除设备
	public void removeEquipment(ArrayList<Equipment>list,String filename){
		try {
		Database database=operations.ReadData(filename);
		ArrayList<Equipment>datalist=database.getEquiments();
		datalist=model1.removeEquipment(datalist, list);
		database.setEquipments(datalist);;
		operations.updateData(filename, database);
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//检查选择为空问题
	public void checkNull(ArrayList<Equipment> equipment) throws NothingContainException{
		try {
			if(equipment.size()==0) {
				throw new NothingContainException();
			}
		}
		catch(NothingContainException e) {
			throw e;
		}
	}
	//检查是否为多重选择
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
	//检查是否为工厂设备
	public void checkAgain(ArrayList<Equipment>equipments)throws NomalException {
		try {
		Iterator<Equipment>iterator=equipments.iterator();
		while(iterator.hasNext()) {
			Equipment k=iterator.next();
			if(k.getNetural2().equals("工厂设备")) {
				throw new NomalException();
			}
		}
		}
		catch(NomalException e) {
			throw e;
		}
	}
	//修改设备
	public void changeEquipment(String filename,String code,String name,String Catagory,String size,String des,String belong)throws SomethingNullException{
		try {
			if(code.isEmpty()||name.isEmpty()||Catagory.isEmpty()||size.isEmpty()||des.isEmpty()||belong.isEmpty()) {
				throw new SomethingNullException();
			}
		Database database=operations.ReadData(filename);
		ArrayList<Equipment> listdata=database.getEquiments();
		listdata=model1.changeEquipment(listdata, code, name, Catagory, size, des, belong);
		database.setEquipments(listdata);
		operations.updateData(filename, database);
		}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//强制归还设备
	public void returnEquipment(String filename,ArrayList<Equipment> list) throws NomalException,SomethingNullException{
		try {
			Iterator<Equipment>iterator=list.iterator();
			ArrayList<Equipment>want=new ArrayList<Equipment>();
		    while(iterator.hasNext()) {
		    	Equipment k=iterator.next();
		    	if(k.getNetural2().equals("已被租用")) {
		    		want.add(k);
		    	}
		    	if(k.getNetural2().equals("工厂设备")) {
		    		throw new NomalException();
		    	}
		    }
	    if(want.size()==0) {
	    	throw new SomethingNullException();
	    }
	    Database database=operations.ReadData(filename);
	    ArrayList<Equipment> listdata=database.getEquiments();
	    listdata=model1.returnEquipment(listdata, want);
	    database.setEquipments(listdata);
	    operations.updateData(filename, database);
	    }
		catch(NomalException e) {
			throw e;
		}
		catch(SomethingNullException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//开关机
	public void turnOn_Off(String filename,ArrayList<Equipment>list) throws NothingContainException{
		try {
			if(list.size()==0) {
				throw new NothingContainException();
			}
			Database database=operations.ReadData(filename);
			ArrayList<Equipment> listData=database.getEquiments();
			listData=model1.turnOn_Off(list, listData);
			database.setEquipments(listData);
			operations.updateData(filename, database);
		}
		catch(NothingContainException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//显示所有空闲设备
	public ArrayList<Equipment> showAllFreeEquipment(String filename) {
		try {
			ArrayList<Equipment> ans=new ArrayList<Equipment>();
			Database database=operations.ReadData(filename);
			ArrayList<Equipment> list=database.getEquiments();
			ans=model1.showAllFreeEquipment(list);
			return ans;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//租借设备
	public void lendEquipment(String filename,ArrayList<Equipment>list,String UserName) throws NothingContainException{
		try {
			if(list.size()==0) {
				throw new NothingContainException();
			}
			String factoryName=null;
			Database database=operations.ReadData(filename);
			ArrayList<User> who=database.getUsers();
			Iterator<User> iterator=who.iterator();
			while(iterator.hasNext()) {
				User k=iterator.next();
				if(k.getCode().equals(UserName)) {
					 factoryName=k.getFaname();
					break;
				}
			}
			ArrayList<Equipment>listdata=database.getEquiments();
			listdata=model1.lendEquipment(listdata, list, factoryName);
			database.setEquipments(listdata);
			operations.updateData(filename, database);
		}
		catch(NothingContainException e) {
			throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
