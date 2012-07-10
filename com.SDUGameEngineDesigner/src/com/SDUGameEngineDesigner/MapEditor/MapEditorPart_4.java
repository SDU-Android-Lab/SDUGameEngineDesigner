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
 * ������ͼ�༭���ĵ��Ĳ���
 * @author xzz
 *
 */
public class MapEditorPart_4 {
	
	private MapEditor editor;
	private Group group_2;
	private Composite composite_2;
	private MapEditorBridge bridge;
	/**
	 * ѡ�����
	 */
	private CTabFolder ctf;
	
	/**
	 * ѡ��Item_1
	 */
	private CTabItem cti_1;
	
	/**
	 * ѡ��Item_2
	 */
	private CTabItem cti_2;
	
	/**
	 * ԭͼ·��ͼƬ
	 */
    private String  imagePath;
    
	/**
	 * ԭͼ
	 */
	private Image image;
	
	/**
	 * ����˫���廭ͼ
	 */
	private Image bufferImage;
	
	/**
	 * ���ص�ͼ
	 */
	private Image viewImage;
	
	/**
	 * ������ʾͼƬ�Ļ���
	 */
	public Canvas canvas;

    /**
	 * ͼƬ�Ĵ�С����
	 */
	private Rectangle imageRec;
	
	/**
	 * ͼƬ�ĳ���
	 */
	private int imageRecWidth,imageRecHeight;
	
	/**
	 *��꿪ʼ������x,y 
	 */
	public  int startx ,starty;
	
	/**
	 *������������x,y 
	 */
	public  int endx ,endy;
	
	/**
	 * ��ʼ����ͼ��������x,y
	 */
	private int x, y;
	
	/**
	 * �Ƿ���ק��������ק
	 */
	private boolean isDrag = false;
	
	/**
	 * �Ƿ�ѡ��ͼƬ
	 */
	private boolean isSelect = false;
	
	/**
	 * ��ͼ��С�ľ���
	 */
	private Rectangle viewRec = null;
	
	/**
	 * ��ͼ�ĳ���
	 */
	private int viewRecWidth ,viewRecHeight;
	
	/**
	 * ���ֹ�����
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
	 * ��ʼ��
	 */
	public void initiaPart_4(){
		ctf = new CTabFolder(composite_2, SWT.BORDER);
        ctf.setLayout(fillLayout);
      //  String name [] = new String []{"��ͼ���ӹ���","ս���������","����ǽ","ͼ��","��ͼ","��ɫ"};
      
        addCTabItem_1();
       
        
        addCTabItem_2();
	}
	
	/**
	 * ���ѡ��һ
	 */
	private void addCTabItem_1(){
		 cti_1 = new CTabItem(ctf, SWT.NONE);
		 cti_1.setText("�༭��ʾ");
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
				gc.drawText("��־������ʼλ��", 70, 25);
				gc.drawString("��־��ײ�¼�", 70, 65);
				gc.drawString("��־�л���ͼ", 70, 105);
				gc.drawString("��־·������", 70, 145);
				gc.drawString("��־��ʰ��Ʒ", 70, 185);
				
				gc.dispose();
			}
        	 
         });
	}
	
	/**
	 * ���ѡ���
	 */
	private void addCTabItem_2(){
		 cti_2 = new CTabItem(ctf, SWT.NONE);
		 cti_2.setText("ͼƬ�༭");
	     Composite cc = new Composite(ctf,SWT.NONE);
	     cc.setLayout(fillLayout);
	     cti_2.setControl(cc);
	     setCanvas(cc);//��ӻ����ͼ���
	    
	}
	
	/**
	 * ���Canvas�ϵĸ��ּ���
	 * @param cc ����
	 */
	private void setCanvas(Composite cc){
		 //����ͼƬ
        setImagePath(imagePath);
		canvas = new Canvas(cc,SWT.BORDER | SWT.DOUBLE_BUFFERED);		
		setCanvasDragListener();
		setDragSource();		      
	}
	
	/**
	 * �ػ����
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
				bufferGC.drawImage(image, 0, 0);//�����ǻ���ͼƬ��canvas�Ĵ�С��������ͼƬ������bufferIamge��
			
				//��������
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
					// ��ק���������Ԥ��ͼƬ
			        if (viewImage != null) {
			            viewImage.dispose();
			        }		    
			        viewImage = new Image(Display.getCurrent(), viewRecWidth,viewRecHeight);
			        GC newGC = new GC(viewImage);
			        try{
			           newGC.drawImage(image, x, y, viewRecWidth,viewRecHeight, 0, 0, viewRecWidth, viewRecHeight);//��image����viewRec������ͼƬ������viewImage������(ע)ĳЩ����»ᱨ��
			        }catch (Exception e1){
			        	MessageDialog.openConfirm(null, "������ʾ��", "�����½�ȡͼƬ");
			        	viewRec = null;
			        	viewImage.dispose();
			        	return;
			        }
			        newGC.dispose();
			        if(!isSelect){//û��ѡ�ý�ȡͼƬʱ�����������ʽ�ṩԤ��
			        	 //��קѡ���ȡͼƬʱ�����������ʽ
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
	 * ���Canvas������϶��¼�
	 */
	private void setCanvasDragListener(){
		canvas.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {				
				if(imageRec.contains(e.x, e.y)){
					//Ĭ�ϴ�����ʼ
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
					isSelect = true;//�����Ѿ�ѡ����ȡ��ͼƬ
				}
			}   	  
	      });
		
	    canvas.addMouseMoveListener(new MouseMoveListener(){
			@Override
			public void mouseMove(MouseEvent e) {
				if(isDrag){	
					//Ĭ�ϴ��������
					 endx = e.x/32*32;
					 endy = e.y/32*32;
					
					if(!imageRec.contains(endx,endy)||endx == startx || endy == starty)//����ͼƬ�ڣ�����ѡ�����ֱ�߷���
						return;
					
					//�����ͼ���εĳ���
					viewRecWidth = Math.abs(startx-endx);
					viewRecHeight = Math.abs(starty-endy);
					
					//�����ͼ���ε��������
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
	 * ����ͼƬ��קԴ����Ӽ����������¼�
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
				isDrag = false;//���ò�������קѡ���ȡͼƬ
			}

			@Override
			public void dragSetData(DragSourceEvent event) {	
				if(ImageTransfer.getInstance().isSupportedType(event.dataType)){
					event.data = viewImage.getImageData();//����ȡ��ͼƬ�е����ݸ����а�
				}
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				viewRec = null;//��ק�������þ�����ʧ
				canvas.redraw();
			}
	    	  
	      });
	}
	
	/**
	 * ����ͼƬ·��
	 * @param imagePath ͼƬ·��
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
