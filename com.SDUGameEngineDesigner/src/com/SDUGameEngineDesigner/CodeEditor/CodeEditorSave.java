package com.SDUGameEngineDesigner.CodeEditor;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.widgets.Text;

/**
 * 保存源代码文件
 * @author xzz
 *
 */
public class CodeEditorSave {

	/**
	 * 要保存的文本
	 */
	private Text text;
	
	/**
	 * 进行编辑的文件
	 */
	private File file;
	
	public CodeEditorSave (Text text,File file){
		this.text = text;
		this.file = file;
	}
	
	/**
	 * 保存文件
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
