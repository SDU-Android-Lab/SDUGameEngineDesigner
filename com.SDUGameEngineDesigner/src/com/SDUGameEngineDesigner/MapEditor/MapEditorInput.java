package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.SDUGameEngineDesigner.View.PackageExplorerElement;

/**
 * ��ͼ�༭��������
 * @author xzz
 *
 */
public class MapEditorInput implements IEditorInput{

	/**
	 * Դ����༭����������Ӧ��Ԫ��
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
	 * ���ر༭��������������
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
		return "��ͼ�༭";
	}
	
	/**
	 * ����������Ӧ��Ԫ��
	 * @param element Ԫ��
	 */
	public void setPackageExplorerElement(PackageExplorerElement element){
		this.element = element;
	}
	
	/**
	 * ���������Ӧ��Ԫ��
	 * @return PackageExplorerElement
	 */
	public PackageExplorerElement getPackageExplorerElement(){
		return element;
	}

}
