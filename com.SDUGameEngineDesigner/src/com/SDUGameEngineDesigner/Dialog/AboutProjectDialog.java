package com.SDUGameEngineDesigner.Dialog;

import java.io.File;
import java.util.Calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;

/**
* 工程属性对话框
* @author xzz
*
*/
public class AboutProjectDialog extends Dialog {

	/**
	 * 要删除文件的文件名或路径
	 */
	private String name;
	
	private Label label_1;
	
	private Label label_2;

	public AboutProjectDialog(Shell parentShell,String name) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginRight = 20;
		layout.marginTop = 20;
		parent.setLayout(layout);
		label_1 = new Label(parent,SWT.NONE);
		label_1.setText("工程的路径：    "+EnvironmentVariables.workspacePath+"\\"+name);
		
		label_2 = new Label(parent,SWT.NONE);
		label_2.setText("最后一次修改时间是： "+getLastModifiedData());

		return parent; 
	}

	private String getLastModifiedData(){
		File file = new File(EnvironmentVariables.workspacePath+"\\"+name);
		Calendar cal = Calendar.getInstance();   
	    cal.setTimeInMillis(file.lastModified());   
		return cal.getTime().toString();
	}
	
	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 
	
	/**
	 * 添加确定
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "确定", true); 
	   super.initializeBounds(); 

	}    
	
	/**
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("工程属性"); 
		String path = ISharedImages.IMG_ETOOL_HOME_NAV;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

}
