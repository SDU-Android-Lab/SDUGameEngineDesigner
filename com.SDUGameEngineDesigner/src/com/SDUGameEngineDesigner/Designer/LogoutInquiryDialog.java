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
 * 该类创建退出询问框
 * @author xzz
 */
public class LogoutInquiryDialog extends Dialog {

	/**
	 * 用于显示部件的Composite
	 */
	private Composite composite;	
	
	/**
	 * 默认路径按钮
	 */
	private Button button1;
	
	/**
	 * 最小化到托盘按钮
	 */
    private Button button2;
    
    /**
     * 是否要选择退出
     */
    public boolean isExit = true;
    
	public LogoutInquiryDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1,true));
		
		Group group = new Group(composite,SWT.NONE);
		group.setText("请选择：");
		group.setLayout(new GridLayout());
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
        button1 = new Button(group,SWT.RADIO);
        button1.setText("自动退出，以后不再询问。");
           
        button2 = new Button(group,SWT.RADIO);
        button2.setText("不退出，最小化到托盘");
               
		return composite; 
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
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		String path = ISharedImages.IMG_DEC_FIELD_WARNING;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
}
