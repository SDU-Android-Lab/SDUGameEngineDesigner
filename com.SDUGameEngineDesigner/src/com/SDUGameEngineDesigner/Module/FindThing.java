package com.SDUGameEngineDesigner.Module;

import java.util.Vector;

/**
 * ��ʰ��Ʒ
 * @author xzz
 *
 */
public class FindThing extends Event{

	private Vector<String> thingName = new Vector<String>();
	private double thingRate;
	
	public FindThing(Vector<String> thingName,double thingRate,int x ,int y){
		//��Ʒ�����ڵ�ͼ��������int x ,y;
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
