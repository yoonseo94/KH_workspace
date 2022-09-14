package product.model.dto;

import java.util.List;

import store.model.dao.TodayDeal;

public class ProductExt extends Product {

	private int productImgCount;
	private int discountRate;
	private List<ProductImage> productImages;
	private List<ProductDescriptionImage> productDescriptionImages;
	private List<TodayDeal> todayDeals;
	private String brandName;
	private String mainCategoryName;
	private String subCategoryName;
	public int getProductImgCount() {
		return productImgCount;
	}
	public void setProductImgCount(int productImgCount) {
		this.productImgCount = productImgCount;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	public List<ProductImage> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}
	public List<ProductDescriptionImage> getProductDescriptionImages() {
		return productDescriptionImages;
	}
	public void setProductDescriptionImages(List<ProductDescriptionImage> productDescriptionImages) {
		this.productDescriptionImages = productDescriptionImages;
	}
	public List<TodayDeal> getTodayDeals() {
		return todayDeals;
	}
	public void setTodayDeals(List<TodayDeal> todayDeals) {
		this.todayDeals = todayDeals;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getMainCategoryName() {
		return mainCategoryName;
	}
	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	@Override
	public String toString() {
		return "ProductExt [productImgCount=" + productImgCount + ", discountRate=" + discountRate + ", productImages="
				+ productImages + ", productDescriptionImages=" + productDescriptionImages + ", todayDeals="
				+ todayDeals + ", brandName=" + brandName + ", mainCategoryName=" + mainCategoryName
				+ ", subCategoryName=" + subCategoryName + ", getProductId()=" + getProductId() + ", getProductName()="
				+ getProductName() + ", getMainCode()=" + getMainCode() + ", getSubCode()=" + getSubCode()
				+ ", getBrandId()=" + getBrandId() + ", getProductHeight()=" + getProductHeight()
				+ ", getProductWidth()=" + getProductWidth() + ", getProductDepth()=" + getProductDepth()
				+ ", getProductColor()=" + getProductColor() + ", getProductPrice()=" + getProductPrice()
				+ ", getRegDate()=" + getRegDate() + ", getPContent()=" + getPContent() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}