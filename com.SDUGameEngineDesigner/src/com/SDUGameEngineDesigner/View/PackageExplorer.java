package com.SDUGameEngineDesigner.View;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.SDUGameEngineDesigner.CodeEditor.CodeEditor;
import com.SDUGameEngineDesigner.CodeEditor.CodeEditorInput;
import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.Designer.SoManyAction;
import com.SDUGameEngineDesigner.FileAction.AboutProject;
import com.SDUGameEngineDesigner.FileAction.Delete;
import com.SDUGameEngineDesigner.FileAction.Rename;
import com.SDUGameEngineDesigner.MapEditor.MapEditor;
import com.SDUGameEngineDesigner.MapEditor.MapEditorInput;
import com.SDUGameEngineDesigner.MapEditor.NewMapAction;

/**
 * 该类创建资源管理器视图
 * 
 * @author xzz
 * 
 */
public class PackageExplorer extends ViewPart {

	/**
	 * 资源管理器中的树查看器
	 */
	public static TreeViewer treeViewer = null;
	
	/**
	 *弹出菜单管理器 
	 */
	private MenuManager menuManager = null;
	
	/**
	 * 右键弹出菜单
	 */
	private Menu menu;
	
	/**
	 * 编辑器的Id
	 */
	private String id = null;

	/**
	 * 树
	 */
	private Tree tree = null;
//	
//	/**
//	 * 新建工程按钮
//	 */
//	private NewProject newProject = null;
	
	/**
	 * 删除按钮
	 */
	private Delete delete = null; 
	
    /**
     * 重命名按钮
     */
	private Rename rename = null;
	
	/**
	 * 关于工程按钮
	 */
	private AboutProject about = null;
	
//	private Composite parent;
	
	@Override
	public void createPartControl(Composite parent) {

//		this.parent = parent;
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeViewer.setContentProvider(new PackageExplorerContentProvider());//设置资源管理器的内容提供者
		treeViewer.setLabelProvider(new PackageExplorerLabelProvider());//设置资源管理器的标签提供者
		treeViewer.setInput(PackageExplorerElementFactory.getData());//设置资源管理器的信息输入
		
		addDoubleClickListener();//添加双击的监听
		addPopupMenu();//添加弹出菜单
	}

	/**
	 * 双击的监听
	 */
	private void addDoubleClickListener() {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection select = (IStructuredSelection) event.getSelection();//获得选中的Selection
				PackageExplorerElement element = (PackageExplorerElement) select.getFirstElement();//获得选中的元素
				PackageExplorerElement parentElement = element.getParent();//获得元素的父元素
				if(parentElement==null)//父元素为null,则选中的元素是根元素，返回
					return;
				if (parentElement.getName().equals("源代码")) {//父元素的名字为源代码，则打开源代码编辑器。
					id = "com.SDUGameEngineDesigner.editor1";
					CodeEditorInput input = (CodeEditorInput) element.getEditorInput();
					input.setPackageExplorerElement(element);//设置输入的具体元素
					
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					CodeEditor editor = (CodeEditor) workbenchPage.findEditor(input);//通过输入获得相应的编辑器
					if (editor != null) {
						workbenchPage.bringToTop(editor);
					} else {
						try {
							editor = (CodeEditor) workbenchPage.openEditor(input, id);
						} catch (PartInitException e) {
							e.printStackTrace(System.out);
						}
					}
				}
				if(parentElement.getName().equals("地图")){//父元素的名字为地图，则打开地图编辑器。
					id = "com.SDUGameEngineDesigner.editor2";
					MapEditorInput input = (MapEditorInput) element.getEditorInput();
					input.setPackageExplorerElement(element);//设置输入的具体元素
					
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					MapEditor editor = (MapEditor) workbenchPage.findEditor(input);//通过输入获得相应的编辑器
					if (editor != null) {
						workbenchPage.bringToTop(editor);
					} else {
						try {
							editor = (MapEditor) workbenchPage.openEditor(input, id);
						} catch (PartInitException e) {
							e.printStackTrace(System.out);
						}
					}
				}
				
			}

		});
	}

	/**
	 * 弹出的菜单
	 */
	private void addPopupMenu(){
		//弹出菜单的实现
		menuManager = new MenuManager();
		menu = menuManager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		
		//在不同类型的节点上显示不同的右键菜单
	    tree  =  treeViewer.getTree(); 
		tree.addMouseListener(   new   MouseAdapter(){
		    public void mouseDown(MouseEvent e){	
		        if(e.button == 3){ //监听右击
		             TreeItem treeItem = tree.getItem(new Point(e.x, e.y)); 
        	         if(treeItem != null){  
		                IStructuredSelection selection = (IStructuredSelection)treeViewer.getSelection(); 
		                PackageExplorerElement element   =   (PackageExplorerElement)selection.getFirstElement();
		                PackageExplorerElement parentElement = element.getParent();
		                if(parentElement == null){//选中的元素为跟元素。
		                	addRootMenu(element);
		                }
		                if(!element.hasChildren()){//为文件添加右键菜单
	                		addFileMenu(element);
	                	}
	                	if(element.getName().equals("地图")){//元素的名称是地图，
	                		addMapMenu(element);
	    		        }
		             }else{
		            	 menuManager.removeAll();
		                 menuManager.add(SoManyAction.newProject);	                
		             }
		         }
		    }
		});
		
//		添加goback ,gohome, gointo操作
//		DrillDownAdapter dda = new DrillDownAdapter(treeViewer);
//		dda.addNavigationActions(menuManager);
	}
	
	/**
	 * 为根元素添加右键菜单
	 * @param element
	 */
	private void addRootMenu(PackageExplorerElement element){
		menuManager.removeAll();
    	menuManager.add(SoManyAction.newProject);
    	delete = SoManyAction.delete;
    	delete.setNameAndTarget(element.getName(),"删除工程");
    	menuManager.add(delete);   
    	rename = SoManyAction.rename;
    	rename.setNameAndTarget(element.getName(), "重命名工程");
    	menuManager.add(rename);
    	about = new AboutProject(element.getName());
    	menuManager.add(about);
	}
	
	/**
	 * 为文件元素添加右键菜单
	 * @param element
	 */
	private void addFileMenu(PackageExplorerElement element){
		menuManager.removeAll();
	    delete = SoManyAction.delete;
    	delete.setNameAndTarget(element.getPath(),"删除文件");
    	menuManager.add(delete);  
    	
    	rename = SoManyAction.rename;
    	rename.setNameAndTarget(element.getPath(), "重命名文件");
    	menuManager.add(rename);
    	
    	NewMapAction newMap = new NewMapAction(EnvironmentVariables.workspacePath+"\\"+element.getPath());
		menuManager.add(newMap);
	}
	
	/**
	 * 为地图元素添加右键元素
	 * @param element
	 */
	private void addMapMenu(final PackageExplorerElement element){
		menuManager.removeAll();
		NewMapAction newMap = new NewMapAction(EnvironmentVariables.workspacePath+"\\"+element.getPath()+"\\");
		menuManager.add(newMap);
	}
	@Override
	public void setFocus() {

	}

}
