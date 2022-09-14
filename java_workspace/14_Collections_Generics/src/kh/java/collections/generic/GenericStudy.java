package kh.java.collections.generic;

public class GenericStudy {

	public static void main(String[] args) {
		GenericStudy study = new GenericStudy();
//		study.test1();
		study.test2();
	}
	
	/**
	 * - 제네릭클래스 : 클래스러벨에 타입변수 사용
	 * - 제네릭메소드 : 메소드레벨에 타입변수 사용
	 * 
	 */
	public void test2() {
		// 명시적으로 지역타입변수 선언
		Box<Integer> iBox = Box.<Integer>getBoxInstance(123);
		System.out.println(iBox);
		
		// 암묵적으로 지역타입변수 추론
		Box<String> strBox = Box.getBoxInstance("ㅎㅎㅎ");
		System.out.println(strBox);
		
	}

	/**
	 * Generic의 Type safety(타입안정성)
	 * - 해당 타입의 요소만 추가가능
	 * - 꺼내올때 해당타입을 리턴(형변환)
	 */
	private void test1() {
		Box<Integer> iBox = new Box<Integer>();
		iBox.setElem(123);
		Integer elem = iBox.getElem();
		System.out.println(elem);
		System.out.println(iBox);
		
		Box<String> strBox = new Box<String>();
		strBox.setElem("안녕");
		String str = strBox.getElem();
		System.out.println(str);
		System.out.println(strBox);
		
	}

}
