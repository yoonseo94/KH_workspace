package community.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class QnaCommentLike  implements Serializable{
	private String memberId;
	private int commentNo;
	private String like;
	public QnaCommentLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaCommentLike(String memberId, int commentNo, String like) {
		super();
		this.memberId = memberId;
		this.commentNo = commentNo;
		this.like = like;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "QnaCommentLike [memberId=" + memberId + ", commentNo=" + commentNo + ", like=" + like + "]";
	}


	
	
	}

