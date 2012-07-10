
package util;

import java.io.File;

/**
 * 将jet模板生成的字符串，格式化为类名，包名，存储路径，文件内容
 *  @author Joycery & Sww
 *
 */
public class Format {
	
	private String className;
	private String packageName;
	private String context;
	private File file;
	
	public Format(String orgin){
		int first=orgin.indexOf("#");
		int second=orgin.indexOf("#",first+1);
		
		packageName=orgin.substring(0,first);
		className=orgin.substring(first+1,second);
		context=orgin.substring(second+1);
		context="package "+packageName+";\n"+context;
		
		String str=packageName.replace('.', '/');
		str="../SDUGameEgine/test/"+str+"/"+className+".java";
		file=new File(str);
	}

	public String getClassName() {
		return className;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getContext() {
		return context;
	}

	public File getFile() {
		if (!file.exists())
			FileCreater.createFile(file);
		return file;
	}
}
