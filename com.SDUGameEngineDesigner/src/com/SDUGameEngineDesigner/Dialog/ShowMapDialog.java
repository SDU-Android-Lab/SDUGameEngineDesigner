package com.SDUGameEngineDesigner.Dialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 地图预览的对话框
 * @author xzz
 *
 */
public class ShowMapDialog extends Dialog {

	private String path;
	private Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	
	
	
	public ShowMapDialog(Shell parentShell,String path) {
		super(parentShell);
		this.path = path;
	}

	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		Composite composite = new Composite (parent,SWT.NONE);
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginTop = 10;
		layout.marginLeft = 30;
		layout.marginRight = 30;
		layout.marginHeight = 10;
		composite.setLayout(layout);
		
		label = new Label(composite,SWT.NONE);
		label_1 = new Label(composite,SWT.NONE);
		label_2 = new Label(composite,SWT.NONE);
		label_3 = new Label(composite,SWT.NONE);
		label_4 = new Label(composite,SWT.NONE);
		
		setLabelContent();

		return parent; 
	}

	/**
	 * 读入文件中所需要的信息，设置label们的显示文本
	 */
	private void setLabelContent(){
		
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(path)));
			String name = read.readLine();
			label.setText("地图的名称是：      "+name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 
	
	/**
	 * 添加确定按钮
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
		shell.setText("地图预览"); 
		String path = ISharedImages.IMG_ETOOL_HOME_NAV;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
}
