package community.model.dto;

import java.util.List;

public class EventAppExt extends EventApplicants{
	private int attachCount;
	private List<EventAppAtt> attachments;

	public EventAppExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventAppExt(int attachCount, List<EventAppAtt> attachments) {
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
	public List<EventAppAtt> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<EventAppAtt> attachments) {
		this.attachments = attachments;
	}
	@Override
	public String toString() {
		return "EventAppExt [attachCount=" + attachCount + ", attachments=" + attachments + "]";
	}
}
