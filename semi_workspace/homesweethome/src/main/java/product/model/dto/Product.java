package product.model.dto;

import java.sql.Date;

public class Product {

	private String productId;
	private String productName;
	private String mainCode;
	private String subCode;
	private String brandId;
	private double productHeight;
	private double productWidth;
	private double productDepth;
	private String productColor;
	private int productPrice;
	private Date regDate;
	private String pContent;
	
	public Product() {
		super();
	}

	public Product(String productId, String productName, String mainCode, String subCode, String brandId,
			double productHeight, double productWidth, double productDepth, String productColor, int productPrice,
			Date regDate, String pContent) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.mainCode = mainCode;
		this.subCode = subCode;
		this.brandId = brandId;
		this.productHeight = productHeight;
		this.productWidth = productWidth;
		this.productDepth = productDepth;
		this.productColor = productColor;
		this.productPrice = productPrice;
		this.regDate = regDate;
		this.pContent = pContent;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMainCode() {
		return mainCode;
	}

	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public double getProductHeight() {
		return productHeight;
	}

	public void setProductHeight(double productHeight) {
		this.productHeight = productHeight;
	}

	public double getProductWidth() {
		return productWidth;
	}

	public void setProductWidth(double productWidth) {
		this.productWidth = productWidth;
	}

	public double getProductDepth() {
		return productDepth;
	}

	public void setProductDepth(double productDepth) {
		this.productDepth = productDepth;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getPContent() {
		return pContent;
	}

	public void setPContent(String pContent) {
		this.pContent = pContent;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", mainCode=" + mainCode
				+ ", subCode=" + subCode + ", brandId=" + brandId + ", productHeight=" + productHeight
				+ ", productWidth=" + productWidth + ", productDepth=" + productDepth + ", productColor=" + productColor
				+ ", productPrice=" + productPrice + ", regDate=" + regDate + ", pContent=" + pContent + "]";
	}

	
}