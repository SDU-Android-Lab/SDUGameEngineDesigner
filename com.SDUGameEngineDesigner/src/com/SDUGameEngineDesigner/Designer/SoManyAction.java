package com.SDUGameEngineDesigner.Designer;

import com.SDUGameEngineDesigner.FileAction.Delete;
import com.SDUGameEngineDesigner.FileAction.NewProject;
import com.SDUGameEngineDesigner.FileAction.Rename;

/**
 * 这个类提供了Designer上面的各种按钮（action）
 * @author xzz
 *
 */
public class SoManyAction {

	/**
	 *新建工程按钮 
	 */
	public static NewProject newProject = new NewProject();
	
	/**
	 * 删除按钮
	 */
	public static Delete delete = new Delete();
	
	/**
	 * 重命名
	 */
	public static Rename rename = new Rename();
   
   
}
