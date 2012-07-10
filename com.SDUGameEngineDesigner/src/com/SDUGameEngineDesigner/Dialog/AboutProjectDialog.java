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
* �������ԶԻ���
* @author xzz
*
*/
public class AboutProjectDialog extends Dialog {

	/**
	 * Ҫɾ���ļ����ļ�����·��
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
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginRight = 20;
		layout.marginTop = 20;
		parent.setLayout(layout);
		label_1 = new Label(parent,SWT.NONE);
		label_1.setText("���̵�·����    "+EnvironmentVariables.workspacePath+"\\"+name);
		
		label_2 = new Label(parent,SWT.NONE);
		label_2.setText("���һ���޸�ʱ���ǣ� "+getLastModifiedData());

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
	 * ���ȷ��
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "ȷ��", true); 
	   super.initializeBounds(); 

	}    
	
	/**
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("��������"); 
		String path = ISharedImages.IMG_ETOOL_HOME_NAV;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

}
