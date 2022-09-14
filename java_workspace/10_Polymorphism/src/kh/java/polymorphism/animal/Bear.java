package kh.java.polymorphism.animal;

public class Bear extends Animal implements Runnable {

	/**
	 * 메소드 재작성
	 * Method Overriding
	 * Method Implementation
	 *  
	 */
	@Override
	public void say() {
		System.out.println("안녕하세요, 곰입니다.");
	}

	@Override
	public void attack() {
		upperCut();
	}
	
	public void upperCut() {
		System.out.println("곰표 어퍼컷!");
	}
	
	@Override
	public void run() {
		System.out.println("곰이 " + LEG_NUM + "발로 달립니다.");
	}

	@Override
	public String getSound() {
		return "우르~";
	}


	
	
}
