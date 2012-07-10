package com.SDUGameEngineDesigner.EditAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 创建撤销按钮
 * 可能不需要
 * @author xzz
 */
public class Undo extends Action {

	private IWorkbenchWindow window;
	public Undo(IWorkbenchWindow window){
		this.window = window;
		this.setText("撤销@Ctrl+Z");
		this.setToolTipText("Undo");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	public void run(){
		MessageDialog.openInformation(window.getShell(), "Undo", "实现撤销功能");
	}
}
