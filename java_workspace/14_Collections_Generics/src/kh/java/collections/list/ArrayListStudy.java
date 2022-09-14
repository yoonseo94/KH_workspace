package kh.java.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArrayListStudy {

	public static void main(String[] args) {
		ArrayListStudy study = new ArrayListStudy();
//		study.test0();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
//		study.test6();
		study.test7();
	}
	
	/**
	 * LinkedList
	 * - 중간에 요소추가/삭제가 빈번히 일어나는 데이터를 관리한다면 LinkedList를 사용하자.
	 */
	private void test7() {
		List<Integer> list = new LinkedList<>();
		list.add(3);
		list.add(2);
		list.add(4);
		list.add(5);
		list.add(1);
		System.out.println(list);
		
	}


	/**
	 * 커스텀 객체를 정렬하기
	 * - 기본정렬기준(1) : 해당클래스(Student)가 Comparable인터페이스 구현
	 * - 그외정렬기준(n) : 별도의 Comparator구현클래스 생성(정렬기준당 1개)
	 * 
	 * - 학생번호 오름차순 (기본정렬)
	 * - 학생번호 내림차순
	 * - 학생이름 오름차순
	 * - 학생이름 내림차순
	 */
	private void test6() {
		List<Student> list = new ArrayList<>();
		list.add(new Student(3, "세종대왕"));
		list.add(new Student(2, "신사임당"));
		list.add(new Student(5, "장영실"));
		list.add(new Student(1, "홍길동"));
		list.add(new Student(4, "이순신"));
		
		// 기본정렬 & 기본정렬 역순
		// Student가 Comparable 인터페이스를 구현해야 한다.
//		Collections.sort(list);
//		Comparator<Student> comp = Collections.reverseOrder();
//		Collections.sort(list, comp);
		
		// 기타정렬 & 기타정렬 역순
//		Comparator<Student> comp = new StudentNameCompartor();
		Comparator<Student> comp = Collections.reverseOrder(new StudentNameCompartor());
		Collections.sort(list, comp);
		
		for(int i = 0; i < list.size(); i++)
			System.out.println(i + " " + list.get(i));
		
	}



	/**
	 * 정렬하기
	 */
	private void test5() {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(2);
		list.add(5);
		list.add(4);
		list.add(1);
		
		// 정렬
//		Collections.sort(list); // 기본정렬(오름차순)
		Comparator<Integer> comparator = Collections.reverseOrder(); // 기본정렬 역순
		Collections.sort(list, comparator); // Comparator 비교기준객체
		
		System.out.println(list);
		
		// 이름정렬
		List<String> names = Arrays.asList("홍길동", "신사임당", "고주몽", "장영실", "논개");
//		List<String> names = new ArrayList<>();
//		names.add("홍길동");
//		names.add("신사임당");
//		names.add("고주몽");
//		names.add("장영실");
//		names.add("논개");
		
		Comparator<String> comp = Collections.reverseOrder();
		Collections.sort(names, comp);
		
		System.out.println(names);
		
	}

	/**
	 * list api
	 */
	private void test4() {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(25);
		list.add(77);
		list.add(77);
		list.add(77);
		list.add(100);
		
		// 삭제 
		Integer removed = list.remove(0);
		System.out.println(removed);
		// 중복된 요소가 있을 경우, 첫번째 요소만 삭제
		boolean isRemoved = list.remove(Integer.valueOf(77)); // 인덱스 77(int)인가? 아니면 요소77(Integer)인가? 
		System.out.println(isRemoved);
		
		// 요소 포함여부
		boolean contained = list.contains(100);
		System.out.println("100이 포함되어 있는가? " + contained);
		
		int index = list.indexOf(77); // 존재하지 않으면 -1을 리턴
		System.out.println(index);
		index = list.lastIndexOf(77); // 뒤에서부터 조회
		System.out.println(index);
		
		// 다른 list 합치기
		List<Integer> other = new ArrayList<>();
		other.add(9);
		other.add(99);
		
//		list.addAll(other);
		list.addAll(2, other);
		
		System.out.println("------------------");
		
		// 개별요소확인
		// 1. 일반 for문 index
		// 2. for each문
		// 3. Iterator객체(목록화) - 순차적으로 접근이 가능
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()) {
			Integer i = iter.next();
			System.out.println(i);
		}
		
		// clear : 모든 요소제거
		list.clear();
		
		// isEmpty : 요소가 0개라면 true 리턴
		System.out.println(list.isEmpty());
		
		// toString Override 모든 요소 확인
		System.out.println(list);
	}

	/**
	 * List<Student> 관리하기
	 */
	private void test3() {
		// 학생 3명 관리
		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "홍길동"));
		list.add(new Student(2, "신사임당"));
		list.add(new Student(3, "세종대왕"));
		
		// 학생 2명 추가
		list.add(new Student(4, "이순신"));
		list.add(new Student(5, "장영실"));
		
		// 2번지 학생 제거
		list.remove(2);
		
		// 2번지에 학생 추가 (삽입)
		list.add(2, new Student(100, "논개"));
		
		// 2번지에 학생 대체
		list.set(2, new Student(200, "유비"));
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(i + " " + list.get(i));
		}
	}

	/**
	 * generics
	 * - 컬렉션의 요소를 타입을 제한하는 기능
	 * - 지정한 타입만 추가가능(그 외 타입은 컴파일 오류)
	 * - getter사용시 리턴될 타입을 한정할 수 있다. 형변환 불필요!
	 * - generics에 기본형을 가질 수 없다.
	 * 
	 */
	private void test2() {
		List<String> list = new ArrayList<String>(); // String만 요소로 하는 list
		list.add("홍길동");
		list.add("신사임당");
//		list.add(Integer.valueOf(123)); // 요소추가시 타입검사 O
		
		String name0 = list.get(0); // generic을 사용하지 않으면 요소를 Object로 처리
		String name1 = list.get(1); // getter를 이용시 generic타입으로 바로 리턴  
		System.out.println(name0);
		System.out.println(name1);
		
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(3); // int -> Integer auto-boxig처리후 추가
		intList.add(Integer.valueOf(3));
		Integer i0 = intList.get(0);
		int i1 = intList.get(1); // Integer -> int auto-unboxing처리
		System.out.println(i0);
		System.out.println(i1);
		
		// 객체생성부의 제네릭은 생략이 가능하다.(1.7)
		List<Double> dList = new ArrayList<>();
	}

	/**
	 * List 
	 * - ArrayList 구현클래스
	 * - LinkedList
	 * 
	 * 중복저장 가능, 저장된 순서 유지
	 */
	private void test1() {
		// list 다형성 적용
		ArrayList list1 = new ArrayList();
		List list2 = list1;
		Collection list3 = list1;
		
		// 요소추가
		list1.add("apple");
		list1.add(123); 	// 기본형이 아닌 Wrapper객체 Integer.valueOf(123) auto boxing
		list1.add(23.45);
		list1.add(true);
		list1.add(new Date());
		list1.add(true);	// 중복된 요소 저장가능
		
		// 요소가져오기(인덱스)
//		System.out.println(list1.get(0));
//		System.out.println(list1.get(1));
//		System.out.println(list1.get(2));
//		System.out.println(list1.get(3));
//		System.out.println(list1.get(4));
		
		// 중간에 추가 : add(index, 요소)
		list1.add(3, false); // 3번지이후는 하나씩 뒤로이동
		
		// 중간에 삭제 
		list1.remove(0); // 뒤요소는 자동으로 하나씩 앞당겨지게 된다.
		
		System.out.println(list1.size());
		// size() : 저장된 요소의 개수
		for(int i = 0; i < list1.size(); i++) {
			System.out.println(i + " " + list1.get(i));
		}
	}

	/**
	 * 배열의 단점
	 * - 크기 변경이 불가능하다.
	 * - 중간에 요소 추가/삭제가 불편하다.
	 */
	private void test0() {
		Student[] students = new Student[3];
		students[0] = new Student(1, "홍길동");
		students[1] = new Student(2, "신사임당");
		students[2] = new Student(3, "세종대왕");
		
		// 학생 2명 추가
		Student[] students2 = new Student[10];
		System.arraycopy(students, 0, students2, 0, students.length);
		students2[3] = new Student(4, "이순신");
		students2[4] = new Student(5, "장영실");
		
		// 학생 1명 제거 - 2번지요소
		students2[2] = students2[3];  
		students2[3] = students2[4];
		students2[4] = null;
		
		// 학생 1명 2번지에 추가
		students2[4] = students2[3];
		students2[3] = students2[2];
		students2[2] = new Student(6, "논개");
		
		// 현재 관리되고 있는 학생현황
		for(int i = 0; i < students2.length; i++) {
			System.out.println(i + " " + students2[i]);
		}
		
	}

}
