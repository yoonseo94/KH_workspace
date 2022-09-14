package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.dto.Student;
import com.kh.student.model.service.StudentService;

/**
 *  
 *  GET /mybatis/student/studentEnroll.do -> StudentEntrollController
 *  POST /mybatis/student/studentEnroll.do -> StudentEntrollController
 *
 */
public class StudentEnrollController extends AbstractController {
	private StudentService studentService;
	
	public StudentEnrollController(StudentService studentService) {
		super();
		this.studentService = studentService;	
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(studentService);
		return "student/studentEnroll";
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		Student student = new Student(0, name, tel, null, null, null);
		System.out.println("student = " + student);
		
		// 2. 업무로직
		int result = studentService.insertStudent(student);
		
		// 사용자피드백
		request.getSession().setAttribute("msg", "학생정보 등록성공!");
	
		return "redirect:/student/studentEnroll.do";
	}
}










