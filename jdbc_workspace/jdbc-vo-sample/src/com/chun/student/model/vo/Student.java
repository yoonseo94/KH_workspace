package com.chun.student.model.vo;

import java.sql.Date;

public class Student {

	private String studentNo;
	private Department department;
	private String studentName;
	private String studentSsn;
	private String studentAddress;
	private Date entranceDate;
	private boolean absenceYn; 		// 'Y' - true, 'N' - false
	private Professor coachProfessor;
}
