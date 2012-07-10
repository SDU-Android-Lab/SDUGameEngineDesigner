package com.SDUGameEngineDesigner.MapEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * 地图编辑器
 * @author xzz
 *
 */
public class MapEditor extends EditorPart {

	/**
	 *地图编辑器的输入 
	 */
	private MapEditorInput input;
	
	/**
	 * 是否需要保存
	 */
	public  boolean dirty = false;
	
	/**
	 * 地图编辑器中的查看器
	 */
	private MapEditorViewer viewer = null;
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		viewer.save.saveMap();
		dirty = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {

		this.input = (MapEditorInput) input;
		this.setSite(site);
		this.setInput(input);
		
		this.setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));	
		String path = input.getPackageExplorerElement().getPath();
       
		viewer = new MapEditorViewer(parent,path,this);
		viewer.initiaViewer();
	}

	@Override
	public void setFocus() {

	}
}