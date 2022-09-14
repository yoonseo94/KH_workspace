package member.model.vo;

public class Silver extends Member {

	public Silver() {
	}

	public Silver(String name, String grade, int point) {
		super(name, grade, point);
	}

	@Override
	public double getEjapoint() {
		return super.getPoint() * 0.02;
	}

	@Override
	public int buy(int price) {
		return price - (int) (price * 0.02);
	}
}