<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<div class="sticky-container2">
				<div class="submenu-container">
					<div class="submenu-nav-wrapper">
						<div class="community-menu-container">
							<div class="community-menu-wrapper">
									<a index="0" class="community-home-menu" href="<%= request.getContextPath() %>/community/home">
										<div class="home-wrapper">
												<p class="home-text">홈</p>
										</div>
									</a>
									<a index="1" class="community-picture-menu" href="<%= request.getContextPath() %>/community/picture">
										<div class="picture-wrapper">
											<p class="picture-text">사진</p>
										</div>
									</a> 
									<a index="2" class="community-knowhow-menu" href="<%= request.getContextPath() %>/community/knowhow">
										<div class="knowhow-wrapper">
											<p class="knowhow-text">노하우</p>
										</div>
									</a> 
									<a index="3" class="community-qna-menu" href="<%= request.getContextPath() %>/community/qna">
										<div class="qna-wrapper">
											<p class="qna-text">질문과답변</p>
										</div> 
									</a> 
									<a index="4" class="community-event-menu" href="<%= request.getContextPath() %>/community/eventList">
										<div class="event-wrapper">
											<p class="event-text">이벤트</p>
										</div>
									</a>
							</div>
						</div>
					</div>
				</div>
			</div>