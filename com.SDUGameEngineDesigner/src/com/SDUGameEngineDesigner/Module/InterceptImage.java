package com.SDUGameEngineDesigner.Module;

import org.eclipse.swt.graphics.Image;

/**
 * 
 * 记录截图在地图上的信息
 * 
 * @author xzz
 *
 */
public class InterceptImage  {
	
	/**
	 * 剪切图对象
	 */
	private Image image;
	
	/**
	 * 剪切图的坐标
	 */
	private int x,y;
		
	/**
	 * @param image 记录剪切图的信息
	 * @param x 记录剪切图在地图上的位置
	 * @param y 记录剪切图在地图上的位置
	 */
	public InterceptImage(Image image,int x,int y){
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
