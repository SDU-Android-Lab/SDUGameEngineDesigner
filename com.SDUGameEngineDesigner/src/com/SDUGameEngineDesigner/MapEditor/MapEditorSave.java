package com.SDUGameEngineDesigner.MapEditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 本类可载入和储存地图上的所有信息
 * @author xzz
 *
 */
public class MapEditorSave {
	
	private String path;
	protected String bgImage;
	protected String resImage;
	protected String bgMusic;
	public MapEditorSave(String path){
		this.path = path;
	}
	
	public void saveMap(){
		File file = new File(path);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(bgImage);
			writer.newLine();
			writer.write(resImage);
			writer.newLine();
			writer.write(bgMusic);
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}

	public void loadMapInfor(){
		File file = new File(path);
		try {
			BufferedReader read = new BufferedReader(new FileReader(file));
			bgImage = read.readLine();
			resImage = read.readLine();
			bgMusic = read.readLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
