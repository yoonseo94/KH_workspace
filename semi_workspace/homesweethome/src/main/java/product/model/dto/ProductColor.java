package product.model.dto;

public class ProductColor {
	private String productColor;
	private String productColorName;
	
	public ProductColor() {
		super();		
	}
	
	public ProductColor(String productColor, String productColorName) {
		super();
		this.productColor = productColor;
		this.productColorName = productColorName;
	}
	
	public String getProductColor() {
		return productColor;
	}
	
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	
	public String getProductColorName() {
		return productColorName;
	}
	
	public void setProductColorName(String productColorName) {
		this.productColorName = productColorName;
	}
	
	@Override
	public String toString() {
		return "ProductColor [productColor=" + productColor + ", productColorName=" + productColorName + "]";
	}
	
	
}
