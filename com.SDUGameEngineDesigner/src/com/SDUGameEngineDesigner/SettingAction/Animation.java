package com.SDUGameEngineDesigner.SettingAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

import editor.Editor;

/**
 * �������ð�ť
 * @author xzz
 *
 */
public class Animation extends Action{
	private IWorkbenchWindow window;
	public Animation(IWorkbenchWindow window){
		this.window=window;
		this.setText("&��������");
		//����ͼ��
		ImageDescriptor ima=WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_LCL_PIN_VIEW);
		this.setImageDescriptor(ima);
	}
	
	public void run() {

		Editor e = new Editor(Display.getCurrent());
		e.launch();
		e.process();
		e.dispose();
	}

}
