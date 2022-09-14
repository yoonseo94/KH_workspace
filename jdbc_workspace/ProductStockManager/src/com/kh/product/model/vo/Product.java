package com.kh.product.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;

public class Product implements Serializable{
	
	private String id;
	private String brand;
	private String name;
	private int price;
	private int monitorSize;
	private String os;
	private int storage;
	private Date regDate;
	
	private int stock;
		
	public Product(){}

	public Product(String id, String brand, String name, int price, int monitorSize, String os, int storage,
			Date regDate, int stock) {
		super();
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.monitorSize = monitorSize;
		this.os = os;
		this.storage = storage;
		this.regDate = regDate;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMonitorSize() {
		return monitorSize;
	}

	public void setMonitorSize(int monitorSize) {
		this.monitorSize = monitorSize;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return id + "\t" + brand + "\t" + name + "\t\t" + new DecimalFormat("#,###").format(price) + "원\t"
				+ monitorSize + "inch / " + os + " / " + storage + "GB\t" + stock;
	}
}
