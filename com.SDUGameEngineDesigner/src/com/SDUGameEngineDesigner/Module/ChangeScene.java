package com.SDUGameEngineDesigner.Module;

/**
 * 切换场景类
 * @author xzz
 *
 */
public class ChangeScene extends Event{
	
	/**
	 * 跳转图地址
	 */
	private String path;

	/**
	 * 跳转图坐标
	 */
	private int x1,y1;
	
	public ChangeScene(String path,int x,int y,int x1,int y1){
		// 原图坐标int x,y;s
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
