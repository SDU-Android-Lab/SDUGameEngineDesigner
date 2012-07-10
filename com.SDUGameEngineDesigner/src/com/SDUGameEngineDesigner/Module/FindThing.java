package com.SDUGameEngineDesigner.Module;

import java.util.Vector;

/**
 * 捡拾物品
 * @author xzz
 *
 */
public class FindThing extends Event{

	private Vector<String> thingName = new Vector<String>();
	private double thingRate;
	
	public FindThing(Vector<String> thingName,double thingRate,int x ,int y){
		//物品出现在地图上是坐标int x ,y;
		super(x,y);
		this.thingName = thingName ;
		this.thingRate = thingRate;
	}

	public Vector<String> geThingName(){
		return thingName;
	}
	
	
	public double getThingRate(){
		return thingRate;
	}
}
