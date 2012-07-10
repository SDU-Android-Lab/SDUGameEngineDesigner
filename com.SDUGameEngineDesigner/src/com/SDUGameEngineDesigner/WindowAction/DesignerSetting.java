package com.SDUGameEngineDesigner.WindowAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

import com.SDUGameEngineDesigner.Designer.VariablesSettingDialog;

/**
 * ����Designer���ð�ť
 * @author xzz
 */
@SuppressWarnings("restriction")
public class DesignerSetting extends Action {
	
	private IWorkbenchWindow window;
	public DesignerSetting(IWorkbenchWindow window){
		this.window = window;
		this.setText("Designer����@Ctrl+Shift+S");
		this.setToolTipText("Designer setting");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	
	public void run(){
		//�����öԻ���
		VariablesSettingDialog dialog = new VariablesSettingDialog(window.getShell());
		dialog.open();
	}
	
}
