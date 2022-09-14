package kh.java.polymorphism.animal;

public class Eagle extends Animal implements Flyable {

	@Override
	public void say() {
		System.out.println("안녕하세요, 이글입니다.");
	}

	@Override
	public void attack() {
		System.out.println("이글이 수직낙하후 쪼았습니다.");
	}
	
	@Override
	public void fly() {
		System.out.println("이글이 " + WING_NUM + "날개로 날아갑니다.");
	}

	@Override
	public String getSound() {
		return "이글이글";
	}

}
