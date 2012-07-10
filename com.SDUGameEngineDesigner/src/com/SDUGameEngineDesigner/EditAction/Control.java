package com.SDUGameEngineDesigner.EditAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 创建管理按钮
 * 可能不需要
 * @author xzz
 */
public class Control extends Action {

	private IWorkbenchWindow window;
	public Control(IWorkbenchWindow window){
		this.window = window;
		this.setText("管理@Ctrl+O");
		this.setToolTipText("Control");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	public void run(){
		MessageDialog.openInformation(window.getShell(), "Control", "实现管理功能");
	}
}
