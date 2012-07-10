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
 * Դ����༭���Ĳ鿴��
 * @author xzz
 */
public class CodeEditorViewer {

	/**
	 * ������ʾ������Composite
	 */
	private Composite parent;
	
	/**
	 * ��ʾ�ı����ı���
	 */
	private static Text text;
	
	/**
	 * Ҫ�鿴��java�ļ���·��
	 */
	private String path;
	
	private CodeEditor editor;
	/**
	 * ���б༭���ļ�
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
	 * ��ʼ��Text,������Ӧ��Java�ļ�����Text����ʾ
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
	 * ��Ӽ�����������ͷ��������¼�
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
