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
		//b1�� ī�װ��� b2�� ī�װ����� ������ ��������
		//b1�� ī�װ��� b2�� ī�װ��� ������  0 ����
		//b1�� ī�װ��� b2�� ī�װ����� ũ�� �������		
		return b1.getCategory()-b2.getCategory();
	}

}
//��������
//ī�װ�(��������), å�̸�(��������)���� �����Ѵٰ� ����.
//�������� obj1 - obj2 => ����̸�, �ڸ��ٲ�.
//�������� obj2 - obj1 => ����̸�, �ڸ��ٲ�.
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
//		//ī�װ��� ���� ���
//		//å�������� ���Ѵ�. String�񱳴� StringŬ���� compareTo�޼ҵ� �̿�.
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