<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<div class="sticky-container2">
				<div class="submenu-container">
					<div class="submenu-nav-wrapper">
						<div class="store-menu-container">
							<div class="store-menu-wrapper">
									<a index="0" class="store-home-menu" href="<%= request.getContextPath() %>/store/storeMain">
										<div class="store-home-wrapper">
												<p class="home-text">홈</p>
										</div>
									</a>
									<a index="1" class="store-category-menu" href="<%= request.getContextPath() %>/store/storeCategory">
										<div class="category-wrapper">
											<p class="category-text">카테고리</p>
										</div>
									</a> 
									<a index="2" class="store-todaydeal-menu" href="<%= request.getContextPath() %>/store/todayDeal">
										<div class="todaydeal-wrapper">
											<p class="todaydeal-text">오늘의딜</p>
										</div> 
									</a> 
							</div>
						</div>
					</div>
				</div>
			</div>