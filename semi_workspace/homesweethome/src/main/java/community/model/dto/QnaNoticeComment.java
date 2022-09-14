package community.model.dto;

import java.sql.Connection;
import java.sql.Date;

public class QnaNoticeComment{
	private int commentNo;
	private String memberId;
	private String nickName;
	private int noticeNo;
	private String content;
	private Date regDate;
	private int likeCnt;
	private int commentLevel;
	private int commentRef;
	public QnaNoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaNoticeComment(int commentNo, String memberId, String nickName, int noticeNo, String content, Date regDate,
			int likeCnt, int commentLevel, int commentRef) {
		super();
		this.commentNo = commentNo;
		this.memberId = memberId;
		this.nickName = nickName;
		this.noticeNo = noticeNo;
		this.content = content;
		this.regDate = regDate;
		this.likeCnt = likeCnt;
		this.commentLevel = commentLevel;
		this.commentRef = commentRef;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}
	@Override
	public String toString() {
		return "QnaNoticeComment [commentNo=" + commentNo + ", memberId=" + memberId + ", nickName=" + nickName
				+ ", noticeNo=" + noticeNo + ", content=" + content + ", regDate=" + regDate + ", likeCnt=" + likeCnt
				+ ", commentLevel=" + commentLevel + ", commentRef=" + commentRef + "]";
	}

}
