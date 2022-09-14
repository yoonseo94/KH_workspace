package com.meshop.member.entity;

import java.sql.Date;
import java.util.List;


public class Member {
	private String memberId;
	private String password;
	private String memberName;

	private String storeName;
	private int storeGrade;
	private Date joinDate;
	private String place;
	private MemberRole memberRole;
	private int BoardCount;

	public int getBoardCount() {
		return BoardCount;
	}

	public void setBoardCount(int boardCount) {
		BoardCount = boardCount;
	}

	private List<Integer> wish;

	
	public Member() {

	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", storeName="
				+ storeName + ", storeGrade=" + storeGrade + ", joinDate=" + joinDate + ", place=" + place
				+ ", memberRole=" + memberRole + "]";
	}

	public Member(String memberId, String password, String memberName, String storeName, int storeGrade, Date joinDate,
			String place, MemberRole memberRole) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.storeName = storeName;
		this.storeGrade = storeGrade;
		this.joinDate = joinDate;
		this.place = place;
		this.memberRole = memberRole;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getStoreGrade() {
		return storeGrade;
	}

	public void setStoreGrade(int storeGrade) {
		this.storeGrade = storeGrade;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public List<Integer> getWish() {
		return wish;
	}

	public void setWish(List<Integer> wish) {
		this.wish = wish;
	}
	
}

