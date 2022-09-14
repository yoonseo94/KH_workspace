package com.collection.list.book.controller;

import java.util.ArrayList;
import java.util.Arrays;

import com.collection.list.book.model.compare.AscCategory;
import com.collection.list.book.model.vo.Book;

public class BookManager {
	private ArrayList<Book> bookList = new ArrayList<>();
	{
		bookList.add(new Book("200", 2, "나미야 잡화점의 기적", "히가시노 게이고"));
		bookList.add(new Book("100", 3, "파리의 아파트", "기욤 뮈소"));
		bookList.add(new Book("400", 1, "ABCDE", "도레미"));
		bookList.add(new Book("300", 2, "미중전쟁", "김진명"));
		bookList.add(new Book("500", 1, "JAVA 삽질하기", "김동현"));
	}
	public BookManager(){}
	public BookManager(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
	
	public void addBook(Book book){
		bookList.add(book);
	}
	
	public void deleteBook(int index){
		if(index!=-1)
			bookList.remove(index);
		else
			System.out.println("해당 도서가 존재하지 않습니다.");
	}
	
	public int searchBook(String title){
		for (int i=0; i<bookList.size(); i++){
			Book b = (Book)bookList.get(i);
			if(title.equals(b.getTitle()))
				return i;
		}
		return -1;
	}
	
	public void printBook(int index){
		if(index!=-1)
			System.out.println((Book)bookList.get(index));
		else
			System.out.println("해당 도서가 존재하지 않습니다.");
	}
	
	public void printAll(){
		for(int i=0; i<bookList.size(); i++){
			System.out.println((Book)bookList.get(i));
		}
	}
	
	public Book[] sortedBookList(){
		//List를 직접 정렬하게 되면, 실제 리스트의 저장순서(index)가 바뀐다.
		//정렬프로그램은 보통 배열복사를 통해 이루어진다.
//		Collections.sort(bookList, new AscCategory());
//		System.out.println(bookList);
//		System.out.println((Book)bookList.get(0));

		//Object[]를 리턴하는 toArray메서드를 이용해 강제로 형변환하면 예외발생
		//Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Lcom.collection.list.book.model.vo.Book;
		//Book[] bookArr = (Book[])bookList.toArray();
		
		Book[] bookArr = new Book[bookList.size()];
		//1.collection을 배열로 옮겨담기
//		for(int i=0; i<bookArr.length; i++){
//			bookArr[i] = (Book)bookList.get(i);
//		}
		
		//2. 빈 Book[]객체를 toArray메소드 인자로 전달
		bookList.toArray(bookArr);	
	

		//배열정렬에 배열과 배열기준객체를 제공한다
		//<Book> void java.util.Arrays.sort(Book[] a, Comparator<? super Book> c)
		Arrays.sort(bookArr, new AscCategory());
//		Arrays.sort(bookArr, Collections.reverseOrder(new AscCategory()));		
		return bookArr;
	}
	
	public void printBookList(Book[] bookArr){
		for(int i=0; i<bookArr.length; i++){
			System.out.println(bookArr[i]);
		}
	}
}
