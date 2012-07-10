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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * ·�����˶Ի���
 * @author xzz
 *
 */
public class EncounterEnemyDialog extends Dialog{

	/**
	 * ���ֶ����޹ؽ�Ҫ����������Ҫ��ע��
	 */
	private Shell parentShell;
	private List list;
	private Button button_1,button_2,button_3;
	private Label label,label_1;
	private Combo combo;
	private Text text;
	
	/**
	 * ���˵�������������Ϊ�ջ�null
	 */
	private Vector<String> enemyName = new Vector<String>();
	
	/**
	 * ս������ͼ��ַ,����Ϊ�ջ�null
	 */
	private String path = null;//����Ӧ��Ĭ��·��
	
	/**
	 * ���ֵ��˵ĸ���,Ĭ��Ϊ0.1
	 */
	private double enemyRate = 0.1;
	
	public EncounterEnemyDialog(Shell parentShell) {
		super(parentShell);
		this.parentShell = parentShell;
	}
	
	/**
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,5,1));
		GridLayout layout = new GridLayout(7,true);
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
		button_1.setText("����");
		button_1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				AddEnemyDialog dialog = new AddEnemyDialog(parentShell);
				int returnCode = dialog.open();
				if(returnCode == AddEnemyDialog.CANCEL)
					return;
				String[] name = dialog.name;
				if(name == null)
					return;
				for(String ss : name){
					list.add(ss);
					enemyName.add(ss);//��ӵ���������������
				}
				list.redraw();
			}
		});
		
		button_2 = new Button(composite_2,SWT.PUSH);
		button_2.setText("ɾ��");
		button_2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				String[] name = list.getSelection();
				if(name == null)
					return;
				for(String ss : name){
					list.remove(ss);
					enemyName.remove(ss);//ɾ����һ�����ֵ�ss
				}
				list.redraw();
			}
		});
		
		
		Composite composite_3 = new Composite(parent,SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		RowLayout layout_3 = new RowLayout(SWT.HORIZONTAL);
		composite_3.setLayout(layout_3);
		label = new Label(composite_3,SWT.NONE);
		label.setText("���˳��ָ��� ��");
		combo = new Combo(composite_3,SWT.NONE);
		combo.setItems(new String[]{"0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1"});
	    combo.select(0);
		combo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo.getSelectionIndex();
				 if(index<0)
				   return;
				 enemyRate = Double.parseDouble(combo.getItem(index));
			}
		});
		
		Composite composite_4 = new Composite(parent,SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		GridLayout layout_4 = new GridLayout(7,true);
		composite_4.setLayout(layout_4);
		label_1 = new Label(composite_4,SWT.NONE);
		label_1.setText("ѡ��ս��������");
		label_1.setLayoutData(new GridData(SWT.FILL,SWT.LEFT,true,true,2,1));
		text = new Text(composite_4,SWT.NONE);
		text.setLayoutData(new GridData(SWT.FILL,SWT.LEFT,true,true,4,1));
	//	text.setText(path); ����path��ΪĬ��·��ʱ������ʹ�����
		button_3 = new Button(composite_4,SWT.PUSH);
		button_3.setText("���");
		button_3.setLayoutData(new GridData(SWT.FILL,SWT.RIGHT,true,true,1,1));
		button_3.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog dialog = new FileDialog(parentShell,SWT.NONE);
				path = dialog.open();
				if(path == null)
					return;
				text.setText(path);
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
	 * @Override 
	 */
	protected void initializeBounds() { 
	
	   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "ȷ��", true); 
	   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "ȡ��", false); 
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
		if(enemyName.size() == 0)
			MessageDialog.openWarning(parentShell, "������ʾ", "��ѡ��һ�����ϵĵ���");
			
	}
	/**
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("·������"); 
		String path = ISharedImages.IMG_DEF_VIEW;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	
	/**
	 * ���ص����������飬����Ϊ�ջ�null
	 * @return String[]
	 */
	public Vector<String> getEnemyName(){
		return enemyName;
	}
	
	/**
	 * ����ս������ͼ��·��������Ϊ�ջ�null
	 * @return String
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * ���ص��˳��ֵĸ���
	 * @return int
	 */
	public double getRate(){
		return enemyRate;
	}
	
	class AddEnemyDialog extends Dialog{
		private List list;
		
		/**
		 *ѡ����˵��������飬����Ϊnull 
		 */
		private String[] name;
		public AddEnemyDialog(Shell parentShell) {
			super(parentShell);
		}
		
		protected Control createDialogArea(Composite parent){		
			GridData data = new GridData(SWT.FILL,SWT.FILL,true,true,1,1);
			data.heightHint = 200;
			data.horizontalIndent = 10;
			data.verticalIndent = 10;
			list = new List(parent,SWT.H_SCROLL|SWT.V_SCROLL|SWT.MULTI);
			list.setLayoutData(data);
			list.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e) {
					name = list.getSelection();
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
		 * @Override 
		 */
		protected void initializeBounds() { 
		
		   super.createButton((Composite)getButtonBar(), IDialogConstants.OK_ID, "ȷ��", true); 
		   super.createButton((Composite)getButtonBar(), IDialogConstants.CANCEL_ID, "ȡ��", false); 
		   super.initializeBounds(); 
//		   Button button = getButton(IDialogConstants.OK_ID);
//		   button.addMouseListener(new MouseAdapter(){
//			   public void mouseDown(MouseEvent e){
//				   if(name == null)
//					   MessageDialog.openWarning(parentShell, "������ʾ", "��ѡ��õ���");
//			   }
//		   });
		}    
		
		/**
		 * ���öԻ���ı���,ͼƬ
		 */
		protected void configureShell(Shell shell) {      
			super.configureShell(shell);      
			shell.setText("��������"); 
			String path = ISharedImages.IMG_DEF_VIEW;
			shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
		}	
	}
}
