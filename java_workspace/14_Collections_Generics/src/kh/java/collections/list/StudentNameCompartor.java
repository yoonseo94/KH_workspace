package kh.java.collections.list;

import java.util.Comparator;

/**
 * 
 * Student의 기본정렬(no)외에 정렬기준을 만들려면
 * 별도의 Comparator구현클래스를 작성해야 한다.
 *
 */
public class StudentNameCompartor implements Comparator<Student> {

	/**
	 * 음수 : 자리교환
	 * 0
	 * 양수
	 */
	@Override
	public int compare(Student s1, Student s2) {
		// Student#name이 String타입이므로, String타입간 정렬기준메소드(String#compareTo)를 이용
		return s1.getName().compareTo(s2.getName());
	}

}
