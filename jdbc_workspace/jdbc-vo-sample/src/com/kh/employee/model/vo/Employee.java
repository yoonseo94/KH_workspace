package com.kh.employee.model.vo;

import java.sql.Date;

public class Employee {
	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;
	private Department dept;
	private Job job;
	private String salLevel;
	private int salary;
	private Double bonus; 		// 기본형 double은 null을 처리할 수 없다. null데이터처리하는 경우, wrapper type사용.
	private Employee manager;
	private Date hireDate;
	private Date quitDate;
	private String quitYN;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String empId, String empName, String empNo, String email, String phone, Department dept, Job job,
			String salLevel, int salary, Double bonus, Employee manager, Date hireDate, Date quitDate, String quitYN) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.dept = dept;
		this.job = job;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bonus = bonus;
		this.manager = manager;
		this.hireDate = hireDate;
		this.quitDate = quitDate;
		this.quitYN = quitYN;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getSalLevel() {
		return salLevel;
	}
	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	public String getQuitYN() {
		return quitYN;
	}
	public void setQuitYN(String quitYN) {
		this.quitYN = quitYN;
	}
	
	
	
}
