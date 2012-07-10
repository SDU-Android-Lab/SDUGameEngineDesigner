package com.SDUGameEngineDesigner.SettingAction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * 数据设置窗口
 * @author xzz
 *
 */
public class DataSettingWindow {

	private Shell current;
	private FillLayout layout;
	private Composite composite;
	private CTabFolder folder;
	private CTabItem item1;
	private CTabItem item2;
	private CTabItem item3;
	private CTabItem item4;
	private CTabItem item5;
	private CTabItem item6;
	private CTabItem item7;
	
	
	public DataSettingWindow(Shell parent){
		current = new Shell(parent,SWT.CLOSE|SWT.RESIZE);
		current.setText("数据设置");
		layout = new FillLayout();
		current.setLayout(layout);
		current.setSize(550,450);
	}
	
	public void initia(){
		
		composite = new Composite(current,SWT.NONE);
		composite.setLayout(layout);
		
		folder = new CTabFolder(composite,SWT.BORDER);
		folder.setLayout(layout);
			
		createItem1();
		createItem2();
		createItem3();
	//	createItem4();
		createItem5();
		createItem6();
		createItem7();
		
		current.open();
	}
	
	private void createItem1(){
		item1 = new CTabItem(folder,SWT.BORDER);
		item1.setText("游戏总体设置");
		Composite cc = new Composite(folder,SWT.NONE);
		RowLayout rl = new RowLayout(SWT.VERTICAL);
		cc.setLayout(rl);		
		item1.setControl(cc);
		OverallSetting setting = new OverallSetting(cc);
		setting.initia();
		
	}
	private void createItem2(){
		item2 = new CTabItem(folder,SWT.BORDER);
		item2.setText("英雄设置");
		Composite cc = new Composite(folder,SWT.NONE);
		cc.setLayout(new GridLayout(3,true));
		item2.setControl(cc);
		RoleSetting setting = new RoleSetting(cc);
		setting.initia();
		
	}
	private void createItem3(){
		item3 = new CTabItem(folder,SWT.BORDER);
		item3.setText("敌人设置");
		Composite cc = new Composite(folder,SWT.NONE);
		cc.setLayout(new GridLayout(5,true));
		item3.setControl(cc);
		EnemySetting setting = new EnemySetting(cc);
		setting.initia();
		
	}
	private void createItem4(){
		item4 = new CTabItem(folder,SWT.BORDER);
		item4.setText("敌人团队");
	}
	private void createItem5(){
		item5 = new CTabItem(folder,SWT.BORDER);
		item5.setText("物品设置");
		Composite cc = new Composite(folder,SWT.NONE);
		cc.setLayout(new GridLayout(3,true));
		item5.setControl(cc);
		MaterialsSetting setting = new MaterialsSetting(cc);
		setting.initial();
	
	}
	private void createItem6(){
		item6 = new CTabItem(folder,SWT.BORDER);
		item6.setText("装备设置");
		Composite cc = new Composite(folder,SWT.NONE);
		cc.setLayout(new GridLayout(3,true));
		item6.setControl(cc);
		EquipmentSetting setting = new EquipmentSetting(cc);
		setting.initial();
	}
	private void createItem7(){
		item7 = new CTabItem(folder,SWT.BORDER);
		item7.setText("技能设置");
		Composite cc = new Composite(folder,SWT.NONE);
		cc.setLayout(new GridLayout(3,true));
		item7.setControl(cc);
		SkillSetting setting = new SkillSetting(cc);
		setting.initial();
	}
	

}
