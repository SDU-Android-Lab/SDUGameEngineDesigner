package com.SDUGameEngineDesigner.View;

import java.io.File;
import java.util.ArrayList;

import com.SDUGameEngineDesigner.CodeEditor.CodeEditorInput;
import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.MapEditor.MapEditorInput;

/**
 * ����ΪPackageExplorer��ͼ�ṩ��ʾ��Ԫ�ص����ݹ���
 * @author xzz
 */
public class PackageExplorerElementFactory {

	public static Object getData(){
		
		String path = EnvironmentVariables.workspacePath;
		PackageExplorerElement[] root = getRoots(path);
		
		//�Ѹ������̼ӵ�list��
		ArrayList<PackageExplorerElement> list = new ArrayList<PackageExplorerElement>();		
		for(PackageExplorerElement element : root){
			if(element!=null){
				list.add(element);
			}
		}
	
		return list;
		
	}
	
	/**
	 * ��ø�����Ԫ��
	 * @param path �����ռ�·��
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
	 * Ϊ��Ԫ�������Ԫ��
	 * @param parent ��Ԫ��
	 * @param parentPath ��Ԫ�ص�·��
	 */
	private static void addChildren(PackageExplorerElement parent, String parentPath){
		
		File directory = new File(parentPath);
	    File[] branchs = directory.listFiles();
	    int count = branchs.length;
		
		for(int i = 0;i<count;i++){
			File file = branchs[i];
			PackageExplorerElement child = new PackageExplorerElement(file.getName(),parent);
			if(parent.getName().equals("Դ����")){
				child.setEditorInput(new CodeEditorInput());//����Ԫ����Ӧ������
			}
			if(parent.getName().equals("��ͼ")){
			    child.setEditorInput(new MapEditorInput());//����Ԫ����Ӧ������
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
