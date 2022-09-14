package kh.java.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressStudy {

	public static void main(String[] args) {
		InetAddressStudy study = new InetAddressStudy();
		study.test1();
	}

	/**
	 * java.net패키지에 네트워크 관련 클래스가 있다.
	 * - InetAddress 
	 * 	- IP(Internet Protocol) Address에 관련한 클래스
	 *  - 도메인이름을 가지고 DNS(Domain Name Server)에 질의해서 ip를 가져옴
	 *  - new연산자 사용불가 
	 */
	private void test1() {
		try {
			// 특정서버
			InetAddress naver = InetAddress.getByName("naver.com");
			// ip 조회
			System.out.println(naver.getHostAddress());
			
			// 내컴퓨터
			InetAddress localhost = InetAddress.getLocalHost();
			System.out.println(localhost.getHostAddress());
			
			// ip가 여러개인 경우
			InetAddress[] navers = InetAddress.getAllByName("naver.com");
			
			for(InetAddress n : navers) {
				System.out.println(n);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
