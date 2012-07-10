package com.SDUGameEngineDesigner;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.SDUGameEngineDesigner.EditAction.Control;
import com.SDUGameEngineDesigner.EditAction.Copy;
import com.SDUGameEngineDesigner.EditAction.Cut;
import com.SDUGameEngineDesigner.EditAction.Find;
import com.SDUGameEngineDesigner.EditAction.Paste;
import com.SDUGameEngineDesigner.EditAction.Redo;
import com.SDUGameEngineDesigner.EditAction.Undo;
import com.SDUGameEngineDesigner.FileAction.ExitDesigner;
import com.SDUGameEngineDesigner.FileAction.NewProject;
import com.SDUGameEngineDesigner.FileAction.OpenProject;
import com.SDUGameEngineDesigner.FileAction.SaveProject;
import com.SDUGameEngineDesigner.SettingAction.Animation;
import com.SDUGameEngineDesigner.SettingAction.Data;
import com.SDUGameEngineDesigner.SourceAction.Export;
import com.SDUGameEngineDesigner.SourceAction.Format;
import com.SDUGameEngineDesigner.SourceAction.Modify;
import com.SDUGameEngineDesigner.SourceAction.Run;
import com.SDUGameEngineDesigner.WindowAction.DesignerSetting;

/**
 * 添加Designer上工具栏，菜单栏的各种按钮
 * @author xzz
 *
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchWindow window;
	//各种按钮
	private NewProject newPro;
	private OpenProject openPro;
	private SaveProject savePro;
	private ExitDesigner exitDesigner;
	
	private Copy copy;
	private Paste paste;
	private Cut cut;
	private Find find;
	private Redo redo;
	private Undo undo;
	private Control control;
	
	private Modify modify;
	private Run run;
	private Format format;
	private Export export;
	
	private DesignerSetting setting;
	
	private Data data;
	private Animation animation;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	this.window = window;
    	newPro = new NewProject(window);
    	openPro = new OpenProject(window);
    	savePro = new SaveProject(window);
    	exitDesigner = new ExitDesigner(window);
    	
    	copy = new Copy(window);
    	paste = new Paste(window);
    	cut = new Cut(window);
    	find = new Find(window);
    	redo = new Redo(window);
    	undo = new Undo(window);
    	control = new Control(window);
    	
    	modify = new Modify(window);
    	run = new Run(window);
    	format = new Format(window);
    	export = new Export(window);
    	
    	setting = new DesignerSetting(window);
    	data = new Data(window);
    	animation = new Animation(window);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	//菜单栏上 的一级菜单
    	MenuManager fileMenu = new MenuManager("文件",IWorkbenchActionConstants.M_FILE);
    	MenuManager editMenu = new MenuManager("编辑",IWorkbenchActionConstants.EDIT_START);
    	MenuManager sourceMenu = new MenuManager("源代码","Source Code");
        MenuManager settingMenu = new MenuManager("设置","Setting");
        MenuManager windowMenu = new MenuManager("窗口","Window");
        MenuManager helpMenu = new MenuManager("帮助","Help");
        //二级菜单
    	MenuManager newMenu = new MenuManager("新建",IWorkbenchActionConstants.NEW_EXT);
    	
    	newMenu.add(newPro);
    	fileMenu.add(newMenu);
    	fileMenu.add(openPro);
    	fileMenu.add(new Separator());
    	fileMenu.add(savePro);
    	
    	fileMenu.add(new Separator());
    	fileMenu.add(exitDesigner);
    	menuBar.add(fileMenu);
    	
    	editMenu.add(copy);
    	editMenu.add(paste);
//    	editMenu.add(cut);
//    	
//    	editMenu.add(new Separator());
//    	editMenu.add(find);
//    	editMenu.add(redo);
//    	editMenu.add(undo);
//    	editMenu.add(control); 
    	
    	editMenu.add(ActionFactory.REDO.create(window));
    	editMenu.add(ActionFactory.UNDO.create(window));
    	editMenu.add(new Separator());
    	
    	editMenu.add(ActionFactory.COPY.create(window));
    	editMenu.add(ActionFactory.PASTE.create(window));
    	editMenu.add(ActionFactory.CUT.create(window));
    	editMenu.add(new Separator());
    	
    	editMenu.add(ActionFactory.DELETE.create(window));
    	editMenu.add(ActionFactory.RENAME.create(window));
    	editMenu.add(ActionFactory.FORWARD_HISTORY.create(window));
    	
    	menuBar.add(editMenu);
    	
    	sourceMenu.add(modify);
    	sourceMenu.add(run);
    	sourceMenu.add(format);
    	sourceMenu.add(export);
    	menuBar.add(sourceMenu);
    	
    	
    	settingMenu.add(data);
    	settingMenu.add(animation);
    	menuBar.add(settingMenu);
    	
    	windowMenu.add(setting);
    	windowMenu.add(ActionFactory.OPEN_NEW_WINDOW.create(window));
    	windowMenu.add(ActionFactory.OPEN_PERSPECTIVE_DIALOG.create(window));
    	windowMenu.add(ContributionItemFactory.VIEWS_SHORTLIST.create(window));
    	windowMenu.add(new Separator());
    	windowMenu.add(ActionFactory.MAXIMIZE.create(window));
    	windowMenu.add(ActionFactory.MINIMIZE.create(window));
    	windowMenu.add(ActionFactory.RESET_PERSPECTIVE.create(window));
    	windowMenu.add(new Separator());
    	windowMenu.add(ActionFactory.CLOSE_PERSPECTIVE.create(window));
    	windowMenu.add(ActionFactory.CLOSE_ALL_PERSPECTIVES.create(window));
    	
    	menuBar.add(windowMenu);
    	
    	helpMenu.add(ActionFactory.ABOUT.create(window));
    	helpMenu.add(ActionFactory.HELP_CONTENTS.create(window));
    	menuBar.add(helpMenu);
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar){
    	IToolBarManager toolBar = new ToolBarManager(coolBar.getStyle());
    	toolBar.add(newPro);
    	toolBar.add(savePro);
    
    	toolBar.add(find);
    	//toolBar.add(ActionFactory.FIND.create(window));
    	toolBar.add(new Separator());
    	toolBar.add(run);
    	toolBar.add(export);
    	coolBar.add(toolBar);
    }
    
    protected void fillStatusLine(IStatusLineManager statusLine){
    	super.fillStatusLine(statusLine);
        StatusLineContributionItem statusItem = new StatusLineContributionItem(" ");
    	statusLine.getProgressMonitor();
    	statusItem.setText("Status Message");
    	statusLine.add(statusItem);
    }
}
