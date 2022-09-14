package member.model.vo;

/**
 * 추상메소드와 상수필드로만 구성된 추상클래스의 변형체.
 * 
 * 추상메소드 : 클래스에 추상메소드를 추가
 * 인터페이스 : 메소드의 통일성을 부여하기 위해 추상메소드만 모음. 
 * 
 * @author shqkel1863
 *
 */
public interface Buyable {
	int buy(int price);
}
