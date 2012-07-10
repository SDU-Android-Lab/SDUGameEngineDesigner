package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;  
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

import com.SDUGameEngineDesigner.Dialog.NewPojectDialog;

/**
 * 创建新建工程按钮
 * @author xzz
 */
@SuppressWarnings("restriction")
public class NewProject extends Action {
	private IWorkbenchWindow window;
	
	public NewProject(){
		this.setText("新建工程@Ctrl+Alt+N");
		this.setToolTipText("New project");
		
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	
	public NewProject(IWorkbenchWindow window){
		this();
		this.window = window;	
	}
	
	public void run(){
		Shell shell = null;
		if(window==null)
			shell = new Shell();
		else
			shell = window.getShell();
		NewPojectDialog dialog = new NewPojectDialog(shell);
		dialog.open();
	}

}
