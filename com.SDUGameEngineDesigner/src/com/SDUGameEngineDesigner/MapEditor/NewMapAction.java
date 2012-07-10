package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Dialog.NewMapDialog;
import com.SDUGameEngineDesigner.View.PackageExplorer;
import com.SDUGameEngineDesigner.View.PackageExplorerElementFactory;

/**
 * ��Դ���������Ҽ��˵����½���ͼ��ť
 * @author xzz
 *
 */
public class NewMapAction extends Action {
	
	private String path;
	public NewMapAction(String path){
		this.path = path;
		
		this.setText("�½���ͼ");
		this.setToolTipText("Save project");
		String path1 = ISharedImages.IMG_TOOL_NEW_WIZARD;	
		ImageDescriptor imgDes = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(path1);
		this.setImageDescriptor(imgDes);
	}
	public void run(){
		Shell shell = new Shell();
		NewMapDialog dialog = new NewMapDialog(shell,path);
		dialog.open();
		//������Դ����������ʾ
		TreeViewer treeViewer = PackageExplorer.treeViewer;
		treeViewer.setInput(PackageExplorerElementFactory.getData());
		treeViewer.refresh();
	}

}
