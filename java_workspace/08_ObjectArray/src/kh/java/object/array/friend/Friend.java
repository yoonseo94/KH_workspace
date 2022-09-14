package kh.java.object.array.friend;

public class Friend {

	private String name;
	
	public Friend() {}
	public Friend(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String getFriendInfo() {
		return "Friend[" + name + "]";
	}
}
