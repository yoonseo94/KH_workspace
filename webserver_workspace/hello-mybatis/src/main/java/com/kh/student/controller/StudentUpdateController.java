package com.kh.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.AbstractController;
import com.kh.student.model.dto.Student;
import com.kh.student.model.service.StudentService;

public class StudentUpdateController extends AbstractController {
	private StudentService studentService;

	public StudentUpdateController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		Student student = new Student(no, name, tel, null, null, null);
		System.out.println("student = " + student);
		
		// 2. 업무로직
		int result = studentService.updateStudent(student);
		
		// 3. 비동기 응답처리
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "정상적으로 수정되었습니다.");
		map.put("student", studentService.selectOne(no));
		new Gson().toJson(map, response.getWriter());
		
		return null;
	}
	
	
	
}
