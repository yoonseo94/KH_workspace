package kh.java.inheritance.shape;

/**
 * has a 포함관계 : Circle has a Point
 * - Circle -----> Point (연관관계)
 * - 해당필드를 통해서 어떤 기능을 사용
 * 
 * is a 상속관계 : Circle is a Shape
 * - Circle -----> Shape (일반화관계)
 * - 상속받은 기능을 내것인양 사용
 * 
 * 
 * Override
 * - 메소드시그니쳐가 동일해야 한다. (메소드명, 매개변수타입/개수/순서, 리턴타입) -> @Override
 * - 접근제한자는 부모메소드보다 넓은 범위로 변경 가능 : protected메소드를 public메소드로 재작성가능
 * - private, final메소드는 오버라이드가 불가하다.
 * - 부모메소드가 던지는 예외를 제거하거나 한정할 수 있다.
 * 
 * - 오버라이드한 부모메소드는 super키워드를 통해 호출할 수 있다.
 * 
 */
public class Circle extends Shape {
	
	private Point center;
	private int r;
	
	public Circle() {
		super();
	}

	public Circle(Point center, int r) {
		super();
		this.center = center;
		this.r = r;
		calcArea();
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
	
	/**
	 * @Override Annotation
	 * - 부가적인 기능을 수행하는 문법요소
	 * - 부모메소드를 정말 재작성하는 지 검증. 메소드 시그니쳐가 다를경우 컴파일오류를 발생시킴.
	 */
	@Override 
	public void draw() {
		System.out.println("원을 그린다.");
	}
	
//	@Override
//	public void calcArea() {
//		setArea(r * r * Math.PI);
//	}
	
	
	/**
	 * Shape의 toString을 재작성
	 */
	@Override 
	public String toString() {
		return "center = " + center + ", r = " + r + ", " + super.toString();  
	}
	
}
