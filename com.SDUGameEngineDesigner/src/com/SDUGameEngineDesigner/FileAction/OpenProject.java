package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * �����򿪹��̰�ť
 * @author xzz
 */
public class OpenProject extends Action {
	
	private IWorkbenchWindow window;
	public OpenProject (IWorkbenchWindow window){
		this.window = window;
		this.setText("�򿪹���@Ctrl+Alt+O");
		this.setToolTipText("Open project");
		
	}
	
	public void run(){
		MessageDialog.openInformation(window.getShell(), "Open Project", "�Ժ������ﵯ���򿪹��̶Ի���");
	}

}
