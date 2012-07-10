package map;

import util.Base;

public class MapBase extends Base {

	private static final long serialVersionUID = 1L;

	private int tileWidth, tileHeight;

	private int[][] map;

	private String StaDownLevel, StaUpLevel;
	
	private boolean autoSearch;
	

	public boolean isAutoSearch() {
		return autoSearch;
	}

	public void setAutoSearch(boolean autoSearch) {
		this.autoSearch = autoSearch;
	}

	public boolean hasDownLevel() {
		if (StaDownLevel != null)
			return true;
		else
			return false;
	}

	public boolean hasUpLevel() {
		if (StaUpLevel != null)
			return true;
		else
			return false;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	public void setTileInt(int height, int width) {
		this.tileHeight = height;
		this.tileWidth = width;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public String getStaDownLevel() {
		return StaDownLevel;
	}

	public void setStaDownLevel(String staDownLevel) {
		StaDownLevel = staDownLevel;
	}

	public String getStaUpLevel() {
		return StaUpLevel;
	}

	public void setStaUpLevel(String staUpLevel) {
		StaUpLevel = staUpLevel;
	}

}
