<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/assets/js/order/mypageDrink.js"></script>

<div class="container">
	
	<div class="border-wrapper">
		<h2 class="container-title">음용내역</h2>
	</div>
							
	<form id="searchForm">
		<input name="drinkingType" type="hidden" value="Y">
		<div class="page-addiction-wrapper">
			<div class="info-copy"></div>
			<div class="form-input select" style="width:348px;">
				<dl>
					<dt style="padding-left:20px;">
						<label>음용고객번호</label>
					</dt>
					
					<dd>
						<div class="dropdown">
							<a id="searchText" class="btn dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">음용중</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" data-value="0, 1">음용중</a>
								<a class="dropdown-item" data-value="2">음용중지</a>
							</div>
						</div>
					</dd>
				</dl>
			</div>
		</div>
	</form>

	<div class="drinking-list" data-list-object="append" id="drink-items">
							
		<c:choose>
			<c:when test="${ empty drkOrderMypageList }">
				<div class="caution-unit page">
					<span class="mark"></span>
					<p>음용내역이 없습니다.</p>
					<p class="small-xs">아래 고객번호찾기 또는 1:1문의 게시판을 통해 문의주세요.</p>
				</div>
			</c:when>
									
			<c:otherwise>
				<c:forEach items="${ drkOrderMypageList }" var="list" varStatus="s">
					<form class="item">
						<div class="head">
							<div class="nickname-format xxl">
								<c:if test="${ s.index eq 0 }">
									<label class="default">기본</label>
								</c:if>
								<h5>${ list.drk_order_name }</h5>
								<span>${ list.drk_order_no }</span>
							</div>
							
							<ul class="info">
								<li>
									<label>담당가맹점</label>
									<p>${ list.fc_name }</p>
								</li>
								
								<li>
									<label>음용시작일</label>
									<p>${ list.drk_start_date }</p>
								</li>
								
								<li>
									<label>결제수단</label>
									<c:choose>
										<c:when test="${ list.drk_pay_method eq 0 }">
											<p data-parse-paymethod="0">카드결제</p>
										</c:when>
										<c:when test="${ list.drk_pay_method eq 1 }">
											<p data-parse-paymethod="1">계좌결제</p>
										</c:when>
										<c:when test="${ list.drk_pay_method eq 2 }">
											<p data-parse-paymethod="2">현장결제</p>
										</c:when>
									</c:choose>
								</li>
							</ul>
						</div>
												
						<div class="tail">
							<a class="rounded-button" href="/mypage/drink/drinks/${ list.drk_order_no }">상세보기 / 변경</a>
							<div class="button-text-set"></div>
						</div>
					</form>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
		
	<div class="alert-area lg">
		<h4>고객번호 찾기/등록 안내</h4>
		<ul>
			<li>
				음용중이시나 고객번호 확인이 되지 않으시는 경우에는
				<button class="button-text" type="button" data-toggle="modal" data-target="#numberModal">고객번호찾기</button>
				를 통해 확인해보세요.
			</li>
			<li>'고객번호찾기'를 통해 고객번호 확인이 어려우신 경우에는 홈페이지 1:1 문의 또는 배송가맹점을 통해 문의하세요.</li>
		</ul>
	</div>
</div>

<script>
	
	$(function () {
		// 음용중, 음용중지 검색
		$(".dropdown-item").on("click", function() {
			location.href = "/mypage/drink/drink?drinkingType="+$(this).attr("data-value");
		})
		
		// DrinkingType 가져와서 음용중/음용중지 문구 변경
		getDrinkingType();
	})
</script>