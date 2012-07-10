package com.SDUGameEngineDesigner.Designer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * ����ɴ�������ѯ�ʶԻ���
 * @author xzz
 */
@SuppressWarnings("restriction")
public class LoginInquiryDialog extends Dialog {

	/**
	 * ����������ʾ������Composite
	 */
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;	
	
	/**
	 * һ����ʾ�ı��ı�ǩ
	 */
	private Label label;
	
	/**
	 * �û������·����ʾ��
	 */
	private Text text;
	
	/**
	 * �ļ����
	 */
	private Button button1;
	
	/**
	 * Ĭ��·����ť
	 */
	private Button button2;
	
	/**
	 * �û�ѡ���·��
	 */
	private String path = null;

	/**
	 * ���ֹ�����
	 */
	private RowLayout layout;
	
	public LoginInquiryDialog(Shell parentShell) {
		super(parentShell);
	
	}

	/**
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) { 
					
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1,false));
		
		layout = new RowLayout();
		layout.marginLeft = 30;
		layout.marginTop = 30;   
		layout.marginRight = 30;
		
        addComposite_1();
        addComposite_2();
        addComposite_3(); 
        
		return composite; 
	}
	
	/**
	 * һ��label
	 */
	private void addComposite_1(){
		composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(layout);
		
		label = new Label(composite_1, SWT.NONE);
	    label.setText("��ѡ�����ռ䣺");
	    label.setEnabled(false);
	    
	}
	
	/**
	 * ѡ����ʾ�����ռ�
	 */
	public void addComposite_2(){
		
		composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(layout);
		 
	    text = new Text(composite_2, SWT.BORDER);
	    text.setText(EnvironmentVariables.workspacePath); 
	        
	    final Shell shell = composite_2.getShell();
	    button1 = new Button(composite_2,SWT.NONE);
	    button1.setText("���");
	    button1.addMouseListener(new MouseAdapter(){
	        public void mouseDown(MouseEvent e) {				
				DirectoryDialog dialog = new DirectoryDialog(shell);
				dialog.open();
			    path = dialog.getFilterPath();
			    text.setText(path);
			    EnvironmentVariables.workspacePath = path;//����·��			   
			}		
	    });
	    
	    text.setEnabled(false);
  	    button1.setEnabled(false);
	    
	}
	
	/**
	 * ѡ���Ƿ���ΪĬ�Ͽռ�İ�ť
	 */
	public void addComposite_3(){
		
		composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(layout);
		
		button2 = new Button(composite_3,SWT.CHECK);
        button2.setText("��ΪĬ�Ϲ����ռ䣬�Ժ���ѯ�ʡ�");
        button2.setSelection(true);
        button2.addMouseListener(new MouseAdapter(){
           	public void mouseDown(MouseEvent e){
              if(button2.getSelection()){
            	  label.setEnabled(true);
            	  text.setEnabled(true);
            	  button1.setEnabled(true);
              }else{
            	  label.setEnabled(false);
            	  text.setEnabled(false);
            	  button1.setEnabled(false);
              }   	  
        	}
        });  
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
			   if(button2.getSelection()){
				   EnvironmentVariables.hasDefaultWorkspace = true;//������Ĭ��·��
			   }
		   }
	   });
	   
	} 

	/**
	 * ���öԻ���ı��⣬ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("Designer Launcher"); 
		shell.setImage(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_WORKINGSET_WIZ).createImage());
	}
}
