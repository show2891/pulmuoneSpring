<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="contents-area">
	<form id="orderForm">
		<input type="hidden" name="price" value="">
		<input type="hidden" name="salePrice" value="">
		<input type="hidden" name="discountPrice" value="">
		<input type="hidden" name="shppingPrice" value="0">
		<input type="hidden" name="payPrice" value="">
		<input type="hidden" name="payMethod" value="0">

		<div class="cont-checkout-area">
			<div class="title-tab-area">
				<h2 class="cont-title">주문서 작성</h2>
			</div>

			<div class="prd-cart-area" style="padding-bottom: 50px;">
				<div class="prd-cart-list-area">
					<%@ include file="/WEB-INF/views/layouts/order/box/prdList.jsp"%>
					<%@ include file="/WEB-INF/views/layouts/order/box/addrInputForm.jsp"%>
					<%@ include file="/WEB-INF/views/layouts/order/box/couponList.jsp"%>
					<%@ include file="/WEB-INF/views/layouts/order/box/payInfo.jsp"%>
				</div>

				<%@ include file="/WEB-INF/views/layouts/order/box/orderInfo.jsp"%>
			</div>
		</div>
	</form>
</div>
