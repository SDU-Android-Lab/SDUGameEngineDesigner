package level.sta;

import java.util.LinkedList;
import java.util.List;

import util.Base;

public class LevelBase extends Base{

	private static final long serialVersionUID = 1L;
		
	final List<String> list=new LinkedList<String>();

	public List<String> getList() {
		return list;
	}

	public void add(String str){
		list.add(str);
	}
}
