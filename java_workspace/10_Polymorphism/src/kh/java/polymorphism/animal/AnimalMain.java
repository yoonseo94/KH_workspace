package kh.java.polymorphism.animal;

import java.util.Random;

public class AnimalMain {

	public static void main(String[] args) {
		AnimalMain main = new AnimalMain();
//		main.test1();
//		main.test2();
//		main.test3();
//		main.test4();
//		main.test5();
//		main.test6();
//		main.test7();
//		main.test8();
//		main.test9();
//		main.test10();
//		main.test11();
		main.test12();
	}
	
	/**
	 * 추상클래스에서 인터페이스구현하기
	 * - 추상클래스는 부모추상메소드를 구현하지 않아도 된다.
	 */
	public void test12() {
		Animal[] animals = new Animal[4];
		animals[0] = new Tiger();
		animals[1] = new Lion();
		animals[2] = new Bear();
		animals[3] = new Eagle();
		
		for(Animal animal : animals) {
			System.out.println(animal.getSound());
		}
	}
	
	/**
	 * Eagle클래스를 작성하세요.
	 * - Animal클래스 상속
	 * - Flyable인터페이스를 구현
	 * 	 - fly추상메소드
	 */
	public void test11() {
		Eagle eagle = new Eagle();
		
		eagle.say();
		eagle.attack();
		eagle.fly(); // 독수리가 2날개로 날아갑니다.
	}
	
	/**
	 * 다중구현
	 * - 자식클래스는 여러개의 인터페이스를 구현할 수 있다.
	 */
	public void test10() {
		Tiger tiger = new Tiger();
		
		Washable washTiger = tiger;
		washTiger.wash();
//		washTiger.run();						
		
		Runnable runTiger = tiger;
		runTiger.run();
		
		Animal aniTiger = tiger;
		aniTiger.attack();
		aniTiger.say();
		
		
	}
	
	/**
	 * jdk8에 추가된 default메소드, static메소드
	 */
	public void test9() {
		Bear bear = new Bear();
		bear.walk();
		
		Runnable runner = new Tiger();
		runner.walk();
		
		Runnable.warmup();
	}
	
	/**
	 * 인터페이스와 구현클래스 사용하기
	 */
	public void test8() {
		Bear bear = new Bear();
		Runnable run = bear; // 다형성 적용
		
		bear.attack();
		bear.say();
		bear.run();
		
		// Runnable관련 기능만 사용가능
		run.run();
		System.out.println(Runnable.LEG_NUM);
		
		Runnable[] runners = new Runnable[3];
		runners[0] = new Tiger();
		runners[1] = new Lion();
		runners[2] = new Bear();
		
		for(Runnable runner : runners) {
			runner.run();
		}
	}
	
	/**
	 * 메소드 Override의 강제화 
	 * - 부모메소드를 추상메소드로 만들면 반드시 자식클래스에서 구현해야 한다.
	 * - 구현하지 않으면 컴파일 오류를 유발한다.
	 * 
	 * 1. 추상클래스의 추상메소드
	 * 2. 인터페이스의 추상메소드
	 * 
	 * - 규격 : Animal 모든 자식클래스는 say를 구현해야한다. 
	 */
	public void test7() {
		Animal[] animals = new Animal[3];
		animals[0] = new Tiger();
		animals[1] = new Lion();
		animals[2] = new Bear();
		
		for(int i = 0; i < animals.length; i++) {
//			animals[i].say();
			animals[i].attack();
		}
		
		// 추상클래스는 객체생성할 수 없다.
//		Animal a = new Animal();
		
	}
	
	
	/**
	 * 정적바인딩 - CompileTime에 결정. 호출한 메소드의 실행타입별로 실행할 메소드가 결정되는 것.
	 * 동적바인딩 (상속, 오버라이드, 다형성) - Runtime에 결정. 
	 * 	- 다형성을 적용해 부모타입으로 메소드를 실행하면 자식클래스의 오버라이드된 메소드가 호출
	 */
	public void test6() {
		// 정적바인딩
//		Animal animal = new Animal();
//		Tiger tiger = new Tiger();
//		animal.say();
//		tiger.say();
//		
//		// 동적바인딩
//		Animal animal2 = new Tiger();
//		animal2.say();
		
		
		// 동적바인딩
		Animal[] arr = new Animal[3];
		arr[0] = new Tiger();
		arr[1] = new Lion();
		arr[2] = new Lion();
		
		for(int i = 0; i < arr.length; i++) {
//			arr[i].say();
			arr[i].attack();
		}
		
	}
	
	/**
	 * 다형성의 활용2 - 리턴타입
	 */
	public void test5() {
		Animal animal = generateAnimal();
		System.out.println(animal);
	}
	
	public Animal generateAnimal() {
		Random rnd = new Random();
		if(rnd.nextBoolean())
			return new Tiger();
		else
			return new Lion();
	}
	
	/**
	 * 다형성의 활용1 - 매개변수선언부
	 */
	public void test4() {
		Tiger tiger = new Tiger();
		Lion lion = new Lion();
		
		action(tiger);
		action(lion);
	}
	
	public void action(Animal animal) {
		if(animal instanceof Tiger) {
			((Tiger) animal).punch();
		}
		else if(animal instanceof Lion) {
			((Lion) animal).kick();
		}
	}
	
//	public void action(Tiger tiger) {}
//	public void action(Lion lion) {}
	
	/**
	 * 부모타입의 변수로 모든 자식타입객체를 다룰수 있다.
	 * -> 부모타입의 배열을 만들어 여러 자식객체를 제어할 수 있다.
	 */
	public void test3() {
		// 변수 a에는 Animal 또는 Animal의 자식클래스는 다 대입가능하다.
		Animal a = null; 
		a = new Tiger();
		a = new Lion();
		
		Animal[] arr = new Animal[3];
		arr[0] = new Tiger();
		arr[1] = new Lion();
		arr[2] = new Tiger();
		
		
		for(Animal animal : arr) {
			System.out.println(animal + " " + (animal instanceof Tiger));
			// 객체 instanceof 클래스 : 객체가 해당클래스타입이면 true반환
			if(animal instanceof Tiger) {
				((Tiger) animal).punch();
			}
			else {
				((Lion) animal).kick();
			}
		}
		
		// instanceof 해당타입으로 형변환 가능한가?
		Tiger tiger = new Tiger();
		System.out.println(tiger instanceof Tiger);
		System.out.println(tiger instanceof Animal);
		System.out.println(tiger instanceof Object);
		
	}
	
	/**
	 * Object
	 * 	Animal
	 * 	 Tiger
	 */
	public void test2() {
		Tiger tiger = new Tiger();
		Animal animal = tiger;
		Object object = tiger;
		
		tiger.punch(); 						// Tiger#punch
		tiger.say(); 							// Animal#say
		System.out.println(tiger.hashCode()); 	// Object#hashCode
		
//		animal.punch(); 						// Tiger#punch X
		animal.say(); 							// Animal#say
		System.out.println(animal.hashCode()); 	// Object#hashCode
		
//		object.punch(); 						// Tiger#punch X
//		object.say(); 							// Animal#say  X
		System.out.println(object.hashCode()); 	// Object#hashCode
		
	}
	
	/**
	 * 다형성
	 * - 자식객체를 부모타입의 변수에 담아 제어할 수 있다.
	 * - 부모타입의 변수에 담게되면 부모클래스에 선언된 필드/메소드만 사용가능하다.
	 * 
	 */
	public void test1() {
		Tiger tiger = new Tiger();
		Animal animal1 = tiger; // 자동으로 UpCasting
		
		Lion lion = new Lion();
		Animal animal2 = lion;
		
		// 자식객체를 자식타입변수에 대입한 경우
		tiger.say();
		tiger.punch();
		
		// 자식객체를 부모타입변수에 대입한 경우 : 자식클래스에 선언한 필드/메소드에 접근할 수 없다.
		animal1.say();
		
		// 자식클래스의 메소드를 사용하고 싶다면, 다시 자식 타입으로 형변환해야 한다. 명시적으로 DownCasting
		((Tiger) animal1).punch();
		
		
		lion.say();
		lion.kick();
		animal2.say();
		((Lion) animal2).kick();
		
	}

}
