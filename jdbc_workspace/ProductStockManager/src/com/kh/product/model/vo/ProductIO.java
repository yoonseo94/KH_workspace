package com.kh.product.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ProductIO extends Product implements Serializable {
	
	private int no;
	private String productId;
	private int count;
	private String status; 
	private Timestamp ioDate;
	
	public ProductIO(){}

	public ProductIO(int no, String productId, int count, String status, Timestamp ioDate) {
		super();
		this.no = no;
		this.productId = productId;
		this.count = count;
		this.status = status;
		this.ioDate = ioDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getIoDate() {
		return ioDate;
	}

	public void setIoDate(Timestamp ioDate) {
		this.ioDate = ioDate;
	}

	@Override
	public String toString() {
		return no + "\t" + productId + "\t" + getName() + "\t" + getBrand() + "\t" 
			 + count + "\t" + status + "\t" + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(ioDate);
	}
	
	
	
}
