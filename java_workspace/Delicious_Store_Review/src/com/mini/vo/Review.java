package com.mini.vo;

import java.io.Serializable;
import java.util.List;

public class Review implements Serializable, Comparable<Review> {
	
	/**
	 * 직렬화된 정보와 일치하는 클래스인지 비교하는 고유값
	 */
	private static final long serialVersionUID = 1L;
	
	private String area;
	private String contents;
	private String storeName;
	private int starScore;
	private int recomandCnt;
	
	// 로그인 시 id값 셋팅
	private String id;
	
	public Review() {
		super();
	}

	public Review(String area, String contents, String storeName, int starScore, String id) {
		super();
		this.area = area;
		this.contents = contents;
		this.storeName = storeName;
		this.starScore = starScore;
		this.id = id;
		
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getStarScore() {
		return starScore;
	}

	public void setStarScore(int starScore) {
		this.starScore = starScore;
	}

	public int getRecomandCnt() {
		return recomandCnt;
	}

	public void setRecomandCnt(int recomandCnt) {
		this.recomandCnt = recomandCnt;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// 숫자 -> 별
	public String star() {
		String star = "";
		for(int i = 0; i < starScore; i++) {
			star += "☆";
		}
		return star;
	}
	
	@Override
	public String toString() {
		return "[" + storeName + "(추천 " + recomandCnt + "개)]\t- " + contents + "\t" + star();
	}

	@Override
	public int compareTo(Review other) {
		return other.starScore - this.starScore;
	}

	
	
	
}
