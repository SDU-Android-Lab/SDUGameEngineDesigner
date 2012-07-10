package com.SDUGameEngineDesigner.View;

import java.util.ArrayList;

import org.eclipse.ui.IEditorInput;

/**
 * 该类为PackageExplorer视图提供显示元素
 * 可为元素添加子元素
 * @author xzz
 */

public class PackageExplorerElement {

    /**
     *元素名称 
     */
	private String name;
	
	/**
	 * 元素的路径
	 */
	private String path = "";
	
	/**
	 * 包含子元素的数组
	 */
	private ArrayList<PackageExplorerElement> array  = new ArrayList<PackageExplorerElement>();
	
	/**
	 * 编辑器的输入
	 */
	private IEditorInput input;
	
	/**
	 * 父元素
	 */
	private PackageExplorerElement parentElement = null;
	
	
	public PackageExplorerElement(){
		
	}
	
	public PackageExplorerElement(String name,PackageExplorerElement parentElement){
		this.name = name;
		this.parentElement = parentElement;
		//设置元素的路径
		if(parentElement==null)
			path = name;
		else
			this.path = parentElement.getPath()+"\\"+name;		
	}
	
	/**
	 * 设置父元素
	 * @param parentElement 父元素
	 */
	public void setParent(PackageExplorerElement parentElement){
		this.parentElement = parentElement;
	}
	
	/**
	 * 获得父元素
	 * @return PackageExplorerElement 父元素
	 */
	public PackageExplorerElement getParent(){
		return parentElement;
	}
		
	/**
	 * 设置包含所有子元素的数组
	 * @param array
	 */
	public void setChildren(ArrayList<PackageExplorerElement> array){
		this.array = array;
	}
	
	/**
	 * 获得包含所有子元素的数组
	 * @return ArrayList
	 */
	public ArrayList<PackageExplorerElement> getChildren(){
		return array;
	}
	
	/**
	 * 设置元素名称
	 * @param name 元素名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 获得元素名称
	 * @return String
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 添加子元素
	 * @param element 子元素
	 */
	public void addChild(PackageExplorerElement element){
		array.add(element);
	}
	
	/**
	 * 删除指定位置的子元素
	 * @param index 制定的位置
	 * @return boolean 是否操作成功
	 */
	public boolean removeChild(int index){
		if(!hasChildren()||array.size()<index)
			return false;
		array.remove(index);
		return true;
	}
	
	/**
	 * 是否有子元素
	 * @return boolean
	 */
	public boolean hasChildren(){
		if(array.size()==0) 
			return false;
		return true;
	}
	
	/**
	 * 设置源代码编辑器的输入
	 * @param input 输入
	 */
	public void setEditorInput(IEditorInput input){
		this.input = input;
	}
		
	/**
	 * 获得源代码编辑器的输入
	 * @return IEditorInput
	 */
	public IEditorInput getEditorInput(){
		return input;
	}
	
	/**
	 * 获得元素的路径
	 * @return String 路径
	 */
	public String getPath(){
		return path;
	}
	
}
