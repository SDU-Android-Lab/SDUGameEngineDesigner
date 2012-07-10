package com.SDUGameEngineDesigner.SettingAction;

import org.eclipse.swt.SWT;
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
 * 角色设置
 * @author xzz
 *
 */
public class RoleSetting {

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
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Button button_1;
	private Button button_2;
	private Canvas canvas_1;
	private Canvas canvas_2;
	
	public RoleSetting(Composite parent){
		this.parent = parent;
	}
	
	public void initia(){
		
		group_1 = new Group(parent,SWT.NONE);
		group_1.setLayout(new GridLayout(4,true));
		group_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		
		Label label_1 = new Label(group_1,SWT.NONE);
		label_1.setText("角色名称:");
		text_1 = new Text(group_1,SWT.BORDER);
		text_1.setText("帅哥");
		text_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_2 = new Label(group_1,SWT.NONE);
		label_2.setText("封顶等级:");
		text_2 = new Text(group_1,SWT.BORDER);
		text_2.setText("99");
		text_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_3 = new Label(group_1,SWT.NONE);
		label_3.setText("角色介绍:");
		text_3 = new Text(group_1,SWT.BORDER);
		text_3.setText("很帅很帅的哥");
		text_3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		
		Label label_4 = new Label(group_1,SWT.NONE);
		label_4.setText("力量初值:");
		text_4 = new Text(group_1,SWT.BORDER);
		text_4.setText("8");
		text_4.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_5 = new Label(group_1,SWT.NONE);
		label_5.setText("力量成长:");
		text_5 = new Text(group_1,SWT.BORDER);
		text_5.setText("8");
		text_5.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_6 = new Label(group_1,SWT.NONE);
		label_6.setText("敏捷初值:");
		text_6 = new Text(group_1,SWT.BORDER);
		text_6.setText("8");
		text_6.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_7 = new Label(group_1,SWT.NONE);
		label_7.setText("敏捷成长:");
		text_7 = new Text(group_1,SWT.BORDER);
		text_7.setText("8");
		text_7.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_8 = new Label(group_1,SWT.NONE);
		label_8.setText("智力初值:");
		text_8 = new Text(group_1,SWT.BORDER);
		text_8.setText("8");
		text_8.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_9 = new Label(group_1,SWT.NONE);
		label_9.setText("智力成长:");
		text_9 = new Text(group_1,SWT.BORDER);
		text_9.setText("8");
		text_9.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_10 = new Label(group_1,SWT.NONE);
		label_10.setText("hp初值:");
		text_10 = new Text(group_1,SWT.BORDER);
		text_10.setText("60");
		text_10.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_11 = new Label(group_1,SWT.NONE);
		label_11.setText("sp初值:");
		text_11 = new Text(group_1,SWT.BORDER);
		text_11.setText("60");
		text_11.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_12 = new Label(group_1,SWT.NONE);
		label_12.setText("攻击力:");
		text_12 = new Text(group_1,SWT.BORDER);
		text_12.setText("60");
		text_12.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_13 = new Label(group_1,SWT.NONE);
		label_13.setText("防御力:");
		text_13 = new Text(group_1,SWT.BORDER);
		text_13.setText("60");
		text_13.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_14 = new Label(group_1,SWT.NONE);
		label_14.setText("闪避:");
		text_14 = new Text(group_1,SWT.BORDER);
		text_14.setText("60");
		text_14.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_15 = new Label(group_1,SWT.NONE);
		label_15.setText("封顶金钱:");
		text_15 = new Text(group_1,SWT.BORDER);
		text_15.setText("99999");
		text_15.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));		
		
		Label label_16 = new Label(group_1,SWT.NONE);
		label_16.setText("经验初值:");
		text_16 = new Text(group_1,SWT.BORDER);
		text_16.setText("8");
		text_16.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));		
	
		Label label_17 = new Label(group_1,SWT.NONE);
		label_17.setText("经验成长:");
		text_17 = new Text(group_1,SWT.BORDER);
		text_17.setText("8");
		text_17.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));	
		
		
		group_2 = new Group(parent,SWT.NONE);
		group_2.setLayout(new GridLayout(2,true));
		group_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		Label label_18 = new Label(group_2,SWT.NONE);
		label_18.setText("行走图:     ");	
		button_1 = new Button(group_2,SWT.PUSH);
		button_1.setText("选择");
		canvas_1 = new Canvas(group_2,SWT.BORDER);
		canvas_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,3));
		
		Label label_19 = new Label(group_2,SWT.NONE);
		label_19.setText("战斗图:     ");
		button_2 = new Button(group_2,SWT.PUSH);
		button_2.setText("选择");
		canvas_2 = new Canvas(group_2,SWT.BORDER);
		canvas_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,3));
		
		Group cc_2 = new Group(parent,SWT.NONE);
		cc_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
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
