package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.SDUGameEngineDesigner.View.PackageExplorerElement;

/**
 * 地图编辑器的输入
 * @author xzz
 *
 */
public class MapEditorInput implements IEditorInput{

	/**
	 * 源代码编辑器的输入相应的元素
	 */
	private PackageExplorerElement element;
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回编辑器标题栏的名称
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return element.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "地图编辑";
	}
	
	/**
	 * 设置输入相应的元素
	 * @param element 元素
	 */
	public void setPackageExplorerElement(PackageExplorerElement element){
		this.element = element;
	}
	
	/**
	 * 获得输入相应的元素
	 * @return PackageExplorerElement
	 */
	public PackageExplorerElement getPackageExplorerElement(){
		return element;
	}

}
