package com.SDUGameEngineDesigner.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

/**
 * 该类创建控制台视图
 * 设置和添加控制台的文本信息
 * @author xzz
 *
 */
public class Console extends ViewPart {

	/**
	 * 控制台上用于显示信息的文本框。
	 */
	private Text text;
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub

		text = new Text(parent,SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
	    text.setEditable(false);//设置不可编辑
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * 在Text上添加文本信息
	 * @param content
	 */
	public void appendText(String content){
		text.append(content);
	}
	
	/**
	 * 在Text上设置文本信息
	 * @param content
	 */
	public void setText(String content){
		text.setText(content);
	}
}
