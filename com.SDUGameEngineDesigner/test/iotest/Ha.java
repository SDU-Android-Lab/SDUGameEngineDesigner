package iotest;

import map.MapBase;

public class Ha {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapBase mb=new MapBase();
		mb.setName("Map1");
		mb.setMap(new int[][]{
				{0,3},
				{1,2}
		});
		mb.setTileInt(30, 30);
		
		
		WriteSdua.writeBase(mb);
	}

}
