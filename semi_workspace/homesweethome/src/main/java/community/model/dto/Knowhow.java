package community.model.dto;

import java.sql.Date;

public class Knowhow {
	private int no;
	private String memberId;
	private int categoryNo;
	private String content;
	private int readCount;
	private int likeCount;
	private Date regDate;
	private String nickName; 
	private String title;
	private String coverPhoto;
	
	public Knowhow(int no, String memberId, int categoryNo, String content, int readCount, int likeCount, Date regDate,
			String nickName, String title, String coverPhoto) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.categoryNo = categoryNo;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.regDate = regDate;
		this.nickName = nickName;
		this.title = title;
		this.coverPhoto = coverPhoto;
	}
	public Knowhow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCoverPhoto() {
		return coverPhoto;
	}
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
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
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Knowhow [no=" + no + ", memberId=" + memberId + ", categoryNo=" + categoryNo + ", content=" + content
				+ ", readCount=" + readCount + ", likeCount=" + likeCount + ", regDate=" + regDate + ", nickName="
				+ nickName + ", title=" + title + ", coverPhoto=" + coverPhoto + "]";
	}

}
