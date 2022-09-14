package member.model.dto;

public class MemberExt extends Member {
	private MemberImage memberImage;

	public MemberImage getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(MemberImage memberImage) {
		this.memberImage = memberImage;
	}

	@Override
	public String toString() {
		return "MemberExt [memberImage=" + memberImage + ", getMemberId()=" + getMemberId() + ", getPassword()="
				+ getPassword() + ", getMemberName()=" + getMemberName() + ", getNickname()=" + getNickname()
				+ ", getMemberRole()=" + getMemberRole() + ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail()
				+ ", getBirthday()=" + getBirthday() + ", getGender()=" + getGender() + ", getSocialType()="
				+ getSocialType() + ", getEnrollDate()=" + getEnrollDate() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}


	
	
}
