<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/purchase.css" />
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<%= request.getContextPath() %>/js/memberSignUp.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String saveId = null; 
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie cookie : cookies){
			if("saveId".equals(cookie.getName())){
				System.out.println(cookie.getName());
				saveId = cookie.getValue();
			}
		}
	}
	
	String memberId = loginMember.getMemberId();
	String memberName = loginMember.getMemberName();
	String nickname = loginMember.getNickname();
	String email = loginMember.getEmail() != null ? loginMember.getEmail() : "";
	String phone = loginMember.getPhone();
	String gender = loginMember.getGender() != null ? loginMember.getGender() : "";
	
	String totalPrice = request.getParameter("totalPrice");

%>
<script>
</script>
<br />
<div class = "main-content">
	<main class = "userinfo-cartinfo-list">
		<h1>주문/결제</h1>
		<hr />
			<section class="whole-container">
				<section class="title">
					<div class="title-name">주문자</div>
				</section>
				<div class="title-container">
					<section class="content">
						<label for="memberName">
							<div class="title-name">이름</div>
							<div class="input_container">
								<input class="typeteg" type="text" name="memberName" id="memberName" maxlength="10" value="<%= memberName %>"/>
							</div>
						</label>
						<label for="memberEmail">
							<div class="title-name">이메일</div>
							<div class="input_container">
								<input class="typeteg" type="text" name="memberEmail" id="memberEmail" maxlength="20" value="<%= email %>"/>
							</div>
						</label>
						<label for="phone1">
							<div class="title-name">휴대전화</div>
							<div class="input_container">
								<input class="typeteg" type="tel" name="phone" id="phone" placeholder="입력해주세요." size="11" maxlength="11" value="<%= phone %>"/>
							</div>
						</label>
					</section>
				</div>
			</section>
			<hr />
			<section class="whole-container">
				<section class="title">
					<div class="title-name">배송지</div>
					<button type="button" onclick="sameInfo();" class="address-btn">위와 동일하게 채우기</button>
				</section>
				<div class="title-container">
					<section class="content">
						<label for="del-name">
							<div class="title-name" id="del-name" name= "del-name">배송지명</div>
							<div class="input_container">
								<input class="typeteg" type="text" name="name" maxlength="10" />
							</div>
						</label>
						<label for="recipientName">
							<div class="title-name">받는 사람</div>
							<div class="input_container">
								<input class="typeteg" type="text" name="recipientName" id="recipientName" maxlength="10" />
							</div>
						</label>
						<label for="phone2">
							<div class="title-name">휴대전화</div>
							<div class="input_container">
								<input class="typeteg" type="tel" name="recipientPhone" id="recipientPhone" placeholder="입력해주세요." size="11" maxlength="11" value=""/>
							</div>
						</label>
						<label for="recipienAddress">
							<div class="title-name">주소</div>
							<div class="address-info">
								<div class="address">
									<button onclick="searchAddress();" type="button" class="btn_blue">주소 찾기</button>
									<input class="typeteg2" type="text" id="postcode" name="postcode" placeholder="우편번호" readonly>
								</div>
								<div class="detail">
									<div class="input_container">
										<div class="">
											<input class="typeteg" type="text" id="address" name="address" placeholder="기본주소" readonly>
										</div>
										<div class="">
											<input class="typeteg" type="text" id="address_detail" name="address_detail" placeholder="상세주소">
										</div>
										<div class="">
											<input class="typeteg" type="text" id="extraAddress" name="extraAddress" placeholder="참고사항" readonly>
										</div>
									</div>
								</div>
							</div>
						</label>
						<div class="">
							<div class="input_container">
								<select class="request">
									<option value="0">배송시 요청사항을 선택해주세요</option>
									<option value="1">부재시 문앞에 놓아주세요</option>
									<option value="2">배송전에 미리 연락주세요</option>
									<option value="3">부재시 경비실에 맡겨 주세요</option>
									<option value="4">부재시 전화주시거나 문자 남겨 주세요</option>
									<option value="5">직접입력</option>
								</select>
							</div>
						</div>
					</section>
				</div>
			</section>
			<hr />
			<section class="whole-container">
				<section class="title">
					<div class="title-name">결제 수단</div>
				</section>
				<div class="title-container">
					<section class="content">
						<label for="memberName">
							<div class="btnpay">
								<button type="button" class="pay-way" id="pay1">
									<picture><source type="image/webp" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=144&amp;h=144&amp;c=c&amp;webp=1" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=180&amp;h=180&amp;c=c&amp;webp=1 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=360&amp;h=360&amp;c=c&amp;webp=1 3x"><img alt="카드 아이콘" class="css-1i2eqgi e149t0mw2" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=144&amp;h=144&amp;c=c" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=144&amp;h=144&amp;c=c 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=180&amp;h=180&amp;c=c 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_card.png?w=360&amp;h=360&amp;c=c 3x"></picture>
									<div class="button-name">카드</div>
								</button>
								<button type="button" class="pay-way" id="pay2">
									<picture><source type="image/webp" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=144&amp;h=144&amp;c=c&amp;webp=1" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=180&amp;h=180&amp;c=c&amp;webp=1 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=360&amp;h=360&amp;c=c&amp;webp=1 3x"><img alt="무통장입금 아이콘" class="css-1i2eqgi e149t0mw2" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=144&amp;h=144&amp;c=c" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=144&amp;h=144&amp;c=c 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=180&amp;h=180&amp;c=c 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_vbank.png?w=360&amp;h=360&amp;c=c 3x"></picture>
									<div class="button-name">무통장입금</div>
								</button>
								<button type="button" class="pay-way" id="pay3">
									<picture><source type="image/webp" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=144&amp;h=144&amp;c=c&amp;webp=1" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=180&amp;h=180&amp;c=c&amp;webp=1 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=360&amp;h=360&amp;c=c&amp;webp=1 3x"><img alt="카카오페이 아이콘" class="css-1i2eqgi e149t0mw2" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=144&amp;h=144&amp;c=c" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=144&amp;h=144&amp;c=c 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=180&amp;h=180&amp;c=c 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_kakaopay.png?w=360&amp;h=360&amp;c=c 3x"></picture>
									<div class="button-name">카카오페이</div>
								</button>
								<button type="button" class="pay-way" id="pay4">
									<picture><source type="image/webp" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=144&amp;h=144&amp;c=c&amp;webp=1" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=180&amp;h=180&amp;c=c&amp;webp=1 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=360&amp;h=360&amp;c=c&amp;webp=1 3x"><img alt="토스 아이콘" class="css-1i2eqgi e149t0mw2" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=144&amp;h=144&amp;c=c" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=144&amp;h=144&amp;c=c 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=180&amp;h=180&amp;c=c 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_toss.png?w=360&amp;h=360&amp;c=c 3x"></picture>
									<div class="button-name">토스</div>
								</button>
								<button type="button" class="pay-way" id="pay5">
									<picture><source type="image/webp" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=144&amp;h=144&amp;c=c&amp;webp=1" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=180&amp;h=180&amp;c=c&amp;webp=1 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=360&amp;h=360&amp;c=c&amp;webp=1 3x"><img alt="페이코 아이콘" class="css-1i2eqgi e149t0mw2" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=144&amp;h=144&amp;c=c" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=144&amp;h=144&amp;c=c 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=180&amp;h=180&amp;c=c 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_payco.png?w=360&amp;h=360&amp;c=c 3x"></picture>
									<div class="button-name">페이코</div>
								</button>
								<button type="button" class="pay-way" id="pay6">
									<picture><source type="image/webp" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=144&amp;h=144&amp;c=c&amp;webp=1" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=180&amp;h=180&amp;c=c&amp;webp=1 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=360&amp;h=360&amp;c=c&amp;webp=1 3x"><img alt="네이버페이 아이콘" class="css-1i2eqgi e149t0mw2" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=144&amp;h=144&amp;c=c" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=144&amp;h=144&amp;c=c 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=180&amp;h=180&amp;c=c 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_naver.png?w=360&amp;h=360&amp;c=c 3x"></picture>
									<div class="button-name">네이버페이</div>
								</button>
								<button type="button" class="pay-way" id="pay7">
									<picture><source type="image/webp" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=144&amp;h=144&amp;c=c&amp;webp=1" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=144&amp;h=144&amp;c=c&amp;webp=1 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=180&amp;h=180&amp;c=c&amp;webp=1 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=360&amp;h=360&amp;c=c&amp;webp=1 3x"><img alt="핸드폰 아이콘" class="css-1i2eqgi e149t0mw2" src="https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=144&amp;h=144&amp;c=c" srcset="https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=144&amp;h=144&amp;c=c 1.5x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=180&amp;h=180&amp;c=c 2x,https://image.ohou.se/i/bucketplace-v2-development/pg/img_phone.png?w=360&amp;h=360&amp;c=c 3x"></picture>
									<div class="button-name">핸드폰</div>
								</button>
							</div>
							<div class="payway-info" id="paywayInfo">
							</div>
						</label>
					</section>
				</div>
			</section>
	</main>
	<div class="total-purchase-info">
		<div class="sticky-container" style="position : sticky; top: 81px; transition: top 0.1s; ease 0s;">
			<div class="" style="position : relative;">
				<div class="" style="padding-top: 40px;">
					<div class ="total-purchaise-view">
						<div class = "payment">
							<div class="pay-info">
								<h2 style="font-size: 20px; line-height: 30px; margin-bottom: 20px; font-weight: bold;">결제금액</h2>
								<div class="amount">
									<div class="amount-name">총 상품금액</div>
									<div class="amount-content"><%= totalPrice %>원</div>
								</div>
								<div class="amount">
									<div class="amount-name">배송비</div>
									<div class="amount-content">0원</div>
								</div>
								<div class="total-amount">
									<div class="total-amount-name">최종결제금액</div>
									<div class="total-amount-content"><strong style ="font-weight : bold;"><span style="color: rgb(53, 197, 240);font-family: Tahoma, sans;"><%= totalPrice %></span>&nbsp;원</strong></div>					
								</div>
							</div>
						</div>
						<div class = "consent">
							<label for="checkout-agree" class="consent-label">
								<div class="">
									<input type="checkbox" class="_3UImz" id="checkout-agree" name="checkout-agree" value="">&nbsp;아래 내용에 모두 동의합니다. (필수)
								</div>
							</label>
							<div class= "consent-content">
								<div class= "consent-read">
									"개인정보 수집 이용 및 제 3자 제공동의"
									<div class="sub-read">
										"본인은 만 14세 이상이며, 주문내용을 확인하였습니다."
									</div>
								</div>
							</div>
						</div>
					</div>
					<button class="purchase-btn" type="button"><%= totalPrice %>원 결제하기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<style>
.pay-choice{margin-bottom: 12px; font-size: 15px; font-weight: bold; line-height: 18px; color: rgb(82, 91, 97);}
.chicoe-detail{font-size: 13px; line-height: 20px; color: rgb(101, 110, 117); }
</style>
<script>
// 위와 같은 정보 가져오기 메소드
const sameInfo = () =>{
	const recipientName = document.querySelector("#memberName").value;
	const recipientPhone = document.querySelector("#phone").value;
	
	document.querySelector("#recipientName").value = recipientName;
	document.querySelector("#recipientPhone").value = recipientPhone;
};

pay1.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/purchase/paywayInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			paywayInfo.innerHTML = '<div class="pay-choice">카드 결제 혜택</div><div class ="chicoe-detail">- 페이북 마이태그 결제혜택, 10만원 이상 결제 시 5천원 결제일 할인, 6/1~30 (기간 내 1인 1회)</br>- 마이태그 혜택은 결제 전, 페이북에서 오늘의집 할인 혜택을 태그해야 적용 가능</div>';
		},
		error : console.log
	});
});

pay2.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/purchase/paywayInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			paywayInfo.innerHTML = '<div class="chicoe-detail">- 현금으로 결제한 모든 금액은 우리은행과 채무지급보증계약을 체결하여 고객님의 안전거래를 보장하고 있습니다.</div>';
		},
		error : console.log
	});
});

pay3.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/purchase/paywayInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			paywayInfo.innerHTML = '<div class="pay-choice">카카오페이 결제 혜택</div> <div class ="chicoe-detail">- 카카오페이 내 KB국민카드로 20만원/10만원 이상 결제 시 1만원/5천원 즉시할인<br/>- 6/16~30 (기간 내 각각 1회 사용 가능)</div>';
		},
		error : console.log
	});
});

pay4.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/purchase/paywayInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			paywayInfo.innerHTML = '<div class="pay-choice">토스페이 후불결제 오픈</div> <div class ="chicoe-detail">- 토스페이로 결제 시 1.5% 캐시백 (최대 30,000원), 기간 내 1인 10회, 5/30~6/19<br/>- 토스페이 생애 첫 결제시, 3천원 캐시백 (1만원 이상 결제 시), 6/1~30</div>';
		},
		error : console.log
	});
});

pay5.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/purchase/paywayInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			paywayInfo.innerHTML = '<div class="pay-choice">페이코 결제 혜택</div> <div class ="chicoe-detail">- 페이코포인트로 결제 시 3% 적립 (한도/횟수 무제한)<br/>- 6/1~30</div>';
		},
		error : console.log
	});
});

pay6.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/purchase/paywayInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			paywayInfo.innerHTML = '<div class="pay-choice">네이버페이 결제 혜택</div> <div class ="chicoe-detail">- 네이버페이 결제 기본 1%적립, 포인트 결제 1.5%적립 + 소득공제- 네이버쇼핑 유입 +1%, 그 외 0.2%</div>';
		},
		error : console.log
	});
});

pay7.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/purchase/paywayInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			paywayInfo.innerHTML = '';
		},
		error : console.log
	});
});



</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>