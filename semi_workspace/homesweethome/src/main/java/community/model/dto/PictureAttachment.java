package community.model.dto;

import java.sql.Date;

public class PictureAttachment {
private int no;
private int imgNo;
private String originalFilename;
private String renamedFilename;
private Date regDate;
public PictureAttachment() {
	super();
	// TODO Auto-generated constructor stub
}
public PictureAttachment(int no, int imgNo, String originalFilename, String renamedFilename, Date regDate) {
	super();
	this.no = no;
	this.imgNo = imgNo;
	this.originalFilename = originalFilename;
	this.renamedFilename = renamedFilename;
	this.regDate = regDate;
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public int getImgNo() {
	return imgNo;
}
public void setImgNo(int imgNo) {
	this.imgNo = imgNo;
}
public String getOriginalFilename() {
	return originalFilename;
}
public void setOriginalFilename(String originalFilename) {
	this.originalFilename = originalFilename;
}
public String getRenamedFilename() {
	return renamedFilename;
}
public void setRenamedFilename(String renamedFilename) {
	this.renamedFilename = renamedFilename;
}
public Date getRegDate() {
	return regDate;
}
public void setRegDate(Date regDate) {
	this.regDate = regDate;
}
@Override
public String toString() {
	return "CommunityImgAttachment [no=" + no + ", imgNo=" + imgNo + ", originalFilename=" + originalFilename
			+ ", renamedFilename=" + renamedFilename + ", regDate=" + regDate + "]";
}

}
