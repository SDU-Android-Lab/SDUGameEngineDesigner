package com.SDUGameEngineDesigner.Dialog;

import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 捡拾物品的对话框
 * @author xzz
 *
 */
public class FindThingDialog extends Dialog{

	private Shell parentShell;
	private List list;
	private Button button_1,button_2;
	private Label label;
	private Combo combo;
	
	/**
	 * 物品的名字链表
	 */
	private Vector<String> thingName = new Vector<String>();
	
	/**
	 * 出现物品的概率
	 */
	private double thingRate = 0.1;
	
	public FindThingDialog(Shell parentShell) {
		super(parentShell);
		this.parentShell = parentShell;
	}
	
	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,5,1));
		GridLayout layout = new GridLayout(6,true);
		composite.setLayout(layout);
		
		Composite composite_1 = new Composite(composite,SWT.NONE);
		GridData data = new GridData(SWT.FILL,SWT.FILL,true,true,5,2);
		data.heightHint = 300;
		composite_1.setLayoutData(data);
		FillLayout layout_1 =new FillLayout(SWT.HORIZONTAL);
		layout_1.spacing = 20;
		composite_1.setLayout(layout_1);		
		list = new List(composite_1,SWT.H_SCROLL|SWT.V_SCROLL);
      
		Composite composite_2 = new Composite(composite,SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,true,1,1));
		FillLayout layout_2 =new FillLayout(SWT.VERTICAL);
		layout_2.spacing = 20;
		composite_2.setLayout(layout_2);
		button_1 = new Button(composite_2,SWT.PUSH);
		button_1.setText("新增");
		button_1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				AddThingDialog dialog = new AddThingDialog(parentShell);
				dialog.open();
				String[] name = dialog.name;
				
				if(name == null)
					return;
				for(String ss: name){
					list.add(ss);
					thingName.add(ss);
				}
				list.redraw();
			}
		});
		
		button_2 = new Button(composite_2,SWT.PUSH);
		button_2.setText("删除");
		button_2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				String[] name = list.getSelection();
				if(name == null)
					return;
				for(String ss : name){
					list.remove(ss);
					thingName.remove(ss);
				}
				list.redraw();
			}
		});
				
		Composite composite_3 = new Composite(parent,SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		RowLayout layout_3 = new RowLayout(SWT.HORIZONTAL);
		composite_3.setLayout(layout_3);
		label = new Label(composite_3,SWT.NONE);
		label.setText("物品出现概率 ：");
		combo = new Combo(composite_3,SWT.NONE);
		combo.setItems(new String[]{"0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1"});
		combo.select(0);
		combo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo.getSelectionIndex();
				 if(index<0)
				   return;
				 thingRate = Double.parseDouble(combo.getItem(index));
			}
		});
		
		return parent; 
	}

	/**
	 * 返回物品名字数组，可能为null
	 * @return String[]
	 */
	public Vector<String> getThingName(){
		return thingName;
	}
	
	/**
	 * 返回物品出现的概率
	 * @return int
	 */
	public double getRate(){
		return thingRate;
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
	   button.setFocus();
	   button.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				judge();
			}
			   
	   });	  
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   judge();
		   }
	   });   
	}  
	
	private void judge(){
		if(thingName.size() == 0)
			MessageDialog.openWarning(parentShell, "友情提示", "请选择一个以上的物品");
			
	}
	
	
	/**
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("捡拾物品"); 
		String path = ISharedImages.IMG_ETOOL_HOME_NAV;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}

	
	class AddThingDialog extends Dialog{
		private List list;
		private String[] name;
		
		public AddThingDialog(Shell parentShell) {
			super(parentShell);
		}
		
		protected Control createDialogArea(Composite parent){
			
			GridData data = new GridData(SWT.FILL,SWT.FILL,true,true,1,1);
			data.heightHint = 200;
			data.horizontalIndent = 10;
			data.verticalIndent = 10;
			list = new List(parent,SWT.H_SCROLL|SWT.V_SCROLL|SWT.MULTI);
			list.setLayoutData(data);
			
			return parent;
		
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
				   name = list.getSelection();
			   }
		   });
		}    
		
		/**
		 * 设置对话框的标题,图片
		 */
		protected void configureShell(Shell shell) {      
			super.configureShell(shell);      
			shell.setText("新增物品"); 
			String path = ISharedImages.IMG_DEF_VIEW;
			shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
		}
	
	}
	
}
