package customerservice.model.dto;

public class CsEmailType {
	private String selectType;
	private String selectTypeName;

	public CsEmailType() {
		super();
	}

	public CsEmailType(String selectType, String selectTypeName) {
		super();
		this.selectType = selectType;
		this.selectTypeName = selectTypeName;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getSelectTypeName() {
		return selectTypeName;
	}

	public void setSelectTypeName(String selectTypeName) {
		this.selectTypeName = selectTypeName;
	}

	@Override
	public String toString() {
		return "CsEmailType [selectType=" + selectType + ", selectTypeName=" + selectTypeName + "]";
	}
}
