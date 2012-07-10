package com.SDUGameEngineDesigner.CodeEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.SDUGameEngineDesigner.Designer.EnvironmentVariables;

/**
 * 源代码编辑器的查看器
 * @author xzz
 */
public class CodeEditorViewer {

	/**
	 * 用于显示部件的Composite
	 */
	private Composite parent;
	
	/**
	 * 显示文本的文本框
	 */
	private static Text text;
	
	/**
	 * 要查看的java文件的路径
	 */
	private String path;
	
	private CodeEditor editor;
	/**
	 * 进行编辑的文件
	 */
	private File file;
	
	public CodeEditorViewer(Composite parent,String path,CodeEditor editor) {
		this.parent = parent;
		this.path = path;
		this.editor = editor;
		text = new Text(parent,SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		initializeText();
		addTextListener();
	}
	
	/**
	 * 初始化Text,读入相应的Java文件，在Text上显示
	 */
	private void initializeText(){
		file = new File(EnvironmentVariables.workspacePath+"\\"+path);
		try {
			BufferedReader reader = new  BufferedReader(new FileReader(file));
			String s = null;
		    while(true){
				if((s=reader.readLine())!=null)
					text.append(s+"\n");			
				else
					break;			
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 添加监听，调用类和方法处理事件
	 */
	private void addTextListener(){
		text.addVerifyListener(new VerifyListener(){

			@Override
			public void verifyText(VerifyEvent e) {
				// TODO Auto-generated method stub
				editor.dirty = true;	
			}
			
		});
	}
	
	public Text getText(){
		return text;
	}
	
	public File getFile(){
		return file;
	}
}
