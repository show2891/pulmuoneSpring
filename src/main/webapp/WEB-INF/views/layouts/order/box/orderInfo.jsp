<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="prd-checkout-area creat-checkout2">
	<dl>
		<dt>
			<span>상품 판매가 </span>
			<b>
				<div class="now-price" id="prd-origin-price">
					<b data-price-view="origin"></b><span>원</span>
				</div>
			</b>
		</dt>
		
		<dd>
			<span>상품 할인 판매가</span>
			<b>
				<div class="now-price" id="prd-sale-price">
					<b data-price-view="sale" class="minus"></b><span>원</span>
				</div>
			</b>
		</dd>

		<dd>
			<span>쿠폰 할인 금액</span>
			<b>
				<div class="now-price minus" id="coupon-discount">
					<b data-price-view="coupon">0</b><span>원</span>
				</div>
			</b>
		</dd>
		
		<dd>
			<span>배송비</span>
			<b>
				<div class="now-price" id="ship-fee">
					<b data-price-view="delivery">0</b><span>원</span>
				</div>
			</b>
		</dd>

		<dd class="checkout-sum">
			<span>최종 결제금액</span>
			<b>
				<div class="now-price" id="final-pay-amount">
					<b data-price-view="payment"></b><span>원</span>
				</div>
			</b>
		</dd>
	</dl>
	
	<div class="checkbox chk-type4">
		<input type="checkbox" id="agree-11" name="chk-agree-condition">
		<label for="agree-11">구매조건 확인 및 결제진행 동의</label>
	</div>

	<div class="checkout-text" style="margin-top: 20px">
		<p>개인 변심에 의한 반품 또는 교환 시 별도 배송비가 청구됩니다.</p>
		<p>냉장 상품은 상품 특성 상 개인 변심에 의한 반품/ 교환이 불가합니다.</p>
		<p>상품에 따라 출고 지연이 발생할 수 있으며, 출고 지연 시 개별적으로 안내 드립니다.</p>
		<p>회원 본인은 위 내용을 확인하였으며, 구매조건, 결제대행 서비스 및 결제에 동의합니다.</p>
	</div>

	<button type="button" id="allOrderBtn" class="btn-default">주문하기</button>
</div>