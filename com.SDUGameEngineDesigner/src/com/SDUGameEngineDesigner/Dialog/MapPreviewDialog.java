package com.SDUGameEngineDesigner.Dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 地图预览对话框
 * @author xzz
 *
 */
public class MapPreviewDialog extends Dialog {

	private Canvas canvas;
	public MapPreviewDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Point getInitialSize(){
		return new Point(540,480); 
	}
	
	/**
	 * 填充对话框
	 * @Override
	 */
	protected Control createDialogArea(Composite parent) {
		
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(new FillLayout());
		canvas = new Canvas(composite,SWT.NONE);
      //  canvas.setSize(100,100);
	
		return parent; 
	}

	@Override
	protected Button createButton(Composite parent, int id, String label,boolean defaultButton) { 
	   return null;
	} 

	protected void initializeBounds() { 

	}    
	
	/**
	 * 设置对话框的标题,图片
	 */
	protected void configureShell(Shell shell) {      
		super.configureShell(shell);      
		shell.setText("地图预览"); 
		String path = ISharedImages.IMG_TOOL_NEW_WIZARD;
		shell.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(path));
	}
	

}
