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
 * ɾ���Ի���
 * @author xzz
 *
 */
public class DeleteDialog extends Dialog {

	/**
	 * Ҫɾ���ļ����ļ�����·��
	 */
	private String name;
	
	/**
	 * ������ʾ������Composite
	 */
	private Composite parent;
	
	/**
	 * �����Ƿ�ȷ��Ҫ�ѹ��̴Ӵ�����ɾ����label
	 */ 
	private Label label;
	
	public DeleteDialog(Shell parentShell,String name) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		GridLayout layout = new GridLayout(1,false);
		layout.marginLeft = 30;
		layout.marginHeight = 30;
		
		this.parent = parent;
		parent.setLayout(layout);
	
		label = new Label(parent,SWT.CHECK);
		label.setText("�Ƿ�ȷ���Ӵ�����ɾ�������ɻظ���");
		
		return parent; 
	}

	
	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 
	
	/**
	 * ���ȷ����ȡ����ť
	 * ����ɾ���ķ�������ˢ����Դ����������ʾ
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
	 * ɾ�����̵ľ���ʵ��
	 */
	private void delete(){
		String path = EnvironmentVariables.workspacePath+"\\"+name;
		deleteProject(path);   
		
		//������Դ����������ʾ
		TreeViewer treeViewer = PackageExplorer.treeViewer;
		treeViewer.setInput(PackageExplorerElementFactory.getData());
		treeViewer.refresh();	
	}

	/**
	 * �ݹ�ɾ���ļ����ļ���
	 * @param path ���̵�·��
	 * @return boolean �Ƿ�ɾ���ɹ�
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
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("ɾ��"); 
		String path = ISharedImages.IMG_DEC_FIELD_WARNING;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

}
