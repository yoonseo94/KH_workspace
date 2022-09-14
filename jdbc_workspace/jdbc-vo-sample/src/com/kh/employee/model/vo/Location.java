package com.kh.employee.model.vo;

public class Location {
	private String localCode;
	private String localName;
	private Nation nation;
	
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(String localCode, String localName, Nation nation) {
		super();
		this.localCode = localCode;
		this.localName = localName;
		this.nation = nation;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public Nation getNation() {
		return nation;
	}
	public void setNation(Nation nation) {
		this.nation = nation;
	}
	
}
