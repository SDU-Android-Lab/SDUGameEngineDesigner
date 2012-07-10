package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Dialog.AboutProjectDialog;

/**
 * �����½����̰�ť
 * @author xzz
 */
public class AboutProject extends Action {
	
	/**
	 * ������
	 */
	private String name;
	private IWorkbenchWindow window;
	
	public AboutProject(String name){
		this.name = name;
		this.setText("��������@Alt+Enter");
		this.setToolTipText("Save project");
		String path = ISharedImages.IMG_ETOOL_HOME_NAV;	
		ImageDescriptor imgDes = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(path);
		this.setImageDescriptor(imgDes);
	}
	public AboutProject(String name,IWorkbenchWindow window){
		this(name);
		this.window = window;
		
	}
	public void run(){
		Shell shell = null;
		if(window==null)
			shell = new Shell();
		else
			shell = window.getShell();
	
		AboutProjectDialog dialog = new AboutProjectDialog(shell,name);
		dialog.open();
	}
}
