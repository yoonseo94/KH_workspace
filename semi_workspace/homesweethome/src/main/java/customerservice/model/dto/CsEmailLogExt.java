package customerservice.model.dto;

public class CsEmailLogExt extends CsEmailLog{
	private CsEmailImage emailImage;

	public CsEmailImage getEmailImage() {
		return emailImage;
	}

	public void setEmailImage(CsEmailImage emailImage) {
		this.emailImage = emailImage;
	}

	@Override
	public String toString() {
		return "CsEmailLogExt [emailImage=" + emailImage + ", getCsEmailLogNo()=" + getCsEmailLogNo() + ", getEmail()="
				+ getEmail() + ", getSelectType()=" + getSelectType() + ", getName()=" + getName() + ", getTitle()="
				+ getTitle() + ", getContent()=" + getContent() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}