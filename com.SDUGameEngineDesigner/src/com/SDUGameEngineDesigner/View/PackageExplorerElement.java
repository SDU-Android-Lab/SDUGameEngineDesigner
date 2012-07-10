package com.SDUGameEngineDesigner.View;

import java.util.ArrayList;

import org.eclipse.ui.IEditorInput;

/**
 * ����ΪPackageExplorer��ͼ�ṩ��ʾԪ��
 * ��ΪԪ�������Ԫ��
 * @author xzz
 */

public class PackageExplorerElement {

    /**
     *Ԫ������ 
     */
	private String name;
	
	/**
	 * Ԫ�ص�·��
	 */
	private String path = "";
	
	/**
	 * ������Ԫ�ص�����
	 */
	private ArrayList<PackageExplorerElement> array  = new ArrayList<PackageExplorerElement>();
	
	/**
	 * �༭��������
	 */
	private IEditorInput input;
	
	/**
	 * ��Ԫ��
	 */
	private PackageExplorerElement parentElement = null;
	
	
	public PackageExplorerElement(){
		
	}
	
	public PackageExplorerElement(String name,PackageExplorerElement parentElement){
		this.name = name;
		this.parentElement = parentElement;
		//����Ԫ�ص�·��
		if(parentElement==null)
			path = name;
		else
			this.path = parentElement.getPath()+"\\"+name;		
	}
	
	/**
	 * ���ø�Ԫ��
	 * @param parentElement ��Ԫ��
	 */
	public void setParent(PackageExplorerElement parentElement){
		this.parentElement = parentElement;
	}
	
	/**
	 * ��ø�Ԫ��
	 * @return PackageExplorerElement ��Ԫ��
	 */
	public PackageExplorerElement getParent(){
		return parentElement;
	}
		
	/**
	 * ���ð���������Ԫ�ص�����
	 * @param array
	 */
	public void setChildren(ArrayList<PackageExplorerElement> array){
		this.array = array;
	}
	
	/**
	 * ��ð���������Ԫ�ص�����
	 * @return ArrayList
	 */
	public ArrayList<PackageExplorerElement> getChildren(){
		return array;
	}
	
	/**
	 * ����Ԫ������
	 * @param name Ԫ������
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * ���Ԫ������
	 * @return String
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * �����Ԫ��
	 * @param element ��Ԫ��
	 */
	public void addChild(PackageExplorerElement element){
		array.add(element);
	}
	
	/**
	 * ɾ��ָ��λ�õ���Ԫ��
	 * @param index �ƶ���λ��
	 * @return boolean �Ƿ�����ɹ�
	 */
	public boolean removeChild(int index){
		if(!hasChildren()||array.size()<index)
			return false;
		array.remove(index);
		return true;
	}
	
	/**
	 * �Ƿ�����Ԫ��
	 * @return boolean
	 */
	public boolean hasChildren(){
		if(array.size()==0) 
			return false;
		return true;
	}
	
	/**
	 * ����Դ����༭��������
	 * @param input ����
	 */
	public void setEditorInput(IEditorInput input){
		this.input = input;
	}
		
	/**
	 * ���Դ����༭��������
	 * @return IEditorInput
	 */
	public IEditorInput getEditorInput(){
		return input;
	}
	
	/**
	 * ���Ԫ�ص�·��
	 * @return String ·��
	 */
	public String getPath(){
		return path;
	}
	
}
