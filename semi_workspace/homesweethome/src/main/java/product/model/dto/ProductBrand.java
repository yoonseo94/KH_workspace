package product.model.dto;

public class ProductBrand {
	private String brandId;
	private String brandName;
	
	public ProductBrand() {
		super();
	}
	
	public ProductBrand(String brandId, String brandName) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
	}
	
	public String getBrandId() {
		return brandId;
	}
	
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Override
	public String toString() {
		return "ProductBrand [brandId=" + brandId + ", brandName=" + brandName + "]";
	}
}