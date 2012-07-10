package com.SDUGameEngineDesigner.CodeEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * Դ����༭��
 * @author xzz
 *
 */
public class CodeEditor extends EditorPart {

	/**
	 * Դ����༭���еĲ鿴��
	 */
	private CodeEditorViewer viewer;
	
	/**
	 *Դ����༭�������� 
	 */
	private CodeEditorInput input;
	
	/**
	 * �Ƿ���Ҫ����
	 */
	public  boolean dirty = false;
	
	/**
	 * ����༭��������
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
	 * ���Ϊ
	 */
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	/**
	 * ��ʼ���༭��
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
	 * ���ϴα���󣬷����Ƿ��ڱ��޸�
	 */
	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return dirty;
	}

	/**
	 * �Ƿ�֧�����Ϊ
	 */
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ��༭����Ӵ��ڲ���
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
