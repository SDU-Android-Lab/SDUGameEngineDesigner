package com.SDUGameEngineDesigner.Module;

/**
 * �л�������
 * @author xzz
 *
 */
public class ChangeScene extends Event{
	
	/**
	 * ��תͼ��ַ
	 */
	private String path;

	/**
	 * ��תͼ����
	 */
	private int x1,y1;
	
	public ChangeScene(String path,int x,int y,int x1,int y1){
		// ԭͼ����int x,y;s
		super(x,y);
		this.path = path;
		this.x1 = x1;
		this.y1 = y1;
	}

	public String getPath(){
		return path;
	}
	
	public int getX1(){
		return x1;
	}
	
	public int getY1(){
		return y1;
	}
}
