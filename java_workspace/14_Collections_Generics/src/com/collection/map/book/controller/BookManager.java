package com.collection.map.book.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.collection.list.book.model.compare.AscCategory;
import com.collection.list.book.model.vo.Book;

public class BookManager {
	private Map<String, Book> bookMap = new HashMap<>();
	{
		bookMap.put("400", new Book("400", 1, "ABCDE", "도레미"));
		bookMap.put("100", new Book("100", 3, "파리의 아파트", "기욤 뮈소"));
		bookMap.put("300", new Book("300", 2, "미중전쟁", "김진명"));
		bookMap.put("200", new Book("200", 2, "나미야 잡화점의 기적", "히가시노 게이고"));
		bookMap.put("500", new Book("500", 1, "Java 삽질하기", "김동현"));
	}

	public BookManager() {
	}

	public BookManager(Map<String, Book> bookMap) {
		this.bookMap = bookMap;
	}

	public void addBook(Book book) {
		bookMap.put(book.getbNo(), book);
	}

	public void deleteBook(String key) {
		if (key != null)
			bookMap.remove(key);
		else
			System.out.println("해당 도서가 존재하지 않습니다.");
	}

	public String searchBook(String title) {
		// Map을 열람하는 방법 3가지
		// 1. keySet()
		// 2. values()
		// 3. entrySet()
		Set<Map.Entry<String, Book>> entrySet = bookMap.entrySet();
		Iterator<Map.Entry<String, Book>> it = entrySet.iterator();
//		Iterator<Map.Entry<String, Book>> it = bookMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Book> entry = (Map.Entry<String, Book>) it.next();
			Book b = (Book) entry.getValue();

			// 대소문자구분않고 검색 가능
			String titleFromMap = b.getTitle().toUpperCase();
			title = title.toUpperCase();
//			if (title.equals(b.getTitle()))
//			if(b.getTitle().contains(title))
			if(titleFromMap.contains(title))
				return b.getbNo();
		}
		return null;
	}

	public void printBook(String key) {
		if (key != null)
			System.out.println((Book) bookMap.get(key));
		else
			System.out.println("해당 도서가 존재하지 않습니다.");
	}

	/**
	 * map은 일반 for문으로 열람할 수 없다.
	 * 1. keySet
	 * 2. entrySet
	 * 
	 */
	public void printAll() {
		Set<String> keySet = bookMap.keySet();
		
//		for(String key : keySet) {
//			Book value = bookMap.get(key);
//			System.out.println(value);
//		}
		
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Book value= bookMap.get(key);
			System.out.println(value);
		}
		
		
		
	}

	public Book[] sortedBookMap() {
		Book[] bookArr = new Book[bookMap.size()];
		Iterator<Map.Entry<String, Book>> it = bookMap.entrySet().iterator();
		int index = 0;
		while (it.hasNext()) {
			Map.Entry<String, Book> entry = it.next();
			bookArr[index++] = entry.getValue();
		}
		
//		System.out.println(Arrays.toString(bookArr));

		// 배열정렬에 배열과 배열기준객체를 제공한다
		// <Book> void java.util.Arrays.sort(Book[] a, Comparator<? super Book> c)
		Arrays.sort(bookArr, new AscCategory());
//		Arrays.sort(bookArr, Collections.reverseOrder(new AscCategory()));
		return bookArr;
	}

	public void printBookMap(Book[] bookArr) {
		for (Book book : bookArr) {
			System.out.println(book);
		}
	}
}
