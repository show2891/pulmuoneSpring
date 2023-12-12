<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="checkout-input-area" style="padding-bottom: 32px" id="coupon-pane">
	<div class="checkout-input-title-area" style="margin-bottom: 17px">
		<h3 class="checkout-input-title">쿠폰적용</h3>
	</div>

	<div class="checkout-input-box">
		<div class="form-input">
			<dl>
				<dt>
					<label for="input01">쿠폰선택</label>
				</dt>
				
				<dd>
					<select id="coupon-selector" class="form-select form-select-lg">
						<c:choose>
							<c:when test="${ empty couponList }">
								<option value="" selected>사용 가능한 쿠폰이 없습니다.</option>
								<script>
                                	$("#coupon-selector").prop("disabled", true);
                                </script>
							</c:when>
							
							<c:otherwise>
								<option value="0" selected>쿠폰을 선택하세요.</option>
									<c:set var="i" value="1" />
									<c:forEach items="${ couponList }" var="coupon">
										<c:choose>
											<c:when test="${ coupon.discount > 1 }">
												<option value="${ i }" data-coupon-no="${ coupon.coupon_no }" data-duplicate="${ coupon.duplication }"
																	data-max-discount="${ coupon.max_discount }" data-discount="${coupon.discount}" data-coupon-name="${ coupon.coupon_name }">
													${ coupon.coupon_name }&nbsp;&nbsp;/&nbsp;&nbsp;
													<fmt:formatNumber value="${ coupon.discount }" type="number"></fmt:formatNumber>원 할인
												</option>
											</c:when>
											
											<c:otherwise>
												<fmt:formatNumber value="${ coupon.discount }" type="percent" />
												<option value="${ i }" data-coupon-no="${ coupon.coupon_no }" data-duplicate="${ coupon.duplication }"
																	data-max-discount="${ coupon.max_discount }" data-discount="${coupon.discount * 100}" data-coupon-name="${ coupon.coupon_name }">
													${ coupon.coupon_name }&nbsp;&nbsp;/&nbsp;&nbsp;
													<fmt:formatNumber value="${ coupon.discount }" type="percent"></fmt:formatNumber> 할인
												</option>
											</c:otherwise>
										</c:choose>
										
										<c:set var="i" value="${i + 1}" />
									</c:forEach>
							</c:otherwise>
						</c:choose>
					</select>
				</dd>
			</dl>
		</div>
			
		<ul class="file-list-area" id="apply-coupon-list">
		</ul>
		
	</div>
</div>