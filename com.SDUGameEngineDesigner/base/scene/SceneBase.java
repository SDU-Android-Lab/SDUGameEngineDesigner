package scene;

import java.util.LinkedList;
import java.util.List;

import util.Base;
import util.GroupCollision;
import util.StaGroup;

public class SceneBase extends Base{

	private static final long serialVersionUID = 1L;

	private String map;
	
	private List<String> dynResource=new LinkedList<String>();
	
	private List<GroupCollision> collision=new LinkedList<GroupCollision>();
	
	private List<StaGroup> mapEvent=new LinkedList<StaGroup>();
		
	private String hero;

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	public void removeDynResource(String resource){
		dynResource.remove(resource);
	}
	
	public void addDynResource(String resource){
		dynResource.add(resource);
	}
	
	public List<String> getDynResource() {
		return dynResource;
	}
	
	public void removeCollision(GroupCollision gc){
		collision.remove(gc);
	}

	public GroupCollision getCollisionGroup(int i)
	{
		return collision.get(i);
	}
	
	public void addCollision(GroupCollision gc){
		collision.add(gc);
	}
	
	public List<GroupCollision> getCollision() {
		return collision;
	}

	public void addMapEvent(StaGroup sg){
		mapEvent.add(sg);
	}
	
	public StaGroup getMapEventGroup(int i)
	{
		return mapEvent.get(i);
	}
	public void removeMapEvent(StaGroup sg){
		mapEvent.remove(sg);
	}
	
	public List<StaGroup> getMapEvent() {
		return mapEvent;
	}

	public void setDynResource(List<String> dynResource) {
		this.dynResource = dynResource;
	}

	public void setCollision(List<GroupCollision> collision) {
		this.collision = collision;
	}

	public void setMapEvent(List<StaGroup> mapEvent) {
		this.mapEvent = mapEvent;
	}
	
}
