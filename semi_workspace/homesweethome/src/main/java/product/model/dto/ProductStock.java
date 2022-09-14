package product.model.dto;

public class ProductStock {
	private String productId;
	private int stock;
	
	public ProductStock() {
		super();
	}

	public ProductStock(String productId, int stock) {
		super();
		this.productId = productId;
		this.stock = stock;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductStock [productId=" + productId + ", stock=" + stock + "]";
	}
}
