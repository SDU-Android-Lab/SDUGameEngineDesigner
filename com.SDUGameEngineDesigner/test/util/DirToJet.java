
package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import level.sta.StaLevelGen;
import map.MapGen;
import scene.SceneGen;

/**
 * ���ඨ��sdua�洢·������ʹ���ĸ�jetģ������������ӳ�䣬ÿ���һ��jetģ������ڱ��������һ��ӳ�䣬
 * �Ա�����WriteCode�����������е�Ŀ����롣
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
	 * ÿ���һ��jetģ������ڱ����������һ��add����
	 * ע�ⲻͬ��jetģ�岻Ҫ����ͬ��·����
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
