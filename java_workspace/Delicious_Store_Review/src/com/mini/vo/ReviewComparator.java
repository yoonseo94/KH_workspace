package com.mini.vo;

import java.util.Comparator;

public class ReviewComparator implements Comparator<Review>{

	@Override
	public int compare(Review r1, Review r2) {
		return r2.getRecomandCnt() - r1.getRecomandCnt();
	}
	
}
