package com.SDUGameEngineDesigner;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.Designer.LoginInquiryDialog;

/**
 * This class controls all aspects of the application's execution
 * @author xzz
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception { 
		if(!EnvironmentVariables.variablesInitialize()) //判断是否初始化成功
			return IApplication.EXIT_OK;
		if(!EnvironmentVariables.hasDefaultWorkspace) {//判断是否有默认工作空间
			LoginInquiryDialog dialog = new LoginInquiryDialog(new Shell());
			dialog.open();
			if(!(dialog.getReturnCode()==IDialogConstants.OK_ID)) {
				return IApplication.EXIT_OK;			
			}
		}			
		Display display = PlatformUI.createDisplay();
		try {			
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
