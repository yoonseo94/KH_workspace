package member.controller;

import member.model.vo.Gold;
import member.model.vo.Silver;
import member.model.vo.VVip;
import member.model.vo.Vip;

public class MemberManager {

	public static final int MAX_MEMBER_COUNT = 10;
	Silver[] silvers = new Silver[MAX_MEMBER_COUNT];
	Gold[] golds = new Gold[MAX_MEMBER_COUNT];
	Vip[] vips = new Vip[MAX_MEMBER_COUNT];
	VVip[] vvips = new VVip[MAX_MEMBER_COUNT];
	
	// 1.각등급의 회원이 추가될때 index에 저장되고, index는 1을 증가시킨다.(다음 회원의 인덱스)
	// 2.현재까지 등록된 회원수를 가리킨다.
	int silverIndex = 0;
	int goldIndex = 0;
	int vipIndex = 0;
	int vvipIndex = 0;
	
	
	public void silverInsert(Silver s) {
		this.silvers[silverIndex++] = s;
	}
	public void goldInsert(Gold g) {
		this.golds[goldIndex++] = g;
	}
	public void vipInsert(Vip v){
		this.vips[vipIndex++] =  v;
	}
	public void vvipInsert(VVip vv){
		this.vvips[vvipIndex++] =  vv;
	}
	
	public void printData() {
		System.out.println("----------------------------------------<<회원정보>>-----------------------------------------");
		System.out.printf("%-15s %-15s %-15s %-15s\n", "이름","등급","포인트","이자포인트");
		System.out.println("------------------------------------------------------------------------------------------------");
		for(int i=0; i<silverIndex;i++) {
			System.out.printf("%-15s %-15s %-15d %-15.2f\n", silvers[i].getName(), silvers[i].getGrade(), silvers[i].getPoint(), silvers[i].getEjapoint());			
		}
		for(int i=0; i<goldIndex;i++) {
			System.out.printf("%-15s %-15s %-15d %-15.2f\n", golds[i].getName(), golds[i].getGrade(), golds[i].getPoint(), golds[i].getEjapoint());
		}
		for(int i=0; i<vipIndex; i++){
			System.out.printf("%-15s %-15s %-15d %-15.2f\n", vips[i].getName(), vips[i].getGrade(), vips[i].getPoint(), vips[i].getEjapoint());
		}
		for(int i=0; i<vvipIndex; i++){
			System.out.printf("%-15s %-15s %-15d %-15.2f\n", vvips[i].getName(), vvips[i].getGrade(), vvips[i].getPoint(), vvips[i].getEjapoint());
		}
	}
}
