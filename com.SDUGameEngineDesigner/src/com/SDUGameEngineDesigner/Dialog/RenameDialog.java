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
 * �������Ի���
 * �������setText()�������ð�ť�ı�
 * @author xzz
 *
 */
public class RenameDialog extends Dialog {

	/**
	 * ������ʾ������Composite
	 */
	private Composite parent;
		
	/**
	 * ��д�����Ƶ��ı���
	 */
	private Text text_1;
	
	/**
	 * ������ʾ�ı���label
	 */
	private Label label_1;

	/**
	 * �ļ����ļ��е�����
	 */
	private String name;
	
	/**
	 * �յĻ���������ĵĶ������ļ��У��ǿգ����Ǵ�����ĵĶ������ļ�,��ʱsΪ�ļ��Ĳ���·��
	 */
	private String s = "";
	
	/**
	 * �ļ��ĺ�׺��
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
	 * ���Ի���
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
		label_1.setText("�µ����ƣ�                                              ");
		label_1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text_1 = new Text(parent,SWT.BORDER);
		text_1.setText(name);
		text_1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        text_1.addTraverseListener(new TraverseListener(){ 
			public void keyTraversed(TraverseEvent e){ 
				if(e.keyCode == 13){ 
					e.doit = true;//ʹ�Iԭ��Ĺ�����Ч
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
	 * ���ȷ����ȡ����ť
	 * ��������������ļ����ļ���
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "ȷ��", true); 
	   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "ȡ��", false); 
	   super.initializeBounds(); 

	   Button button = getButton(IDialogConstants.OK_ID); 
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   rename();			
		   }
	   });
	}    

	/**
	 * ������������ʵ��
	 */
	private void rename(){
		File file = null;
		String path = null;
		String newPath = null;
		if(s.equals("")){//sΪ���ַ���������ļ�������
			path = EnvironmentVariables.workspacePath+"\\"+name;
			file = new File(path);
		    newPath = EnvironmentVariables.workspacePath+"\\"+text_1.getText();
		}else{//s�ǿգ�����ļ�����
			path = EnvironmentVariables.workspacePath+"\\"+s+name+ss;
			file = new File(path);
			newPath = EnvironmentVariables.workspacePath+"\\"+s+text_1.getText()+ss;
		}
		File newFile = new File(newPath);
		file.renameTo(newFile);
	    //������Դ����������ʾ
		TreeViewer treeViewer = PackageExplorer.treeViewer;
		treeViewer.setInput(PackageExplorerElementFactory.getData());
		treeViewer.refresh();      
		   
	}
	
	/**
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("������"); 
		String path = ISharedImages.IMG_DEF_VIEW;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
}
