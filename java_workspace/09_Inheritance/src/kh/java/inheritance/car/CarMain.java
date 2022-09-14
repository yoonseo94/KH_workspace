package kh.java.inheritance.car;

public class CarMain {

	public static void main(String[] args) {
		Car car1 = new Car("현대", "소나타", 4);
		System.out.println(car1);
//		System.out.println(car1.toString());
		// getClass().getName() + "@" + Integer.toHexString(hashCode())
		// kh.java.inheritance.car.Car@4d591d15
//		System.out.println(car1.getClass().getName());
//		System.out.println(Integer.toHexString(car1.hashCode()));
		System.out.println(car1.hashCode());
		
		Car car2 = new Car("현대", "소나타", 2);
		System.out.println(car2);
		System.out.println(car2.hashCode());
		
		// 동일성 indentity 두객체가 같은 주소값을 가지고 있는가  ==
		System.out.println(car1 == car2);
		// 동등성 equality 두객체의 정보가 같은가 equals
		System.out.println(car1.equals(car2));
		
		// Object#clone protected이고 다른패키지, 상속관계가 아니므로 접근할 수 없다. 
		Car car3 = car1.clone();
		System.out.println(car3);
		System.out.println(car1 == car3);
		
		
		
	}
	
}
