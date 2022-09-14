package community.model.dto;

import java.sql.Date;

public class Picture {
	private int imgNo;
	private String memberId;
	private String nickName;
	private String title;
	private String content;
	private int categorySpace;
	private int categoryShape;
	private String coverPhoto;
	private int readCount;
	private int likeCount;
	private Date regDate;
	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Picture(int imgNo, String memberId, String nickName, String title, String content, int categorySpace,
			int categoryShape, String coverPhoto, int readCount, int likeCount, Date regDate) {
		super();
		this.imgNo = imgNo;
		this.memberId = memberId;
		this.nickName = nickName;
		this.title = title;
		this.content = content;
		this.categorySpace = categorySpace;
		this.categoryShape = categoryShape;
		this.coverPhoto = coverPhoto;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.regDate = regDate;
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategorySpace() {
		return categorySpace;
	}
	public void setCategorySpace(int categorySpace) {
		this.categorySpace = categorySpace;
	}
	public int getCategoryShape() {
		return categoryShape;
	}
	public void setCategoryShape(int categoryShape) {
		this.categoryShape = categoryShape;
	}
	public String getCoverPhoto() {
		return coverPhoto;
	}
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
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
	@Override
	public String toString() {
		return "CommunityImage [imgNo=" + imgNo + ", memberId=" + memberId + ", nickName=" + nickName + ", title="
				+ title + ", content=" + content + ", categorySpace=" + categorySpace + ", categoryShape="
				+ categoryShape + ", coverPhoto=" + coverPhoto + ", readCount=" + readCount + ", likeCount=" + likeCount
				+ ", regDate=" + regDate + "]";
	}
	
}
