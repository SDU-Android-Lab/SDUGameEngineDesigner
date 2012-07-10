package com.SDUGameEngineDesigner.SourceAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 创建修改代码按钮
 * @author xzz
 */
public class Modify extends Action {
	
	private IWorkbenchWindow window;
	public Modify(IWorkbenchWindow window){
		this.window = window;
		this.setText("查看-修改@Ctrl+Shift+M");
		this.setToolTipText("Modify Source Code");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	
	public void run(){
		MessageDialog.openInformation(window.getShell(), "modify", "弹出修改视图");
	}
	
}
