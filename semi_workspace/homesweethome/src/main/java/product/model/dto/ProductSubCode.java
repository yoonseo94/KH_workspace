package product.model.dto;

public class ProductSubCode {
	private String productSubCode;
	private String productMainCode;
	private String productSubName;

	public ProductSubCode() {
		super();
	}

	public ProductSubCode(String productSubCode, String productMainCode, String productSubName) {
		super();
		this.productSubCode = productSubCode;
		this.productMainCode = productMainCode;
		this.productSubName = productSubName;
	}

	public String getProductSubCode() {
		return productSubCode;
	}

	public void setProductSubCode(String productSubCode) {
		this.productSubCode = productSubCode;
	}

	public String getProductMainCode() {
		return productMainCode;
	}

	public void setProductMainCode(String productMainCode) {
		this.productMainCode = productMainCode;
	}

	public String getProductSubName() {
		return productSubName;
	}

	public void setProductSubName(String productSubName) {
		this.productSubName = productSubName;
	}

	@Override
	public String toString() {
		return "ProductSubCode [productSubCode=" + productSubCode + ", productMainCode=" + productMainCode
				+ ", productSubName=" + productSubName + "]";
	}
}