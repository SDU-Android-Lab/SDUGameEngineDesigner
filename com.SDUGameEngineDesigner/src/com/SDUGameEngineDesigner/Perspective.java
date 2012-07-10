package com.SDUGameEngineDesigner;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * 在透视图中添加视图
 * @author xzz
 *
 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
	
		String editorArea = layout.getEditorArea();
		layout.addView("com.SDUGameEngineDesigner.view1", IPageLayout.LEFT, 0.25f, editorArea);
		layout.addView("com.SDUGameEngineDesigner.view2", IPageLayout.BOTTOM, 0.75f, editorArea);
	}
}
