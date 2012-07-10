package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Dialog.RenameDialog;

/**
 * 重命名按钮
 * 创建本类的对象后，建议调用setText()方法设置名称
 * @author xzz
 */
public class Rename extends Action {
	
	/**
	 * 要重命名文件的文件名
	 */
	private String name;
	
	private IWorkbenchWindow window;
	
	public Rename(){
		this.setToolTipText("Rename");
		String path = ISharedImages.IMG_ELCL_SYNCED;
		ImageDescriptor imageDes = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(path);
		this.setImageDescriptor(imageDes);
	}
	
	public Rename(IWorkbenchWindow window){
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
		RenameDialog dialog = new RenameDialog(shell,name);
		dialog.open();
	}

}

