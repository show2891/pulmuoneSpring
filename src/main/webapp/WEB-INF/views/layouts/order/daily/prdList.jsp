<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="checkout-prd-list">
									
	<div class="checkout-title-area">
		<strong class="list-amount">주문상품
			<em class="prd-count" style="padding: 0 16px; margin-left: 2px">${ fn:length(itemInfos) }</em>
		</strong>
		<button type="button" class="btn-down">
			<i class="ico ico-down"><span class="hide">주문서 닫기</span></i>
		</button>
	</div>
								
	<ul style="" class="prd-cart-list" id="order_targets">
		<c:forEach items="${ itemInfos }" var="item" varStatus="s">
			<li data-id="" data-itemcode="${ item.products_no }" class="order-item order-chk active" data-price="${ item.price }">
				<input type="hidden" value="${ item.products_no }" name="drkScheduleList[${s.index}].products_no" id="products_no">
				<a href="/product/daily/${ item.products_tag }?eventIdx=" class="prd-cart">
					<div class="thumb">
						<img src="/${ item.img_path }/${ item.system_name }" alt="">
					</div>
					<div class="prd-info">
						<b class="prd-title"> ${ item.products_name }</b>
						<b class="now-price">
							<fmt:formatNumber value="${ item.price }" type="number"></fmt:formatNumber>
							<span> 원</span>
						</b>
					</div>
				</a>
				
				<div class="prd-cart-select-daily">
					<ul data-cart-idx="" data-itemcode="${ item.products_no }">
						<c:forEach items="${ item.products_cnt }" var="cnt" varStatus="status">
							<li>
								<input type="hidden" data-index="${s.index}" data-count="1" value="${ cnt }" name="" id="cnt">
								<span class="weekDays" data-count="${ status.index }"></span>
								<div class="prd-select-daily">
									<button type="button" class="prod-add ea-control" data-index="0">
										+<span class="hide">제품 추가</span>
									</button>
									<em data-itemcount-view="${ status.index }">${ cnt }</em>
									<button type="button" class="prod-remove ea-control btn-minus" data-index="0">
										<span class="hide">제품 빼기</span>
									</button>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</li>
		</c:forEach>
	</ul>

	<div class="select-daily-amount" style="padding: 18px 28px 26px 30px">
		<span>요일별 수량을 선택해주세요.</span>
		<button type="button" id="addProductBtn" class="btn-square" style="padding: 0 25px; margin-top: 2px;">상품추가하기</button>
	</div>
									
</div>