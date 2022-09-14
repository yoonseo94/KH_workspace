package product.model.dto;

public class ProductMainCode {
	private String productMainCode;
	private String productMainName;
	
	public ProductMainCode() {
		super();
	}

	public ProductMainCode(String productMainCode, String productMainName) {
		super();
		this.productMainCode = productMainCode;
		this.productMainName = productMainName;
	}

	public String getProductMainCode() {
		return productMainCode;
	}

	public void setProductMainCode(String productMainCode) {
		this.productMainCode = productMainCode;
	}

	public String getProductMainName() {
		return productMainName;
	}

	public void setProductMainName(String productMainName) {
		this.productMainName = productMainName;
	}

	@Override
	public String toString() {
		return "ProductMainCode [productMainCode=" + productMainCode + ", productMainName=" + productMainName + "]";
	}
}