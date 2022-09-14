package store.model.dao;

public class TodayDeal {
	private int todayDealNo;
	private String productId;
	private int discountRate;

	public TodayDeal() {
		super();
	}

	public TodayDeal(int todayDealNo, String productId, int discountRate) {
		super();
		this.todayDealNo = todayDealNo;
		this.productId = productId;
		this.discountRate = discountRate;
	}

	public int getTodayDealNo() {
		return todayDealNo;
	}

	public void setTodayDealNo(int todayDealNo) {
		this.todayDealNo = todayDealNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	@Override
	public String toString() {
		return "TodayDeal [todayDealNo=" + todayDealNo + ", productId=" + productId + ", discountRate=" + discountRate
				+ "]";
	}

	

}
