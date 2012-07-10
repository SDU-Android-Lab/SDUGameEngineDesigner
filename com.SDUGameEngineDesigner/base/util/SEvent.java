package util;

import java.io.Serializable;

public class SEvent implements Serializable {

	private Type type;
	
	
	
	/**
	 * 
	 */
	public SEvent() {
		super();
	}

	/**
	 * @param type
	 */
	public SEvent(Type type) {
		super();
		this.type = type;
	}

	public enum Type{
		BUY_AND_SAIL,
		FEIGHT,
		DIALOG,
		SWITCH,
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SEvent [type=" + type + "]";
	}
	
	
}
