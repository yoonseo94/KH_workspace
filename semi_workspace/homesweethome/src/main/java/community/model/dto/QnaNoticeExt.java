package community.model.dto;

import java.util.List;

public class QnaNoticeExt extends QnaNotice {
	private int attachCount;
	private int commentCount;
	private List<Attachment> attachments; 
	private List<QnaNoticeComment> comments;
	public QnaNoticeExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaNoticeExt(int attachCount, int commentCount, List<Attachment> attachments,
			List<QnaNoticeComment> comments) {
		super();
		this.attachCount = attachCount;
		this.commentCount = commentCount;
		this.attachments = attachments;
		this.comments = comments;
	}
	public int getAttachCount() {
		return attachCount;
	}
	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}

	
	
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public  QnaNoticeExt(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public List<QnaNoticeComment> getComments() {
		return comments;
	}
	public void setComments(List<QnaNoticeComment> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "QnaNoticeBoardExt [attachCount=" + attachCount + ", commentCount=" + commentCount + ", attachments="
				+ attachments + ", comments=" + comments + "]";
	}


	

}
