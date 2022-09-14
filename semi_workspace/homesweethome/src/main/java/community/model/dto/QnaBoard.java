package community.model.dto;

import java.sql.Date;
import java.util.List;

public class QnaBoard {
	private int no;
	private String memberId;
	private String title;
	private String content;
	private int readCount;
	private Date regDate;
	private String nickName;

	public QnaBoard() {
		super();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public QnaBoard(int no, String memberId, String title, String content, int readCount, Date regDate,
			String nickName) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
		this.regDate = regDate;
		this.nickName = nickName;
	}

	public QnaBoard(int no, String memberId, String title, String content, int readCount, Date regDate) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "QnaBoard [no=" + no + ", memberId=" + memberId + ", title=" + title + ", content=" + content
				+ ", readCount=" + readCount + ", regDate=" + regDate + "]";
	}








}
