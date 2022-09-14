package com.kh.jdk8.api.optional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;

public class OptionalStudy {

	public static void main(String[] args) {
		OptionalStudy study = new OptionalStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}
	
	
	/**
	 * Optional#filter
	 */
	private void test3() {
		Stream
			.of(30, 50, 60, -35, 130, null, new Integer(88))
			.filter(this::isValid)
			.forEach(System.out::println);
	}
	
	public boolean isValid(Integer i) {
		return Optional.ofNullable(i).filter(n -> (n >= 0 && n <= 100)).isPresent();
	}



	/**
	 * 주문객체의 회원주소 조회하기
	 */
	private void test2() {
		Address address = new Address("서울", "강남구 역삼동", "00234");
		Member member = new Member(100L, "홍길동", address);
		Order order = new Order(123L, LocalDateTime.now(), member);
		
		String street = getStreetOfMemberOfOrder(order);
		System.out.println(street);
		
		
		Order crazyOrder = new Order(456L, LocalDateTime.now(), null);
		street = getStreetOfMemberOfOrder(crazyOrder);
		System.out.println(street);
		
		
	}

	private String getStreetOfMemberOfOrder(Order order) {
		return Optional.ofNullable(order)
				.map(Order::getMember) 		// Optional<Member>
				.map(Member::getAddress) 	// Optional<Address>
				.map(Address::getStreet) 	// Optional<String>
				.orElseGet(() -> null);
	}

//	private String getStreetOfMemberOfOrder(Order order) {
//		if(order != null) {
//			Member member = order.getMember();
//			if(member != null) {
//				Address address = member.getAddress();
//				if(address != null) {
//					return address.getStreet();
//				}
//			}
//			
//		}
//		return null;
//	}

	/**
	 * 문자열의 길이 체크
	 */
	private void test1() {
		int len = getStrLen("홍길동");
		System.out.println(len);
		len = getStrLen("");
		System.out.println(len);
		len = getStrLen(null);
		System.out.println(len);
	}

	/**
	 * Optional 종류
	 * - Optional.empty()
	 * - Optional.of(value)
	 * - Optional.ofNullable(value)
	 * 
	 * @param str
	 * @return
	 */
	private int getStrLen(String str) {
		Optional<String> maybeStr = Optional.ofNullable(str);
		return maybeStr.map(s -> s.length()).orElseThrow(() -> new RuntimeException("nuuuuuuuuuuuuuuuuuulllll"));
	}

}


@Data
@AllArgsConstructor
class Order {
	private Long id;
	private LocalDateTime datetime;
	private Member member;
}

@Data
@AllArgsConstructor
class Member {
	private Long id;
	private String name;
	private Address address;
}

@Data
@AllArgsConstructor
class Address {
	private String city;
	private String street;
	private String zipCode;
}




