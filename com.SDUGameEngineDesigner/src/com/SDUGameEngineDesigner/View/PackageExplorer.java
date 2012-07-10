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
 * ���ഴ����Դ��������ͼ
 * 
 * @author xzz
 * 
 */
public class PackageExplorer extends ViewPart {

	/**
	 * ��Դ�������е����鿴��
	 */
	public static TreeViewer treeViewer = null;
	
	/**
	 *�����˵������� 
	 */
	private MenuManager menuManager = null;
	
	/**
	 * �Ҽ������˵�
	 */
	private Menu menu;
	
	/**
	 * �༭����Id
	 */
	private String id = null;

	/**
	 * ��
	 */
	private Tree tree = null;
//	
//	/**
//	 * �½����̰�ť
//	 */
//	private NewProject newProject = null;
	
	/**
	 * ɾ����ť
	 */
	private Delete delete = null; 
	
    /**
     * ��������ť
     */
	private Rename rename = null;
	
	/**
	 * ���ڹ��̰�ť
	 */
	private AboutProject about = null;
	
//	private Composite parent;
	
	@Override
	public void createPartControl(Composite parent) {

//		this.parent = parent;
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeViewer.setContentProvider(new PackageExplorerContentProvider());//������Դ�������������ṩ��
		treeViewer.setLabelProvider(new PackageExplorerLabelProvider());//������Դ�������ı�ǩ�ṩ��
		treeViewer.setInput(PackageExplorerElementFactory.getData());//������Դ����������Ϣ����
		
		addDoubleClickListener();//���˫���ļ���
		addPopupMenu();//��ӵ����˵�
	}

	/**
	 * ˫���ļ���
	 */
	private void addDoubleClickListener() {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection select = (IStructuredSelection) event.getSelection();//���ѡ�е�Selection
				PackageExplorerElement element = (PackageExplorerElement) select.getFirstElement();//���ѡ�е�Ԫ��
				PackageExplorerElement parentElement = element.getParent();//���Ԫ�صĸ�Ԫ��
				if(parentElement==null)//��Ԫ��Ϊnull,��ѡ�е�Ԫ���Ǹ�Ԫ�أ�����
					return;
				if (parentElement.getName().equals("Դ����")) {//��Ԫ�ص�����ΪԴ���룬���Դ����༭����
					id = "com.SDUGameEngineDesigner.editor1";
					CodeEditorInput input = (CodeEditorInput) element.getEditorInput();
					input.setPackageExplorerElement(element);//��������ľ���Ԫ��
					
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					CodeEditor editor = (CodeEditor) workbenchPage.findEditor(input);//ͨ����������Ӧ�ı༭��
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
				if(parentElement.getName().equals("��ͼ")){//��Ԫ�ص�����Ϊ��ͼ����򿪵�ͼ�༭����
					id = "com.SDUGameEngineDesigner.editor2";
					MapEditorInput input = (MapEditorInput) element.getEditorInput();
					input.setPackageExplorerElement(element);//��������ľ���Ԫ��
					
					IWorkbenchPage workbenchPage = getViewSite().getPage();
					MapEditor editor = (MapEditor) workbenchPage.findEditor(input);//ͨ����������Ӧ�ı༭��
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
	 * �����Ĳ˵�
	 */
	private void addPopupMenu(){
		//�����˵���ʵ��
		menuManager = new MenuManager();
		menu = menuManager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		
		//�ڲ�ͬ���͵Ľڵ�����ʾ��ͬ���Ҽ��˵�
	    tree  =  treeViewer.getTree(); 
		tree.addMouseListener(   new   MouseAdapter(){
		    public void mouseDown(MouseEvent e){	
		        if(e.button == 3){ //�����һ�
		             TreeItem treeItem = tree.getItem(new Point(e.x, e.y)); 
        	         if(treeItem != null){  
		                IStructuredSelection selection = (IStructuredSelection)treeViewer.getSelection(); 
		                PackageExplorerElement element   =   (PackageExplorerElement)selection.getFirstElement();
		                PackageExplorerElement parentElement = element.getParent();
		                if(parentElement == null){//ѡ�е�Ԫ��Ϊ��Ԫ�ء�
		                	addRootMenu(element);
		                }
		                if(!element.hasChildren()){//Ϊ�ļ�����Ҽ��˵�
	                		addFileMenu(element);
	                	}
	                	if(element.getName().equals("��ͼ")){//Ԫ�ص������ǵ�ͼ��
	                		addMapMenu(element);
	    		        }
		             }else{
		            	 menuManager.removeAll();
		                 menuManager.add(SoManyAction.newProject);	                
		             }
		         }
		    }
		});
		
//		���goback ,gohome, gointo����
//		DrillDownAdapter dda = new DrillDownAdapter(treeViewer);
//		dda.addNavigationActions(menuManager);
	}
	
	/**
	 * Ϊ��Ԫ������Ҽ��˵�
	 * @param element
	 */
	private void addRootMenu(PackageExplorerElement element){
		menuManager.removeAll();
    	menuManager.add(SoManyAction.newProject);
    	delete = SoManyAction.delete;
    	delete.setNameAndTarget(element.getName(),"ɾ������");
    	menuManager.add(delete);   
    	rename = SoManyAction.rename;
    	rename.setNameAndTarget(element.getName(), "����������");
    	menuManager.add(rename);
    	about = new AboutProject(element.getName());
    	menuManager.add(about);
	}
	
	/**
	 * Ϊ�ļ�Ԫ������Ҽ��˵�
	 * @param element
	 */
	private void addFileMenu(PackageExplorerElement element){
		menuManager.removeAll();
	    delete = SoManyAction.delete;
    	delete.setNameAndTarget(element.getPath(),"ɾ���ļ�");
    	menuManager.add(delete);  
    	
    	rename = SoManyAction.rename;
    	rename.setNameAndTarget(element.getPath(), "�������ļ�");
    	menuManager.add(rename);
    	
    	NewMapAction newMap = new NewMapAction(EnvironmentVariables.workspacePath+"\\"+element.getPath());
		menuManager.add(newMap);
	}
	
	/**
	 * Ϊ��ͼԪ������Ҽ�Ԫ��
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
