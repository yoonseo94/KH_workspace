package member.model.vo;

public class Ruby extends Member {
	public Ruby() {
	}

	public Ruby(String name, String grade, int point) {
		super(name, grade, point);
	}

	@Override
	public double getEjapoint() {
		return super.getPoint() * 0.3;
	}

	@Override
	public int buy(int price) {
		return price - (int) (price * 0.3);
	}
}
