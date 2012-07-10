package com.SDUGameEngineDesigner.SettingAction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 游戏总体设置
 * @author xzz
 *
 */
public class OverallSetting {

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
	private Text text_18;
	private Button button_1;
	private Button button_2;
	
	public OverallSetting(Composite parent){
		this.parent = parent;
	}
	
	public void initia(){
		Composite cc_1 = new Composite(parent,SWT.NONE);
		cc_1.setLayout(new GridLayout(5,true));
		
		group_1 = new Group(cc_1,SWT.NONE);
		GridLayout layout = new GridLayout(4,true);
		group_1.setLayout(layout);
		group_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,3,1));
		
		Label label_1 = new Label(group_1,SWT.NONE);
		label_1.setText("游戏名称:");
		text_1 = new Text(group_1,SWT.BORDER);
		text_1.setText("RPG小游戏");
		text_1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		
		Label label_2 = new Label(group_1,SWT.NONE);
		label_2.setText("力量用语:");
		text_2 = new Text(group_1,SWT.BORDER);
		text_2.setText("力量");
		text_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_3 = new Label(group_1,SWT.NONE);
		label_3.setText("sp用语:");
		text_3 = new Text(group_1,SWT.BORDER);
		text_3.setText("sp");
		text_3.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		
		Label label_4 = new Label(group_1,SWT.NONE);
		label_4.setText("hp用语:");
		text_4 = new Text(group_1,SWT.BORDER);
		text_4.setText("hp");
		text_4.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_5 = new Label(group_1,SWT.NONE);
		label_5.setText("智力用语:");
		text_5 = new Text(group_1,SWT.BORDER);
		text_5.setText("智力");
		text_5.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_6 = new Label(group_1,SWT.NONE);
		label_6.setText("闪避用语:");
		text_6 = new Text(group_1,SWT.BORDER);
		text_6.setText("闪避");
		text_6.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_7 = new Label(group_1,SWT.NONE);
		label_7.setText("攻击力用语:");
		text_7 = new Text(group_1,SWT.BORDER);
		text_7.setText("攻击力");
		text_7.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_8 = new Label(group_1,SWT.NONE);
		label_8.setText("防御力用语:");
		text_8 = new Text(group_1,SWT.BORDER);
		text_8.setText("防御力");
		text_8.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_9 = new Label(group_1,SWT.NONE);
		label_9.setText("敏捷用语:");
		text_9 = new Text(group_1,SWT.BORDER);
		text_9.setText("敏捷");
		text_9.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_10 = new Label(group_1,SWT.NONE);
		label_10.setText("金钱用语:");
		text_10 = new Text(group_1,SWT.BORDER);
		text_10.setText("金钱");
		text_10.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_11 = new Label(group_1,SWT.NONE);
		label_11.setText("头盔用语:");
		text_11 = new Text(group_1,SWT.BORDER);
		text_11.setText("头盔");
		text_11.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_12 = new Label(group_1,SWT.NONE);
		label_12.setText("铠甲用语:");
		text_12 = new Text(group_1,SWT.BORDER);
		text_12.setText("铠甲");
		text_12.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_13 = new Label(group_1,SWT.NONE);
		label_13.setText("盾牌用语:");
		text_13 = new Text(group_1,SWT.BORDER);
		text_13.setText("盾牌");
		text_13.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		
		Label label_14 = new Label(group_1,SWT.NONE);
		label_14.setText("武器用语:");
		text_14 = new Text(group_1,SWT.BORDER);
		text_14.setText("武器");
		text_14.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_15 = new Label(group_1,SWT.NONE);
		label_15.setText("战靴用语:");
		text_15 = new Text(group_1,SWT.BORDER);
		text_15.setText("战靴");
		text_15.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		Label label_16 = new Label(group_1,SWT.NONE);
		label_16.setText("饰品用语:");
		text_16 = new Text(group_1,SWT.BORDER);
		text_16.setText("饰品");
		text_16.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		
		group_2 = new Group(cc_1,SWT.NONE);
		group_2.setLayout(new GridLayout(1,true));
		group_2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,1));
		Label label_17 = new Label(group_2,SWT.NONE);
		label_17.setText("    游戏帮助");		
		text_17 = new Text(group_2,SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.BORDER);
		GridData data_1 = new GridData(SWT.FILL,SWT.FILL,true,true);
		data_1.heightHint = 100;
		text_17.setLayoutData(data_1);
		
		Label label_18 = new Label(group_2,SWT.NONE);
		label_18.setText("    游戏说明");
		text_18 = new Text(group_2,SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.BORDER);
		GridData data_2 = new GridData(SWT.FILL,SWT.FILL,true,true);
		data_2.heightHint = 100;
		text_18.setLayoutData(data_2); 
		
		Group cc_2 = new Group(parent,SWT.NONE);
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
