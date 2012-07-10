
package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import level.sta.StaLevelGen;
import map.MapGen;
import scene.SceneGen;

/**
 * 此类定义sdua存储路径到到使用哪个jet模板来解析它的映射，每添加一个jet模板必须在本类中添加一条映射，
 * 以便运行WriteCode类能生成所有的目标代码。
 *@author Joycery & Sww
 *
 */
public class DirToJet {
	
	private static final Map<File,IGenerator> pathToGen=new HashMap<File,IGenerator>();
	
	public static Map<File,IGenerator> getPathToGen(){
		if (pathToGen.isEmpty()){
			load();
		}
		return pathToGen;
	}

	/**
	 * 每添加一个jet模板必须在本方法中添加一个add方法
	 * 注意不同的jet模板不要有相同的路径名
	 */
	private static void load() {
		add("level\\sta",new StaLevelGen());
		add("map",new MapGen());
		add("scene",new SceneGen());
	}

	private static void add(String string, IGenerator gen) {
		File file=new File("sdua\\"+string);
		pathToGen.put(file, gen);
	}
}
