package com.SDUGameEngineDesigner.FileAction;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;
import com.SDUGameEngineDesigner.Designer.LogoutInquiryDialog;

/**
* �����˳���ť
* @author xzz
*/
public class ExitDesigner extends Action {

	private IWorkbenchWindow window;
	public ExitDesigner(IWorkbenchWindow window){
		this.window = window;
		this.setText("�˳�");
		this.setToolTipText("Exit");
	}
	
	public void run(){
		if(!EnvironmentVariables.isAutoExit){
			LogoutInquiryDialog dialog = new LogoutInquiryDialog(new Shell());
	    	dialog.open();
	    	if(dialog.getReturnCode()==IDialogConstants.OK_ID){
    			if(dialog.isExit){ // �´��Զ��˳�  				
    			    EnvironmentVariables.variablesChange();
    			}else{//��С��������
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
