package com.SDUGameEngineDesigner.View;

import java.io.File;
import java.util.ArrayList;

import com.SDUGameEngineDesigner.CodeEditor.CodeEditorInput;
import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.MapEditor.MapEditorInput;

/**
 * 该类为PackageExplorer视图提供显示的元素的数据工厂
 * @author xzz
 */
public class PackageExplorerElementFactory {

	public static Object getData(){
		
		String path = EnvironmentVariables.workspacePath;
		PackageExplorerElement[] root = getRoots(path);
		
		//把各个工程加到list中
		ArrayList<PackageExplorerElement> list = new ArrayList<PackageExplorerElement>();		
		for(PackageExplorerElement element : root){
			if(element!=null){
				list.add(element);
			}
		}
	
		return list;
		
	}
	
	/**
	 * 获得各个根元素
	 * @param path 工作空间路径
	 * @return PackageExplorerElement[]
	 */
	private static PackageExplorerElement[] getRoots(String path){
		
		File directory = new File(path);
	    File[] branchs = directory.listFiles();
	    int count = branchs.length;
	   
	    PackageExplorerElement[] roots = new PackageExplorerElement[count];
		int index = 0;
	    for(int i = 0;i<count;i++){
			if(branchs[i].isDirectory()){
				roots[index] = new PackageExplorerElement(branchs[i].getName(),null);
				addChildren(roots[index],branchs[i].getPath());
				index++;
			}				  
		}	 
	    
		return roots;
	}
	
	/**
	 * 为父元素添加子元素
	 * @param parent 父元素
	 * @param parentPath 父元素的路径
	 */
	private static void addChildren(PackageExplorerElement parent, String parentPath){
		
		File directory = new File(parentPath);
	    File[] branchs = directory.listFiles();
	    int count = branchs.length;
		
		for(int i = 0;i<count;i++){
			File file = branchs[i];
			PackageExplorerElement child = new PackageExplorerElement(file.getName(),parent);
			if(parent.getName().equals("源代码")){
				child.setEditorInput(new CodeEditorInput());//设置元素相应的输入
			}
			if(parent.getName().equals("地图")){
			    child.setEditorInput(new MapEditorInput());//设置元素相应的输入
			}
			if(file.isDirectory()){
				String path = branchs[i].getPath();
				addChildren(child,path);
			}
			
			parent.addChild(child);
		}		
	    return ;
	}
	
}
