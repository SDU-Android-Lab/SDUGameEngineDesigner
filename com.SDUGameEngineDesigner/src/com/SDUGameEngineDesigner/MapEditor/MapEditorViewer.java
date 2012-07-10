package com.SDUGameEngineDesigner.MapEditor;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;

/**
 * 地图编辑器的具体实现
 * @author xzz
 */
public class MapEditorViewer {

	protected MapEditorSave save;
	private Composite parent;
	private Composite composite;
	private String path;
	private MapEditor editor;
	private Group group_1 = null;
	private Group group_2 = null;
	private MapEditorToolBar_1 bar_1;
	private MapEditorToolBar_2 bar_2;
	private MapEditorPart_3 part_3;
	private MapEditorPart_4 part_4; 
	private MapEditorBridge bridge;
	private String imagePath_3;
	private String imagePath_4;
	
	public MapEditorViewer(Composite parent,String path,MapEditor editor){
		this.parent = parent;
		this.path = EnvironmentVariables.workspacePath+"\\"+path;
		this.editor = editor;
		
	}
	
	public void initiaViewer(){	
		save = new MapEditorSave(path);
		save.loadMapInfor();
		
		composite = new Composite(parent,SWT.NONE);
		composite.setLayout(new GridLayout(1,false));
		
		createMapContol();
		createToolBar();
		
		bridge = new MapEditorBridge(bar_1,bar_2,part_3,part_4);
		bar_1.setBridge(bridge); 
		bar_2.setBridge(bridge);
		part_3.setBridge(bridge);
		part_4.setBridge(bridge);
		
		initiaAll();
		addMany();
	
	}

	private void createToolBar(){
		group_1 = new Group(composite,SWT.NONE);
		group_1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group_1.setLayout(new GridLayout());
		group_1.setText("工具栏");
		
		bar_1 = new MapEditorToolBar_1(group_1,editor,save);
		bar_2 = new MapEditorToolBar_2(group_1);	
	}
	
	private void createMapContol(){		
        group_2 = new Group(composite,SWT.NONE);
        group_2.setLayoutData(new GridData(GridData.FILL_BOTH));
		group_2.setText("编辑地图");
		group_2.setLayout(new GridLayout(3,true));
		
		imagePath_3 = save.bgImage;
		part_3 = new MapEditorPart_3(group_2,imagePath_3, editor);		
		imagePath_4 = save.resImage;
		part_4 = new MapEditorPart_4(group_2,imagePath_4,editor);
	}
	
	private void initiaAll(){
		bar_1.initiaToolBar_1();
		part_3.initiaPart_3();
		part_4.initiaPart_4();
	}
	
	private void addMany(){
		bar_1.addButtonListener(path);
		bar_1.addButton_1Listener();
		bar_1.addButton_2Listener();
		bar_1.addButton_4Listener();
		bar_1.addButton_6Listener();
		bar_2.addButton_18Listener();
		part_3.addRedrawListener();
		part_3.addCombo_1Lietener();
		part_4.addCanvasPaintListener();	
	}
	
}