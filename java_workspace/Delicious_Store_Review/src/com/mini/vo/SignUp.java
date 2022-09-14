package com.mini.vo;

import java.io.Serializable;

public class SignUp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ID;
	private String PassWord;
	private String Name;
	private String PhoneNum;

	public SignUp() {
		super();

	}
	
	public SignUp(String id, String password) {
		super();
		this.ID = id;
	}
	//생성자 생성
	public  SignUp(String id, String password, String name, String phoneNum) {
		super();
		this.ID = id;
		this.PassWord = password;
		this.Name = name;
		this.PhoneNum = phoneNum;
	}

	//getter , setter
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}
	
	@Override
	public String toString() {
		return "SignUp [ID=" + ID + ", PassWord=" + PassWord + ", Name=" + Name + ", PhoneNum=" + PhoneNum + "]";
	}
}


	

