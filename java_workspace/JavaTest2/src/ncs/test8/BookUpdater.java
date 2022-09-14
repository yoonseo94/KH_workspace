package ncs.test8;

import ncs.test7.Book;

/**
 * 
 * 매니져 클래스
 * 
 * BookUpdater
 *
 */
public class BookUpdater {
	private Book bookData;

	public BookUpdater() {}

	public BookUpdater(Book bookData) {
		this.bookData = bookData;
	}

	/**
	 * 할인율이 적용된 판매가를 다시 설정하는 메소드
	 */
	public void updateBookPrice(){
		double price = bookData.getBookPrice(); // 현재가
//		double salePrice = price * (1 - bookData.getBookDiscountRate());
		double salePrice = price - (price * bookData.getBookDiscountRate());
		bookData.setBookPrice(salePrice);
	}

	public Book getBookData() {
		return bookData;
	}

	public void setBookData(Book bookData) {
		this.bookData = bookData;
	}
	
	
}
