package util;

import java.awt.Point;
import java.io.Serializable;

import util.SEvent;
public class StaGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Point point;
	SEvent event;
	
	
	public StaGroup(Point point, SEvent event2) {
		super();
		this.point = point;
		this.event = event2;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public SEvent getEvent() {
		return event;
	}
	public void setEvent(SEvent event) {
		this.event = event;
	}
}
