/**
 * 
 */
package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 能保证对任意一个GeName对象不会取相同的名字，且同一个类的名字前缘相同，均是从下标为1开始
 * 并且存储了对每个类取过的名字。
 * @author Administrator
 *
 */
public class GetName implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final Map<String,List<String>> classToName=new HashMap<String,List<String>>();
	private final Map<String,String> classToHead=new HashMap<String,String>();
	
	/**
	 * 给一个类型的变量取名称
	 * @param className 类名
	 * @return 变量名字
	 */
	public String newName(String className){
		String re=getNameHead(className);
		List<String> list=classToName.get(className);
		if (list==null || list.isEmpty()){
			re+="_1";
			list=new ArrayList<String>();
			list.add(re);
			classToName.put(className, list);
		}else{
			int size=list.size();
			re+="_"+(size+1);
			list.add(re);
		}
		return re;
	}
	
	/**
	 * 取到对应一个类的名字
	 * @param className 类名
	 * @return 已经取到的名字
	 */
	public String getFirstName(String className){
		return getNameList(className).get(0);
	}
	
	/**
	 * 取到对应一个类的名字列表
	 * @param className 类名
	 * @return 已经取到的名字列表
	 */
	public List<String> getNameList(String className){
		return classToName.get(className);
	}
	
	/**
	 *取到对应一个类的指定位置名字 
	 * @param className 类名
	 * @param index 位置从0开始
	 * @return 已经取到的名字
	 */
	public String getNameAt(String className,int index){
		return getNameList(className).get(index);
	}
	
	private String getNameHead(String className){
		String re=classToHead.get(className);
		if (re!=null)
			return re;
		
		re=className;
		int index=className.lastIndexOf('.');
		if (index>-1)
			re=re.substring(index+1);
		char first=re.charAt(0);
		if (first>='A' && first<='Z'){
			first=(char) (first+'a'-'A');
		}
		re=first+re.substring(1);

		while (classToHead.containsValue(re)){
			re="s"+re;
		}
		classToHead.put(className, re);
		
		return re;
	}
	
	public static void main(String[] args){
		GetName name=new GetName();
		System.out.println(name.newName("java.util.ArrayList"));
		System.out.println(name.newName("java.util.ArrayList"));
		System.out.println(name.newName("java.util.ArrayList"));

		System.out.println(name.newName("util.ArrayList"));
		System.out.println(name.newName("util.ArrayList"));

		
		
		System.out.println(name.getNameList("java.util.ArrayList"));
		
		System.out.println(name.getFirstName("java.util.ArrayList"));
		
		System.out.println(name.getNameAt("java.util.ArrayList",2));

	}
}
