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
 * 创建地图编辑器的第三部分
 * 
 * @author xzz
 * 
 */
public class MapEditorPart_3 {
	
	private MapEditor editor;
	
	/**
	 * 图片路径
	 */
	private String imagePath;
	
	protected Group group_2;
	private Composite composite_1;
	private MapEditorBridge bridge;
	
	/**
	 * 工具栏
	 */
	private ToolBar bar_3;

	/**
	 * 地图预览按钮
	 */
	private Button button_13;

	/**
	 * 地图生成按钮
	 */
	private Button button_14;

	/**
	 * 地图放大按钮
	 */
	private Button button_16;

	/**
	 * 地图缩小按钮
	 */
	private Button button_17;
	
	/**
	 * 
	 */
    private Combo combo_1;
	
	/**
	 * 显示地图的画布
	 */
	protected  Canvas canvas;

	/**
	 * canvas上的右键弹出菜单
	 */
	private Menu menu;
	
	/**
	 * 弹出菜单的菜单项
	 */
	private MenuItem item;
	private MenuItem item1;
	private MenuItem item2;
	private MenuItem item3;
	private MenuItem item4;
	
	/**
	 * 地图
	 */
	private Image image;

	/**
	 * 缓冲的图片
	 */
	private Image bufferImage;

	/**
	 * 原点位置
	 */
	private Point origin;

	/**
	 * 水平滚动条
	 */
	private ScrollBar hBar;

	/**
	 * 竖直滚动条
	 */
	private ScrollBar vBar;

	/**
	 * 放大或缩小的倍数
	 */
	protected double rate = 1.0;

	/**
	 * 原图（image）的大小矩形
	 */
	private Rectangle imageRec;

	/**
	 * 原图的宽
	 */
	private int width;

	/**
	 * 原图的高
	 */
	private int height;

	/**
	 * 第一个保存传递过来图片信息的Vector
	 */
    private Vector<InterceptImage> newImageVector_1 = new Vector<InterceptImage>();
    
    /**
	 * 第二个保存传递过来图片信息的Vector
	 */
    private Vector<InterceptImage> newImageVector_2 = new Vector<InterceptImage>();
    
    /**
     * 保存所有保存事件Vector的List
     */
    public List allEvent = new LinkedList();
    
    /**
     * 保存碰撞事件在画布上位置的Vector
     */
    private Vector<CollidePoint> collideVector = new Vector<CollidePoint>();
    
    /**
     * 保存切换创建在画布上位置的Vector
     */
    protected Vector<ChangeScene> changeScene = new Vector<ChangeScene>();
    
    /**
     * 保存路遇敌人在画布上位置的Vector
     */
    protected Vector<EncounterEnemy> encounterEnemy = new Vector<EncounterEnemy>();
    
    /**
     * 保存捡拾物品在画布上位置的Vector
     */
    protected Vector<FindThing> findThing = new Vector<FindThing>();
    
    /**
     * 主角起始位置
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
		//按顺序把所有保存事件的Vector加到一个List中
		allEvent.add(collideVector);
		allEvent.add(changeScene);
		allEvent.add(encounterEnemy);
		allEvent.add(findThing);
	}
	
	public void setBridge(MapEditorBridge bridge){
		this.bridge = bridge;	
	}
	
	/**
	 * 初始化
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
		label.setText("选择绘制层:");
		
		createCombo_1();
		createCanvas();
		
	}

	/**
	 * 地图预览
	 */
	private void createButton_13() {
		button_13 = new Button(bar_3, SWT.PUSH);
		button_13.setText("地图预览");
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
		button_14.setText("地图生成");

	}

	/**
	 * 放大图片按钮
	 */
	private void createButton_16() {
		button_16 = new Button(bar_3, SWT.NONE);
		button_16.setText("放大");
		button_16.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				enlargeMap();
			}
		});
	}

	/**
	 * 缩小图片按钮
	 */
	private void createButton_17() {
		button_17 = new Button(bar_3, SWT.NONE);
		button_17.setText("缩小");
		button_17.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				miniMap();
			}
		});
	}

	/**
	 * 选择编辑层
	 */
	public void createCombo_1(){
    	combo_1 = new Combo(bar_3,SWT.CHECK);
		combo_1.setItems(new String[]{"绘图层一","绘图层二","事件层","所有层"});
		combo_1.select(0);
    }
	
	/**
	 * 选择编辑层的监听
	 */
	public void addCombo_1Lietener(){
		combo_1.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo_1.getSelectionIndex();
			    if(index<0)
			    	return;
			    Button button_18 = bridge.bar_2.button_18;
			    if(combo_1.getItem(index).equals("绘图层一")){		
			    	button_18.setEnabled(false);
			    	layer = MapEditorConstants.PAINT_LAYER_1;		    	
			    }else{
			    	if(combo_1.getItem(index).equals("绘图层二")){
			    		button_18.setEnabled(false);
				    	layer = MapEditorConstants.PAINT_LAYER_2;			    		
			    	}else{
			    		button_18.setEnabled(true);
				    	if(combo_1.getItem(index).equals("事件层"))
				    		layer = MapEditorConstants.EVENT_LAYER;
				    	else
				    		layer = MapEditorConstants.ALL_LAYER;			    		
			    	}	    	    	
			    }
			    canvas.redraw();
			}
		});
	}
	
	// F:\\小虫图片\\图库\\!!.png      F:\\小虫图片\\艳婷\\2011-07-07-10.bmp  F:\\小虫图片\\clannad.jpg
	/**
	 * 创建画布
	 */
	private void createCanvas() {
		//载入图片，让照片的大小是32的整倍数
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
	 * 画布的滚动条
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
	 * 添加滚动时Canvas大小变化的监听
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
	 * 添加重新绘制Canvas的监听。
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
				bufferg.drawImage(image, 0, 0, width,height, 0, 0, w,h);//指定大小。
				
				switch(layer){
				//画绘图层一
				case(MapEditorConstants.PAINT_LAYER_1):
					drawPaintLayer_1(bufferg);break;
			    //画绘图层二
				case(MapEditorConstants.PAINT_LAYER_2):
					drawPaintLayer_2(bufferg);break;
				//画事件层
				case(MapEditorConstants.EVENT_LAYER):
					drawEventLayer(button_18,bufferg);break;
				//画绘图层和事件层
				case(MapEditorConstants.ALL_LAYER):
					drawPaintLayer_1(bufferg);drawPaintLayer_2(bufferg);drawEventLayer(button_18,bufferg);break;
				}
				
			    //button_6(显示网格按钮)被选中，则画网格线		
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
				
				//画出标志主角起始位置
				bufferg.setBackground(new Color(Display.getCurrent(),255,0,255));
				bufferg.fillOval((int)(x*rate),(int)(y*rate),(int)(32*rate), (int)(32*rate));
				
				if(!gc.isDisposed()){
					gc.drawImage(bufferImage, origin.x, origin.y);
				}
				
				bufferg.dispose();
				bufferImage.dispose();
				gc.dispose();

				// 填充背景
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
	 * 绘制绘图层一
	 * @param bufferg GC
	 */
	private void drawPaintLayer_1(GC bufferg){
		//画截取过来的图片
		for(int i = 0; i<newImageVector_1.size();i++){
	    	InterceptImage ni = newImageVector_1.get(i);
	        Image image = ni.getImage();
	        ImageData data = image.getImageData();
	        data = setImageData(data);
	        Image ii = new Image(Display.getCurrent(),data);
	        Rectangle rec = image.getBounds();
	    	bufferg.drawImage(ii,0,0, rec.width,rec.height ,(int)(ni.getX()*rate),(int)(ni.getY()*rate),(int)(rec.width*rate),(int)(rec.height*rate));//设置随着放大和缩小，改变图片的大小和位置坐标    	
	    }		    
	}
	
	/**
	 * 绘制绘图层二
	 * @param bufferg GC
	 */
	private void drawPaintLayer_2(GC bufferg){
		//画截取过来的图片
		for(int i = 0; i<newImageVector_2.size();i++){
	    	InterceptImage ni = newImageVector_2.get(i);
	        Image image = ni.getImage();
	        ImageData data = image.getImageData(); 
	        data = setImageData(data);
	        Image ii = new Image(Display.getCurrent(),data);
	        Rectangle rec = image.getBounds();
	    	bufferg.drawImage(ii,0,0, rec.width,rec.height ,(int)(ni.getX()*rate),(int)(ni.getY()*rate),(int)(rec.width*rate),(int)(rec.height*rate));//设置随着放大和缩小，改变图片的大小和位置坐标    	
	    }		    
	}
	
	/**
	 * 设置截取图片中的Imagedata，使之能够有透明背景
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
	 * 绘制事件层
	 * @param bufferg GC
	 */
	private void drawEventLayer(Button button_18,GC bufferg){
		//button_18（显示碰撞事件按钮）被选中，则画上框框
		//if(button_18.getSelection()){
			bufferg.setBackground(new Color(Display.getCurrent(),0,255,255));
			for(CollidePoint cp : collideVector){
				bufferg.fillRectangle((int) (cp.getX()*rate), (int)(cp.getY()*rate), (int)(32*rate),(int)( 32*rate));
			}
	//	}
		//画上切换地图
		bufferg.setBackground(new Color(Display.getCurrent(),255,0,255));
		for(ChangeScene cs : changeScene){
			bufferg.fillRectangle((int) (cs.getX()*rate), (int)(cs.getY()*rate), (int)(32*rate),(int)( 32*rate));
		}
		//画上路遇敌人
		bufferg.setBackground(new Color(Display.getCurrent(),0,0,255));
		for(EncounterEnemy ee : encounterEnemy){
			bufferg.fillRectangle((int) (ee.getX()*rate), (int)(ee.getY()*rate), (int)(32*rate),(int)( 32*rate));
		}
		//画上捡拾物品
		bufferg.setBackground(new Color(Display.getCurrent(),255,0,0));
		for(FindThing ft : findThing){
			bufferg.fillRectangle((int) (ft.getX()*rate), (int)(ft.getY()*rate), (int)(32*rate),(int)( 32*rate));
		}
	}
	
	/**
	 * 添加画布监听
	 */
	private void addCanvasListener(){
		canvas.addMouseListener(new MouseAdapter(){
			Button button_18 = bridge.bar_2.button_18;	
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			
				int x = (e.x - origin.x)/32*32;
				int y = (e.y - origin.y)/32*32;
				
				/**
				 *双击某一格子，除去事件
				 *若格子有多个事件则按碰撞，切换地图，路遇敌人，捡拾物品的顺序一次次除去 
				 */
				//历遍保存碰撞事件Vector,除去双击位置的碰撞事件
				for(CollidePoint point : collideVector){ 
					if(point.getX() == x && point.getY() == y){
						collideVector.removeElement(point);
						rate = 1;
						canvas.redraw();
						return;
					}
				}		
				//历遍保存切换地图事件Vector,除去双击位置的切换地图事件	
				for(ChangeScene cs : changeScene){
					if(cs.getX() == x && cs.getY() == y){
						changeScene.removeElement(cs);
						rate = 1;
						canvas.redraw();
						return;
					}
				}
				//历遍保存路遇敌人事件Vector,除去双击位置的路遇敌人事件			
				for(EncounterEnemy ee : encounterEnemy){
					if(ee.getX() == x && ee.getY() == y){
						encounterEnemy.removeElement(ee);
						rate = 1;
						canvas.redraw();
						return;
					}
				}
				//历遍保存捡拾物品事件Vector,除去双击位置的捡拾物品事件		
				for(FindThing ft : findThing){
					if(ft.getX() == x && ft.getY() == y){
						findThing.removeElement(ft);
						rate = 1;
						canvas.redraw();
						return;
					}
				}		
				//如果button_18(碰撞事件按钮)没选不能添加碰撞事件
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
	 * 弹出菜单
	 */
	private void showPopupMenu(final int x1,final int y1){
		menu = new Menu(composite_1);
		canvas.setMenu(menu);
		item = new MenuItem(menu,SWT.PUSH);
		item.setText("主角起始位置");
		item1 = new MenuItem(menu,SWT.PUSH);
		item1.setText("切换场景");
		item2 = new MenuItem(menu,SWT.PUSH);
		item2.setText("遇到敌人");
		item3 = new MenuItem(menu,SWT.PUSH);
		item3.setText("捡拾物品");
		item4 = new MenuItem(menu,SWT.PUSH);
		item4.setText("NPC对话");
		
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
	 * 放大地图
	 */
	private void enlargeMap() {
		if (image == null) {
			return;
		}
		rate = rate + 0.1;
		if (rate > 3) {
			MessageDialog.openInformation(null, "友情提示", "地图不能再放大了！");
			rate = 3;
		}
		canvas.redraw();
	}
	
	
	/**
	 * 缩小地图
	 */
	private void miniMap() {
		if (image == null)
			return;
		rate = rate - 0.1;
		if (rate < 0.3) {
			MessageDialog.openInformation(null, "友情提示", "地图不能再缩小了！");
			rate = 0.3;
		}
		canvas.redraw();
	}
	
	
	/**
	 * 设置为拖拽目标，添加监听和事件处理
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
					Image i = new Image(null, data);//将截取的图片中的数据从剪切板读出，构造出Image对象
				   
					//计算鼠标释放位置相对于画布的坐标
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
					rate = 1;//还原画布大小
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
	 * 设置图片路径，重新载入图片
	 * @param imagePath 图片路径
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
