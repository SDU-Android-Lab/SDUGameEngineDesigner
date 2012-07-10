package com.SDUGameEngineDesigner.Module;

/**
 * 事件类，任何事件都继承它
 * @param x 记录事件在地图上的位置
 * @param y 记录事件在地图上的位置
 * @author xzz
 */
public class Event {

	/**
	 * 碰撞点的坐标
	 */
	private int x,y;
	
	public Event(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
