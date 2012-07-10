package com.SDUGameEngineDesigner.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

/**
 * ���ഴ������̨��ͼ
 * ���ú���ӿ���̨���ı���Ϣ
 * @author xzz
 *
 */
public class Console extends ViewPart {

	/**
	 * ����̨��������ʾ��Ϣ���ı���
	 */
	private Text text;
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub

		text = new Text(parent,SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
	    text.setEditable(false);//���ò��ɱ༭
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * ��Text������ı���Ϣ
	 * @param content
	 */
	public void appendText(String content){
		text.append(content);
	}
	
	/**
	 * ��Text�������ı���Ϣ
	 * @param content
	 */
	public void setText(String content){
		text.setText(content);
	}
}
