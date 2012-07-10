/**
 * 
 */
package util;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public abstract class Base implements Serializable {

	private static final long serialVersionUID = -1282954935166900250L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
