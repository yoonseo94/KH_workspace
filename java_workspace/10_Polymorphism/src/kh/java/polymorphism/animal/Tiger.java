package kh.java.polymorphism.animal;


public class Tiger extends Animal implements Runnable, Washable {

	public void punch() {
		System.out.println("호랑이 펀치를 날렸습니다.");
	}
	
	@Override
	public void attack() {
		punch();
	}
	
	@Override
	public void say() {
		System.out.println("안녕하세요. 타이거입니다.");
	}

	@Override
	public void run() {
		System.out.println("타이거가 " + Runnable.LEG_NUM + "발로 달립니다.");
	}

	@Override
	public void wash() {
		System.out.println("타이거는 냇가에서 샤워를 합니다.");
	}

	@Override
	public String getSound() {
		return "어흥~";
	}

	
}
