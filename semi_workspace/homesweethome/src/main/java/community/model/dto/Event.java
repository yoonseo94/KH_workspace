package community.model.dto;

import java.sql.Date;

public class Event {
	private String eventId;
	private String eventTitle;
	private String eventContent;
	private Date sDate;
	private Date eDate;
	private Date regDate;
	private String titlefileName;
	private int no;
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(String eventId, String eventTitle, String eventContent, Date sDate, Date eDate, Date regDate,
			String titlefileName , int no) {
		super();
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.sDate = sDate;
		this.eDate = eDate;
		this.regDate = regDate;
		this.titlefileName = titlefileName;
		this.no=no;
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
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getTitlefileName() {
		return titlefileName;
	}
	public void setTitlefileName(String titlefileName) {
		this.titlefileName = titlefileName;
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventTitle=" + eventTitle + ", eventContent=" + eventContent
				+ ", sDate=" + sDate + ", eDate=" + eDate + ", regDate=" + regDate + ", titlefileName=" + titlefileName
				+ ", no=" + no + "]";
	}

	
	
	
	
}
