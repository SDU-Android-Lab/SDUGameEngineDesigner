package com.SDUGameEngineDesigner.View;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 资源管理器的标签提供者
 * @author xzz
 *
 */
public class PackageExplorerLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	/**
	 * 设置树节点图标
	 */
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		String image1 = ISharedImages.IMG_OBJ_FILE;
		String image2 = ISharedImages.IMG_OBJ_FOLDER;
		
		boolean hasChildren = ((PackageExplorerElement)element).hasChildren();
		String name = ((PackageExplorerElement)element).getName();
		if(name.equals("源代码")||name.equals("游戏资源")||name.equals("动画")||name.equals("声音")||name.equals("地图"))
			return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
		if(hasChildren)
			return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
		else
			return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
		
	}

	/**
	 * 返回给定元素的文本
	 */
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		PackageExplorerElement inputElement = (PackageExplorerElement)element;
		String name = inputElement.getName();
		
		return name;
	}

}
