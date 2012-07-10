package com.SDUGameEngineDesigner.Designer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * ���ഴ���˳�ѯ�ʿ�
 * @author xzz
 */
public class LogoutInquiryDialog extends Dialog {

	/**
	 * ������ʾ������Composite
	 */
	private Composite composite;	
	
	/**
	 * Ĭ��·����ť
	 */
	private Button button1;
	
	/**
	 * ��С�������̰�ť
	 */
    private Button button2;
    
    /**
     * �Ƿ�Ҫѡ���˳�
     */
    public boolean isExit = true;
    
	public LogoutInquiryDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1,true));
		
		Group group = new Group(composite,SWT.NONE);
		group.setText("��ѡ��");
		group.setLayout(new GridLayout());
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
        button1 = new Button(group,SWT.RADIO);
        button1.setText("�Զ��˳����Ժ���ѯ�ʡ�");
           
        button2 = new Button(group,SWT.RADIO);
        button2.setText("���˳�����С��������");
               
		return composite; 
	}
	
	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 
	
	/**
	 * ���ȷ����ȡ����ť
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "ȷ��", true); 
	   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "ȡ��", false); 
	   super.initializeBounds(); 

	   Button button = getButton(IDialogConstants.OK_ID);
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   if(button1.getSelection()){
				  isExit = true;
				  EnvironmentVariables.isAutoExit = true;
			   }
			   else{
				   if(button2.getSelection())
					   isExit = false;
			   }				   
		   }
	   });
	} 

	/**
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		String path = ISharedImages.IMG_DEC_FIELD_WARNING;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
}
