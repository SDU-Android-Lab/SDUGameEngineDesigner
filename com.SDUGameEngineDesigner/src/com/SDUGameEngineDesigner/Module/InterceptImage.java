package com.SDUGameEngineDesigner.Module;

import org.eclipse.swt.graphics.Image;

/**
 * 
 * ��¼��ͼ�ڵ�ͼ�ϵ���Ϣ
 * 
 * @author xzz
 *
 */
public class InterceptImage  {
	
	/**
	 * ����ͼ����
	 */
	private Image image;
	
	/**
	 * ����ͼ������
	 */
	private int x,y;
		
	/**
	 * @param image ��¼����ͼ����Ϣ
	 * @param x ��¼����ͼ�ڵ�ͼ�ϵ�λ��
	 * @param y ��¼����ͼ�ڵ�ͼ�ϵ�λ��
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
