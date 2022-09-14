package member.model.vo;

public class Vip extends Member {
	public Vip() {
	}

	public Vip(String name, String grade, int point) {
		super(name, grade, point);
	}

	@Override
	public double getEjapoint() {
		return super.getPoint() * 0.1;
	}

	@Override
	public int buy(int price) {
		return price - (int) (price * 0.1);
	}
}