package customerservice.model.dto;

import java.sql.Date;

public class CsEmailImage {
	private int no;
	private int csEmailLogNo;
	private String originalFilename;
	private String renamedFilename;
	private Date regDate;

	public CsEmailImage() {
		super();
	}

	public CsEmailImage(int no, int csEmailLogNo, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.no = no;
		this.csEmailLogNo = csEmailLogNo;
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

	public int getCsEmailLogNo() {
		return csEmailLogNo;
	}

	public void setCsEmailLogNo(int csEmailLogNo) {
		this.csEmailLogNo = csEmailLogNo;
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
		return "CsEmailImage [no=" + no + ", csEmailLogNo=" + csEmailLogNo + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", regDate=" + regDate + "]";
	}
}