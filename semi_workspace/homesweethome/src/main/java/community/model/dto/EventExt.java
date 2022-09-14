package community.model.dto;

import java.sql.Date;
import java.util.List;
import community.model.dto.EventAttachment;

public class EventExt extends Event{
	private int attachCount;
	private List<EventAttachment> attachments;
	public EventExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventExt(int attachCount, List<EventAttachment> attachments) {
		super();
		this.attachCount = attachCount;
		this.attachments = attachments;
	}
	public int getAttachCount() {
		return attachCount;
	}
	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	public List<EventAttachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<EventAttachment> attachments) {
		this.attachments = attachments;
	}
	@Override
	public String toString() {
		return "EventExt [attachCount=" + attachCount + ", attachments=" + attachments + "]";
	}


	

	
}
