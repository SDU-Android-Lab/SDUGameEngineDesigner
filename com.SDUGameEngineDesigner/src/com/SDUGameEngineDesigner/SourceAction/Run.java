package com.SDUGameEngineDesigner.SourceAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * 创建运行代码按钮
 * @author xzz
 */
public class Run extends Action {
	
	private IWorkbenchWindow window;
	public Run(IWorkbenchWindow window){
		this.window = window;
		this.setText("运行@Ctrl+F11");
		this.setToolTipText("Run Source Code");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	
	public void run(){
		MessageDialog.openInformation(window.getShell(), "run", "弹出运行对话框");
	}
	
}