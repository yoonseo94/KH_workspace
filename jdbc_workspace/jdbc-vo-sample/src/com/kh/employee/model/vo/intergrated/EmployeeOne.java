package com.kh.employee.model.vo.intergrated;

import java.sql.Date;

public class EmployeeOne {
	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;

	// 부서정보
	private String deptId;
	private String deptTitle;
	
	// 지역정보
	private String localId;
	private String localName;
	
	// 국가정보
	private String nationalCode;
	private String nationalName;
	
	// 직급정보
	private String jobId;
	private String jobName;
	
	// 매니져정보
	private String managerId;
	
	private String salLevel;
	private int salary;
	private Double bonus; 		// 기본형 double은 null을 처리할 수 없다. null데이터처리하는 경우, wrapper type사용.
	private Date hireDate;
	private Date quitDate;
	private String quitYN;
}
