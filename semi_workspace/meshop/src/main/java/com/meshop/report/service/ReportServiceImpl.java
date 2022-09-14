package com.meshop.report.service;

import java.util.List;

import com.meshop.report.dao.ReportDAO;
import com.meshop.report.dao.ReportDAOImpl;
import com.meshop.report.entity.Report;

public class ReportServiceImpl implements ReportService{
	ReportDAO reportDAO = new ReportDAOImpl();
	@Override
	public List<Report> findByprodutId(String productId) {
		return null;
	}

	@Override
	public List<Report> findAll() {
		return reportDAO.findAll();
	}
	
}
