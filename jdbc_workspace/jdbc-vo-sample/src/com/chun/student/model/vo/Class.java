package com.chun.student.model.vo;

public class Class {
	private String classNo;
	private Department departmentNo;
	private Class preattendingClass;
	private String className;
	private ClassType classType = ClassType.공통과목;  // "공통과목" enum 상수모음 클래스
	// public static final String 공통과목 = "공통과목";
}
