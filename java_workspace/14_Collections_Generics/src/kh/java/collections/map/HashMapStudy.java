package kh.java.collections.map;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import kh.java.collections.list.Student;

public class HashMapStudy {

	public static void main(String[] args) {
		HashMapStudy study = new HashMapStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
		study.test5();
	}
	
	/**
	 * containsKey
	 * constainsValue
	 */
	public void test5() {
		Map<Integer, Student> studentMap = new HashMap<>();
		studentMap.put(1, new Student(1, "홍길동"));
		studentMap.put(2, new Student(2, "신사임당"));
		studentMap.put(3, new Student(3, "논개"));
		
		// 해당객체가 equals/hashCode 오버라이드를 통해 동등성 비교가 가능한 경우여야 한다.
		// 특정 key값 검사
		System.out.println(studentMap.containsKey(1));
		System.out.println(studentMap.containsKey(100));
		
		// 특정 value값 검사
		System.out.println(studentMap.containsValue(new Student(3, "논개")));
		
		System.out.println(studentMap);
		
	}

	/**
	 * 학생객체 관리
	 * - Map<Integer,Student>
	 * - value의 고유값을 꺼내어 key값(Student#no)으로 사용
	 */
	private void test4() {
		Map<Integer, Student> map = new HashMap<>();
		map.put(1, new Student(1, "홍길동"));
		map.put(2, new Student(2, "신사임당"));
		map.put(3, new Student(3, "이순신"));
		
//		System.out.println(map);
		
		// 학생번호 1번, 이름은 [홍길동]학생입니다.
		// 1.keySet
		Set<Integer> keySet = map.keySet();
//		for(Integer key : keySet) {
//			Student value = map.get(key);
//			System.out.printf(
//					"학생번호 %d번, 이름은 [%s]학생입니다.%n", 
//					value.getNo(), 
//					value.getName());
//		}
		
		Iterator<Integer> iter = keySet.iterator();
		while(iter.hasNext()) {
			Integer key = iter.next();
			Student value = map.get(key);
			System.out.printf(
					"학생번호 %d번, 이름은 [%s]학생입니다.%n", 
					value.getNo(), 
					value.getName());
		}
		
		
		// 2.entrySet
		Set<Map.Entry<Integer, Student>> entrySet = map.entrySet();
//		for(Map.Entry<Integer, Student> entry : entrySet) {
//			Student value = entry.getValue();
//			System.out.printf(
//					"학생번호 %d번, 이름은 [%s]학생입니다.%n", 
//					value.getNo(), 
//					value.getName());
//		}
		
		Iterator<Map.Entry<Integer, Student>> entryIter = entrySet.iterator();
		while(entryIter.hasNext()) {
			Map.Entry<Integer, Student> entry = entryIter.next();
			Integer key = entry.getKey();
			Student value = entry.getValue();
			System.out.printf(
					"학생번호 %d번, 이름은 [%s]학생입니다.%n", 
					value.getNo(), 
					value.getName());	
		}
		
	}

	/**
	 * 맵요소를 순회 열람
	 * - 1. keySet : Key를 별도의 Set으로 반환
	 * - 2. entrySet : entry(Key, Value)를 별도의 Set으로 반환 
	 */
	private void test3() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "홍길동");
		map.put(2, "신사임당");
		map.put(3, "세종대왕");
		
		// 1. keySet
		Set<Integer> keySet = map.keySet();
//		for(Integer key : keySet) {
//			String value = map.get(key);
//			System.out.println(key + " = " + value);
//		}
		
		Iterator<Integer> iter = keySet.iterator();
		while(iter.hasNext()) {
			Integer key = iter.next();
			String value = map.get(key);
			System.out.println(key + " = " + value);
		}
		
		
		
		// 2. entrySet
		// entry key/value 한쌍의 요소
		Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
//		for(Map.Entry<Integer, String> entry : entrySet) {
//			Integer key = entry.getKey();
//			String value = entry.getValue();
//			System.out.println(key + " = " + value);
//		}
		
		// 1.iterator객체생성 -> 2.while(iter.hasNext) -> 3.iter.next
		Iterator<Map.Entry<Integer, String>> iter2 = entrySet.iterator();
		while(iter2.hasNext()) {
			Map.Entry<Integer, String> entry = iter2.next();
			Integer key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + " = " + value);	
		}
		
	}


	/**
	 * 다른 맵 병합
	 */
	private void test2() {
		Map<Integer, String> map1 = new HashMap<>();
		map1.put(1, "홍길동");
		map1.put(2, "신사임당");
		map1.put(3, "세종대왕");
		
		Map<Integer, String> map2 = new HashMap<>();
		map2.put(3, "논개");
		map2.put(4, "이화주");
		map2.put(5, "유관순");
		
		System.out.println("map1 = " + map1);
		System.out.println("map2 = " + map2);
		
		// map1에 map2내용을 추가
		map1.putAll(map2);
		System.out.println("map1 = " + map1);
		
		// 다른 맵을 인자로 하는 생성자
		Map<Integer, String> map3 = new HashMap<>(map2);
		System.out.println("map3 = " + map3);
		
		
	}


	/**
	 * Map<K, V>
	 *  - K key타입 
	 *  - V value타입
	 */
	private void test1() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 요소추가 put
		// 기존요소가 있다면, 삭제된 요소를 반환
		map.put("hello", "안녕하세요");
		map.put("num", 123);
		map.put("today", new Date());
		
		System.out.println(map.put("name", "홍길동"));
		System.out.println(map.put("name", "신사임당")); // 동일한 Key값인 경우, 기존값을 치환
		
		// 요소 가져오기 get(K)
		System.out.println(map.get("num"));
		System.out.println(map.get("hello"));
		System.out.println(map.get("today"));
		System.out.println(map.get("name"));
		
		// 요소 삭제 remove(K)
		System.out.println("remove : " + map.remove("num"));
		
		// 요소 전체 개수
		System.out.println(map.size());
		
		// toString override되어 있음
		System.out.println(map);
		
	}

}
