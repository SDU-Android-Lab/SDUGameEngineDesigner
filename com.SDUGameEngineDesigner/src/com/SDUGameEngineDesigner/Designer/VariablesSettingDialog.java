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
 * 该类创建Designer设置对话框
 * @author xzz
 */
@SuppressWarnings("restriction")
public class VariablesSettingDialog extends Dialog {

	/**
	 * 用于显示部件的Composite
	 */
	private Composite composite;
	
	/**
	 * 选择是否自动登入的按钮
	 */
	private Button button1;
	
	/**
	 * 选择是否自动退出的按钮
	 */
	private Button button2;
	
	/**
	 * 选择是否更改工作空间的路径的按钮
	 */
	private Button button3;
	
	/**
	 * 用于浏览文件夹的按钮
	 */
	private Button button4;
	
	/**
	 * 用于显示工作空间的路径 的文本框
	 */
	private Text text;
	
	public VariablesSettingDialog(Shell parentShell) {
		super(parentShell);
		
	}
	

	/**
	 * 设置大小
	 * @Override
	 */
    protected Point getInitialSize() { 
		return new Point(300,200); 
	}
	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) { 				
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginTop = 10;   
		layout.marginRight = 30;
		
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(layout);
		
		//button1~button3用于设置Designer
		button1 = new Button(composite,SWT.CHECK);
		button1.setText("是否自动登入？");
		
        button2 = new Button(composite,SWT.CHECK);
        button2.setText("是否自动退出？");
       
        button3 = new Button(composite,SWT.CHECK);
        button3.setText("是否更改工作空间路径？");
        button3.addMouseListener(new MouseAdapter(){
    	   public void mouseDown(MouseEvent e){
    		   text.setEnabled(true);
    		   button4.setEnabled(true);
    	   }
        });
      
        //浏览目录，选择并显示工作空间
        button4 = new Button(composite,SWT.NONE);
        button4.setText("浏览");
        button4.setEnabled(false);
        button4.addMouseListener(new MouseAdapter(){
    	   public void mouseDown(MouseEvent e){
    		   DirectoryDialog dialog = new DirectoryDialog(new Shell());
    		   dialog.setMessage("请选择工作空间路径 :");
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
    		   //依据button1和button3是否有勾选设置Designer变量
    		   if(button1.getSelection()) 
    			   EnvironmentVariables.hasDefaultWorkspace = true;
    		   else
    			   EnvironmentVariables.hasDefaultWorkspace = false;
    		   if(button2.getSelection())
    			   EnvironmentVariables.isAutoExit = true;
    		   else
    			   EnvironmentVariables.isAutoExit = false;
    		   //修改Designer的变量
    		   EnvironmentVariables.variablesChange();
    	   }
       });
       
	} 

	/**
	 * 设置对话框的标题，图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("Designer Setting"); 
		shell.setImage(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_WORKINGSET_WIZ).createImage());
	}
}
