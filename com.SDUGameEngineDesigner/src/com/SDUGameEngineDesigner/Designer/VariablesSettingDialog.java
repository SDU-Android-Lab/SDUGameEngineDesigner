package com.SDUGameEngineDesigner.Designer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

/**
 * ���ഴ��Designer���öԻ���
 * @author xzz
 */
@SuppressWarnings("restriction")
public class VariablesSettingDialog extends Dialog {

	/**
	 * ������ʾ������Composite
	 */
	private Composite composite;
	
	/**
	 * ѡ���Ƿ��Զ�����İ�ť
	 */
	private Button button1;
	
	/**
	 * ѡ���Ƿ��Զ��˳��İ�ť
	 */
	private Button button2;
	
	/**
	 * ѡ���Ƿ���Ĺ����ռ��·���İ�ť
	 */
	private Button button3;
	
	/**
	 * ��������ļ��еİ�ť
	 */
	private Button button4;
	
	/**
	 * ������ʾ�����ռ��·�� ���ı���
	 */
	private Text text;
	
	public VariablesSettingDialog(Shell parentShell) {
		super(parentShell);
		
	}
	

	/**
	 * ���ô�С
	 * @Override
	 */
    protected Point getInitialSize() { 
		return new Point(300,200); 
	}
	/**
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) { 				
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginTop = 10;   
		layout.marginRight = 30;
		
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(layout);
		
		//button1~button3��������Designer
		button1 = new Button(composite,SWT.CHECK);
		button1.setText("�Ƿ��Զ����룿");
		
        button2 = new Button(composite,SWT.CHECK);
        button2.setText("�Ƿ��Զ��˳���");
       
        button3 = new Button(composite,SWT.CHECK);
        button3.setText("�Ƿ���Ĺ����ռ�·����");
        button3.addMouseListener(new MouseAdapter(){
    	   public void mouseDown(MouseEvent e){
    		   text.setEnabled(true);
    		   button4.setEnabled(true);
    	   }
        });
      
        //���Ŀ¼��ѡ����ʾ�����ռ�
        button4 = new Button(composite,SWT.NONE);
        button4.setText("���");
        button4.setEnabled(false);
        button4.addMouseListener(new MouseAdapter(){
    	   public void mouseDown(MouseEvent e){
    		   DirectoryDialog dialog = new DirectoryDialog(new Shell());
    		   dialog.setMessage("��ѡ�����ռ�·�� :");
    		   dialog.open();
    		   String path = dialog.getFilterPath();
    		   text.setText(path);
    		   EnvironmentVariables.workspacePath = path;
    	   }
        });      
       
        text = new Text(composite,SWT.BORDER_SOLID);
        text.setText(EnvironmentVariables.workspacePath);
        text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
        text.setEnabled(false);
                   
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
    		   //����button1��button3�Ƿ��й�ѡ����Designer����
    		   if(button1.getSelection()) 
    			   EnvironmentVariables.hasDefaultWorkspace = true;
    		   else
    			   EnvironmentVariables.hasDefaultWorkspace = false;
    		   if(button2.getSelection())
    			   EnvironmentVariables.isAutoExit = true;
    		   else
    			   EnvironmentVariables.isAutoExit = false;
    		   //�޸�Designer�ı���
    		   EnvironmentVariables.variablesChange();
    	   }
       });
       
	} 

	/**
	 * ���öԻ���ı��⣬ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("Designer Setting"); 
		shell.setImage(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_WORKINGSET_WIZ).createImage());
	}
}
