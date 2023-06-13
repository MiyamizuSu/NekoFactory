package DreamFatcory.Control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Main.Database;

public class Data_operations {
	public Data_operations() {
		
	}
	public Database ReadData(String filename)throws Exception{
		try {
			File file=new File(filename);
			FileInputStream input=new FileInputStream(file);
			ObjectInputStream objectInput=new ObjectInputStream(input);
			Database ans=(Database)objectInput.readObject();
			objectInput.close();
			return ans;
	}
		catch (Exception e) {
			throw e;
		}
	}
	public  void  updateData(String filename,Database database) throws Exception{
			File file =new File(filename);
			FileOutputStream output=new FileOutputStream(file);
			ObjectOutputStream objectOutput=new ObjectOutputStream(output);
			objectOutput.writeObject(database);
			objectOutput.close();
	}
	
}
