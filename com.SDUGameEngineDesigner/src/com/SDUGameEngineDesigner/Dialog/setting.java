package com.SDUGameEngineDesigner.Dialog;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * �趨֡���Ի���
 * @author 
 *
 */
public class setting extends Dialog {

	
	/**
	 * ������ʾ������Composite
	 */
	private Composite parent;
	
	/**
	 * ��ʾ�ı��ĶԻ���
	 */ 
	private Label label;
	
	
	private Text text;
	public setting(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * ���Ի��򣬣�����ûŪ��to ����
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginHeight = 30;
		
		this.parent = parent;
		parent.setLayout(layout);
	
		label = new Label(parent,SWT.CHECK);
		label.setText("�趨֡��");
		
		text = new Text(parent,SWT.BORDER);
		text.setText("0");
		
		return parent; 
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
	   button.setFocus();
	   button.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
	            
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}	   
		   });	  
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			 
		   }
	   });   
	}    
	
	
	/**
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("�趨֡��"); 
		String path = ISharedImages.IMG_DEC_FIELD_WARNING;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

}

