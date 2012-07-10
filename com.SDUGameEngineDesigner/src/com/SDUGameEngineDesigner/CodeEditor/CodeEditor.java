package com.SDUGameEngineDesigner.CodeEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * 源代码编辑器
 * @author xzz
 *
 */
public class CodeEditor extends EditorPart {

	/**
	 * 源代码编辑器中的查看器
	 */
	private CodeEditorViewer viewer;
	
	/**
	 *源代码编辑器的输入 
	 */
	private CodeEditorInput input;
	
	/**
	 * 是否需要保存
	 */
	public  boolean dirty = false;
	
	/**
	 * 保存编辑器的内容
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		CodeEditorSave save = new CodeEditorSave(viewer.getText(),viewer.getFile());
		save.saveText();
		dirty = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	/**
	 * 另存为
	 */
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化编辑器
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub

		this.input = (CodeEditorInput) input;
		this.setSite(site);
		this.setInput(input);
		this.setPartName(input.getName());
		
	}
       
	/**
	 * 在上次保存后，返回是否在被修改
	 */
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return dirty;
	}

	/**
	 * 是否支持另存为
	 */
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 向编辑器添加窗口部件
	 */
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		String path = input.getPackageExplorerElement().getPath();
	    viewer = new CodeEditorViewer(parent,path,this);	
	}

	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
	
}
