package com.SDUGameEngineDesigner.Dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * �����л��Ի���
 * @author xzz
 *
 */
public class ChangeSceneDialog extends Dialog {

	/**
	 * ���ֶ����޹ؽ�Ҫ����������Ҫ��ע��
	 */
	private Shell parentShell;
	private Composite composite_1;
	private Composite composite_2;
	private Label label_1;
	private Label label_2;
	private Text text;
	private Button button;
	private Canvas canvas;
	private Point origin ;
	private Image image;
	private boolean isSelect=false;
	
	/**
	 * ѡ����ת��ͼ��·��
	 */
	public String path;	
	
	/**
	 * ��תλ�õ�����
	 */
	public int x,y;
	
	public ChangeSceneDialog(Shell parentShell) {
		super(parentShell);
		this.parentShell = parentShell;
	}

	/**
	 * ���Ի���
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		composite_1 = new Composite(parent,SWT.NONE);
		GridLayout layout1 = new GridLayout(8,true);
		layout1.marginLeft = 20;
		layout1.marginTop = 10;
		composite_1.setLayout(layout1);	
		
		label_1 = new Label(composite_1,SWT.NONE);
		label_1.setText("ѡ����ת�ĵ�ͼ��");
		label_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,1));
		
		text = new Text(composite_1,SWT.NONE);
		text.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,4,1));
		
		button = new Button(composite_1,SWT.PUSH);
		button.setText("���");
		button.setLayoutData(new GridData(SWT.RIGHT,SWT.FILL,true,true,1,1));
		button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog dialog = new FileDialog(parentShell);
				dialog.setFilterExtensions(new String[]{"*.jpg"});//����Ӧ��Ϊ.map�õ�����ͼ�ĵ�ַ
				path = dialog.open();
				if(path==null)
					return;
				ImageData data = new ImageData(path);
				data = data.scaledTo(data.width/32*32, data.height/32*32);
				image = new Image(Display.getCurrent(),data);		
				text.setText(path);
				addScrollBar();
				canvas.redraw();
			}

		});
		
		composite_2 = new Composite(parent,SWT.NONE);
		RowLayout layout2 = new RowLayout(SWT.VERTICAL);
		layout2.marginLeft = 20;
		layout2.marginTop = 10;
		composite_2.setLayout(layout2);
		label_2 = new Label(composite_2,SWT.NONE);
		label_2.setText("�뵥����ͼ��ѡ����ת��λ�á��Ҽ�ȡ����");
		label_2.setLayoutData(new RowData(300,30));
		
		canvas = new Canvas(composite_2,SWT.H_SCROLL|SWT.V_SCROLL|SWT.DOUBLE_BUFFERED);
		canvas.setLayoutData(new RowData(500,400));
		addCanvasListener();
		
		return parent; 
	}

	/**
	 * ���ù������ͻ��������С����
	 */
	private void addScrollBar(){
		origin = new Point (0, 0);
		final ScrollBar hBar = canvas.getHorizontalBar();
		hBar.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				int hSelection = hBar.getSelection ();
				int destX = -hSelection - origin.x;
				Rectangle rect = image.getBounds ();
				canvas.scroll (destX, 0, 0, 0, rect.width, rect.height, false);
				origin.x = -hSelection;
			}
		});
		final ScrollBar vBar = canvas.getVerticalBar ();
		vBar.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				int vSelection = vBar.getSelection ();
				int destY = -vSelection - origin.y;
				Rectangle rect = image.getBounds ();
				canvas.scroll (0, destY, 0, 0, rect.width, rect.height, false);
				origin.y = -vSelection;
			}
		});
		canvas.addListener (SWT.Resize,  new Listener () {
			public void handleEvent (Event e) {				
				Rectangle rect = image.getBounds ();
				Rectangle client = canvas.getClientArea ();
				hBar.setMaximum (rect.width);
				vBar.setMaximum (rect.height);
				hBar.setThumb (Math.min (rect.width, client.width));
				vBar.setThumb (Math.min (rect.height, client.height));
				int hPage = rect.width - client.width;
				int vPage = rect.height - client.height;
				int hSelection = hBar.getSelection ();
				int vSelection = vBar.getSelection ();
				if (hSelection >= hPage) {
					if (hPage <= 0) hSelection = 0;
					origin.x = -hSelection;
				}
				if (vSelection >= vPage) {
					if (vPage <= 0) vSelection = 0;
					origin.y = -vSelection;
				}
				canvas.redraw ();
			}
		});
		
		
	}
	
	/**
	 * ��ӻ����ϵ����������ػ����
	 */
	private void addCanvasListener(){
		canvas.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e){
				if(image == null||!image.getBounds().contains(e.x,e.y))
					return;
				if(e.button == 1){
					isSelect = true;
					x = (e.x-origin.x)/32*32;
					y = (e.y-origin.y)/32*32;
					canvas.redraw();
					return;
				}
				if(e.button == 3){
					isSelect = false;
					canvas.redraw();
					return;
				}
			}
		});
		
		canvas.addPaintListener(new PaintListener(){
			@Override
			public void paintControl(PaintEvent e) {		
			    if(image==null)
			    	return;
			    Rectangle rec = image.getBounds();
			    int width = rec.width;
			    int height = rec.height;
				
			    GC gc = e.gc;
			    Image bufferedImage = new Image(Display.getCurrent(),width,height);
			    GC bufferG = new GC(bufferedImage);
			    bufferG.drawImage(image, 0, 0);
		
			    for(int i = 0;i<width/32+1;i++){
			    	bufferG.drawLine(i*32, 0, i*32, height);
			    }
			    for(int i =0;i<height/32+1;i++){
			    	bufferG.drawLine(0, i*32, width, i*32);
			    }
			    
			    if(isSelect){
			    	bufferG.setBackground(new Color(Display.getCurrent(),0,255,255));
			    	bufferG.fillOval(x, y, 32, 32);
			    }
			    
			    gc.drawImage(bufferedImage,origin.x, origin.y);
			    bufferedImage.dispose();
			    bufferG.dispose();
			    gc.dispose();
			}
			
		});
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
	   button.addMouseListener(new MouseAdapter(){
		   public void mouseDown(MouseEvent e){
			   judge();
		   }
	   });
	   button.addSelectionListener(new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			judge();
		}
		   
	   });
	}    
	
	private void judge(){
		if(path == null || !isSelect)
			MessageDialog.openWarning(parentShell, "������ʾ", "������ѡ����ת��ͼ����ת�ص㣡");
	}
	/**
	 * ���öԻ���ı���,ͼƬ
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("�л�����"); 
		String path = ISharedImages.IMG_DEF_VIEW;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
}