package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;

/**
 * 创建地图编辑器的工具栏——2
 * @author xzz
 *
 */
public class MapEditorToolBar_2 {

	private MapEditorBridge bridge;
	private ToolBar bar_2;
	protected Button button_18;
	
	public MapEditorToolBar_2(Group group_1){
		bar_2 = new ToolBar(group_1,SWT.NONE);
		bar_2.setLayoutData(new GridData(GridData.FILL_BOTH));
		bar_2.setLayout(new FillLayout());
		
		createButton_18();
		createCombo_2();
		createButton_8();
		createButton_9();
		createButton_10();

		Label label_2 = new Label(bar_2,SWT.NONE);
		label_2.setText("         刷子:");
		
		createCombo_3();
		createButton_11();
		createButton_12();
		
	}
	
	private void createButton_18(){
		button_18 = new Button(bar_2,SWT.CHECK);
		button_18.setText("添加碰撞");
		button_18.setEnabled(false);
	}
	
	private void createCombo_2(){
		Combo combo_2 = new Combo(bar_2,SWT.CHECK);
		combo_2.setItems(new String[]{"默认","各种方案"});
		combo_2.select(0);
	}
	
	private void createButton_8(){
		Button button_8 = new Button(bar_2,SWT.PUSH);
		button_8.setText("添加方案");
		
		
	}
	
    private void createButton_9(){
    	Button button_9 = new Button(bar_2,SWT.PUSH);
		button_9.setText("删除方案");
		
	}
    
    private void createButton_10(){
    	Button button_10 = new Button(bar_2,SWT.PUSH);
		button_10.setText("更改方案");
	}
	
    private void createCombo_3(){
    	Combo combo_3 = new Combo(bar_2,SWT.CHECK);
		combo_3.setItems(new String[]{"默认","各种刷子"});
		combo_3.select(0);
	}
	
    private void createButton_11(){
    	Button button_11 = new Button(bar_2,SWT.PUSH);
		button_11.setText("添加刷子");
		
    }
    
    private void createButton_12(){
    	Button button_12 = new Button(bar_2,SWT.PUSH);
		button_12.setText("删除刷子");
    }
    
	public void setBridge(MapEditorBridge bridge){
		this.bridge = bridge;
	}
	
	protected void addButton_18Listener(){
		button_18.addListener(SWT.CHECK,new Listener(){
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				MapEditorPart_3 part_3 = bridge.part_3;
				Canvas canvas = part_3.canvas;		
				if(button_18.getSelection()){
					Cursor cursor = new Cursor(Display.getCurrent(),SWT.CURSOR_CROSS);
					canvas.getShell().setCursor(cursor);
				}else{
					Cursor cursor = new Cursor(Display.getCurrent(),SWT.CURSOR_ARROW);
					canvas.getShell().setCursor(cursor);
				}				
				canvas.redraw();				
			}			
		});
	}
}
