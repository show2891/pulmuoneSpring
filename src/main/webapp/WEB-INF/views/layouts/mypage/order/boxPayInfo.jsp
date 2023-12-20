<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
	<div class="box-partition">
		<div class="part" style="padding-bottom: 33px;">
			<div class="part-head">
				<h5>결제정보</h5>
			</div>
					
			<ul class="horizontal-info-list">
				<li>
					<label>상품 판매가</label>
					<div class="price-tag xs">
						<p class="value" style="color: #333;">
							<fmt:formatNumber value="${ boxPayDTO.box_price }" type="number" />
						</p>
						<span class="unit">원</span>
					</div>
				</li>

				<li>
					<label>상품 할인 판매가</label>
					<div class="price-tag xs">
						<c:choose>
							<c:when test="${ boxPayDTO.box_sale_price eq boxPayDTO.box_price }">
								<p class="value" style="color: #333;">
									<fmt:formatNumber value="${ boxPayDTO.box_sale_price }" type="number" />
								</p>
							</c:when>
							<c:otherwise>
								<p class="value" style="color: #db2929;">
									<fmt:formatNumber value="${ boxPayDTO.box_sale_price }" type="number" />
								</p>
							</c:otherwise>
						</c:choose>
						<span class="unit">원</span>
					</div>
				</li>
					<li>
					<label>쿠폰 할인 금액</label>
					<div class="price-tag xs">
						<c:choose>
							<c:when test="${ boxPayDTO.box_discount_price eq 0 }">
								<p class="value" style="color: #333;">0</p>
							</c:when>
							<c:otherwise>
								<p class="value" style="color: #db2929;">
									<fmt:formatNumber value="${ boxPayDTO.box_discount_price }" type="number" />
								</p>
							</c:otherwise>
						</c:choose>
						<span class="unit">원</span>
					</div>
				</li>
					<li>
					<label>배송비</label>
					<div class="price-tag xs">
						<p class="value" style="color: #333;">
							<fmt:formatNumber value="${ boxPayDTO.box_shipping_fee }" type="number" />
						</p>
						<span class="unit">원</span>
					</div>
				</li>
					<li>
					<label>결제수단</label>
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
					<li style="margin-top: 14px;" class="bigger">
					<label>최종결제금액</label>
					<div class="price-tag sm">
						<p class="value" style="color: #7acc12;">
							<fmt:formatNumber value="${ boxPayDTO.box_final_price }" type="number" />
						</p>
						<span class="unit">원</span>
					</div>
				</li>
			</ul>
				<ul class="horizontal-info-list">
				<li style="align-items: flex-start;">
					<label style="margin-bottom: 0; margin-top: 3px; font-size: 16px; color: #333">사용된 쿠폰</label>
				</li>
					<c:choose>
					<c:when test="${ empty usedCouponList }">
						<li style="margin-top: 13px;">
							<label style="margin-bottom: 3px;">없음</label>
							<div class="price-tag xs" style="margin-top: 4px;">
								<p class="value" style="color: #333;"></p>
								<span class="unit"></span>
							</div>
						</li>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ usedCouponList }" var="c">
							<li style="margin-top: 13px;">
								<label style="margin-bottom: 3px;">${ c.coupon_name }</label>
								<div class="price-tag xs" style="margin-top: 4px;">
									<c:choose>
										<c:when test="${ c.discount <= 1 }">
											<p class="value" style="color: #333;">
												<fmt:formatNumber value="${ c.discount*100 }" type="number" />
											</p>
											<span class="unit">%</span>
										</c:when>
										<c:otherwise>
											<p class="value" style="color: #333;">
												<fmt:formatNumber value="${ c.discount }" type="number" />
											</p>
											<span class="unit">원</span>
										</c:otherwise>
									</c:choose>
								</div>
							</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>