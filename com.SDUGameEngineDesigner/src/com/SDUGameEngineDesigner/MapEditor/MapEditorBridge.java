package com.SDUGameEngineDesigner.MapEditor;

/**
 * ����洢�ŵ�ͼ�༭���ĸ������ֵ�����
 * @author xzz
 *
 */
public class MapEditorBridge {
	
	/**
	 * ������һ
	 */
	protected MapEditorToolBar_1 bar_1;
	
	/**
	 * ��������
	 */
	protected MapEditorToolBar_2 bar_2;
	
	/**
	 * �˵���һ
	 */
	protected MapEditorPart_3 part_3;
	
	/**
	 * �˵�����
	 */
	protected MapEditorPart_4 part_4;
	
	public MapEditorBridge(MapEditorToolBar_1 bar_1,MapEditorToolBar_2 bar_2, MapEditorPart_3 part_3,MapEditorPart_4 part_4){
		this.bar_1 = bar_1;
		this.bar_2 = bar_2;
		this.part_3 = part_3;
		this.part_4 = part_4;		
	}
	
	/**
	 * ��ù�����һ
	 * @return MapEditorToolBar_1
	 */
	public MapEditorToolBar_1 getToolBar_1(){
		return bar_1;
	}
	
	/**
	 * ��ù�������
	 * @return MapEditorToolBar_2
	 */
	public MapEditorToolBar_2 getToolBar_2(){
		return bar_2;
	}

	/**
	 * ��� �˵���һ
	 * @return MapEditorPart_3
	 */
    public MapEditorPart_3 getPart_3(){
    	return part_3;
    }
    
    /**
	 * ��ò˵�����
	 * @return MapEditorPart_4
	 */
    public MapEditorPart_4 getPart_4(){
    	return part_4;
    }
	
}
