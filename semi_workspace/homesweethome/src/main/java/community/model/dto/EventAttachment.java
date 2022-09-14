package community.model.dto;

import java.sql.Date;

public class EventAttachment  extends Event{
	private int no;
	private String eventId;
	private String original_filename;
	private String renamed_filename;
	private int eventNo;
	public EventAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventAttachment(int no, String eventId, String original_filename, String renamed_filename, int eventNo) {
		super();
		this.no = no;
		this.eventId = eventId;
		this.original_filename = original_filename;
		this.renamed_filename = renamed_filename;
		this.eventNo = eventNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getOriginal_filename() {
		return original_filename;
	}
	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}
	public String getRenamed_filename() {
		return renamed_filename;
	}
	public void setRenamed_filename(String renamed_filename) {
		this.renamed_filename = renamed_filename;
	}
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	@Override
	public String toString() {
		return "EventAttachment [no=" + no + ", eventId=" + eventId + ", original_filename=" + original_filename
				+ ", renamed_filename=" + renamed_filename + ", eventNo=" + eventNo + "]";
	}



	
}
