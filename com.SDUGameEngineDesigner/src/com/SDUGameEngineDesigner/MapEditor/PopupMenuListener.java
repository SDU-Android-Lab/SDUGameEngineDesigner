package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;

import com.SDUGameEngineDesigner.Dialog.ChangeSceneDialog;
import com.SDUGameEngineDesigner.Dialog.EncounterEnemyDialog;
import com.SDUGameEngineDesigner.Dialog.FindThingDialog;
import com.SDUGameEngineDesigner.Module.ChangeScene;
import com.SDUGameEngineDesigner.Module.EncounterEnemy;
import com.SDUGameEngineDesigner.Module.FindThing;

/**
 * �����ϵ����˵�������
 * @author xzz
 *
 */
public class PopupMenuListener implements SelectionListener {

	private MapEditorPart_3 part_3;
	
	private int x ,y;
	
	public PopupMenuListener(MapEditorPart_3 part_3,int x,int y){
		this.part_3 = part_3;
		this.x = x;
		this.y = y;
	}
	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

		MenuItem i = (MenuItem)e.widget;
		if(i==null)
			return;
		String name = i.getText();
		if(name.equals("�л�����")){
			ChangeSceneDialog dialog = new ChangeSceneDialog(Display.getCurrent().getActiveShell());
			int returnCode = dialog.open();
			if(returnCode == ChangeSceneDialog.CANCEL)
				return;
	        part_3.changeScene.add(new ChangeScene(dialog.path,x,y,dialog.x,dialog.y));
	        part_3.canvas.redraw();
	        return;
		}
		if(name.equals("��������")){
		    EncounterEnemyDialog dialog = new EncounterEnemyDialog(Display.getCurrent().getActiveShell());
		    int returnCode = dialog.open();
			if(returnCode == EncounterEnemyDialog.CANCEL)
				return;
		    part_3.encounterEnemy.add(new EncounterEnemy(dialog.getEnemyName(), dialog.getPath(),dialog.getRate(),x,y));
		    part_3.canvas.redraw();
	        return;
		}
		if(name.equals("��ʰ��Ʒ")){
			FindThingDialog dialog = new FindThingDialog(Display.getCurrent().getActiveShell());
			int returnCode = dialog.open();
			if(returnCode == FindThingDialog.CANCEL)
				return;
			part_3.findThing.add(new FindThing(dialog.getThingName(),dialog.getRate(),x,y));
			part_3.canvas.redraw();
	        return;
		}
		if(name.equals("NPC�Ի�")){
			
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
