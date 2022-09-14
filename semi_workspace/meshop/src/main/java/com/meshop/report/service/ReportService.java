package com.meshop.report.service;

import java.util.List;

import com.meshop.report.entity.Report;

public interface ReportService {
	//상품 아이디로 신고 게시글 가져오기
	List<Report> findByprodutId(String productId);

	//신고 게시글 모두 가져오기
	List<Report> findAll();
}
