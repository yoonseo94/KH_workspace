package kh.java.polymorphism.product;

public class ProductMain {

	public static void main(String[] args) {
		ProductMain main = new ProductMain();
		Product[] products = main.test1(); // Product[3] - Desktop, SmartPhone, Tv
		
		for(Product product : products) {
//			main.test2(product); // 제품별 정보를 출력
			main.test3(product);
		}
	}
	
	
	/**
	 * 동적바인딩 적용
	 * @param product
	 * 
	 * 상속관계, 메소드Override, 다형성
	 */
	private void test3(Product product) {
		System.out.println(product.getProductInfo());
	}



	public Product[] test1() {
		// Product[] 생성
		Product[] products = new Product[3];
		products[0] = new Desktop("삼성", "ss-123462342", "울트라데스크탑", 1_000_000, "Windows11", "커브드모니터", "기계식키보드", "버티컬마우스");
		products[1] = new SmartPhone("애플", "app-45437234", "iPhone13-Pro", 1_300_000, "iOS15", "KT");
		products[2] = new Tv("LG", "lg-3462344", "QLED TV", 3_000_000, "UHD", 80);
		return products;
	}
	
	public void test2(Product product) {
		// Product 타입별 info메소드 호출
//		System.out.println(product);
		if(product instanceof Desktop)
			System.out.println(((Desktop) product).getDesktopInfo());
		else if(product instanceof SmartPhone)
			System.out.println(((SmartPhone) product).getSmartPhoneInfo());
		else if(product instanceof Tv)
			System.out.println(((Tv) product).getTvInfo());		
		
	}
}
