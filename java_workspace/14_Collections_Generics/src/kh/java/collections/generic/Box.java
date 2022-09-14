package kh.java.collections.generic;

/**
 * 
 * 제네릭 타입변수
 * - T type
 * - E element
 * - K key
 * - V value
 *
 * @param <T>
 */
public class Box<T> {
	
	private T elem;
	
	public void setElem(T elem) {
		this.elem = elem;
	}
	
	public T getElem() {
		return elem;
	}
	
	/**
	 * static 자원(필드/메소드)에는 클래스레벨의 제네릭 타입변수를 사용할 수 없다.
	 * (객체 생성전 타입변수의 타입을 지정할 수 없기때문이다.)
	 * 
	 * 제네릭메소드로 처리하면 가능하다. (static메소드는 제네릭메소드만 사용가능)
	 * - 리터타입 앞에 타입변수 선언 
	 * - 클래스레벨의 타입변수 T와 다른 지역타입변수(메소드안에서만 사용가능)
	 * 
	 * 
	 * 요소를 인자로 받아서 Box객체를 생성, 필드설정후 리턴
	 * 
	 * @param elem
	 * @return
	 */
	public static <T> Box<T> getBoxInstance(T elem){
		Box<T> box = new Box<>();
		box.setElem(elem);
		return box;
	}

	@Override
	public String toString() {
		return "Box [elem=" + elem + "]";
	}
	

}
