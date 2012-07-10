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
 * 该类可创建登入询问对话框
 * @author xzz
 */
@SuppressWarnings("restriction")
public class LoginInquiryDialog extends Dialog {

	/**
	 * 各个用于显示部件的Composite
	 */
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;	
	
	/**
	 * 一个显示文本的标签
	 */
	private Label label;
	
	/**
	 * 用户输入框，路径显示框
	 */
	private Text text;
	
	/**
	 * 文件浏览
	 */
	private Button button1;
	
	/**
	 * 默认路径按钮
	 */
	private Button button2;
	
	/**
	 * 用户选择的路径
	 */
	private String path = null;

	/**
	 * 布局管理器
	 */
	private RowLayout layout;
	
	public LoginInquiryDialog(Shell parentShell) {
		super(parentShell);
	
	}

	/**
	 * 填充对话框
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
	 * 一个label
	 */
	private void addComposite_1(){
		composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(layout);
		
		label = new Label(composite_1, SWT.NONE);
	    label.setText("请选择工作空间：");
	    label.setEnabled(false);
	    
	}
	
	/**
	 * 选择并显示工作空间
	 */
	public void addComposite_2(){
		
		composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(layout);
		 
	    text = new Text(composite_2, SWT.BORDER);
	    text.setText(EnvironmentVariables.workspacePath); 
	        
	    final Shell shell = composite_2.getShell();
	    button1 = new Button(composite_2,SWT.NONE);
	    button1.setText("浏览");
	    button1.addMouseListener(new MouseAdapter(){
	        public void mouseDown(MouseEvent e) {				
				DirectoryDialog dialog = new DirectoryDialog(shell);
				dialog.open();
			    path = dialog.getFilterPath();
			    text.setText(path);
			    EnvironmentVariables.workspacePath = path;//设置路径			   
			}		
	    });
	    
	    text.setEnabled(false);
  	    button1.setEnabled(false);
	    
	}
	
	/**
	 * 选择是否作为默认空间的按钮
	 */
	public void addComposite_3(){
		
		composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(layout);
		
		button2 = new Button(composite_3,SWT.CHECK);
        button2.setText("作为默认工作空间，以后不再询问。");
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
	 * 添加确定，取消按钮
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "确定", true); 
	   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "取消", false); 
	   super.initializeBounds(); 

	   Button button = getButton(IDialogConstants.OK_ID);
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   if(button2.getSelection()){
				   EnvironmentVariables.hasDefaultWorkspace = true;//设置有默认路径
			   }
		   }
	   });
	   
	} 

	/**
	 * 设置对话框的标题，图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("Designer Launcher"); 
		shell.setImage(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_WORKINGSET_WIZ).createImage());
	}
}
