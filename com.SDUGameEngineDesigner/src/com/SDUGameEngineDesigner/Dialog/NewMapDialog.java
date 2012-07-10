package com.SDUGameEngineDesigner.Dialog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 新建地图对话框
 * @author xzz
 *
 */
public class NewMapDialog extends Dialog {

	private Shell parentShell;
	private Composite parent;
	private RowLayout layout;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	private Composite composite_4;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Button button_2;
	private Button button_3;
	private Button button_4;
	private String path;
	
	public NewMapDialog(Shell parentShell,String path) {
		super(parentShell);
		this.parentShell = parentShell;
		this.path = path;
	}

	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		this.parent = parent;
		layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginLeft = 20;
		layout.marginRight = 20;
		layout.marginHeight = 10;
		initiaPart_1();
		initiaPart_2();
		initiaPart_3();
		initiaPart_4();
	
		return parent; 
	}

	private void initiaPart_1(){
		composite_1 = new Composite(parent,SWT.NONE);
		composite_1.setLayout(layout);
		label_1 = new Label(composite_1,SWT.NONE);
		label_1.setText("地图名: ");
		text_1 = new Text(composite_1,SWT.NONE);
		text_1.setLayoutData(new RowData(100,20));
	}
	
	private void initiaPart_2(){
		composite_2 = new Composite(parent,SWT.NONE);
		composite_2.setLayout(layout);
		label_2 = new Label(composite_2,SWT.NONE);
		label_2.setText("背景图: ");
		text_2 = new Text(composite_2,SWT.NONE);
		text_2.setLayoutData(new RowData(100,20));
		button_2 = new Button(composite_2,SWT.NONE);
		button_2.setText("浏览");
		button_2.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = new FileDialog(parentShell).open();
				if(s!=null)
				   text_2.setText(s);
			}
		});
		
	}
	
	private void initiaPart_3(){
		composite_3 = new Composite(parent,SWT.NONE);
		composite_3.setLayout(layout);
		label_3 = new Label(composite_3,SWT.NONE);
		label_3.setText("资源图: ");
		text_3 = new Text(composite_3,SWT.NONE);
		text_3.setLayoutData(new RowData(100,20));
		button_3 = new Button(composite_3,SWT.NONE);
		button_3.setText("浏览");
		button_3.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = new FileDialog(parentShell).open();
				if(s!=null)
				   text_3.setText(s);
			}
		});
	
	}
	
	private void initiaPart_4(){
		composite_4 = new Composite(parent,SWT.NONE);
		composite_4.setLayout(layout);
		label_4 = new Label(composite_4,SWT.NONE);
		label_4.setText("背景音乐: ");
		text_4 = new Text(composite_4,SWT.NONE);
		text_4.setLayoutData(new RowData(90,20));
		button_4 = new Button(composite_4,SWT.NONE);
		button_4.setText("浏览");
		button_4.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = new FileDialog(parentShell).open();
				if(s!=null)
				   text_4.setText(s);
			}
		});
	}
	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 
	
	/**
	 * 添加确定
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "确定", true); 
	   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "取消", true); 
	   super.initializeBounds(); 
	   
	   Button button = getButton(IDialogConstants.OK_ID);
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   newMap();
		   }
	   });

	}    
	
	/**
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("新建地图"); 
		String path = ISharedImages.IMG_TOOL_NEW_WIZARD;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
	/**
	 * 创建地图
	 */
	private void newMap(){
		File file = new File(path.substring(0,path.lastIndexOf("\\"))+"\\"+text_1.getText()+".txt");
		try {
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(text_2.getText()+"\n");
			writer.write(text_3.getText()+"\n");
			writer.write(text_4.getText()+"\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
