package kh.java.inheritance.product.after;


public class ProductAfterMain {

	public static void main(String[] args) {
		Desktop desktop = new Desktop("삼성", "ss-123462342", "울트라데스크탑", 1_000_000, 
				"Windows11", "커브드모니터", "기계식키보드", "버티컬마우스");
		System.out.println(desktop.getDesktopInfo());

		SmartPhone smartPhone = new SmartPhone("애플", "app-45437234", "iPhone13-Pro", 1_300_000, 
									"iOS15", "KT");
		System.out.println(smartPhone.getSmartPhoneInfo());
		System.out.println(smartPhone.getProductInfo());
		
		Tv tv = new Tv("LG", "lg-3462344", "QLED TV", 3_000_000, "UHD", 80);
		System.out.println(tv.getTvInfo());

	}

}
