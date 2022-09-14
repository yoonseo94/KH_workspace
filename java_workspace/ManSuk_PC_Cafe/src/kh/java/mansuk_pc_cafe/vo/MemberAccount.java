package kh.java.mansuk_pc_cafe.vo;

import java.io.Serializable;
import java.util.Objects;


/**
 * 회원 VO 클래스
 * 
 * @author 수진
 */
public class MemberAccount implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private int memberNo;		// 회원번호
	
	private String name;		// 이름
	private int age;			// 나이
	private char gender;		// 성별

	private String id;			// 아이디
	private String pwd;			// 비밀번호
	private String pwd2;		// 비밀번호 확인(회원가입용)
	
	private String phone;		// 전화번호
	private String email;		// 이메일
	
	private int seatNum;		// 좌석번호
	private boolean state;		// 이용상태
	private int remnants;		// 잔여시간
	
	private boolean memberYN;	// 회원/비회원 구분
	
	// 기본 생성자
	public MemberAccount() {
		super();
	}
	
	// 비회원 생성자
	public MemberAccount(String phone) {
		super();
		this.memberNo = 0;
		this.id = "guest_user";
		this.pwd = "1111";
		this.phone = phone;
		this.seatNum = -1;
		this.state = false;
		this.remnants = 0;
		this.memberYN = false;
	}
	
	// 관리자 생성자
	public MemberAccount(String name, String id, String pwd) {
		this.memberNo = 999;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
	}
	
	// 회원 생성자
	public MemberAccount(String name, int age, char gender, String id,
			String pwd, String phone, String email) {
		this(name, id, pwd);
		this.memberNo = 0;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.seatNum = -1;
		this.state = false;
		this.remnants = 0;
		this.memberYN = true;
	}
	
	// 회원 생성자(회원가입용)
	public MemberAccount(String name, int age, char gender, String id,
			String pwd, String pwd2, String phone, String email) {
		this(name, age, gender, id, pwd, phone, email);
		this.memberNo = 0;
		this.pwd2 = pwd2;
		this.seatNum = -1;
		this.state = false;
		this.remnants = 0;
		this.memberYN = true;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getRemnants() {
		return remnants;
	}

	public void setRemnants(int remnants) {
		this.remnants = remnants;
	}

	public boolean isMemberYN() {
		return memberYN;
	}

	public void setMemberYN(boolean memberYN) {
		this.memberYN = memberYN;
	}

	@Override
	public String toString() {
		return "계정정보 [회원번호=" + memberNo + ", 이름=" + name + ", 나이=" + age + ", 성별=" + gender
				+ ", 아이디=" + id + ", 전화번호=" + phone + ", 이메일=" + email
				+ ", 접속상태=" + state + ", 잔여시간=" + remnants + ", 회원/비회원=" + (memberYN ? "회원]" : "비회원]");
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, email, gender, id, memberNo, memberYN, name, phone, pwd, pwd2, remnants, seatNum,
				state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberAccount other = (MemberAccount) obj;
		return age == other.age && Objects.equals(email, other.email) && gender == other.gender
				&& Objects.equals(id, other.id) && memberNo == other.memberNo && memberYN == other.memberYN
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(pwd, other.pwd) && Objects.equals(pwd2, other.pwd2) && remnants == other.remnants
				&& seatNum == other.seatNum && state == other.state;
	}
	
}