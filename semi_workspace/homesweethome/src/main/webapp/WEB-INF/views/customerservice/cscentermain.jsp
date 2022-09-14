<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customerservice/cscentermain.css" />
	<div id="cs-content-wrapper">
		<section id="cs-container-top">
			<div id="top-wrapper">
				<div id="top-first-cont">
					<h2 class="top-h2-1">무엇을 도와드릴까요?</h2>
					<ul>     
						<li class="top-li"><a href="javascript: viewAnswerDelivery()" class="top-a"><span class="q-css">Q</span>  배송은 얼마나 걸리나요?</a></li>
						<li class="top-li"><a href="javascript: viewAnswerOrder()" class="top-a"><span class="q-css">Q</span>  주문 취소는 어떻게 하나요?</a></li>
						<li class="top-li"><a href="javascript: viewAnswerEtc()" class="top-a"><span class="q-css">Q</span> 제품의 자세한 정보를 알고 싶어요.</a> </li>
						<li class="top-li"><a href="javascript: viewAnswerRefund()" class="top-a"><span class="q-css">Q</span>  제품이 불량일 때는?</a></li>
						<li class="top-li"><a href="javascript: viewAnswerMember()" class="top-a"><span class="q-css">Q</span>  카카오 계정으로 로그인하면 이미 가입되었다고 합니다.</a></li>
					</ul>
				</div>
				<div id="top-second-cont">
					<h2 class="top-h2-2">고객센터 평일 <span class="cs-time">09:00 ~ 18:00</span></h2>
					<p class="holiday-text">주말/공휴일 휴무</p>
					<p>
						<span class="call-img-wrapper"><img src="<%=request.getContextPath() %>/images/customerservice/call.png" alt="전화기 아이콘" id="call-img" /></span>
						<strong class="contact-us">1111-1111</strong>
					</p>
					<div id="btn-wrapper">
						<button id="kakaotalk-btn" onclick="homesweethomeChat();">1:1 카톡 상담하기</button>
						<a href="<%= request.getContextPath() %>/customerservice/sendEmail" id="email-send">이메일 문의하기</a>
						<button id="email-copy-btn" onclick="copyEmail();">이메일 주소 복사하기</button>
					</div>
				</div>
			</div>
		</section>
		<div class="nav-tab-container">
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-all" >
				<input type="checkbox" class="nav-input-check" />
				<span class="span-text all" id="all">전체</span> 
			</div> 
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-order">
				<input type="checkbox" class="nav-input-check order"/>
				<span class="span-text order" id="order" >주문/결제</span>
			</div>
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-delivery">
				<input type="checkbox" class="nav-input-check delivery" />
				<span class="span-text delivery"  id="delivery">배송관련</span>
			</div>
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-refund">
				<input type="checkbox" class="nav-input-check refund" />
				<span class="span-text refund" id="refund">취소/환불</span>
			</div>
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-exchange">
				<input type="checkbox" class="nav-input-check exchange" />
				<span class="span-text exchange"  id="exchange">반품/교환</span>
			</div>
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-cert">
				<input type="checkbox" class="nav-input-check cert"/>
				<span class="span-text cert"  id="cert">증빙서류발급</span>
			</div>
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-member">
				<input type="checkbox" class="nav-input-check member"/>
				<span class="span-text member" id="member" >로그인/회원정보</span>
			</div>
			<div class="nav-tab-wrapper" id= "nav-tab-wrapper-etc">
				<input type="checkbox" class="nav-input-check etc"/>
				<span class="span-text etc" id="etc" >서비스/기타</span>
			</div>
		</div> 
		<section id="cs-container-bottom">
		<ul class="faq-container">
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">주문 내역은 어떻게 확인할 수 있나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3> 
				<div class="answer-wrapper">
					<p class="answer-text">우측 상단 프로필 사진을 클릭 후 [나의쇼핑]을 통해 확인 가능합니다</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">결제 방법은 어떤 것이 있나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">신용카드 및 체크카드, 무통장 입금, 휴대폰 소액결제, 네이버페이를 이용해 결제 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">비회원 주문 및 전화주문이 가능한가요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">전화주문은 준비중에 있습니다.</p>
					<p class="answer-text">비회원 주문은 가능하지만 일부 상품에 한해 제한되어있습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">신용카드 무이자 할부가 되나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">각 카드사 별로 상이하며, 카드사를 통해 확인 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">신용카드 결제 후 할부 개월 수를 변경 가능한가요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">결제 후 결제 정보 변경은 불가능 합니다.</p>
					<p class="answer-text">단, 결제 완료 단계에서는 취소 후 재주문을 통해 변경 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">가상계좌 은행을 변경할 수 있나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">결제 후 결제 정보 변경은 불가능 합니다.</p>
					<p class="answer-text">단, 결제 완료 단계에서는 취소 후 재주문을 통해 변경 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">주문 후 결제 방법을 변경하고 싶은데 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">결제 후 결제 정보 변경은 불가능합니다.</p>
					<p class="answer-text">단, 입금 대기 및 결제 완료 단계에서는 취소 후 재주문을 통해 변경 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">결제 후 에러 메시지가 나오는 경우 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">우측 상단 프로필 사진을 클릭 후 [고객센터 > 1:1 카톡 상담하기]를 통해 문의 부탁드립니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">신용카드 안전결제(ISP)는 무엇인가요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">국민카드, BC카드는 인터넷 안전결제(ISP)로 결제가 진행됩니다.</p>
					<p class="answer-text">결제진행시 나타나는 안내에 따라 결제하실 카드번호와 사용하시는 이메일 및 비밀번호를 입력하여 인터넷 안전결제(ISP)등록 후 결제를 진행해 주시면 됩니다.</p>
					<p class="answer-text">단, 30만원 이상 결제 시에는 공인인증서가 필요합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">가상 계좌 입금은 언제 확인되나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">입금 후 24시간 이내에 확인됩니다.</p>
					<p class="answer-text">24시간 이후에도 입금확인이 되지 않는 경우 결제 금액과 입금한 금액이 같은지, 올바른 계좌로 입금하였는지 확인 후</p>
					<p class="answer-text">[고객센터 > 1:1 카톡 상담하기]를 통해 문의 부탁드립니다.</p>
				</div>
			</li>
			<li class="faq-wrapper order-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">가상 계좌 입금인을 다르게 적은 경우 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">입금인이 달라도 가상계좌번호만 같으면 문제없습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural" id="delivery-answer">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">배송비는 얼마인가요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">Home Sweet Home은 상품정보 중계 및 판매 매체이며, 판매 업체 별로 배송비 정책이 상이합니다각 상품상세페이지에서 배송비를 확인하실 수 있습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">배송확인은 어떻게 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text" >우측 상단 프로필의 [나의쇼핑]을 통해 배송단계를 한눈에 보실 수 있습니다.</p>
					<p class="answer-text">또한 배송이 시작되면 카카오톡 알림톡 또는 SMS로 안내메시지가 발송됩니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">배송은 얼마나 걸리나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">상품 배송 기간은 배송 유형에 따라 출고 일자 차이가 있습니다.자세한 사항은 구매하신 상품의 상세 페이지에서 확인 가능하며, 배송 유형 별 기본 출고 기간은 아래와 같습니다. </p>
					<p class="answer-text">∙ 일반 택배 / 화물 택배 : 결제 후 1~3 영업일 이내 출고됩니다.</p>
					<p class="answer-text">∙ 업체 직접 배송 : 배송 지역에 따라 배송 일자가 상이할 수 있으므로 상품 상세 페이지에서 확인 해주세요.</p>
					<p class="answer-text">※ 영업일은 주말, 공휴일을 제외한 기간입니다.</p>
					<p class="answer-text">※ 제조사의 사정에 따라 출고일은 지연될 수 있는 점 양해 부탁드립니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">여러 상품을 묶음 배송 받으려면 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">각 상품별로 배송처가 상이할 수 있기 때문에 묶음 배송은 어렵습니다.</p>
					<p class="answer-text">단, 배송처가 같은 경우 배송처의 정책에 따라 가능 할 수 있습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">원하는 날짜로 맞춰서 배송을 받을 수 있나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">각 배송처 정책에 따라 상이할 수 있습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">배송 시작 알림 메시지를 받았는데, 배송추적이 되지 않습니다. 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">송장번호 등록 후 1영업일 이내 또는 실제 상품배송이 진행됨과 동시에 배송추적이 가능합니다.</p>
					<p class="answer-text">※ 배송처에서 배송이 시작되기 전, 송장을 먼저 출력 후 송장번호를 입력하는 경우가 있습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural" id="order-answer">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">배송 조회를 해보면 배송완료로 확인되는데 택배를 받지 못했습니다. 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">경비실 또는 무인택배함을 먼저 확인 부탁드립니다.</p>
					<p class="answer-text">별도의 위탁 장소가 없는 경우 배송기사님께서 임의의 장소에 보관하셨을 수 있으니, 기사님께 문의 부탁드립니다.</p>
				</div>
			</li>
			<li class="faq-wrapper delivery-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">해외 배송이 가능한가요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">현재는 국내배송만 진행하고 있습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper refund-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">주문 취소는 어떻게 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">판매처에서 주문을 확인하기 전이라면 언제든 취소하실 수 있으며, [주문배송내역 조회 > 주문상세보기]에서 직접 주문취소하실 수 있습니다.</p>
					<p class="answer-text">※ 이미 판매처에서 상품을 확인해 준비 중이라면, 직접 취소가 어렵습니다. 상세 페이지에 확인되는 판매자 연락처 또는 오늘의집 고객센터로 문의 부탁드립니다.</p>
				</div>
			</li>
			<li class="faq-wrapper refund-plural" id="refund-answer">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
 						<span class="faq-question">취소 후 환불은 언제되나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">신용카드 및 체크카드의 경우 카드사에서 확인 절차를 거치는 관계로 평균 3~7일 영업일 이내 환불처리가 완료됩니다. </p>
					<p class="answer-text">무통장 입금의 경우 평균 3영업일 이내 요청 하신 계좌로 환불됩니다.</p>
					<p class="answer-text">휴대폰 소액결제의 경우 평균 3영업일 이내 환불 또는 취소 처리가 완료됩니다.</p>
				</div>
			</li>
			<li class="faq-wrapper refund-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">여러 개의 상품을 주문했는데, 일부만 취소할 수도 있나요></span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">신용카드 및 체크카드 또는 무통장 입금의 경우 배송 상태에 따라 결제후 부분 취소가 가능하며,배송상태에 따른 취소 가능 여부는 아래와 같습니다.</p>
					<p class="answer-text">- 입금대기 및 결제완료 단계: 우측 상단 프로필 사진을 클릭 후 [주문배송내역 조회]에서 즉시취소가능합니다.</p>
					<p class="answer-text">- 배송준비중: 우측 상단 프로필 사진을 클릭 후 [고객센터 > 1:1 카톡 상담하기]로 취소 가능 여부 문의 부탁드립니다.</p>
					<p class="answer-text">- 배송중 : 주문 취소가 불가능합니다. 수령후 반품 처리 부탁드립니다.</p>
					<p class="answer-text">※ 단, 가상계좌로 입금 전이라면 부분 취소처리가 불가능하므로 재주문 부탁드립니다.</p>
				</div>
			</li>
			<li class="faq-wrapper exchange-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">제품이 불량입니다. 반품 혹은 교환은 어떻게 하나요?</span> 
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">업체(브랜드)마다 발송처 및 반품절차가 다릅니다.</p>
					<p class="answer-text">- 우측 상단 프로필 사진을 클릭 후 [나의쇼핑] 페이지에서 원하는 주문의 상세보기 버튼을 클릭 후 교환/반품 접수 후 진행 할수 있습니다. 교환/반품 접수 없이 임의로 반송한 경우에는 처리가 늦어질 수 있습니다.</p>
					<p class="answer-text">- 교환/반품 접수 시 원활한 처리를 위해 불량 사진이 필요하오니, 가급적 사진을 첨부하여 주시기 바랍니다.</p>
					<p class="answer-text">단, 구매확정 이후 교환/반품을 원하시는 경우 판매 업체에 교환/반품 가능 여부를 먼저 문의 부탁드립니다.</p>
				</div>
			</li>
			<li class="faq-wrapper exchange-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">제품의 교환 또는 반품을 어떻게 할 수 있나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">상품을 수령하신 후 7일 이내에 교환, 반품이 가능하며, 고객님의 변심에 의한 교환/반품의 경우 배송비용이 부과될 수 있습니다.</p>
					<p class="answer-text"> ※ 단, 아래의 경우 교환/반품이 불가능합니다.</p>
					<p class="answer-text">- 고객님의 책임 사유로 인해 상품 등이 멸실 또는 훼손된 경우</p>
					<p class="answer-text">- 개봉 및 포장이 훼손으로 상품가치가 현저히 상실된 경우</p>
					<p class="answer-text">- 시간 경과에 의해 재판매가 어려울정도로 상품 가치가 현저히 저하된 경우</p>
					<p class="answer-text">- 고객주문 확인 후 상품제작에 들어가는 주문 제작 상품</p>
					<p class="answer-text">- 직접 조립하는(DIY) 상폼을 조립 한 경우</p>
				</div>
			</li>
			<li class="faq-wrapper exchange-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">반품 시, 사은품도 같이 반품해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">사은품도 같이 동봉하여 반품해 주셔야 반품처리가 완료됩니다.</p>
				</div>
			</li>
			<li class="faq-wrapper exchange-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">주문한 것과 다른 상품이 왔습니다. 어떻게 해아하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">우측 상단 프로필 사진을 클릭 후 [고객센터 > 1:1 카톡 상담하기]를 통해 문의 주시면 확인 도움드리겠습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper cert-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">세금계산서를 받고 싶은데 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">세금계산서 발급은 어려우며, 결제 시 지출증빙 또는 현금영수증 발행은 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper cert-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">주문 후 지출증빙/현금영수증 발행이 가능한가요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">주문 완료 후 지출증빙/현금영수증 발행은 불가능합니다. </p>
					<p class="answer-text">지출증빙/현금영수증을 원하사는 경우 반드시 주문 시 "입금자 정보 입력"란에 지출증빙/소득공제 발행 신청을 선택 후 요청정보(주민등록번호/현금영수증 카드번호/휴대폰번호/사업자등록번호)를 입력하셔야 합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper cert-plural" id="member-answer">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">현금영수증 발급을 다른 사람으로 하고 싶습니다.</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">타인의 명의로 현금영수증 발급을 원하실경우 상품 주문 시 발급 받으실 고객님의 정보(주민등록번호/현금영수증 카드번호/휴대폰번호)를 입력해주시면 됩니다.</p>
				</div>
			</li>
			<li class="faq-wrapper cert-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">주문건의 결제 금액을 나눠서 영수증 발행이 가능한가요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">주문하신 내역 중 일부 상품에 대해서만 분할로 영수증을 발급 받으실 수는 없습니다.</p>
				</div>
			</li>
			<li class="faq-wrapper member-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">카카오 계정으로 로그인 하면 '이미 카카오로 가입하신 이메일입니다' 라고 나오는데 어떻게 해야 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">휴대전화 번호를 변경하셨거나 카카오톡 계정을 탈퇴하고 새로 가입하신 경우 이런 문제가 발생할 수 있습니다.이 경우 새로운 정보가 반영될 수 있도록 번거로우시더라도 고객센터로 문의 부탁드립니다.</p>
					<p class="answer-text">Home Sweet Home 고객센터 1111-1111 (운영 시간 : 평일 09:00~18:00)  - 고객센터 > 1:1 카톡 상담하기</p>
				</div>
			</li>
			<li class="faq-wrapper member-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">비밀번호 변경은 어떻게하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">우측 상단 프로필 사진을 클릭 후 [마이페이지] 페이지에서 비밀번호 변경이 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper member-plural"  id="etc-answer">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">회원정보를 수정하고 싶은데 어떻게 해야하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">우측 상단 프로필 사진을 클릭 후 [마이페이지] 페이지에서 회원 정보 수정이 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper member-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">회원탈퇴는 어떻게 하나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">PC 웹사이트: 우측 상단 프로필 사진 클릭 후 [마이페이지] 탈퇴하기 버튼 클릭해주세요.</p>
				</div>
			</li>
			<li class="faq-wrapper etc-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">제품의 자세한 정보는 어떻게 알 수 있나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">각 제품의 상세 페이지에서 확인 가능하며, 더욱 자세한 정보는 제품상세페이지 내 문의하기를 통해 판매 업체에 문의 가능합니다.</p>
				</div>
			</li>
			<li class="faq-wrapper etc-plural">
				<h3 class="faq-inner"><button class="faq-btn">
						<sapn class="faq-btn-img">Q</sapn>
						<span class="faq-question">상담방법과 상담가능시간 유선번호는 어떻게 되나요?</span>
						<sapn class="faq-down-icon"></sapn>
					</button></h3>
				<div class="answer-wrapper">
					<p class="answer-text">상담방법과 상담가능시간 유선번호는 어떻게 되나요?</p>
					<p class="answer-text">우측 상단 프로필 사진을 클릭 후 [나의쇼핑 > 고객센터 > 1:1 카톡 상담하기] 를 이용하시면 상담원과 채팅으로 더욱 빠르고 편리하게 문의 가능합니다.</p>
				</div>
			</li>

		</ul>
		
		</section>
	</div>
	<script>
// 탭 메뉴 선택 시 css 변경 & 해당하는 카테고리의 FAQ만 보여주기
 	document.querySelectorAll(".span-text").forEach((span) => {
 		span.onclick = (e) => {
 			document.querySelectorAll(".nav-tab-wrapper").forEach((e) => {
 				e.style.backgroundColor = "#f7f9fa";
 				e.style.color = "#828C94"; 
 			});
 			document.querySelectorAll(".faq-wrapper").forEach((e) => {
 				e.style.display = "none";
 			});
 			document.querySelectorAll(".answer-wrapper").forEach((e) => {
 				e.style.display = "none";
 			});
 			
		
			let value = e.target.id;
			let navWrapper = "";
			let id = "";
		 	switch(value){
				case "all": id = "faq-wrapper"; navWrapper = "nav-tab-wrapper-"+ value;; break; 
				case "order": id = "order-plural"; navWrapper = "nav-tab-wrapper-" + value;  break; 
				case "delivery": id = "delivery-plural"; navWrapper = "nav-tab-wrapper-" + value;  break; 
				case "refund": id = "refund-plural"; navWrapper = "nav-tab-wrapper-" + value;  break; 
				case "exchange": id = "exchange-plural"; navWrapper = "nav-tab-wrapper-" + value;  break; 
				case "cert": id = "cert-plural"; navWrapper = "nav-tab-wrapper-" + value;  break; 
				case "member": id = "member-plural"; navWrapper = "nav-tab-wrapper-" + value;  break; 
				case "etc": id = "etc-plural"; navWrapper = "nav-tab-wrapper-" + value;  break; 
		 
			}
	
		 	document.querySelector(`#\${navWrapper}`).style.backgroundColor = "#35C5F0";
			document.querySelector(`#\${navWrapper}`).style.color = "#FFF"; 
			document.querySelectorAll(`.\${id}`).forEach((e) => {
	
 			 	e.style.display = "inline-block";
 			});	
 		}
	});
	
	// 원하는 FAQ 선택 시 화면 노출

	const styleShowFAQ = {"display" : "inline-block", "width" : "97%", "margin-left" : "2rem", "padding": "0.5rem, 0.6rem", "opacity" : "1", "height" : "auto"}
	const styleHideFAQ = {"opacity" : "0", "height" : "0", "display" :"none"}

	$(".faq-wrapper").click(function(){

			document.querySelectorAll(".faq-wrapper").forEach((e) => {
				e.style.height="2rem";
			}); 
			document.querySelectorAll(".faq-inner").forEach((e) => {
				e.style.fontWeight = "500";
			}); 
			$(this).children().eq(0).css({"font-weight" : "bold"});  
			document.querySelectorAll(".answer-wrapper").forEach((e) => {
				e.style.height = "0";
				e.style.opacity = "0";
				e.style.display = "none";
			})
			$(this).css({"height" : "auto"});       
			console.log($(this).children().eq(1));   
			$(this).children().eq(1).css(styleShowFAQ);    
			
	}); 
	
const copyEmail = () => {
	  const emailAddr = document.createElement("textarea");
	    document.body.appendChild(emailAddr);
	    emailAddr.value = "cscenter@HomeSweetHome.com";
	    emailAddr.select();
	    document.execCommand('copy');
	    document.body.removeChild(emailAddr);
		alert("이메일 주소가 복사되었습니다.");

}
// 선택한 FAQ로 이동 viewAnswerDelivery() viewAnswerOrder() viewAnswerEtc() viewAnswerRefund() viewAnswerMember()
/* #delivery-answer #order-answer #etc-answer #refund-answer #member-answer */
const viewAnswerDelivery = () =>{
	document.querySelector("#delivery-answer").scrollIntoView();
}
const viewAnswerOrder = () =>{
	document.querySelector("#order-answer").scrollIntoView();
}
const viewAnswerEtc = () =>{
	document.querySelector("#etc-answer").scrollIntoView();
}
const viewAnswerRefund = () =>{
	document.querySelector("#refund-answer").scrollIntoView();
}
const viewAnswerMember = () =>{
	document.querySelector("#member-answer").scrollIntoView();
}
// 1 대 1 고객센터 채팅
window.onload = () =>{
	Kakao.init('e8297c1ed4b33061177ef12c15580963'); 
	
}
const homesweethomeChat = () => {
	Kakao.Channel.chat({
		  channelPublicId: '_Dmxgxgb' 
		});
}
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
