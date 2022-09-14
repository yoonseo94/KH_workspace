package com.kh.spring.data.corona19;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	private String createDt; 	// 등록일시 2022-07-26 09:03:14.995
	private int deathCnt; 		// 사망자수 16
	private int defCnt; 		// 확진자수 11806
	private String gubun; 		// 시도명 제주
	private String gubunCn; 	// 시도명 济州
	private String gubunEn; 	// 시도명 Jeju
	private int incDec; 		// 전일대비 증감수 17
	private int localOccCnt; 	// 지역발생수 0
	private int overFlowCnt; 	// 해외유입수 17
	private String qurRate; 	// 10만명당 발생율 38556
	private int seq; 			// 고유번호 18344
	private String stdDay; 		// 기준일 2022년 07월 26일 00시
	private String updateDt; 	// 수정일 null
}
