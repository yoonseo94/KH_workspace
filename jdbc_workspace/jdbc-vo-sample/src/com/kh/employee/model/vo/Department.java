package com.kh.employee.model.vo;

public class Department {
	
	private String detpId;
	private String deptTitle;
	private Location location;
	
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(String detpId, String deptTitle, Location location) {
		super();
		this.detpId = detpId;
		this.deptTitle = deptTitle;
		this.location = location;
	}
	public String getDetpId() {
		return detpId;
	}
	public void setDetpId(String detpId) {
		this.detpId = detpId;
	}
	public String getDeptTitle() {
		return deptTitle;
	}
	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
