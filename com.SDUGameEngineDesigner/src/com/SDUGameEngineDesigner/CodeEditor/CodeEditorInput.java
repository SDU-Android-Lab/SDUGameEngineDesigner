package com.SDUGameEngineDesigner.CodeEditor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.SDUGameEngineDesigner.View.PackageExplorerElement;

/**
 * Դ����༭��������
 * @author xzz
 *
 */
public class CodeEditorInput implements IEditorInput {

	/**
	 * Դ����༭����������Ӧ��Ԫ��
	 */
	private PackageExplorerElement element;
	
	/**
	 * ���һ���༭����������
	 */
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ���ر༭���������Ƿ����
	 */
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ���������ͼƬ����
	 */
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

	/**
	 * �����ܹ��������Ա���༭��������״̬�Ķ���
	 */
	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ���ر�������ʾ�����ֱ�ǩ
	 */
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Դ����༭��";
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
