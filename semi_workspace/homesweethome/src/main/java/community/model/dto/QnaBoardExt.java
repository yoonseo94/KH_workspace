package community.model.dto;

import java.sql.Date;

import java.util.List;

import community.model.dto.Attachment;
import community.model.dto.QnaBoardComment;

public class QnaBoardExt extends QnaBoard{
	private int attachCount;
	private int commentCount;
	private List<Attachment> attachments; 
	private List<QnaBoardComment> comments; 

	private String sort;
	
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	public List<QnaBoardComment> getBoardComments(){
		return comments;
	}

	public void setBoardComments(List<QnaBoardComment> comments) {
		this.comments = comments;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "BoardExt [attachCount=" + attachCount + ", attachments=" + attachments + ", comments=" + comments
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
