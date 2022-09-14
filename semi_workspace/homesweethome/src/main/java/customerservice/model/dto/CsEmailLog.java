package customerservice.model.dto;

public class CsEmailLog {
	private int csEmailLogNo;
	private String email;
	private String selectType;
	private String name;
	private String title;
	private String content;

	public CsEmailLog() {
		super();
	}

	public CsEmailLog(int csEmailLogNo, String email, String selectType, String name, String title, String content) {
		super();
		this.csEmailLogNo = csEmailLogNo;
		this.email = email;
		this.selectType = selectType;
		this.name = name;
		this.title = title;
		this.content = content;
	}

	public int getCsEmailLogNo() {
		return csEmailLogNo;
	}

	public void setCsEmailLogNo(int csEmailLogNo) {
		this.csEmailLogNo = csEmailLogNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "CsEmailLog [csEmailLogNo=" + csEmailLogNo + ", email=" + email + ", selectType=" + selectType
				+ ", name=" + name + ", title=" + title + ", content=" + content + "]";
	}	
}