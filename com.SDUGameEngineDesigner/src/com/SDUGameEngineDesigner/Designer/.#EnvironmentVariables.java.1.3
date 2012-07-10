package com.SDUGameEngineDesigner.Designer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 该类包含了SDUGameEngine-Designer的各种环境变量
 * 该类可以读取,修改SDUGameEngine-Designer的各种环境变量
 * @author xzz
 */
public class EnvironmentVariables {
	      
	/**
	 * 是否有默认工作空间
	 */
	public static boolean hasDefaultWorkspace = false;
	
	/**
	 * 工作空间路径
	 */
	public static String workspacePath = "D:\\Designer_Workspace";
	
	/**
	 * 是否自动退出
	 */
	public static boolean isAutoExit = false;
	
    /**
     * 配置文件路径（后面应改为安装目录下）
     */
	private static String confiPath = "C:\\Designer.ini";
	
	/**
	 * 依据配置文件（Designer.ini)初始化各种环境变量
	 * @return 是否成功初始化
	 */
     public static boolean variablesInitialize(){
    	 
    	 File file = new File(confiPath);
    	 if(!file.exists()){
    		 createFile(file);
    	 }
    	 String[] data = null;
    	 try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			data = reader.readLine().split(" ");
			hasDefaultWorkspace = (Boolean.parseBoolean(data[2]));
			data = reader.readLine().split(" ");
			workspacePath = data[2];
			data = reader.readLine().split(" ");
			isAutoExit = Boolean.parseBoolean(data[2]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}    	 
		return true;
     }
	
     /**
      * 修改各种环境变量
      */
     public static void variablesChange(){
    	 File file = new File(confiPath);
    	 if(!file.exists()){
    		 createFile(file);
    		 return;
    	 }
    	 BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("hasDefaultWorkspace = "+hasDefaultWorkspace);
			writer.newLine();
			writer.write("workspacePath = "+workspacePath);
			writer.newLine();
			writer.write("isAutoExit = "+isAutoExit);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
			
     }
     
     /**
      * 创建配置文件，并写入默认值
      * @param file
      */
     private static void createFile(File file){
    	 try {
				file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));				
				writer.write("hasDefaultWorkspace = false");
				writer.newLine();
				writer.write("workspacePath = D:\\Designer_Workspace");
				writer.newLine();
				writer.write("isAutoExit = false");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			   
			}
     }
}
