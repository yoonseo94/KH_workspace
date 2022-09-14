package community.model.dto;

import java.sql.Date;
import java.util.List;

public class KnowhowExt extends Knowhow {
	private int attachCount;
	private int commentCount;
	private List<Attachment> attachments; 
	private List<KnowhowComment> comments;
	private List<LikeDTO> likes;
	public KnowhowExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KnowhowExt(int no, String memberId, int categoryNo, String content, int readCount, int likeCount,
			Date regDate, String nickName, String title, String coverPhoto, int attachCount, int commentCount,
			List<Attachment> attachments, List<KnowhowComment> comments, List<LikeDTO> likes) {
		super(no, memberId, categoryNo, content, readCount, likeCount, regDate, nickName, title, coverPhoto);
		this.attachCount = attachCount;
		this.commentCount = commentCount;
		this.attachments = attachments;
		this.comments = comments;
		this.likes = likes;
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
	public List<KnowhowComment> getComments() {
		return comments;
	}
	public void setComments(List<KnowhowComment> comments) {
		this.comments = comments;
	}
	public List<LikeDTO> getLikes() {
		return likes;
	}
	public void setLikes(List<LikeDTO> likes) {
		this.likes = likes;
	}
	@Override
	public String toString() {
		return "KnowhowExt [attachCount=" + attachCount + ", commentCount=" + commentCount + ", attachments="
				+ attachments + ", comments=" + comments + ", likes=" + likes + "]";
	}

}
