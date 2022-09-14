package store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.dto.ProductExt;
import product.model.dto.ProductImage;
import product.model.service.ProductService;

/**
 * Servlet implementation class StoreSearchByMainCategoryServlet
 */
@WebServlet("/store/StoreSearchByMainCategoryServlet")
public class StoreSearchByMainCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maincode = request.getParameter("maincode");
		String searchType = request.getParameter("searchType");

		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("maincode", maincode);
		
		List<ProductExt> productsExtList = productService.findAllProductsByCategory(param);

		List<ProductExt> productExtListByCategory = new ArrayList<>();
		for(ProductExt product : productsExtList) {
			List<ProductImage> productImages = productService.findProductImagesByProductId(product.getProductId());
			product.setProductImages(productImages);
			productExtListByCategory.add(product);
		}
		
		// 2. 응답처리 
		response.setContentType("application/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String categoryName = "";
		switch(maincode) {
		case "furniture" : categoryName = "가구"; break;
		case "electronics" : categoryName = "가전"; break;
		case "lighting" : categoryName = "조명"; break;
		case "organizing" : categoryName = "수납/정리"; break;
		case "living" : categoryName = "생활용품"; break;
		case "bookshelf" : categoryName = "책상"; break;
		case "desk" : categoryName = "가전"; break;
		case "table" : categoryName = "식탁"; break;
		case "table_chair" : categoryName = "식탁 의자"; break;
		case "office_chair" : categoryName = "사무용 의자"; break;
		case "chest_of_drawers" : categoryName = "서랍장"; break;
		case "wardrobe" : categoryName = "옷장"; break;
		case "bed" : categoryName = "침대"; break;
		case "tv" : categoryName = "TV"; break;
		case "air_conditioner" : categoryName = "에어컨"; break;
		case "refrigerator" : categoryName = "냉장고"; break;
		case "kimchi_refrigerator" : categoryName = "김치냉장고"; break;
		case "oven" : categoryName = "오븐"; break;
		case "microwave" : categoryName = "전자레인지"; break;
		case "washing_machine" : categoryName = "세탁기"; break;
		case "led_lighting" : categoryName = "LED등"; break;
		case "fluorescent_lamp" : categoryName = "형광등"; break;
		case "desk_stand" : categoryName = "데스크 스탠드"; break;
		case "mood" : categoryName = "무드등"; break;
		case "wall_light" : categoryName = "벽조명"; break;
		case "sensor_light" : categoryName = "센서등"; break;
		case "storage_closet" : categoryName = "서랍장"; break;
		case "living_box" : categoryName = "리빙박스"; break;
		case "basket" : categoryName = "바구니"; break;
		case "clothes_rack" : categoryName = "행거"; break;
		case "shelf" : categoryName = "선반"; break;
		case "hanger" : categoryName = "옷걸이"; break;
		case "bathroom_products" : categoryName = "욕실용품"; break;
		case "towel" : categoryName = "수건"; break;
		case "cleaning_tools" : categoryName = "청소용품"; break;
		case "laundry_products" : categoryName = "세탁용품"; break;
		case "household_goods" : categoryName = "생활잡화"; break;
		}
	
		out.append("<header class='store-index-category-list-header' style='margin-left:1rem;'>");
		out.append("<h1 class='store-index-category-list-title' >#"+categoryName+"</h1>");
		out.append("</header>");
		out.append("<div class='store-category-list-content'>");
		
		for(ProductExt productsCategory : productExtListByCategory) {						
			System.out.println(productsCategory);
		out.append("<div class='category-item-cont'>");
		out.append("<div class='category-item-wrapper'>");
		out.append("<article class=''category-item'>");
		out.append("<a class='category-item-overlay' href='" + request.getContextPath() +"/store/productView?productId="+ productsCategory.getProductId() + "'>");
		out.append("<div class='category-item-image'>");
		out.append("<div class=''category-item-image-item'>");
		out.append("<div class='production-item-image'>");
		 	List<ProductImage> productImagesCate = productsCategory.getProductImages();
			 	ProductImage pImg = productImagesCate.get(0);
		out.append("<img class='product-image' alt='' src='" + request.getContextPath() + "/upload/product/" + pImg.getRenamedFilename() + "'>");
		out.append("</div>");	 	
		out.append("</div>");	 	
		out.append("</div>");	 	
		out.append("<div class='category-item-content'>");
		out.append("<div class='category-item-content-wrap'>");
		out.append("<h1 class='category-item-header'>");
		out.append("<span class='category-item-header-brand'>" + productsCategory.getBrandName() + "</span>");
		out.append("<span class='category-item-header-name'>" + productsCategory.getProductName() + "</span>");
		out.append("</h1>");
		if( productsCategory.getDiscountRate() == 0 ) {
		out.append("<span class='production-item-price'>");
		out.append("<span class='production-item-price-price'>" + productsCategory.getProductPrice() + "</span>");
		out.append("</span>");
		} else {
		out.append("<span class='production-item-price'>");
		out.append("<span class='production-item-price-rate'>" + productsCategory.getDiscountRate() + "<span class='percentage'>% </span></span>");	
		out.append("<span class='production-item-price-price'>" + (int) Math.floor(productsCategory.getProductPrice() * (100 - productsCategory.getDiscountRate()) / 1000)*10 + "</span>");	
		out.append("</span>");	
		} 
		out.append("</div>");	
		out.append("</div>");	
		out.append("</a>");	
		out.append("</article>");	
		out.append("</div>");	
		out.append("</div>");					
		}			
		
	}



}
