package com.SDUGameEngineDesigner.EditAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 创建复制按钮
 * 可能不需要
 * @author xzz
 */
public class Paste extends Action {

	private IWorkbenchWindow window;
	public Paste(IWorkbenchWindow window){
		this.window = window;
		this.setText("粘帖@Ctrl+V");
		this.setToolTipText("Paste");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_VIEW_DEFAULTVIEW_MISC);
		this.setImageDescriptor(imgDes);
	}
	public void run(){
		MessageDialog.openInformation(window.getShell(), "paste", "实现黏贴功能");
	}
}
