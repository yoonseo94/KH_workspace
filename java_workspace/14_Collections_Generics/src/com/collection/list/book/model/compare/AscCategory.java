package com.collection.list.book.model.compare;

import java.util.Comparator;

import com.collection.list.book.model.vo.Book;

//public class AscCategory implements Comparator {
//
//	@Override
//	public int compare(Object o1, Object o2) {
//		Book b1 = (Book)o1;
//		Book b2 = (Book)o2;
//
//		return b1.getCategory()-b2.getCategory();
//	}
//
//}

public class AscCategory implements Comparator<Book> {

	@Override
	public int compare(Book b1, Book b2) {
		//b1의 카테고리가 b2의 카테고리보다 적으면 음수리턴
		//b1의 카테고리가 b2의 카테고리와 같으면  0 리턴
		//b1의 카테고리가 b2의 카테고리보다 크면 양수리턴		
		return b1.getCategory()-b2.getCategory();
	}

}
//다중정렬
//카테고리(오름차순), 책이름(오름차순)으로 정렬한다고 가정.
//오름차순 obj1 - obj2 => 양수이면, 자리바꿈.
//내림차순 obj2 - obj1 => 양수이면, 자리바꿈.
//public class AscCategory implements Comparator<Book> {
//	@Override
//	public int compare(Book b1, Book b2){
//		int result = 0;
//		if(b1.getCategory()-b2.getCategory() > 0){
//			result = 1;
//		}
//		else if(b1.getCategory()-b2.getCategory() < 0){
//			result = -1;
//		}
//		//카테고리가 같은 경우
//		//책제목으로 비교한다. String비교는 String클라스의 compareTo메소드 이용.
//		else {
//			if(b1.getTitle().compareTo(b2.getTitle())<0)
//				result = -1;
//			else if (b1.getTitle().compareTo(b2.getTitle())>0)
//				result = 1;
//			else 
//				result = 0;
//		}
//		
//		return result;
//	}
//}