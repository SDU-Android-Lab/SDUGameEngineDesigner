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
 * �½����̶Ի���
 * @author xzz
 *
 */
public class NewPojectDialog extends Dialog {

	/**
	 * ����������ʾ������Composite
	 */
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	
	/**
	 * ��д���������ı���
	 */
	private Text text_1;
	
	/**
	 * ��ʾ�����ռ�·�����ı���
	 */
	private Text text_2;
	
	/**
	 * ��������ļ��еİ�ť
	 */
	private Button button_2;
	
	 /**
     * ѡ���Ƿ�ʹ��Ĭ��·���İ�ť
     */
	private Button button_3;
	
	/**
	 * ������ʾ�ı�������label
	 */
	private Label label_1;
	private Label label_2;
	
	/**
	 * ���ֹ�����
	 */
	private RowLayout rowLayout;
	
	public NewPojectDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���Ի���
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
	 * ��д��������Text
	 */
	private void addComposite_1(){
		composite_1 = new Composite(composite,SWT.NONE);		
		composite_1.setLayout(rowLayout);
		label_1 = new Label(composite_1,SWT.NONE);
		label_1.setText("��������");
		text_1 = new Text(composite_1,SWT.BORDER);
		text_1.setLayoutData(new RowData(300,-1));	
		text_1.addTraverseListener(new TraverseListener(){ 
			public void keyTraversed(TraverseEvent e){ 
				if(e.keyCode == 13){ 
					e.doit = true;//ʹ�Iԭ��Ĺ�����Ч
					createProject();
				} 
			} 
		}); 
	}
	
	/**
	 * ѡ����ʾ����·��
	 */
	private void addComposite_2(){
		composite_2 = new Composite(composite,SWT.NONE);		
		composite_2.setLayout(rowLayout);
		label_2 = new Label(composite_2,SWT.NONE);
		label_2.setText("����·����");
		text_2 = new Text(composite_2,SWT.BORDER);
		text_2.setText(EnvironmentVariables.workspacePath);
		text_2.setLayoutData(new RowData(300,-1));
		button_2 = new Button(composite_2,SWT.NONE);
		button_2.setText("���");
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
	 * �Ƿ�ʹ��Ĭ��·���İ�ť
	 */
	public void addComposite_3(){

		composite_3 = new Composite(composite,SWT.NONE);
		composite_3.setLayout(rowLayout);
		button_3 = new Button(composite_3,SWT.CHECK);
		button_3.setText("ʹ��Ĭ��·��");
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
			   createProject();
		   }
	   });
	}    

	/**
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("�½�����"); 
		String path = ISharedImages.IMG_DEF_VIEW;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
	/**
	 * �½����̵ľ���ʵ��
	 */
	private void createProject(){
		String path = EnvironmentVariables.workspacePath+"\\"+text_1.getText();
		   File file = new File(path);
		   file.mkdir();
		   File file_1 = new File(path+"\\"+"Դ����");
		   file_1.mkdir();
		   File file_2 = new File(path+"\\"+"��Ϸ��Դ");
		   file_2.mkdir();
		   String[] name = new String[]{"����","��ͼ","����"}; 
		   File file_2_1  = null;
		   for(String element:name){
			  file_2_1 = new File(path+"\\"+"��Ϸ��Դ"+"\\"+element);
			  file_2_1.mkdir();
		   }
		   File file_3 = new File(path+"\\"+".project");
		   try {
			file_3.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//������Դ����������ʾ
		TreeViewer treeViewer = PackageExplorer.treeViewer;
		treeViewer.setInput(PackageExplorerElementFactory.getData());
		treeViewer.refresh();
	}

}
