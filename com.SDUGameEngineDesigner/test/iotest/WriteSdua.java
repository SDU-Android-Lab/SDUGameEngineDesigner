package iotest;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import util.Base;
import util.BaseToDir;

public class WriteSdua {
	static{
		classToDir=BaseToDir.getClassToDir();
	}
	
	private static  Map<Class<?>,File> classToDir;
	
	public static void writeBase(Base base){
		Class<?> cl=base.getClass();
		File dir=classToDir.get(cl);
		if (!dir.exists())
			dir.mkdirs();
		File file=new File(dir,base.getName()+".sdua");
		writeObject(file, base);
	}
	
	public static void writeObject(File file, Object object) {  
	    try {  
	        ObjectOutputStream out = new ObjectOutputStream(  
	                new BufferedOutputStream(new FileOutputStream(file)));  
	        out.writeObject(object);  
	        out.close();  
	        System.out.println("д ��"+object.getClass().getName()+"�� ���һ�������ļ���"+file.getPath()+"���ɹ�");
	    } catch (Exception e) {  
	        System.err.println(e);  
	    }  
	}
	
	
}
