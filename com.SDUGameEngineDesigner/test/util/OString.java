package util;

public class OString {

	String classname;
	String objectname;
	
	public OString(String classname, String objectname) {
		super();
		this.classname = classname;
		this.objectname = objectname;
	}

	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getObjectname() {
		return objectname;
	}
	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}
	
	
}
