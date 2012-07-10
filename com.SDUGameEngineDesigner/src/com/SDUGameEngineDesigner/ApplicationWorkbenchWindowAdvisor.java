package com.SDUGameEngineDesigner;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.util.PrefUtil;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.Designer.LogoutInquiryDialog;

/**
 * Designer������
 * ϵͳ���̣��Ƴ�ѯ�ʵ�ʵ��
 * @author xzz
 *
 */
@SuppressWarnings("restriction")
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private IWorkbenchWindow window;
	private TrayItem trayItem;
	private Image image;//��Ӧ����Ϊlogo
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();

       // configurer.setInitialSize(new Point(400,300));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowFastViewBars(true);
        configurer.setShowMenuBar(true);
        configurer.setShowPerspectiveBar(true);
        configurer.setShowProgressIndicator(true);
        configurer.setTitle("SDUGameEngine-Designer"); //$NON-NLS-1$
        IPreferenceStore preStore = PrefUtil.getAPIPreferenceStore();
        preStore.setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
        preStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, IWorkbenchPreferenceConstants.TOP_RIGHT);
    }
    
    /**
     * �ƶ�ϵͳ����
     */
    public void postWindowOpen(){
    	super.preWindowOpen();
    	window = getWindowConfigurer().getWindow();
    	trayItem = initTrayItem();
    	if(trayItem!=null){
    		Minimize();
    		trayItem.addListener(SWT.MenuDetect, new Listener(){
				public void handleEvent(Event event) {
					MenuManager trayMenu = new MenuManager();
					Menu menu = trayMenu.createContextMenu(window.getShell());
					Action action = new Action(){
						public void run(){
						    Shell shell = window.getShell();
				    		shell.setVisible(true);
				    		shell.setMinimized(false);
						}
					};
					action.setText("��");
					action.setToolTipText("��������");
					trayMenu.add(action);
					trayMenu.add(ActionFactory.ABOUT.create(window));
					trayMenu.add(ActionFactory.QUIT.create(window));					
					menu.setVisible(true);
				} 			
    		});
    	}   	
    }
    
    /**
     * ��ʼ��ϵͳ����
     * @return TrayItem
     */
    private TrayItem initTrayItem(){
    	
    	Tray tray = window.getShell().getDisplay().getSystemTray();
    	TrayItem trayItem = new TrayItem(tray,SWT.NONE);
    	//image = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,"/icons/sample.gif").createImage();
    	image = WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EXPORT_WIZ).createImage();
    	trayItem.setImage(image);
    	trayItem.setToolTipText("SDUGameEngine-Designer");
    	return trayItem;
    }
    
    /**
     * ��С��
     */
    private void Minimize(){
    	final Shell shell = window.getShell();
    	shell.addShellListener(new ShellAdapter(){    		
			public void shellIconified(ShellEvent e) {
				// TODO Auto-generated method stub
				//shell.setMinimized(true);
    			shell.setVisible(false);
			}			
    	});
    	trayItem.addListener(SWT.DefaultSelection, new  Listener(){
			public void handleEvent(Event event) {
				if(!shell.isVisible()){
					shell.setVisible(true);
					shell.setMinimized(false);
				}
			}
    		
    	});
    }
    
    /**
     * ������Դ
     */
    public void dispose(){
    	if(image!=null){
    		image.dispose();
    		trayItem.dispose();
    	}
    }
    
    
    /**
     * �˳���ʾ
     */
    public boolean preWindowShellClose(){
    	if(!EnvironmentVariables.isAutoExit){//�����Զ��˳��������˳���ʾ�Ի���ѡ���Ƿ���С�������̣������´��Զ��˳�   		    	
        	LogoutInquiryDialog dialog = new LogoutInquiryDialog(new Shell());
        	dialog.open();
    		if(dialog.getReturnCode()==IDialogConstants.OK_ID){
    			if(dialog.isExit){ // �´��Զ��˳�  				
    			    EnvironmentVariables.variablesChange();
    			    return true;
    			}else{//��С��������
    				window.getShell().setMinimized(true);  				
    				return false;
    			}
    		}else{   			
   			    return false;
    		}   	    		
    	}
    	EnvironmentVariables.variablesChange();//�Զ��˳�ǰ����Designer�ı���
    	return true;//�Զ��˳�������true
    		
	}


    
}
