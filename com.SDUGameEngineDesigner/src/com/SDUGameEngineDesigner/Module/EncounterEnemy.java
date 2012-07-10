package com.SDUGameEngineDesigner.Module;

import java.util.Vector;

/**
 * ·������
 * @author xzz
 *
 */
public class EncounterEnemy extends Event{
	
	/**
	 * �������˵����֣���ţ���Ҳ������һ������
	 */
	private Vector<String> enemyName = new Vector<String>();
	

    /**
     * ս��ͼ�ĵ�ַ
     */
	private String path_2;
	private double enemyRate;
	
	public EncounterEnemy(Vector<String> enemyName,String path_2,double enemyRate,int x ,int y){
		//���˳����ڵ�ͼ��������int x ,y;
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
