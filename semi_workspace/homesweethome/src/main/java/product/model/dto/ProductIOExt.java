package product.model.dto;

public class ProductIOExt extends ProductIO{
	private String productName;
	private int stock;
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductIOExt [productName=" + productName + ", stock=" + stock + ", getNo()=" + getNo()
				+ ", getProductId()=" + getProductId() + ", getCount()=" + getCount() + ", getStatus()=" + getStatus()
				+ ", getIoDateTime()=" + getIoDateTime() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}