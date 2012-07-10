package com.SDUGameEngineDesigner.CodeEditor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.SDUGameEngineDesigner.View.PackageExplorerElement;

/**
 * 源代码编辑器的输入
 * @author xzz
 *
 */
public class CodeEditorInput implements IEditorInput {

	/**
	 * 源代码编辑器的输入相应的元素
	 */
	private PackageExplorerElement element;
	
	/**
	 * 获得一个编辑器的适配器
	 */
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回编辑器的输入是否存在
	 */
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 返回输入的图片描述
	 */
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

	/**
	 * 返回能够用来可以保存编辑器的输入状态的对象
	 */
	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回标题栏提示性文字标签
	 */
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "源代码编辑器";
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
