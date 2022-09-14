package com.kh.ajax.celeb.manager;

import java.util.ArrayList;
import java.util.List;

import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.dto.CelebType;

/**
 * 
 * singletone 프로그램 운영중 단하나의 객체만 사용.
 *
 */
public class CelebManager {
	private static CelebManager instance;
	private List<Celeb> celebList = new ArrayList<>();
	
	/**
	 * 외부에서는 객체생성 불가
	 */
	private CelebManager(){
		celebList.add(new Celeb(1, "daft punk", CelebType.SINGER, "daftpunk.jpg"));
		celebList.add(new Celeb(2, "hwang", CelebType.COMEDIAN, "hwang.jpg"));
		celebList.add(new Celeb(3, "쥴리아 로버츠", CelebType.ACTOR, "juliaRoberts.jpg"));
		celebList.add(new Celeb(4, "맷 데이먼", CelebType.ACTOR, "mattDamon.jpg"));
		celebList.add(new Celeb(5, "유재석", CelebType.ENTERTAINER, "유재석.jpg"));
	}
	
	public static CelebManager getInstance() {
		if(instance == null)
			instance = new CelebManager();
		return instance;
	}

	public List<Celeb> getCelebList() {
		return celebList;
	}
	
}
