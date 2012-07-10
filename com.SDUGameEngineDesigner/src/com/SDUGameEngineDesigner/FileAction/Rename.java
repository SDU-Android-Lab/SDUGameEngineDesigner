package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Dialog.RenameDialog;

/**
 * ��������ť
 * ��������Ķ���󣬽������setText()������������
 * @author xzz
 */
public class Rename extends Action {
	
	/**
	 * Ҫ�������ļ����ļ���
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

