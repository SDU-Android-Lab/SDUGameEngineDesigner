package com.SDUGameEngineDesigner.EditAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * ����������ť
 * ���ܲ���Ҫ
 * @author xzz
 */
public class Undo extends Action {

	private IWorkbenchWindow window;
	public Undo(IWorkbenchWindow window){
		this.window = window;
		this.setText("����@Ctrl+Z");
		this.setToolTipText("Undo");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	public void run(){
		MessageDialog.openInformation(window.getShell(), "Undo", "ʵ�ֳ�������");
	}
}
