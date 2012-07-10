package com.SDUGameEngineDesigner.WindowAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

import com.SDUGameEngineDesigner.Designer.VariablesSettingDialog;

/**
 * 创建Designer设置按钮
 * @author xzz
 */
@SuppressWarnings("restriction")
public class DesignerSetting extends Action {
	
	private IWorkbenchWindow window;
	public DesignerSetting(IWorkbenchWindow window){
		this.window = window;
		this.setText("Designer设置@Ctrl+Shift+S");
		this.setToolTipText("Designer setting");
		ImageDescriptor imgDes = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART);
		this.setImageDescriptor(imgDes);
	}
	
	public void run(){
		//打开设置对话框
		VariablesSettingDialog dialog = new VariablesSettingDialog(window.getShell());
		dialog.open();
	}
	
}
