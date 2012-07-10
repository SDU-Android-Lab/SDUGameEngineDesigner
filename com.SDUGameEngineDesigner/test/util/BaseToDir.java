
package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import level.sta.LevelBase;
import map.MapBase;
import scene.SceneBase;

/**
 * ���ඨ����ÿһ��base����Ĵ洢·������sdua��·��
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
	 * ÿ���һ��base������ڱ����������һ��add����
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
