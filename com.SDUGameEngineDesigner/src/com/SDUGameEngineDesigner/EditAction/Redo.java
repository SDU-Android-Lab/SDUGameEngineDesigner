package com.SDUGameEngineDesigner.EditAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 创建重做按钮
 * 可能不需要
 * @author xzz
 */
public class Redo extends Action {

	private IWorkbenchWindow window;
	public Redo(IWorkbenchWindow window){
		this.window = window;
		this.setText("重做@Ctrl+Y");
		this.setToolTipText("Redo");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	public void run(){
		MessageDialog.openInformation(window.getShell(), "redo", "实现重做功能");
	}
}
