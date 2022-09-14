package community.model.dto;

public class EventAppAtt {
	private int no;
	private String originalFilename;
	private String renamedFilename;
	private int eventNo;
	public EventAppAtt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventAppAtt(int no, String originalFilename, String renamedFilename, int eventNo) {
		super();
		this.no = no;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.eventNo = eventNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	@Override
	public String toString() {
		return "EventAppAtt [no=" + no + ", originalFilename=" + originalFilename + ", renamedFilename="
				+ renamedFilename + ", eventNo=" + eventNo + "]";
	}
	
}
