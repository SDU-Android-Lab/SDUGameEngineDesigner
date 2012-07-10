/**
 * 
 */
package util;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class GroupCollision implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String sprite1;
	private String sprite2;
	private SEvent event;
	
	public GroupCollision(String sprite1, String sprite2, SEvent event) {
		super();
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
		this.event = event;
	}
	
	public String getSprite1() {
		return sprite1;
	}
	public void setSprite1(String sprite1) {
		this.sprite1 = sprite1;
	}
	public String getSprite2() {
		return sprite2;
	}
	public void setSprite2(String sprite2) {
		this.sprite2 = sprite2;
	}
	public SEvent getSEvent() {
		return event;
	}
	
	public void setSEvent(SEvent event) {
		this.event = event;
	}
	
	
	
}
