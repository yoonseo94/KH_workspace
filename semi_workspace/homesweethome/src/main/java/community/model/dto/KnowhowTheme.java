package community.model.dto;

public class KnowhowTheme extends Knowhow {
	private int themeNo;
	private String themeString;
	public KnowhowTheme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KnowhowTheme(int themeNo, String themeString) {
		super();
		this.themeNo = themeNo;
		this.themeString = themeString;
	}
	public int getThemeNo() {
		return themeNo;
	}
	public void setThemeNo(int themeNo) {
		this.themeNo = themeNo;
	}
	public String getThemeString() {
		return themeString;
	}
	public void setThemeString(String themeString) {
		this.themeString = themeString;
	}
	@Override
	public String toString() {
		return "KnowhowTheme [themeNo=" + themeNo + ", themeString=" + themeString + "]";
	}
	
	
}
