package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;

/**
 * 创建地图编辑器的工具栏——1
 * @author xzz
 *
 */
public class MapEditorToolBar_1 {

	private MapEditor editor;
	private MapEditorSave save;
	private ToolBar bar1;
	private MapEditorBridge bridge;
	public  static Button button;
	protected  Button button_1;
	protected  Button button_2;
	protected  Button button_3;
	protected  Button button_4;
	protected  Button button_5;
	protected  Button button_6;
	protected  Button button_7;
	
	public MapEditorToolBar_1(Group group_1,MapEditor editor,MapEditorSave save){
		this.editor = editor;
		this.save = save;
		bar1 = new ToolBar(group_1,SWT.NONE);
		bar1.setLayoutData(new GridData(GridData.FILL_BOTH));
		bar1.setLayout(new FillLayout());
	}
	
	/**
	 * 初始化
	 */
	public void initiaToolBar_1(){
		createButton();
		createButton_1();
		createButton_2();
		createButton_3();
		createButton_4();
		createButton_5();
		createButton_6();
		createButton_7();		
	}
	
	public void setBridge(MapEditorBridge bridge){
		this.bridge = bridge;
	}
	
    private void createButton(){
    	button = new Button(bar1,SWT.PUSH);
		button.setText("地图总览");
	}
	
	private void createButton_1(){
	    button_1 = new Button(bar1,SWT.PUSH);
		button_1.setText("载入背景");
	}
	
    private void createButton_2(){
    	button_2 = new Button(bar1,SWT.PUSH);
		button_2.setText("更改音乐");
	}
    
    private void createButton_3(){
        button_3 = new Button(bar1,SWT.PUSH);
		button_3.setText("载入动画");
	}

    private void createButton_4(){
    	button_4 = new Button(bar1,SWT.PUSH);
		button_4.setText("更改图片");
	
    }
    
    private void createButton_5(){	
    	button_5 = new Button(bar1,SWT.CHECK);
		button_5.setText("添加显示");
    }

    private void createButton_6(){
    	button_6 = new Button(bar1,SWT.CHECK);
		button_6.setText("网格显示");	
     }
    
    private void createButton_7(){
    	button_7 = new Button(bar1,SWT.CHECK);
		button_7.setText("精灵编号");
	}
    
    /**
     * 为button（地图总览）添加监听
     * @param mapPath地图的路径
     */
    public void addButtonListener(final String mapPath){  	
		button.addSelectionListener(new SelectionAdapter(){
    		public void widgetSelected(SelectionEvent e){
    			MapPreview mapPreview = new MapPreview(bar1.getShell(),mapPath);
    			mapPreview.show();
    			editor.dirty = true;
    		}
    	});
    }
  
    /**
     * 为button_1（载入背景）添加监听
     * @param canvas
     * @param part_3
     */
    public void addButton_1Listener(){
    	
    	button_1.addSelectionListener(new SelectionAdapter() {
    		public void widgetSelected(SelectionEvent e) {
    			MapEditorPart_3 part_3 = bridge.part_3;	
    	    	Canvas canvas = part_3.canvas; 
    			FileDialog dialog = new FileDialog(bar1.getShell());
				String imagePath = dialog.open();;
				if(imagePath!=null){
					 part_3.setImagePath(imagePath);
					 canvas.redraw();
					 save.bgImage = imagePath;
					 editor.dirty = true;
				}		
    		}
    	});
    }
    
    /**
     * 为button_2（更改音乐）添加监听
     */
    public void addButton_2Listener(){    
    	button_2.addSelectionListener(new SelectionAdapter() {
    		public void widgetSelected(SelectionEvent e) {
    			FileDialog dialog = new FileDialog(bar1.getShell());
				String musicPath = dialog.open();;
				if(musicPath!=null){				
					 save.bgMusic = musicPath;
					 editor.dirty = true;
				}		
    		}
    	});
    }   
    
    /**
     * 为button_4（更改图片）添加监听
     * @param canvas
     * @param part_4
     */
    public void addButton_4Listener(){
    	
    	button_4.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				MapEditorPart_4 part_4 = bridge.part_4;
		    	Canvas canvas = part_4.canvas;
				FileDialog dialog = new FileDialog(bar1.getShell());
				String imagePath = dialog.open();
				if(imagePath!=null){
					part_4.setImagePath(imagePath);
					canvas.redraw();
					save.resImage = imagePath;
					editor.dirty = true;
				}			
			}
    	}); 	
    }
    
    /**
     * 为button_6（网格显示）添加监听
     * @param canvas
     */
    public void addButton_6Listener(){
    	
    	button_6.addListener(SWT.CHECK, new Listener(){
			@Override
			public void handleEvent(Event event) {
				bridge.part_3.canvas.redraw();
			}
			
		});
    }
    
}
