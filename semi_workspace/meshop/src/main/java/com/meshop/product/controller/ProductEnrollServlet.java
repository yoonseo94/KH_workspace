package com.meshop.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshop.common.MeshopFileRenamePolicy;
import com.meshop.member.entity.Member;
import com.meshop.product.entity.AttachType;
import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.ProductExt;
import com.meshop.product.entity.ProductStatus;
import com.meshop.product.service.ProductService;
import com.meshop.product.service.ProductServiceImpl;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class ProductEnrollServlet
 */
@WebServlet("/product/productEnroll")
public class ProductEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/product/productEnroll.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// MultipartRequest 객체 생성
			String saveDirectory = request.getServletContext().getRealPath("/images");
			System.out.println("saveDirectory :: " + saveDirectory);
			
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			FileRenamePolicy policy = new MeshopFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 1. 사용자 입력값 처리
			String boardType = multiReq.getParameter("boardType");
			
			String title = multiReq.getParameter("productTitle");
			String brand = multiReq.getParameter("productBrand");
			String category = multiReq.getParameter("productCategory");
			ProductStatus status = ProductStatus.valueOf(multiReq.getParameter("productStatus"));
			int price = Integer.parseInt(multiReq.getParameter("productPrice"));
			String content = multiReq.getParameter("productContent");
			
			Member member = (Member) request.getSession().getAttribute("loginMember");
			
			ProductExt product = new ProductExt();
			product.setMemberId(member.getMemberId());
			product.setPlace(member.getPlace());
			product.setTitle(title);
			product.setBrand(brand);
			product.setCategory(category);
			product.setStatus(status);
			product.setPrice(price);
			product.setContent(content);
			
			// 2. 업무 로직
			int result = 0;
			if("buyBoard".equals(boardType)) {
				// 구매글인 경우
				result = productService.insertProductBuy(product);
			} else {
				// 판매글인 경우
				File productImage = multiReq.getFile("productImage");
				List<Attachment> attachments = new ArrayList<>();
				if(productImage != null) {
					Attachment attach = getAttachment(multiReq, "productImage");
					attach.setAttachtype(AttachType.T);
					attachments.add(attach);
				}
				product.setAttachments(attachments);
				result = productService.insertProduct(product);
			}
			
			// 3. 리다이렉트
			response.sendRedirect(request.getContextPath() + "/product/productList");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	private Attachment getAttachment(MultipartRequest multiReq, String filename) {
		Attachment attach = new Attachment();
		String originalFilename = multiReq.getOriginalFileName(filename); // 업로드 파일명
		String renamedFilename = multiReq.getFilesystemName(filename); // 저장된 파일명
		attach.setOriginalFilename(originalFilename);
		attach.setRenamedFilename(renamedFilename);
		return attach;
	}

}
