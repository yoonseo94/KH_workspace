package com.meshop.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.Gson;
import com.meshop.report.entity.Report;
import com.meshop.report.service.ReportService;
import com.meshop.report.service.ReportServiceImpl;

@WebServlet("/admin/report")
public class AdminReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReportService reportService = new ReportServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//ajax 통신
		// 1. 업무 로직
		List<Report> list = reportService.findAll();
		
		// 2. 응답처리 json
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(new Gson().toJson(list));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
