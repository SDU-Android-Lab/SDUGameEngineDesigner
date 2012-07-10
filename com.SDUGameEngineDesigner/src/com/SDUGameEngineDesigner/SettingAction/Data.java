package com.SDUGameEngineDesigner.SettingAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 数据设置按钮
 * @author xzz
 *
 */
public class Data extends Action{
	private IWorkbenchWindow window;
	public Data(IWorkbenchWindow window){
		this.window=window;
		this.setText("&数据设置");
		//设置图标
		ImageDescriptor ima=WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_LCL_PIN_VIEW);
		this.setImageDescriptor(ima);
	}
	
	public void run() {
		new DataSettingWindow(window.getShell()).initia();
	}

}
