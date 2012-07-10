package com.SDUGameEngineDesigner.View;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * ��Դ���������ı���Ϣ�ṩ��
 * @author xzz
 *
 */
public class PackageExplorerContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	/**
	 * ���ز鿴������ʾ��Ԫ��
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if(inputElement instanceof List){
			ArrayList<?> list = (ArrayList<?>)inputElement;
			return list.toArray();
		}
		return new Object[0];
	}

	/**
	 * ���ظ�Ԫ�ص���Ԫ��
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		PackageExplorerElement  inputElement = (PackageExplorerElement)parentElement;
		if(!inputElement.hasChildren())
			return new Object[0];
		return inputElement.getChildren().toArray();
	}

	/**
	 * ���ظ�Ԫ��
	 */
	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
//		PackageExplorerElement  inputElement = (PackageExplorerElement)element;
//		if(inputElement.getParent() == null)
//			return new Object[0];
//		return inputElement.getParent();
        return null;		
	}
 
	/**
	 * ����Ԫ���Ƿ�����Ԫ��
	 */
	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		PackageExplorerElement inputElement = (PackageExplorerElement)element;
		boolean has  = inputElement.hasChildren();
		return has;
	}

}
