package com.SDUGameEngineDesigner.Dialog;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.View.PackageExplorer;
import com.SDUGameEngineDesigner.View.PackageExplorerElementFactory;

/**
 * 重命名对话框
 * 建议调用setText()方法设置按钮文本
 * @author xzz
 *
 */
public class RenameDialog extends Dialog {

	/**
	 * 用于显示部件的Composite
	 */
	private Composite parent;
		
	/**
	 * 填写新名称的文本框
	 */
	private Text text_1;
	
	/**
	 * 用于显示文本的label
	 */
	private Label label_1;

	/**
	 * 文件或文件夹的名称
	 */
	private String name;
	
	/**
	 * 空的话，代表更改的对象是文件夹，非空，则是代表更改的对象是文件,此时s为文件的部分路径
	 */
	private String s = "";
	
	/**
	 * 文件的后缀名
	 */
	private String ss;
	
	public RenameDialog(Shell parentShell,String name) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		if(name.contains("\\")){
			this.s = name.substring(0, name.lastIndexOf("\\")+1);
			this.name = name.substring(name.lastIndexOf("\\")+1, name.lastIndexOf("."));
			this.ss = name.substring(name.lastIndexOf("."), name.length());
		}else{
			this.name = name;
		}
	}

	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginRight = 30;
		layout.marginTop = 20;
		
		this.parent = parent;
		parent.setLayout(layout);
		
		label_1 = new Label(parent,SWT.NONE);
		label_1.setText("新的名称：                                              ");
		label_1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text_1 = new Text(parent,SWT.BORDER);
		text_1.setText(name);
		text_1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        text_1.addTraverseListener(new TraverseListener(){ 
			public void keyTraversed(TraverseEvent e){ 
				if(e.keyCode == 13){ 
					e.doit = true;//使鍵原來的功能有效
					rename();
				} 
			} 
		}); 
		
		return parent; 
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
			   rename();			
		   }
	   });
	}    

	/**
	 * 具体重命名的实现
	 */
	private void rename(){
		File file = null;
		String path = null;
		String newPath = null;
		if(s.equals("")){//s为空字符串，则对文件夹命名
			path = EnvironmentVariables.workspacePath+"\\"+name;
			file = new File(path);
		    newPath = EnvironmentVariables.workspacePath+"\\"+text_1.getText();
		}else{//s非空，则对文件命名
			path = EnvironmentVariables.workspacePath+"\\"+s+name+ss;
			file = new File(path);
			newPath = EnvironmentVariables.workspacePath+"\\"+s+text_1.getText()+ss;
		}
		File newFile = new File(newPath);
		file.renameTo(newFile);
	    //更新资源管理器的显示
		TreeViewer treeViewer = PackageExplorer.treeViewer;
		treeViewer.setInput(PackageExplorerElementFactory.getData());
		treeViewer.refresh();      
		   
	}
	
	/**
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("重命名"); 
		String path = ISharedImages.IMG_DEF_VIEW;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
}
