package community.model.dto;

import java.sql.Date;

public class QnaBoardComment {
	
	private int no; //댓글번호
	private int boardNo;
	private String memberId;
	private String nickName;
	private String content;
	private int likeCnt;
	private Date regDate;
	
	private int commentLevel;
	private int commentRef;


	public QnaBoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public QnaBoardComment(int no, int boardNo, String memberId, String nickName, String content, int likeCnt,
			Date regDate, int commentLevel, int commentRef) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.nickName = nickName;
		this.content = content;
		this.likeCnt = likeCnt;
		this.regDate = regDate;
		this.commentLevel = commentLevel;
		this.commentRef = commentRef;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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


	public int getLikeCnt() {
		return likeCnt;
	}


	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
		return "QnaBoardComment [no=" + no + ", boardNo=" + boardNo + ", memberId=" + memberId + ", nickName="
				+ nickName + ", content=" + content + ", likeCnt=" + likeCnt + ", regDate=" + regDate
				+ ", commentLevel=" + commentLevel + ", commentRef=" + commentRef + "]";
	}




	
}
