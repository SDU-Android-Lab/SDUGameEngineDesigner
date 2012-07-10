package com.SDUGameEngineDesigner.Dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * ��NPC�Ի��ĶԻ���
 * @author Administrator
 *
 */
public class TalkWithNPC extends Dialog {

	public TalkWithNPC(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		
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
		shell.setText("NPC�Ի�"); 
		String path = ISharedImages.IMG_DEC_FIELD_WARNING;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

}
