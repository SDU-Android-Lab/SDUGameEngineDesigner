package com.SDUGameEngineDesigner.Module;

import java.util.Vector;

/**
 * 路遇敌人
 * @author xzz
 *
 */
public class EncounterEnemy extends Event{
	
	/**
	 * 各个敌人的名字（编号），也可能是一个敌人
	 */
	private Vector<String> enemyName = new Vector<String>();
	

    /**
     * 战斗图的地址
     */
	private String path_2;
	private double enemyRate;
	
	public EncounterEnemy(Vector<String> enemyName,String path_2,double enemyRate,int x ,int y){
		//敌人出现在地图上是坐标int x ,y;
		super(x,y);
		this.enemyName = enemyName ;
		this.path_2 = path_2;
		this.enemyRate = enemyRate;
	}

	public Vector<String>getEnemyName(){
		return enemyName;
	}
	
	public String getPath(){
		return path_2;
	}
	
	public double getEnemyRate(){
		return enemyRate;
	}
}
