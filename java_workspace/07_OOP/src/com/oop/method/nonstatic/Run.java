package com.oop.method.nonstatic;

public class Run {

	public static void main(String[] args) {
		// 생성된 객체의 참조주소값을 변수에 담지 않고, 그대로 메소드 호출!
		new Run().test1();
	}

	
	/**
	 * NonStaticSample 예제풀이
	 */
	private void test1() {
		NonStaticSample n = new NonStaticSample();
		//1
		n.printLottoNumbers();
		
		//2
		n.outputChar(5, 'x');
		
		//3
		char c = n.alphabet();
		System.out.println(c);
		
		//4
		String sparam = "javaloveJAVALOVE";
		String result = n.mySubstring(sparam, 5, 9);
		System.out.println(result);
	}

}
