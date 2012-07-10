package com.SDUGameEngineDesigner.CodeEditor;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.widgets.Text;

/**
 * ����Դ�����ļ�
 * @author xzz
 *
 */
public class CodeEditorSave {

	/**
	 * Ҫ������ı�
	 */
	private Text text;
	
	/**
	 * ���б༭���ļ�
	 */
	private File file;
	
	public CodeEditorSave (Text text,File file){
		this.text = text;
		this.file = file;
	}
	
	/**
	 * �����ļ�
	 */
	public void saveText(){
		
		String s = text.getText();
        try {
        	DataOutputStream stream = new DataOutputStream (new FileOutputStream(file));
        	stream.writeUTF(s);
        	stream.flush();
        	stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
