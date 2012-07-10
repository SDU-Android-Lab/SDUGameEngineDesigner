package com.SDUGameEngineDesigner.MapEditor;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.ToolBar;

import com.SDUGameEngineDesigner.Dialog.MapPreviewDialog;
import com.SDUGameEngineDesigner.Module.ChangeScene;
import com.SDUGameEngineDesigner.Module.CollidePoint;
import com.SDUGameEngineDesigner.Module.EncounterEnemy;
import com.SDUGameEngineDesigner.Module.FindThing;
import com.SDUGameEngineDesigner.Module.InterceptImage;

/**
 * ������ͼ�༭���ĵ�������
 * 
 * @author xzz
 * 
 */
public class MapEditorPart_3 {
	
	private MapEditor editor;
	
	/**
	 * ͼƬ·��
	 */
	private String imagePath;
	
	protected Group group_2;
	private Composite composite_1;
	private MapEditorBridge bridge;
	
	/**
	 * ������
	 */
	private ToolBar bar_3;

	/**
	 * ��ͼԤ����ť
	 */
	private Button button_13;

	/**
	 * ��ͼ���ɰ�ť
	 */
	private Button button_14;

	/**
	 * ��ͼ�Ŵ�ť
	 */
	private Button button_16;

	/**
	 * ��ͼ��С��ť
	 */
	private Button button_17;
	
	/**
	 * 
	 */
    private Combo combo_1;
	
	/**
	 * ��ʾ��ͼ�Ļ���
	 */
	protected  Canvas canvas;

	/**
	 * canvas�ϵ��Ҽ������˵�
	 */
	private Menu menu;
	
	/**
	 * �����˵��Ĳ˵���
	 */
	private MenuItem item;
	private MenuItem item1;
	private MenuItem item2;
	private MenuItem item3;
	private MenuItem item4;
	
	/**
	 * ��ͼ
	 */
	private Image image;

	/**
	 * �����ͼƬ
	 */
	private Image bufferImage;

	/**
	 * ԭ��λ��
	 */
	private Point origin;

	/**
	 * ˮƽ������
	 */
	private ScrollBar hBar;

	/**
	 * ��ֱ������
	 */
	private ScrollBar vBar;

	/**
	 * �Ŵ����С�ı���
	 */
	protected double rate = 1.0;

	/**
	 * ԭͼ��image���Ĵ�С����
	 */
	private Rectangle imageRec;

	/**
	 * ԭͼ�Ŀ�
	 */
	private int width;

	/**
	 * ԭͼ�ĸ�
	 */
	private int height;

	/**
	 * ��һ�����洫�ݹ���ͼƬ��Ϣ��Vector
	 */
    private Vector<InterceptImage> newImageVector_1 = new Vector<InterceptImage>();
    
    /**
	 * �ڶ������洫�ݹ���ͼƬ��Ϣ��Vector
	 */
    private Vector<InterceptImage> newImageVector_2 = new Vector<InterceptImage>();
    
    /**
     * �������б����¼�Vector��List
     */
    public List allEvent = new LinkedList();
    
    /**
     * ������ײ�¼��ڻ�����λ�õ�Vector
     */
    private Vector<CollidePoint> collideVector = new Vector<CollidePoint>();
    
    /**
     * �����л������ڻ�����λ�õ�Vector
     */
    protected Vector<ChangeScene> changeScene = new Vector<ChangeScene>();
    
    /**
     * ����·�������ڻ�����λ�õ�Vector
     */
    protected Vector<EncounterEnemy> encounterEnemy = new Vector<EncounterEnemy>();
    
    /**
     * �����ʰ��Ʒ�ڻ�����λ�õ�Vector
     */
    protected Vector<FindThing> findThing = new Vector<FindThing>();
    
    /**
     * ������ʼλ��
     */
    private int x = 0,y = 0;
    
    private int layer = 0;
    
	public MapEditorPart_3(Group group_2,String imagePath,MapEditor editor) {
		this.group_2 = group_2;
		this.imagePath = imagePath;
		this.editor = editor;
		composite_1 = new Composite(group_2, SWT.NONE);
		composite_1.setLayout(new GridLayout());
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,2, 1));
		//��˳������б����¼���Vector�ӵ�һ��List��
		allEvent.add(collideVector);
		allEvent.add(changeScene);
		allEvent.add(encounterEnemy);
		allEvent.add(findThing);
	}
	
	public void setBridge(MapEditorBridge bridge){
		this.bridge = bridge;	
	}
	
	/**
	 * ��ʼ��
	 */
	public void initiaPart_3(){
		bar_3 = new ToolBar(composite_1, SWT.FLAT | SWT.RIGHT);
		bar_3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		bar_3.setLayout(new FillLayout());

		createButton_13();
		createButton_14();
		createButton_16();
		createButton_17();

		Label label = new Label(bar_3,SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setText("ѡ����Ʋ�:");
		
		createCombo_1();
		createCanvas();
		
	}

	/**
	 * ��ͼԤ��
	 */
	private void createButton_13() {
		button_13 = new Button(bar_3, SWT.PUSH);
		button_13.setText("��ͼԤ��");
		button_13.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				MapPreviewDialog dialog = new MapPreviewDialog(group_2.getShell());
				dialog.open();
			}
		});
	}

	private void createButton_14() {
		button_14 = new Button(bar_3, SWT.PUSH);
		button_14.setText("��ͼ����");

	}

	/**
	 * �Ŵ�ͼƬ��ť
	 */
	private void createButton_16() {
		button_16 = new Button(bar_3, SWT.NONE);
		button_16.setText("�Ŵ�");
		button_16.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				enlargeMap();
			}
		});
	}

	/**
	 * ��СͼƬ��ť
	 */
	private void createButton_17() {
		button_17 = new Button(bar_3, SWT.NONE);
		button_17.setText("��С");
		button_17.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				miniMap();
			}
		});
	}

	/**
	 * ѡ��༭��
	 */
	public void createCombo_1(){
    	combo_1 = new Combo(bar_3,SWT.CHECK);
		combo_1.setItems(new String[]{"��ͼ��һ","��ͼ���","�¼���","���в�"});
		combo_1.select(0);
    }
	
	/**
	 * ѡ��༭��ļ���
	 */
	public void addCombo_1Lietener(){
		combo_1.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo_1.getSelectionIndex();
			    if(index<0)
			    	return;
			    Button button_18 = bridge.bar_2.button_18;
			    if(combo_1.getItem(index).equals("��ͼ��һ")){		
			    	button_18.setEnabled(false);
			    	layer = MapEditorConstants.PAINT_LAYER_1;		    	
			    }else{
			    	if(combo_1.getItem(index).equals("��ͼ���")){
			    		button_18.setEnabled(false);
				    	layer = MapEditorConstants.PAINT_LAYER_2;			    		
			    	}else{
			    		button_18.setEnabled(true);
				    	if(combo_1.getItem(index).equals("�¼���"))
				    		layer = MapEditorConstants.EVENT_LAYER;
				    	else
				    		layer = MapEditorConstants.ALL_LAYER;			    		
			    	}	    	    	
			    }
			    canvas.redraw();
			}
		});
	}
	
	// F:\\С��ͼƬ\\ͼ��\\!!.png      F:\\С��ͼƬ\\����\\2011-07-07-10.bmp  F:\\С��ͼƬ\\clannad.jpg
	/**
	 * ��������
	 */
	private void createCanvas() {
		//����ͼƬ������Ƭ�Ĵ�С��32��������
		setImagePath(imagePath);
		origin = new Point(0, 0);	
		canvas = new Canvas(composite_1, SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL
				| SWT.H_SCROLL | SWT.DOUBLE_BUFFERED);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		createScrollBar();
		addResizeListener();
		addRedrawListener();
		addCanvasListener();
		setDropTarget();
	}

	/**
	 * �����Ĺ�����
	 */
	private void createScrollBar() {
		hBar = canvas.getHorizontalBar();
		hBar.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int hSelection = hBar.getSelection();
				int destX = -hSelection - origin.x;
				canvas.scroll(destX, 0, 0, 0, (int) (width * rate),
						(int) (height * rate), false);
				origin.x = -hSelection;
			}
		});
		vBar = canvas.getVerticalBar();
		vBar.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int vSelection = vBar.getSelection();
				int destY = -vSelection - origin.y;
				canvas.scroll(0, destY, 0, 0, (int) (width * rate),
						(int) (height * rate), false);
				origin.y = -vSelection;
			}
		});

	}

	/**
	 * ��ӹ���ʱCanvas��С�仯�ļ���
	 */
	private void addResizeListener() {
		canvas.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event e) {
				Rectangle client = canvas.getClientArea();
				hBar.setMaximum(imageRec.width);
				vBar.setMaximum(imageRec.height);
				hBar.setThumb(Math.min(imageRec.width, client.width));
				vBar.setThumb(Math.min(imageRec.height, client.height));

				 int hPage = imageRec.width - client.width;
				 int vPage = imageRec.height - client.height;
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
				canvas.redraw();
			}
		});
	}

	/**
	 * ������»���Canvas�ļ�����
	 */
	protected void addRedrawListener() {
		final Button button_6 = bridge.bar_1.button_6;
		final Button button_18 = bridge.bar_2.button_18;
		canvas.addPaintListener(new PaintListener() {		
			@Override
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				int w = (int) (width * rate);
				int h = (int) (height * rate);
				hBar.setMaximum(w);
				vBar.setMaximum(h);

				bufferImage = new Image(Display.getCurrent(), w, h);
				GC bufferg = new GC(bufferImage);			
				bufferg.drawImage(image, 0, 0, width,height, 0, 0, w,h);//ָ����С��
				
				switch(layer){
				//����ͼ��һ
				case(MapEditorConstants.PAINT_LAYER_1):
					drawPaintLayer_1(bufferg);break;
			    //����ͼ���
				case(MapEditorConstants.PAINT_LAYER_2):
					drawPaintLayer_2(bufferg);break;
				//���¼���
				case(MapEditorConstants.EVENT_LAYER):
					drawEventLayer(button_18,bufferg);break;
				//����ͼ����¼���
				case(MapEditorConstants.ALL_LAYER):
					drawPaintLayer_1(bufferg);drawPaintLayer_2(bufferg);drawEventLayer(button_18,bufferg);break;
				}
				
			    //button_6(��ʾ����ť)��ѡ�У���������		
				if (button_6.getSelection()) {
					bufferg.setBackground(new Color(Display.getCurrent(),0,0,0));
					int newRate =(int)(rate*32);
					for (int i = 0; i < h/newRate+1; i++) {
						bufferg.drawLine(0, (int) (i * newRate),
								w, (int) (i * newRate));
					}
					for (int i = 0; i < w/newRate+1; i++) {					
						bufferg.drawLine((int) (i * newRate), 0,
								(int) (i * newRate), h);
					}
				}
				
				//������־������ʼλ��
				bufferg.setBackground(new Color(Display.getCurrent(),255,0,255));
				bufferg.fillOval((int)(x*rate),(int)(y*rate),(int)(32*rate), (int)(32*rate));
				
				if(!gc.isDisposed()){
					gc.drawImage(bufferImage, origin.x, origin.y);
				}
				
				bufferg.dispose();
				bufferImage.dispose();
				gc.dispose();

				// ��䱳��
				// Rectangle rect = image.getBounds ();
				// Rectangle client = canvas.getClientArea ();
				//
				// int marginWidth = client.width - rect.width;
				// if (marginWidth > 0) {
				// gc.fillRectangle (rect.width, 0, marginWidth, client.height);
				// }
				// int marginHeight = client.height - rect.height;
				// if (marginHeight > 0) {
				// gc.fillRectangle (0, rect.height, client.width,
				// marginHeight);
				// }
			}
		});
	}

	/**
	 * ���ƻ�ͼ��һ
	 * @param bufferg GC
	 */
	private void drawPaintLayer_1(GC bufferg){
		//����ȡ������ͼƬ
		for(int i = 0; i<newImageVector_1.size();i++){
	    	InterceptImage ni = newImageVector_1.get(i);
	        Image image = ni.getImage();
	        ImageData data = image.getImageData();
	        data = setImageData(data);
	        Image ii = new Image(Display.getCurrent(),data);
	        Rectangle rec = image.getBounds();
	    	bufferg.drawImage(ii,0,0, rec.width,rec.height ,(int)(ni.getX()*rate),(int)(ni.getY()*rate),(int)(rec.width*rate),(int)(rec.height*rate));//�������ŷŴ����С���ı�ͼƬ�Ĵ�С��λ������    	
	    }		    
	}
	
	/**
	 * ���ƻ�ͼ���
	 * @param bufferg GC
	 */
	private void drawPaintLayer_2(GC bufferg){
		//����ȡ������ͼƬ
		for(int i = 0; i<newImageVector_2.size();i++){
	    	InterceptImage ni = newImageVector_2.get(i);
	        Image image = ni.getImage();
	        ImageData data = image.getImageData(); 
	        data = setImageData(data);
	        Image ii = new Image(Display.getCurrent(),data);
	        Rectangle rec = image.getBounds();
	    	bufferg.drawImage(ii,0,0, rec.width,rec.height ,(int)(ni.getX()*rate),(int)(ni.getY()*rate),(int)(rec.width*rate),(int)(rec.height*rate));//�������ŷŴ����С���ı�ͼƬ�Ĵ�С��λ������    	
	    }		    
	}
	
	/**
	 * ���ý�ȡͼƬ�е�Imagedata��ʹ֮�ܹ���͸������
	 * @return
	 */
	private ImageData setImageData(ImageData data){
		 int whitePixel = data.palette.getPixel(new RGB(255, 255, 255));		       
		 for (int y = 0; y < data.height; y++) {
			 for (int x = 0; x < data.width; x++) {
	            int pixel = data.getPixel(x, y);
	            if (pixel == 0)                        
	                  data.setPixel(x, y, whitePixel);
	            else
	                  data.setPixel(x, y, pixel);
			 }
	     }   
	     data.transparentPixel = whitePixel; 
	     return data;
	}
		
	/**
	 * �����¼���
	 * @param bufferg GC
	 */
	private void drawEventLayer(Button button_18,GC bufferg){
		//button_18����ʾ��ײ�¼���ť����ѡ�У����Ͽ��
		//if(button_18.getSelection()){
			bufferg.setBackground(new Color(Display.getCurrent(),0,255,255));
			for(CollidePoint cp : collideVector){
				bufferg.fillRectangle((int) (cp.getX()*rate), (int)(cp.getY()*rate), (int)(32*rate),(int)( 32*rate));
			}
	//	}
		//�����л���ͼ
		bufferg.setBackground(new Color(Display.getCurrent(),255,0,255));
		for(ChangeScene cs : changeScene){
			bufferg.fillRectangle((int) (cs.getX()*rate), (int)(cs.getY()*rate), (int)(32*rate),(int)( 32*rate));
		}
		//����·������
		bufferg.setBackground(new Color(Display.getCurrent(),0,0,255));
		for(EncounterEnemy ee : encounterEnemy){
			bufferg.fillRectangle((int) (ee.getX()*rate), (int)(ee.getY()*rate), (int)(32*rate),(int)( 32*rate));
		}
		//���ϼ�ʰ��Ʒ
		bufferg.setBackground(new Color(Display.getCurrent(),255,0,0));
		for(FindThing ft : findThing){
			bufferg.fillRectangle((int) (ft.getX()*rate), (int)(ft.getY()*rate), (int)(32*rate),(int)( 32*rate));
		}
	}
	
	/**
	 * ��ӻ�������
	 */
	private void addCanvasListener(){
		canvas.addMouseListener(new MouseAdapter(){
			Button button_18 = bridge.bar_2.button_18;	
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			
				int x = (e.x - origin.x)/32*32;
				int y = (e.y - origin.y)/32*32;
				
				/**
				 *˫��ĳһ���ӣ���ȥ�¼�
				 *�������ж���¼�����ײ���л���ͼ��·�����ˣ���ʰ��Ʒ��˳��һ�δγ�ȥ 
				 */
				//���鱣����ײ�¼�Vector,��ȥ˫��λ�õ���ײ�¼�
				for(CollidePoint point : collideVector){ 
					if(point.getX() == x && point.getY() == y){
						collideVector.removeElement(point);
						rate = 1;
						canvas.redraw();
						return;
					}
				}		
				//���鱣���л���ͼ�¼�Vector,��ȥ˫��λ�õ��л���ͼ�¼�	
				for(ChangeScene cs : changeScene){
					if(cs.getX() == x && cs.getY() == y){
						changeScene.removeElement(cs);
						rate = 1;
						canvas.redraw();
						return;
					}
				}
				//���鱣��·�������¼�Vector,��ȥ˫��λ�õ�·�������¼�			
				for(EncounterEnemy ee : encounterEnemy){
					if(ee.getX() == x && ee.getY() == y){
						encounterEnemy.removeElement(ee);
						rate = 1;
						canvas.redraw();
						return;
					}
				}
				//���鱣���ʰ��Ʒ�¼�Vector,��ȥ˫��λ�õļ�ʰ��Ʒ�¼�		
				for(FindThing ft : findThing){
					if(ft.getX() == x && ft.getY() == y){
						findThing.removeElement(ft);
						rate = 1;
						canvas.redraw();
						return;
					}
				}		
				//���button_18(��ײ�¼���ť)ûѡ���������ײ�¼�
				if(!button_18.getSelection())
					return;			
				collideVector.add(new CollidePoint(x,y));
				rate = 1;
				canvas.redraw();
			}
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button == 3){
					int x = (e.x - origin.x)/32*32;
					int y = (e.y - origin.y)/32*32;
					showPopupMenu(x,y);
				}
			}
		});
	}
	
	/**
	 * �����˵�
	 */
	private void showPopupMenu(final int x1,final int y1){
		menu = new Menu(composite_1);
		canvas.setMenu(menu);
		item = new MenuItem(menu,SWT.PUSH);
		item.setText("������ʼλ��");
		item1 = new MenuItem(menu,SWT.PUSH);
		item1.setText("�л�����");
		item2 = new MenuItem(menu,SWT.PUSH);
		item2.setText("��������");
		item3 = new MenuItem(menu,SWT.PUSH);
		item3.setText("��ʰ��Ʒ");
		item4 = new MenuItem(menu,SWT.PUSH);
		item4.setText("NPC�Ի�");
		
		item.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				x = x1; 
				y = y1;
				rate = 1;
				canvas.redraw();
			}		
		});
		PopupMenuListener listener = new PopupMenuListener(this,x1,y1);
		item1.addSelectionListener(listener);
		item2.addSelectionListener(listener);
		item3.addSelectionListener(listener);
		item4.addSelectionListener(listener);
	}

	/**
	 * �Ŵ��ͼ
	 */
	private void enlargeMap() {
		if (image == null) {
			return;
		}
		rate = rate + 0.1;
		if (rate > 3) {
			MessageDialog.openInformation(null, "������ʾ", "��ͼ�����ٷŴ��ˣ�");
			rate = 3;
		}
		canvas.redraw();
	}
	
	
	/**
	 * ��С��ͼ
	 */
	private void miniMap() {
		if (image == null)
			return;
		rate = rate - 0.1;
		if (rate < 0.3) {
			MessageDialog.openInformation(null, "������ʾ", "��ͼ��������С�ˣ�");
			rate = 0.3;
		}
		canvas.redraw();
	}
	
	
	/**
	 * ����Ϊ��קĿ�꣬��Ӽ������¼�����
	 */
	private void setDropTarget(){
		DropTarget target = new DropTarget(composite_1, DND.DROP_MOVE
				| DND.DROP_DEFAULT | DND.DROP_COPY);	
		target.setTransfer(new Transfer[] { ImageTransfer.getInstance() });
		target.addDropListener(new DropTargetListener() {
			@Override
			public void dragEnter(DropTargetEvent event) {			
				if (event.detail == DND.DROP_DEFAULT)
					event.detail = DND.DROP_COPY;
				
			}
			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT)
					event.detail = DND.DROP_COPY;
			}
			@Override
			public void dragOver(DropTargetEvent event) {
				canvas.setFocus();
				event.feedback = DND.FEEDBACK_EXPAND | DND.FEEDBACK_SELECT;
			}
			@Override
			public void drop(DropTargetEvent event) {			
				if (ImageTransfer.getInstance().isSupportedType(event.currentDataType)) {
					ImageData data = (ImageData) event.data;
					Image i = new Image(null, data);//����ȡ��ͼƬ�е����ݴӼ��а�����������Image����
				   
					//��������ͷ�λ������ڻ���������
					Point p = canvas.toControl(event.x,event.y);
					int x = (p.x-origin.x)/32*32;
					int y = (p.y-origin.y)/32*32;		
					InterceptImage ni = new InterceptImage(i, x,y);
					if(layer == MapEditorConstants.PAINT_LAYER_1){
						newImageVector_1.add(ni);
					}else{
						if(layer == MapEditorConstants.PAINT_LAYER_2){
							newImageVector_2.add(ni);
						}				
					}			
					rate = 1;//��ԭ������С
					canvas.redraw();
					editor.dirty = true;
				}
			}
			@Override
			public void dragLeave(DropTargetEvent event) {
			}
			@Override
			public void dropAccept(DropTargetEvent event) {
				Cursor cursor = new Cursor(Display.getCurrent(),SWT.CURSOR_ARROW);			
				group_2.getParent().getShell().setCursor(cursor);	
			}
		});
	}
	
	
	/**
	 * ����ͼƬ·������������ͼƬ
	 * @param imagePath ͼƬ·��
	 */
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
		ImageData imageData = new ImageData(imagePath);
	 	imageData = imageData.scaledTo(imageData.width/32*32, imageData.height/32*32);
		image = new Image(Display.getCurrent(),imageData);
		imageRec = image.getBounds();
		width = imageRec.width;
		height = imageRec.height;
	}
}
