
package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import level.sta.LevelBase;
import map.MapBase;
import scene.SceneBase;

/**
 * 此类定义了每一种base对象的存储路径，即sdua的路径
 *@author Joycery & Sww
 *
 */
public class BaseToDir {
	
	private static final Map<Class<?>,File> classToDir=new HashMap<Class<?>,File>();
	
	public static Map<Class<?>,File> getClassToDir(){
		if (classToDir.isEmpty()){
			load();
		}
		return classToDir;
	}

	/**
	 * 每添加一个base类必须在本方法中添加一个add方法
	 */
	private static void load() {
		add(LevelBase.class,"level\\sta");
		add(MapBase.class,"map");
		add(SceneBase.class,"scene");
	}

	/**
	 * @param class1
	 * @param string
	 */
	private static void add(Class<?> class1, String string) {
		File file=new File("sdua\\"+string);
		classToDir.put(class1,file);
	}
}
