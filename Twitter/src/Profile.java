
public class Profile {
	protected int id;
	protected String name;
	protected boolean isGroup;
	
	Profile (int newId, String newName, boolean isG) {
		id = newId;
		name = newName;
		isGroup = isG;
	}
	
	public int getId () {
		return id;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean isGroup() {
		return isGroup;
	}
}
