package com.SDUGameEngineDesigner.MapEditor;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Dialog.NewMapDialog;
import com.SDUGameEngineDesigner.Dialog.ShowMapDialog;
import com.SDUGameEngineDesigner.View.PackageExplorer;
import com.SDUGameEngineDesigner.View.PackageExplorerElementFactory;

/**
 * 地图总览
 * @author xzz
 *
 */
public class MapPreview {
	private Shell parent;
	private Shell shell;
	private String mapPath;
	private List list;
	private File file;
	public MapPreview(Shell parent,String mapPath){
		this.parent = parent;
		this.mapPath = mapPath;
	}
	
	public void show(){
		shell = new Shell(parent,SWT.CLOSE | SWT.SYSTEM_MODAL | SWT.MAX|SWT.MIN);
		shell.setText("地图总览");
		shell.setSize(400,300);
		shell.setLayout(new FillLayout());
		initiaMapPreview();
		shell.open();
	}
	
	private void initiaMapPreview(){
		Composite composite = new Composite(shell,SWT.NONE);
		composite.setLayout(new FillLayout());
        
        list = new List (composite, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        addListListener();
        redraw();
	
	}
	
	private void addListListener(){
		list.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String name = list.getItem(list.getSelectionIndex());
				String path = file.getParentFile().getAbsolutePath()+"\\"+name;
				showMapDialog(path);
			}
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button == 3){				 
					showPopupMenu();
				}
			}			
		});
	}

	private void showMapDialog(String path){
		ShowMapDialog dialog = new ShowMapDialog(shell,path);
		dialog.open();
	}
	
	private void showPopupMenu(){
		 Menu menu = new Menu(shell,SWT.POP_UP); 
		 list.setMenu(menu);
		  
		 MenuItem menuItem_1 = new MenuItem(menu,SWT.PUSH);  
		 menuItem_1.setText("删除地图");
		 menuItem_1.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_DELETE));
		 menuItem_1.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = list.getSelectionIndex();
				if(index < 0)
					return;
				String name = list.getItem(index);
				if(name!=null){
					boolean done = MessageDialog.openConfirm(shell, "删除地图", "您确定要删除地图吗？");
					if(done){						
					    String path = file.getParentFile().getAbsolutePath()+"\\"+name;
					    File file = new File(path);
					    file.delete();
					    redraw();
					}
				}
			}
		  });
		 MenuItem menuItem_2 = new MenuItem(menu,SWT.PUSH);  
		 menuItem_2.setText("新建地图");
		 menuItem_2.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_NEW_WIZARD));
		 menuItem_2.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewMapDialog dialog = new NewMapDialog(shell,mapPath);
				dialog.open();
				redraw();
			}
		 });		 
	}
	
	public void redraw(){
		if(list.getItemCount()!=0)
			list.removeAll();
		 file = new File(mapPath);
	     File []manyFiles = file.getParentFile().listFiles();
		 for(int i = 0;i<manyFiles.length;i++){
	        	list.add(manyFiles[i].getName());
	        }
		 list.redraw();//更新列表
		 
		//更新资源管理器的显示
		 TreeViewer treeViewer = PackageExplorer.treeViewer;
	     treeViewer.setInput(PackageExplorerElementFactory.getData());
	     treeViewer.refresh();
			
	}
	
}
