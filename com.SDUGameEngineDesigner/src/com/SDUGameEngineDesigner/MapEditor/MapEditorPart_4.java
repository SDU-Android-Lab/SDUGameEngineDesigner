package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

/**
 * 创建地图编辑器的第四部分
 * @author xzz
 *
 */
public class MapEditorPart_4 {
	
	private MapEditor editor;
	private Group group_2;
	private Composite composite_2;
	private MapEditorBridge bridge;
	/**
	 * 选项卡容器
	 */
	private CTabFolder ctf;
	
	/**
	 * 选项Item_1
	 */
	private CTabItem cti_1;
	
	/**
	 * 选项Item_2
	 */
	private CTabItem cti_2;
	
	/**
	 * 原图路径图片
	 */
    private String  imagePath;
    
	/**
	 * 原图
	 */
	private Image image;
	
	/**
	 * 用于双缓冲画图
	 */
	private Image bufferImage;
	
	/**
	 * 所截的图
	 */
	private Image viewImage;
	
	/**
	 * 用于显示图片的画布
	 */
	public Canvas canvas;

    /**
	 * 图片的大小矩形
	 */
	private Rectangle imageRec;
	
	/**
	 * 图片的长宽
	 */
	private int imageRecWidth,imageRecHeight;
	
	/**
	 *鼠标开始的坐标x,y 
	 */
	public  int startx ,starty;
	
	/**
	 *鼠标结束的坐标x,y 
	 */
	public  int endx ,endy;
	
	/**
	 * 开始画截图框框的坐标x,y
	 */
	private int x, y;
	
	/**
	 * 是否拖拽，可以拖拽
	 */
	private boolean isDrag = false;
	
	/**
	 * 是否选中图片
	 */
	private boolean isSelect = false;
	
	/**
	 * 截图大小的矩形
	 */
	private Rectangle viewRec = null;
	
	/**
	 * 截图的长宽
	 */
	private int viewRecWidth ,viewRecHeight;
	
	/**
	 * 布局管理器
	 */
	private FillLayout fillLayout;
		
	public MapEditorPart_4(Group group_2,String imagePath,MapEditor editor){
		this.group_2 = group_2;
		this.imagePath = imagePath;
		this.editor = editor;
		fillLayout = new FillLayout();		
		composite_2 = new Composite(group_2,SWT.NONE);
        composite_2.setLayout(fillLayout);
        composite_2.setLayoutData( new GridData(SWT.FILL, SWT.FILL, true,true, 1, 1));
    }
	
	public void setBridge(MapEditorBridge bridge){
		this.bridge = bridge;
	}
	/**
	 * 初始化
	 */
	public void initiaPart_4(){
		ctf = new CTabFolder(composite_2, SWT.BORDER);
        ctf.setLayout(fillLayout);
      //  String name [] = new String []{"地图连接管理","战斗属性面板","背景墙","图块","贴图","角色"};
      
        addCTabItem_1();
       
        
        addCTabItem_2();
	}
	
	/**
	 * 添加选项一
	 */
	private void addCTabItem_1(){
		 cti_1 = new CTabItem(ctf, SWT.NONE);
		 cti_1.setText("编辑提示");
		 Composite c = new Composite(ctf,SWT.NONE);
		 c.setLayout(new FillLayout());
         cti_1.setControl(c);	     
         final Canvas cc = new Canvas(c,SWT.NONE);
         cc.addPaintListener(new PaintListener(){

			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				GC gc = e.gc;
				gc.setBackground(new Color(Display.getCurrent(),255,0,255));
				gc.fillOval(20, 20, 32, 32);
				gc.setBackground(new Color(Display.getCurrent(),0,255,255));
				gc.fillRectangle(20, 60, 32, 32);
				gc.setBackground(new Color(Display.getCurrent(),255,0,255));
				gc.fillRectangle(20,100,32,32);
				gc.setBackground(new Color(Display.getCurrent(),0,0,255));
				gc.fillRectangle(20,140,32,32);
				gc.setBackground(new Color(Display.getCurrent(),255,0,0));
				gc.fillRectangle(20,180,32,32);
				
				gc.setBackground(cc.getBackground());
				gc.drawText("标志主角起始位置", 70, 25);
				gc.drawString("标志碰撞事件", 70, 65);
				gc.drawString("标志切换地图", 70, 105);
				gc.drawString("标志路遇敌人", 70, 145);
				gc.drawString("标志捡拾物品", 70, 185);
				
				gc.dispose();
			}
        	 
         });
	}
	
	/**
	 * 添加选项二
	 */
	private void addCTabItem_2(){
		 cti_2 = new CTabItem(ctf, SWT.NONE);
		 cti_2.setText("图片编辑");
	     Composite cc = new Composite(ctf,SWT.NONE);
	     cc.setLayout(fillLayout);
	     cti_2.setControl(cc);
	     setCanvas(cc);//添加画布和监听
	    
	}
	
	/**
	 * 添加Canvas上的各种监听
	 * @param cc 容器
	 */
	private void setCanvas(Composite cc){
		 //载入图片
        setImagePath(imagePath);
		canvas = new Canvas(cc,SWT.BORDER | SWT.DOUBLE_BUFFERED);		
		setCanvasDragListener();
		setDragSource();		      
	}
	
	/**
	 * 重绘监听
	 */
	public void addCanvasPaintListener(){
		canvas.addPaintListener(new PaintListener(){
			@Override
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				
				if(bufferImage!=null){
					bufferImage.dispose();
				}
				bufferImage = new Image(Display.getCurrent(),imageRecWidth,imageRecHeight);
				GC bufferGC = new GC(bufferImage);
				bufferGC.setForeground(new Color(Display.getCurrent(),0,0,0));
				bufferGC.drawImage(image, 0, 0);//不考虑缓冲图片和canvas的大小，把整张图片拷贝到bufferIamge里
			
				//画网格线
				for(int i = 0;i<imageRecHeight/32;i++){
					int yy = i*32;
					bufferGC.drawLine(0, yy, imageRecWidth, yy);
				}
				for(int i = 0;i<imageRecWidth/32;i++){
					int xx = i*32;
					bufferGC.drawLine(xx, 0, xx, imageRecHeight);
				}
				
				if(viewRec!=null){
					bufferGC.setForeground(new Color(Display.getCurrent(),255,0,0));
					bufferGC.drawRectangle(viewRec);
					// 拖拽结束后计算预览图片
			        if (viewImage != null) {
			            viewImage.dispose();
			        }		    
			        viewImage = new Image(Display.getCurrent(), viewRecWidth,viewRecHeight);
			        GC newGC = new GC(viewImage);
			        try{
			           newGC.drawImage(image, x, y, viewRecWidth,viewRecHeight, 0, 0, viewRecWidth, viewRecHeight);//把image中在viewRec矩形中图片拷贝到viewImage。。。(注)某些情况下会报错
			        }catch (Exception e1){
			        	MessageDialog.openConfirm(null, "错误提示！", "请重新截取图片");
			        	viewRec = null;
			        	viewImage.dispose();
			        	return;
			        }
			        newGC.dispose();
			        if(!isSelect){//没有选好截取图片时，设置鼠标样式提供预览
			        	 //拖拽选择截取图片时，设置鼠标样式
				        Cursor cursor = new Cursor(Display.getCurrent(),viewImage.getImageData(),0,0);
					    group_2.getParent().getShell().setCursor(cursor);		       
			        }
				}
				
				gc.drawImage(bufferImage, 0, 0);			
				gc.dispose();
				bufferGC.dispose();			
			}    	   
	       });
	}
	
	/**
	 * 添加Canvas上鼠标拖动事件
	 */
	private void setCanvasDragListener(){
		canvas.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {				
				if(imageRec.contains(e.x, e.y)){
					//默认从整格开始
					startx = e.x/32*32;
				    starty = e.y/32*32;
				    x = startx;
				    y = starty;
				    isDrag = true;  
				}
				
			}
			@Override
			public void mouseUp(MouseEvent e) {
				isDrag = false;		
				Cursor cursor = new Cursor(Display.getCurrent(),SWT.CURSOR_ARROW);			
				group_2.getParent().getShell().setCursor(cursor);	
				if(viewRec!=null){
					isSelect = true;//设置已经选定截取的图片
				}
			}   	  
	      });
		
	    canvas.addMouseMoveListener(new MouseMoveListener(){
			@Override
			public void mouseMove(MouseEvent e) {
				if(isDrag){	
					//默认从整格结束
					 endx = e.x/32*32;
					 endy = e.y/32*32;
					
					if(!imageRec.contains(endx,endy)||endx == startx || endy == starty)//不在图片内，或者选择的是直线返回
						return;
					
					//计算截图矩形的长宽
					viewRecWidth = Math.abs(startx-endx);
					viewRecHeight = Math.abs(starty-endy);
					
					//计算截图矩形的起点坐标
				    if(endx > startx){
				    	if(endy < starty){
				    		y = endy;
				    	}
				    }else{
				    	if(endy < starty){
				    		x = endx;
				    		y = endy;
				    	}else{
				    		x = endx;
				    	}
				    }			
				    isSelect = false;		    
					viewRec = new Rectangle(x,y,viewRecWidth,viewRecHeight);	
					canvas.redraw();	
				}
			}    	  
	      });
	}
	
	/**
	 * 设置图片拖拽源，添加监听，处理事件
	 */
	private void setDragSource(){
		  DragSource source = new DragSource(canvas,DND.DROP_COPY|DND.DROP_MOVE);
	      source.setTransfer(new Transfer[]{ImageTransfer.getInstance()});
	      source.addDragListener(new DragSourceListener(){
			@Override
			public void dragStart(DragSourceEvent event) {		
				if(viewImage == null||viewRec==null){
					event.doit = false; 
					return;
				}
				if(!viewRec.contains(event.x,event.y)){
					event.doit = false; 
					return;
				}
				isDrag = false;//设置不是在拖拽选择截取图片
			}

			@Override
			public void dragSetData(DragSourceEvent event) {	
				if(ImageTransfer.getInstance().isSupportedType(event.dataType)){
					event.data = viewImage.getImageData();//将截取的图片中的数据给剪切板
				}
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				viewRec = null;//拖拽结束后让矩形消失
				canvas.redraw();
			}
	    	  
	      });
	}
	
	/**
	 * 设置图片路径
	 * @param imagePath 图片路径
	 */
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
		ImageData imageData = new ImageData(imagePath);
	    imageData = imageData.scaledTo(imageData.width/32*32, imageData.height/32*32);
	    image = new Image(Display.getCurrent(),imageData);
	    imageRec = image.getBounds();
	    imageRecWidth = imageRec.width;
	    imageRecHeight = imageRec.height;
		
	}

	
}
