package com.SDUGameEngineDesigner.SettingAction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 技能设置
 * @author xzz
 *
 */
public class SkillSetting {
	
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
	private Combo combo_1;
	private Combo combo_2;
	private Combo combo_3;
	private Button button_1;
	private Button button_2;
	
	public SkillSetting(Composite parent){
		this.parent = parent;
	}

	public void initial(){
		
		group_2 = new Group(parent,SWT.NONE);
		group_2.setLayout(new FillLayout());
		group_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		
		group_1 = new Group(parent,SWT.NONE);
		group_1.setLayout(new GridLayout(4,true));
		group_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		
		Label label_1 = new Label(group_1,SWT.NONE);
		label_1.setText("名称：");
		text_1 = new Text(group_1,SWT.BORDER);
		text_1.setText("万剑决");
		text_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_2 = new Label(group_1,SWT.NONE);
		label_2.setText("图标：");
		combo_1 = new Combo(group_1,SWT.NONE);
		combo_1.setText("hahha");
		
		Label label_3 = new Label(group_1,SWT.NONE);
		label_3.setText("说明：");
		text_3 = new Text(group_1,SWT.BORDER);
		text_3.setText("全体工具六十");
		text_3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		
		Label label_4 = new Label(group_1,SWT.NONE);
		label_4.setText("类型：");
		combo_2 = new Combo(group_1,SWT.NONE);
		combo_2.setText("wo ai ");
		
		
		Label label_5 = new Label(group_1,SWT.NONE);
		label_5.setText("技能等级：");
		text_5 = new Text(group_1,SWT.BORDER);
		text_5.setText("1");
		text_5.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_8 = new Label(group_1,SWT.NONE);
		label_8.setText("HP伤害：");
		text_8 = new Text(group_1,SWT.BORDER);
		text_8.setText("60");
		text_8.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_9 = new Label(group_1,SWT.NONE);
		label_9.setText("SP消耗：");
		text_9 = new Text(group_1,SWT.BORDER);
		text_9.setText("");
		text_9.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_10 = new Label(group_1,SWT.NONE);
		label_10.setText("物理攻击削弱：");
		text_10 = new Text(group_1,SWT.BORDER);
		text_10.setText("");
		text_10.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_12 = new Label(group_1,SWT.NONE);
		label_12.setText("物理防御削弱：");
		text_12 = new Text(group_1,SWT.BORDER);
		text_12.setText("");
		text_12.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_13 = new Label(group_1,SWT.NONE);
		label_13.setText("攻击速度削弱：");
		text_13 = new Text(group_1,SWT.BORDER);
		text_13.setText("");
		text_13.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_15 = new Label(group_1,SWT.NONE);
		label_15.setText("消耗金钱：");
		text_15 = new Text(group_1,SWT.BORDER);
		text_15.setText("10");
		text_15.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_16 = new Label(group_1,SWT.NONE);
		label_16.setText("播放动画：");
		combo_3 = new Combo(group_1,SWT.NONE);
		combo_3.setText("wo dd");
		
		Label label_17 = new Label(group_1,SWT.NONE);
		label_17.setText("技能分散度：");
		text_4 = new Text(group_1,SWT.BORDER);
		text_4.setText("");
		text_4.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Group cc_2 = new Group(parent,SWT.NONE);
		RowLayout rl = new RowLayout();
		rl.pack = true;
		rl.marginLeft = 430;
		cc_2.setLayout(rl);
		cc_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		button_1 = new Button(cc_2,SWT.PUSH);
		button_1.setText("确定");
		button_2 = new Button(cc_2,SWT.PUSH);
		button_2.setText("取消");
		
	}
}
