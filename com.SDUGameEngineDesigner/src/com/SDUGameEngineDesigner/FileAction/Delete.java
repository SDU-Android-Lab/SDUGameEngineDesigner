package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;  
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Dialog.DeleteDialog;

/**
 * 删除按钮
 * @author xzz
 */
public class Delete extends Action {
	
	/**
	 * 要删除文件的文件名或路径
	 */
	private String name;
	
	private IWorkbenchWindow window;
	
	public Delete(){
		this.setToolTipText("Delete");
		String path = ISharedImages.IMG_ETOOL_DELETE;
		ImageDescriptor imageDes = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(path);
		this.setImageDescriptor(imageDes);
	}
	
	public Delete(String name,String target,IWorkbenchWindow window){
		this();
		this.window = window;	
	}
	
	public void setNameAndTarget(String name,String target){
		this.name = name;
		this.setText(target);		
	}
	
	public void run(){
		Shell shell = null;
		if(window==null)
			shell = new Shell();
		else
			shell = window.getShell();
		DeleteDialog dialog = new DeleteDialog(shell,name);
		dialog.open();
	}

}

