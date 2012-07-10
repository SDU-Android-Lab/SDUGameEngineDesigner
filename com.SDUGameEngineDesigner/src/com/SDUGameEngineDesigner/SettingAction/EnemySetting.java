package com.SDUGameEngineDesigner.SettingAction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 敌人设置
 * @author xzz
 *
 */
public class EnemySetting {

	private Composite parent;
	private Group group_1;
	private Group group_2;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Canvas canvas_1;
	public EnemySetting(Composite parent){
		this.parent = parent;
	}
	
	public void initia(){
		
		group_2 = new Group(parent,SWT.NONE);
		group_2.setLayout(new FillLayout());
		group_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		
		Composite cc_1 = new Composite(parent,SWT.NONE);
		cc_1.setLayout(new GridLayout(5,true));
		cc_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,4,1));
		
		group_1 = new Group(cc_1,SWT.NONE);
		GridLayout layout = new GridLayout(6,true);
		group_1.setLayout(layout);
		group_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,5,1));
		
		Label label_1 = new Label(group_1,SWT.NONE);
		label_1.setText("名称:");
		text_1 = new Text(group_1,SWT.BORDER);
		text_1.setText("帅哥");
		text_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		
		Label label_13 = new Label(group_1,SWT.NONE);
		label_13.setText("战斗图:");
		button_3 = new Button(group_1,SWT.PUSH);
		button_3.setText("选择");
		button_3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_2 = new Label(group_1,SWT.NONE);
		label_2.setText("介绍:");
		text_2 = new Text(group_1,SWT.BORDER);
		text_2.setText("很帅很帅滴哥");
		text_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		
		canvas_1 = new Canvas(group_1,SWT.BORDER);
		canvas_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,3));
		
		Label label_3 = new Label(group_1,SWT.NONE);
		label_3.setText("等级:");
		text_3 = new Text(group_1,SWT.BORDER);
		text_3.setText("1");
		text_3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));	
		
		Label label_4 = new Label(group_1,SWT.NONE);
		label_4.setText("金钱:");
		text_4 = new Text(group_1,SWT.BORDER);
		text_4.setText("10");
		text_4.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_5 = new Label(group_1,SWT.NONE);
		label_5.setText("HP:");
		text_5 = new Text(group_1,SWT.BORDER);
		text_5.setText("10");
		text_5.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
				
		Label label_6 = new Label(group_1,SWT.NONE);
		label_6.setText("SP:");
		text_6 = new Text(group_1,SWT.BORDER);
		text_6.setText("1");
		text_6.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_7 = new Label(group_1,SWT.NONE);
		label_7.setText("攻击力:");
		text_7 = new Text(group_1,SWT.BORDER);
		text_7.setText("1");
		text_7.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_8 = new Label(group_1,SWT.NONE);
		label_8.setText("防御力:");
		text_8 = new Text(group_1,SWT.BORDER);
		text_8.setText("1");
		text_8.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_9 = new Label(group_1,SWT.NONE);
		label_9.setText("敏捷:");
		text_9 = new Text(group_1,SWT.BORDER);
		text_9.setText("1");
		text_9.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_10 = new Label(group_1,SWT.NONE);
		label_10.setText("智力:");
		text_10 = new Text(group_1,SWT.BORDER);
		text_10.setText("1");
		text_10.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_11 = new Label(group_1,SWT.NONE);
		label_11.setText("经验:");
		text_11 = new Text(group_1,SWT.BORDER);
		text_11.setText("1");
		text_11.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_12 = new Label(group_1,SWT.NONE);
		label_12.setText("力量:");
		text_12 = new Text(group_1,SWT.BORDER);
		text_12.setText("1");
		text_12.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_14 = new Label(group_1,SWT.NONE);
		label_14.setText("技能列表:");
		
		
		
		Group cc_2 = new Group(parent,SWT.NONE);
		cc_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,5,1));
		RowLayout rl = new RowLayout();
		rl.pack = true;
		rl.marginLeft = 430;
		cc_2.setLayout(rl);
		button_1 = new Button(cc_2,SWT.PUSH);
		button_1.setText("确定");
		button_2 = new Button(cc_2,SWT.PUSH);
		button_2.setText("取消");
		
	}
}
