package com.SDUGameEngineDesigner.SourceAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * ������ʽ�����밴ť
 * @author xzz
 */
public class Format extends Action {
	
	private IWorkbenchWindow window;
	public Format(IWorkbenchWindow window){
		this.window = window;
		this.setText("��ʽ��@Ctrl+Shift+F");
		this.setToolTipText("Format Source Code");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	
	public void run(){
		MessageDialog.openInformation(window.getShell(), "format", "������ʽ���Ի���");
	}
	
}
