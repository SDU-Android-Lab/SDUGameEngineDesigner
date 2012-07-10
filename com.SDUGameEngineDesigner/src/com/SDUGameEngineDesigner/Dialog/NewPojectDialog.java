package com.SDUGameEngineDesigner.Dialog;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.View.PackageExplorer;
import com.SDUGameEngineDesigner.View.PackageExplorerElementFactory;

/**
 * 新建工程对话框
 * @author xzz
 *
 */
public class NewPojectDialog extends Dialog {

	/**
	 * 各个用于显示部件的Composite
	 */
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	
	/**
	 * 填写工程名的文本框
	 */
	private Text text_1;
	
	/**
	 * 显示工作空间路径的文本框
	 */
	private Text text_2;
	
	/**
	 * 用于浏览文件夹的按钮
	 */
	private Button button_2;
	
	 /**
     * 选择是否使用默认路径的按钮
     */
	private Button button_3;
	
	/**
	 * 用于显示文本的两个label
	 */
	private Label label_1;
	private Label label_2;
	
	/**
	 * 布局管理器
	 */
	private RowLayout rowLayout;
	
	public NewPojectDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1,false));
		
		rowLayout = new RowLayout();
		rowLayout.spacing = 30;
		rowLayout.marginLeft = 30;
		rowLayout.marginTop = 30;   
		rowLayout.marginRight = 30;
		
		addComposite_1();
		addComposite_3();
		addComposite_2();
		
		return composite; 
	}

	/**
	 * 填写工程名的Text
	 */
	private void addComposite_1(){
		composite_1 = new Composite(composite,SWT.NONE);		
		composite_1.setLayout(rowLayout);
		label_1 = new Label(composite_1,SWT.NONE);
		label_1.setText("工程名：");
		text_1 = new Text(composite_1,SWT.BORDER);
		text_1.setLayoutData(new RowData(300,-1));	
		text_1.addTraverseListener(new TraverseListener(){ 
			public void keyTraversed(TraverseEvent e){ 
				if(e.keyCode == 13){ 
					e.doit = true;//使鍵原來的功能有效
					createProject();
				} 
			} 
		}); 
	}
	
	/**
	 * 选择并显示工作路径
	 */
	private void addComposite_2(){
		composite_2 = new Composite(composite,SWT.NONE);		
		composite_2.setLayout(rowLayout);
		label_2 = new Label(composite_2,SWT.NONE);
		label_2.setText("工程路径：");
		text_2 = new Text(composite_2,SWT.BORDER);
		text_2.setText(EnvironmentVariables.workspacePath);
		text_2.setLayoutData(new RowData(300,-1));
		button_2 = new Button(composite_2,SWT.NONE);
		button_2.setText("浏览");
		button_2.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				DirectoryDialog dialog = new DirectoryDialog(new Shell());
				dialog.open();
				text_2.setText(dialog.getFilterPath());
			}
		});
		
		label_2.setEnabled(false);
		text_2.setEnabled(false);
		button_2.setEnabled(false);
	}
	
	
	/**
	 * 是否使用默认路径的按钮
	 */
	public void addComposite_3(){

		composite_3 = new Composite(composite,SWT.NONE);
		composite_3.setLayout(rowLayout);
		button_3 = new Button(composite_3,SWT.CHECK);
		button_3.setText("使用默认路径");
		button_3.setSelection(true);
		button_3.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				if(button_3.getSelection()){
					label_2.setEnabled(true);
					text_2.setEnabled(true);
					button_2.setEnabled(true);			
				}else{
					label_2.setEnabled(false);
					text_2.setEnabled(false);
					button_2.setEnabled(false);
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
	 * 创建工程所需的文件和文件夹
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "确定", true); 
	   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "取消", false); 
	   super.initializeBounds(); 

	   Button button = getButton(IDialogConstants.OK_ID);
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   createProject();
		   }
	   });
	}    

	/**
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("新建工程"); 
		String path = ISharedImages.IMG_DEF_VIEW;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
	/**
	 * 新建工程的具体实现
	 */
	private void createProject(){
		String path = EnvironmentVariables.workspacePath+"\\"+text_1.getText();
		   File file = new File(path);
		   file.mkdir();
		   File file_1 = new File(path+"\\"+"源代码");
		   file_1.mkdir();
		   File file_2 = new File(path+"\\"+"游戏资源");
		   file_2.mkdir();
		   String[] name = new String[]{"声音","地图","动画"}; 
		   File file_2_1  = null;
		   for(String element:name){
			  file_2_1 = new File(path+"\\"+"游戏资源"+"\\"+element);
			  file_2_1.mkdir();
		   }
		   File file_3 = new File(path+"\\"+".project");
		   try {
			file_3.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//更新资源管理器的显示
		TreeViewer treeViewer = PackageExplorer.treeViewer;
		treeViewer.setInput(PackageExplorerElementFactory.getData());
		treeViewer.refresh();
	}

}
