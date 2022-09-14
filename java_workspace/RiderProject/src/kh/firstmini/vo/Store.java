package kh.firstmini.vo;

import java.io.Serializable;
import java.time.LocalTime;

public class Store implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String storeID; // 매장고유번호
	private String storeName; // 매장명
	private String storeTel; // 전화번호
	private int riderTip; // 배달팁
	private int minOrderPrice; // 최소주문금액
	private String address; // 주소
	private String runTime; // 운영시간
	private LocalTime openTime; // 개점시간
	private LocalTime closeTime; // 폐점시간

	public Store() {
		super();
		// TODO Auto-generated constructor stub

	}

	public Store(String storeID, String storeName, String storeTel, int riderTip, int minOrderPrice, String address,
			String runTime) {
		super();
		this.storeID = storeID;
		this.storeName = storeName;
		this.storeTel = storeTel;
		this.riderTip = riderTip;
		this.minOrderPrice = minOrderPrice;
		this.address = address;
		this.runTime = runTime;

		String[] open_close = runTime.split("~");
		String[] hour_minute;
		for (int i = 0; i < open_close.length; i++) {
			hour_minute = open_close[i].split(":");

			if (i == 0)
				openTime = LocalTime.of(Integer.parseInt(hour_minute[0]), Integer.parseInt(hour_minute[1]));
			else if (i == 1)
				closeTime = LocalTime.of(Integer.parseInt(hour_minute[0]), Integer.parseInt(hour_minute[1]));
		}
	}

	public LocalTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(LocalTime openTime) {
		this.openTime = openTime;
	}

	public LocalTime getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(LocalTime closeTime) {
		this.closeTime = closeTime;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public int getRiderTip() {
		return riderTip;
	}

	public void setRiderTip(int riderTip) {
		this.riderTip = riderTip;
	}

	public int getMinOrderPrice() {
		return minOrderPrice;
	}

	public void setMinOrderPrice(int minOrderPrice) {
		this.minOrderPrice = minOrderPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	@Override
	public String toString() {
		return "Store [storeID=" + storeID + ", storeName=" + storeName + ", storeTel=" + storeTel + ", riderTip="
				+ riderTip + ", minOrderPrice=" + minOrderPrice + ", address=" + address + ", runTime=" + runTime + "]";
	}

}
