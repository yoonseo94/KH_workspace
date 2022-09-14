package cart.model.dto;

public class Cart {

	private int cartNo;
	private String memberId;
	private String productId;
	private int productCount;
	
	public Cart() {
		super();

	}

	public Cart(int cartNo, String memberId, String productId, int productCount) {
		super();
		this.cartNo = cartNo;
		this.memberId = memberId;
		this.productId = productId;
		this.productCount = productCount;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", memberId=" + memberId + ", productId=" + productId + ", productCount="
				+ productCount + "]";
	}

}
