package com.chun.student.model.vo.intergrated;

import java.sql.Date;

public class StudentOne {

	private String studentNo;
	// 학과정보
	private String departmentNo;
	private String departmentName;
	
	private String studentName;
	private String studentSsn;
	private String studentAddress;
	private Date entranceDate;
	private boolean absenceYn; 		// 'Y' - true, 'N' - false
	
	// 담당교수정보
	private String coachProfessorNo;
	private String coachProfessorName;
}
