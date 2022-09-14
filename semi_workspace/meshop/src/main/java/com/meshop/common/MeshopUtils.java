package com.meshop.common;

public class MeshopUtils {

	public static String getPagebar(int cPage, int numPerPage, int totalProducts, String url) {
		StringBuilder pagebar = new StringBuilder();
		int totalPages = (int) Math.ceil((double) totalProducts / numPerPage);
		int pagebarSize = 10;
		int pagebarStart = (cPage - 1) / pagebarSize * pagebarSize + 1;
		int pagebarEnd = pagebarStart + pagebarSize - 1;
		int pageNo = pagebarStart;
		
		url += "?cPage=";
		// 이전 prev
		if(pageNo == 1) {
			pagebar.append("<span>이전</span>");
		} else {
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>이전</a>");
		}
		pagebar.append("\n");
		
		// 번호
		while(pageNo <= pagebarEnd && pageNo <= totalPages) {
			if(pageNo == cPage) {
				pagebar.append("<span class='cPage'>" + pageNo + "</span>");
				pagebar.append("\n");
			} else {
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>");
				pagebar.append("\n");
			}
			pageNo++;
		}
		
		// 다음 next
		if(pageNo > totalPages) {
			pagebar.append("<span>다음</span>");
		} else {
			pagebar.append("<a href='" + url + (pageNo + 1) + "'>다음</a>");
		}
		pagebar.append("\n");
		return pagebar.toString();
	}
}
