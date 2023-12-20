<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box-partition rf" style="margin-top:20px;">
	<div class="part" style="padding-left:28px; padding-top:24px; padding-bottom:34px;">
		<div class="part-head">
			<h5>환불안내</h5>
		</div>
		<ul class="horizontal-info-list" style="margin-top:24px;">
			<li>
				<label>최종 상품금액</label>
				<div class="price-tag xs">
					<p class="value" style="color:#333;">
						<fmt:formatNumber value="${ boxPayDTO.box_sale_price }" type="number"></fmt:formatNumber>
					</p>
					<span class="unit">원</span>
				</div>
			</li>
			<c:choose>
				<c:when test="${ boxPayDTO.box_discount_price eq 0 }">
					<li>
						<label>쿠폰할인금액</label>
						<div class="price-tag xs">
							<p class="value" style="black;">0</p>
							<span class="unit">원</span>
						</div>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<label>쿠폰할인금액</label>
						<div class="price-tag xs">
							<p class="value" style="color:red;">
								<fmt:formatNumber value="${ boxPayDTO.box_discount_price }" type="number"></fmt:formatNumber>
							</p>
							<span class="unit">원</span>
						</div>
					</li>
				</c:otherwise>
			</c:choose>
			<li>
				<label>배송비</label>
				<div class="price-tag xs">
					<p class="value" style="color:#333;">
						<fmt:formatNumber value="${ boxPayDTO.box_shipping_fee }" type="number"></fmt:formatNumber>
					</p>
					<span class="unit">원</span>
				</div>
			</li>
			
			<li>
				<label>환불예상금액</label>
				<div class="price-tag xs">
					<p class="value" style="color:#333;">
						<fmt:formatNumber value="${ boxPayDTO.box_final_price }" type="number"></fmt:formatNumber>
					</p>
					<span class="unit">원</span>
				</div>
			</li>
			<li>
				<label>환불 수단</label>
				<c:choose>
					<c:when test="${ boxPayDTO.box_pay_method eq 0 }">
						<p>신용카드</p>
					</c:when>
					<c:when test="${ boxPayDTO.box_pay_method eq 1 }">
						<p>실시간 계좌이체</p>
					</c:when>
					<c:otherwise>
						<p>가상계좌</p>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>
	</div>
</div>