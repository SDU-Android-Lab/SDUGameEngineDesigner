package com.SDUGameEngineDesigner.Module;

/**
 * �¼��࣬�κ��¼����̳���
 * @param x ��¼�¼��ڵ�ͼ�ϵ�λ��
 * @param y ��¼�¼��ڵ�ͼ�ϵ�λ��
 * @author xzz
 */
public class Event {

	/**
	 * ��ײ�������
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
