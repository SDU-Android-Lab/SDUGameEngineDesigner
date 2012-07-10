package com.SDUGameEngineDesigner.MapEditor;

/**
 * 本类存储着地图编辑器的各个部分的引用
 * @author xzz
 *
 */
public class MapEditorBridge {
	
	/**
	 * 工具栏一
	 */
	protected MapEditorToolBar_1 bar_1;
	
	/**
	 * 工具栏二
	 */
	protected MapEditorToolBar_2 bar_2;
	
	/**
	 * 菜单栏一
	 */
	protected MapEditorPart_3 part_3;
	
	/**
	 * 菜单栏二
	 */
	protected MapEditorPart_4 part_4;
	
	public MapEditorBridge(MapEditorToolBar_1 bar_1,MapEditorToolBar_2 bar_2, MapEditorPart_3 part_3,MapEditorPart_4 part_4){
		this.bar_1 = bar_1;
		this.bar_2 = bar_2;
		this.part_3 = part_3;
		this.part_4 = part_4;		
	}
	
	/**
	 * 获得工具栏一
	 * @return MapEditorToolBar_1
	 */
	public MapEditorToolBar_1 getToolBar_1(){
		return bar_1;
	}
	
	/**
	 * 获得工具栏二
	 * @return MapEditorToolBar_2
	 */
	public MapEditorToolBar_2 getToolBar_2(){
		return bar_2;
	}

	/**
	 * 获得 菜单栏一
	 * @return MapEditorPart_3
	 */
    public MapEditorPart_3 getPart_3(){
    	return part_3;
    }
    
    /**
	 * 获得菜单栏二
	 * @return MapEditorPart_4
	 */
    public MapEditorPart_4 getPart_4(){
    	return part_4;
    }
	
}
