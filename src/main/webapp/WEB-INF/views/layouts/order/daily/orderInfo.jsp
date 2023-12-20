<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="prd-checkout-area creat-checkout2">
	<dl>
		<dt>
			<span>상품 판매가 (4주기준)</span>
			<b>
				<div class="now-price">
					<b data-price-view="origin">0</b> <span>원</span>
				</div>
			</b>
		</dt>
		
		<dd>
			<span>상품 할인 판매가</span>
			<b>
				<div class="now-price">
					<b data-price-view="sale" class="">0</b> <span>원</span>
				</div>
			</b>
		</dd>
		
		<dd class="checkout-sum">
			<span>4주 예상 금액</span>
			<b>
				<div class="now-price">
					<b data-price-view="payment">0</b> <span>원</span>
				</div>
			</b>
		</dd>
	</dl>
								
	<div class="checkbox chk-type4">
		<input type="checkbox" id="agree-11" name="chk-agree-condition">
		<label for="agree-11">구매조건 확인 및 결제진행 동의</label>
	</div>

	<div class="checkout-text" style="margin-top: 20px; margin-bottom: 14px;">
		<p>4주 기준 예상 음용 금액으로, 실제 청구 금액과 상이할 수 있습니다.</p>
		<p>4주 음용 이후 별도 중지 요청이 없으실 경우 매일 정기배송이 유지됩니다.</p>
		<p>영업일 기준 3일 이내에 배송 가맹점에서 유선으 로 주문 확인 안내드리며, 가맹점 사정에 따라 달라질 수 있습니다.</p>
		<p>가맹점의 사정으로 주문 변경 및 취소될 수 있습니다.</p>
	</div>

	<button type="button" id="allOrderBtn" class="btn-default">주문신청</button>
</div>