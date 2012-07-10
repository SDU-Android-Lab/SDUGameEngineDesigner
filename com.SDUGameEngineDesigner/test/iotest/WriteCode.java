package iotest;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import util.DirToJet;
import util.Format;
import util.IGenerator;

/**
 * 读取所有的sdua文件，生成目标代码，打印到控制台，输出到指定目录
 * @author Administrator
 *
 */

public class WriteCode {
	private static  Map<File,IGenerator> pathToGen;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		pathToGen=DirToJet.getPathToGen();
		File file=new File("sdua");
		searchDir(file);
	}
	
	private static void searchDir(File file){
		if (file.getName().equals("CVS"))
			return;
		File[] list=file.listFiles();
	
		IGenerator gen=pathToGen.get(file);
		for (File sub:list){
			if (sub.isDirectory()){
				searchDir(sub);
			}else{
				Object obj=readObject(sub);
				String str=gen.generate(obj);
				Format format=new Format(str);
			
				System.out.println(format.getPackageName()+"."+format.getClassName()+".java");
				System.out.println("*****************");
				System.out.println(format.getContext());
				System.out.println("------------------------------\n");
						
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(format.getFile()));
					bw.write(format.getContext());
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Object readObject(File inFile) {  
	    if(!inFile.exists()){  
	        return null;  
	    }  
	    Object o = null;  
	    try {  
	        ObjectInputStream in = new ObjectInputStream(  
	                new BufferedInputStream(new FileInputStream(inFile)));  
	        o = in.readObject();  
	        in.close();  
	    } catch (Exception e) {  
	        System.out.println(e);  
	    }  
	    return o;  
	}  
}
