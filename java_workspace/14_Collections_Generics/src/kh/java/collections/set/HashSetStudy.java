package kh.java.collections.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import kh.java.collections.list.Student;

public class HashSetStudy {

	public static void main(String[] args) {
		HashSetStudy study = new HashSetStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		study.test6();		
	}
	
	/**
	 * 로또번호 생성
	 * - 1 ~ 45사이의 중복없는 난수 6개
	 * - 오름차순 정렬해서 관리
	 */
	private void test6() {
		Set<Integer> lotto = new TreeSet<>();
		while(lotto.size() < 6){
			int n = (int)(Math.random() * 45) + 1;
			lotto.add(n);
		}
		System.out.println(lotto);
	}

	/**
	 * LinkedHashSet : 저장된 순서를 기억
	 * TreeSet : 기본정렬처리(요소로 사용된 클래스는 Comparable인터페이스를 구현)
	 */
	private void test5() {
//		Set<Student> set = new LinkedHashSet<>();
		Set<Student> set = new TreeSet<>();
		set.add(new Student(3, "이순신"));
		set.add(new Student(1, "홍길동"));
		set.add(new Student(2, "신사임당"));

		System.out.println(set);
	}

	/**
	 * 커스텀객체 Set으로 관리하기
	 * - 동일한 데이터를 가진 객체를 중복처리하기
	 * - equals & hashCode 오버라이드 필수 
	 */
	private void test4() {
		Set<Student> set = new HashSet<>();
		set.add(new Student(1, "홍길동"));
		set.add(new Student(2, "신사임당"));
		set.add(new Student(3, "이순신"));
		set.add(new Student(1, "홍길동"));
		
		
		for(Student s : set) {
			System.out.println(s);
		}
			
		
	}


	/**
	 * List --> Set
	 * - 중복제거
	 * 
	 * Set --> List
	 * - 순서적용 (하나씩 접근, 정렬..)
	 */
	private void test3() {
		List<Integer> list = Arrays.asList(2,3,4,5,2,3,4,1,3);
		System.out.println(list);
		
		// Set으로 변환
		Set<Integer> set = new HashSet<>(list);
		System.out.println(set);
		
		// List로 변환
		List<Integer> newList = new ArrayList<>(set);
		System.out.println(newList);
		System.out.println(newList.get(3));
		
	}


	/**
	 * Set API
	 */
	private void test2() {
		Set<String> set = new HashSet<>(); // 타입추론에 의한 생략(1.7)
		set.add("홍길동");
		set.add("고길동");
		set.add("마길동");
		
		// 포함여부
		System.out.println(set.contains("홍길동"));
		System.out.println(set.contains("최길동"));
		
		// 요소전체제거
//		set.clear();
		
		// isEmpty()
//		System.out.println(set.isEmpty());
		
		// 다른 set과 병합
		Set<String> other = new HashSet<>();
		other.add("조길동");
		other.add("홍길동");
		other.add("하길동");
		
		set.addAll(other);
		
		System.out.println(set);
		
	}



	/**
	 * Collection
	 *  - Set
	 *  	- HashSet
	 *  
	 *  중복허용X
	 *  저장된 순서를 관리X
	 */
	private void test1() {
		HashSet<Integer> set1 = new HashSet<Integer>();
		Set<Double> set2 = new HashSet<Double>();
		Collection<String> set3 = new HashSet<String>();
		
		// 요소추가 : 저장된 순서 무시
		// 기본형 정렬처리되는 것은 무시할 것.
		System.out.println(set1.add(3));
		System.out.println(set1.add(2));
		System.out.println(set1.add(1));
		System.out.println(set1.add(2)); // 중복허용 하지 않는다.
		System.out.println(set1.add(2));
		System.out.println(set1.add(2));
		System.out.println(set1.add(2));
		System.out.println(set1.add(2));
		
		// get(index) 없음 - 요소하나를 가져올 수 없다.
		// 모든 요소를 순차열람
		// 일반 for문 X - index사용불가
		// 1.for each
		// 2.iterator
//		Iterator<Integer> iter = set1.iterator();
//		while(iter.hasNext()) {
//			Integer i = iter.next();
//			System.out.println(i);
//		}
		
		for(Integer i : set1) {
			System.out.println(i);
		}
		
		// 요소 제거 : 인덱스를 이용한 제거 없고, 요소와 일치하는 방식만 제공
		set1.remove(2);
		
		// 요소개수
		System.out.println(set1.size());
		
		// toString
		System.out.println(set1);
	}

}
