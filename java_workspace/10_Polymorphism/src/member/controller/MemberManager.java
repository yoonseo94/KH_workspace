package member.controller;

import member.model.vo.Member;

public class MemberManager {
	public static final int MAX_MEMBER = 40;
	private Member[] memberArr = new Member[MAX_MEMBER];
	private int memberIndex = 0;
	
	//다형성 
	public void insertMember(Member m){
		this.memberArr[memberIndex++] = m;
	}
	
	public void printData() {
		System.out.println("-----------정보출력-------------");
		System.out.printf("%-15s %-15s %-15s %-15s\n", "이름","등급","포인트","이자포인트");
		for(int i = 0; i < memberIndex; i++){
			Member m = memberArr[i];
			System.out.printf("%-15s %-15s %-15d %-15.2f\n", 
							  m.getName(), 
							  m.getGrade(), 
							  m.getPoint(), 
							  m.getEjapoint());
		}
	}
	
	//인터페이스: 
	//Member타입이 Buyable인터페이스를 구현하고 있으므로, 별도의 형변환 없이 buy메소드 사용가능
	public void printBuyInfo(int price) {
		for(int i = 0; i < memberIndex; i++)
			System.out.printf("%s등급회원 %s는 %d원 상품을 %d원에 구매합니다.\n", 
							  memberArr[i].getGrade(), 
							  memberArr[i].getName(), 
							  price, 
							  memberArr[i].buy(price)
							);
	}
}
