package community.model.dto;

import java.sql.Date;

public class KnowhowComment {
	private int no;
	private int commentLevel;
	private int commentRef;
	private int knowhowNo;
	private String memberId;
	private String nickName;
	private String content;
	private int likeCount;
	private Date regDate;
	
	public KnowhowComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KnowhowComment(int no, int commentLevel, int commentRef, int knowhowNo, String memberId, String nickName,
			String content, int likeCount, Date regDate) {
		super();
		this.no = no;
		this.commentLevel = commentLevel;
		this.commentRef = commentRef;
		this.knowhowNo = knowhowNo;
		this.memberId = memberId;
		this.nickName = nickName;
		this.content = content;
		this.likeCount = likeCount;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public int getKnowhowNo() {
		return knowhowNo;
	}

	public void setKnowhowNo(int knowhowNo) {
		this.knowhowNo = knowhowNo;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "KnowhowComment [no=" + no + ", commentLevel=" + commentLevel + ", commentRef=" + commentRef
				+ ", knowhowNo=" + knowhowNo + ", memberId=" + memberId + ", nickName=" + nickName + ", content="
				+ content + ", likeCount=" + likeCount + ", regDate=" + regDate + "]";
	}
	
	
	
	
}
