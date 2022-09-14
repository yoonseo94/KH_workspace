package com.meshop.product.entity;

import java.sql.Date;

public class Product {
	private int productId;
	private String memberId;
	private String title;
	private String content;
	private String category;
	private String place;
	private ProductStatus status;
	private int price;
	private Date regDate;
	private int read_count;
	private String brand;
	
	public Product() {
	}
	
	public Product(int productId, String memberId, String title, String content, String category, String place,
			ProductStatus status, int price, Date regDate, int read_count, String brand) {
		super();
		this.productId = productId;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.category = category;
		this.place = place;
		this.status = status;
		this.price = price;
		this.regDate = regDate;
		this.read_count = read_count;
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", memberId=" + memberId + ", title=" + title + ", content="
				+ content + ", category=" + category + ", place=" + place + ", status=" + status + ", price=" + price
				+ ", regDate=" + regDate + ", read_count=" + read_count + ", brand=" + brand + "]";
	}


	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public ProductStatus getStatus() {
		return status;
	}
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	
	
	
}
