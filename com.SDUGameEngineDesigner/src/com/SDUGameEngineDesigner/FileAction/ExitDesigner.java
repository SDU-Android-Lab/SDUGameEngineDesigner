package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.Designer.LogoutInquiryDialog;

/**
* 创建退出按钮
* @author xzz
*/
public class ExitDesigner extends Action {

	private IWorkbenchWindow window;
	public ExitDesigner(IWorkbenchWindow window){
		this.window = window;
		this.setText("退出");
		this.setToolTipText("Exit");
	}
	
	public void run(){
		if(!EnvironmentVariables.isAutoExit){
			LogoutInquiryDialog dialog = new LogoutInquiryDialog(new Shell());
	    	dialog.open();
	    	if(dialog.getReturnCode()==IDialogConstants.OK_ID){
    			if(dialog.isExit){ // 下次自动退出  				
    			    EnvironmentVariables.variablesChange();
    			}else{//最小化到托盘
    				window.getShell().setMinimized(true);  				
    				return ; 
    			} 
    		}else{   			
   			    return ;
    		}   		
		}
		System.exit(0);
	}
}
