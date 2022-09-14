package com.kh.uml.association.aggregration;

/**
 * 
 * Aggregation 집약관계
 * - Book객체와 BookInfo객체의 생명주기는 독립적이다.
 * - Book객체가 제거되도, BookInfo객체는 유지될 수 있다.
 */
public class Book {
	private BookInfo bookInfo;
	private long id; // 관리번호
	private boolean isRented;
	
	public Book(long id, BookInfo bookInfo) {
		this.id = id;
		this.bookInfo = bookInfo;
	}
	
	public BookInfo getBookInfo() {
		return bookInfo;
	}
}
