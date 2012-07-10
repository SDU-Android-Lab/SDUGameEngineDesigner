package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**  
 * 创建保存工程按钮
 * @author xzz
 */ 
public class SaveProject extends Action {

	private IWorkbenchWindow window;
	public SaveProject(IWorkbenchWindow window){
		this.window = window;
		this.setText("保存工程@Ctrl+S");
		this.setToolTipText("Save project");
		String path = ISharedImages.IMG_ETOOL_SAVE_EDIT;
		ImageDescriptor des = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(path);
		this.setImageDescriptor(des);
	}
	public void run(){
		    
	}
}
