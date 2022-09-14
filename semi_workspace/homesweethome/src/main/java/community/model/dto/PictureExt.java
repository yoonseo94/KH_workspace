package community.model.dto;

import java.sql.Date;
import java.util.List;

public class PictureExt extends Picture {
	private int attachCount;
	 private int likeCount; 
	private List<PictureAttachment> attachments;
	private List<LikeDTO> likes;
	public PictureExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PictureExt(int attachCount, int likeCount, List<PictureAttachment> attachments, List<LikeDTO> likes) {
		super();
		this.attachCount = attachCount;
		this.likeCount = likeCount;
		this.attachments = attachments;
		this.likes = likes;
	}
	public int getAttachCount() {
		return attachCount;
	}
	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public List<PictureAttachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<PictureAttachment> attachments) {
		this.attachments = attachments;
	}
	public List<LikeDTO> getLikes() {
		return likes;
	}
	public void setLikes(List<LikeDTO> likes) {
		this.likes = likes;
	}
	@Override
	public String toString() {
		return "PictureExt [attachCount=" + attachCount + ", likeCount=" + likeCount + ", attachments=" + attachments
				+ ", likes=" + likes + "]";
	}
	
	
	
	

	

}
