package community.model.dto;

import java.sql.Date;
import java.util.List;

public class QnaNotice {
	private int no;
	private String memberId;
	private String nickName;
	private String title;
	private String content;
	private int readCnt;
	private Date regDate;
	public QnaNotice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaNotice(int no, String memberId, String nickName, String title, String content, int readCnt,
			Date regDate) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.nickName = nickName;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.regDate = regDate;
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
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "QnaNotice [no=" + no + ", memberId=" + memberId + ", nickName=" + nickName + ", title=" + title
				+ ", content=" + content + ", readCnt=" + readCnt + ", regDate=" + regDate + "]";
	}

}
