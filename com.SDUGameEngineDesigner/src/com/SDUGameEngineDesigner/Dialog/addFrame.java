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
* 增加帧数对话框
* @author 
*
*/
public class addFrame extends Dialog {

	
	/**
	 * 用于显示部件的Composite
	 */
	private Composite parent;
	
	/**
	 * 显示文本的对话框
	 */ 
	private Label label;
	private Label label_1;
	private Label label_2;
	
	private Text text;
	private Text text_1;
	private Text text_2;
	
	
	public addFrame(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * 填充对话框，布局没弄。
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginHeight = 30;
		
		this.parent = parent;
		parent.setLayout(layout);
	
		label = new Label(parent,SWT.CHECK);
		label.setText("开始帧数");
		text = new Text(parent,SWT.BORDER);
		
		label_1 = new Label(parent,SWT.BORDER);
		label_1.setText("结束帧数");
		text_1 = new Text(parent,SWT.BORDER);
		
		label_2 = new Label(parent,SWT.BORDER);
		label_2.setText("要补的帧数");
		text_2 = new Text(parent,SWT.BORDER);
		
		return parent; 
	}

	
	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 
	
	/**
	 * 添加确定，取消按钮
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "确定", true); 
	   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "取消", false); 
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
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("增加帧数"); 
		String path = ISharedImages.IMG_DEC_FIELD_WARNING;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

}


