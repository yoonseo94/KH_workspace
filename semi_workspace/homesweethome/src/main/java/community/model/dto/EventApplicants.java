package community.model.dto;

public class EventApplicants{
	private String eventapplyCode;
	private int no;
	private String memberId;
	private String nickName;
	private String content;
	private int eventNo;
	public EventApplicants() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventApplicants(String eventapplyCode, int no, String memberId, String nickName, String content,
			int eventNo) {
		super();
		this.eventapplyCode = eventapplyCode;
		this.no = no;
		this.memberId = memberId;
		this.nickName = nickName;
		this.content = content;
		this.eventNo = eventNo;
	}
	public String getEventapplyCode() {
		return eventapplyCode;
	}
	public void setEventapplyCode(String eventapplyCode) {
		this.eventapplyCode = eventapplyCode;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	@Override
	public String toString() {
		return "EventApplicants [eventapplyCode=" + eventapplyCode + ", no=" + no + ", memberId=" + memberId
				+ ", nickName=" + nickName + ", content=" + content + ", eventNo=" + eventNo + "]";
	}

	
	
}
