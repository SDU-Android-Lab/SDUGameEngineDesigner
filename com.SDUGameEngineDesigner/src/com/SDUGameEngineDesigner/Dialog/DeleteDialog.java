package com.SDUGameEngineDesigner.Dialog;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
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
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.View.PackageExplorer;
import com.SDUGameEngineDesigner.View.PackageExplorerElementFactory;

/**
 * 删除对话框
 * @author xzz
 *
 */
public class DeleteDialog extends Dialog {

	/**
	 * 要删除文件的文件名或路径
	 */
	private String name;
	
	/**
	 * 用于显示部件的Composite
	 */
	private Composite parent;
	
	/**
	 * 用于是否确定要把工程从磁盘上删除的label
	 */ 
	private Label label;
	
	public DeleteDialog(Shell parentShell,String name) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginHeight = 30;
		
		this.parent = parent;
		parent.setLayout(layout);
	
		label = new Label(parent,SWT.CHECK);
		label.setText("是否确定从磁盘上删除，不可回复？");
		
		return parent; 
	}

	
	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 
	
	/**
	 * 添加确定，取消按钮
	 * 调用删除的方法，并刷新资源管理器的显示
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
	            delete();	
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}	   
		   });	  
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   delete();
		   }
	   });   
	}    
	
	/**
	 * 删除工程的具体实现
	 */
	private void delete(){
		String path = EnvironmentVariables.workspacePath+"\\"+name;
		deleteProject(path);   
		
		//更新资源管理器的显示
		TreeViewer treeViewer = PackageExplorer.treeViewer;
		treeViewer.setInput(PackageExplorerElementFactory.getData());
		treeViewer.refresh();	
	}

	/**
	 * 递归删除文件和文件夹
	 * @param path 工程的路径
	 * @return boolean 是否删除成功
	 */
	private boolean deleteProject(String path){
		File file = new File(path);
		if(file.isFile())
		    return file.delete();		
		File[] array = file.listFiles();	
		if(array==null||array.length==0)
			return file.delete();
		for(int i = 0;i<array.length;i++){
			deleteProject(array[i].getPath());
		}
		return file.delete();			
	}
	
	/**
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("删除"); 
		String path = ISharedImages.IMG_DEC_FIELD_WARNING;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

}
