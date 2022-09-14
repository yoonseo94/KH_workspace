package kh.java.polymorphism.animal;

public class Lion extends Animal implements Runnable {

	public void kick() {
		System.out.println("라이언 킥을 날렸습니다.");
	}
	
	@Override
	public void attack() {
		kick();
	}
	
	@Override
	public void say() {
		System.out.println("안녕하세요. 라이언입니다.");
	}

	@Override
	public void run() {
		System.out.println("라이언이 " + LEG_NUM + "발로 달립니다.");
	}

	@Override
	public String getSound() {
		return "롸이언~~~";
	}

}
