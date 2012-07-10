package com.SDUGameEngineDesigner.Module;

/**
 * 碰撞点类
 * @param x 记录碰撞点在地图上的位置
 * @param y 记录碰撞点在地图上的位置
 * @author xzz
 */
public class CollidePoint extends Event{

	public CollidePoint(int x,int y){
		 // 碰撞点的坐标 x,y;
		super(x,y);
	}
}
