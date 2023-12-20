<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box-partition">
	<div class="delivery-order-list details">
		<ul>
			<c:forEach items="${ boxOrderMypageDTO.boxOrderProductsList }" var="list">
				<li>
					<div class="item-wrapper">
						<a class="item" href="/product/box/${ list.products_tag }?eventIdx=">
							<div class="thumb">
								<img src="/${ list.img_path }/${ list.system_name }" alt="">
							</div>
							
							<div class="contents">
								<div class="product" style="padding-top: 1px;">
									<label class="category">${ list.products_type }</label>
									<p class="title">${ list.products_name }<span>${ list.products_size }</span>
									</p>
									<div class="span-tie">
										<span>수량 ${ list.products_cnt }개</span>
										<c:choose>
											<c:when test="${ boxOrderMypageDTO.box_order_status eq -1 }">
												<span style="font-weight:600; color:#7acc12;">주문 취소</span>
											</c:when>
											<c:when test="${ boxOrderMypageDTO.box_order_status eq 0 }">
												<span style="font-weight:600; color:#7acc12;">결제대기-(가상계좌)</span>
											</c:when>
											<c:when test="${ boxOrderMypageDTO.box_order_status eq 1 }">
												<span style="font-weight:600; color:#7acc12;">결제완료</span>
											</c:when>
											<c:when test="${ boxOrderMypageDTO.box_order_status eq 2 }">
												<span style="font-weight:600; color:#7acc12;">배송준비중</span>
												</c:when>
												<c:when test="${ boxOrderMypageDTO.box_order_status eq 3 }">
												<span style="font-weight:600; color:#7acc12;">배송중</span>
											</c:when>
											<c:otherwise>
												<span style="font-weight:600; color:#7acc12;">배송완료</span>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								
								<div class="info">
									<c:choose>
										<c:when test="${ list.event_price eq 0 }">
											<div style="margin-bottom: -1px; padding-left: 2px;" class="price-tag right sm">
												<p class="value" style="color: #333;"><fmt:formatNumber value="${ list.price }" type="number" /></p>
												<span class="unit">원</span>
											</div>
										</c:when>
										<c:otherwise>
											<div style="margin-bottom: -1px; padding-left: 2px;" class="before-price-tag">
												<p class="value origin" style="color: #333;"><fmt:formatNumber value="${ list.price }" type="number" /></p>
												<span class="unit">원</span>
											</div>
											<div style="margin-bottom: -1px; padding-left: 2px;" class="price-tag right sm">
												<p class="value" style="color: #333;"><fmt:formatNumber value="${ list.event_price }" type="number" /></p>
												<span class="unit">원</span>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</a>
						
						<div class="button-area">
							<button class="btn-default btn-white" onclick="location.href='/forum/inquiry/write.do'">1:1 문의</button>
							<c:choose>
								<c:when test="${ boxOrderMypageDTO.box_order_status eq -1 }">
									<button class="btn-default btn-black" style="background: grey" onclick="alert('주문취소된 상품입니다.')">주문취소</button>
								</c:when>
								<c:when test="${ boxOrderMypageDTO.box_order_status >= 3 }">
									<form action="http://info.sweettracker.co.kr/tracking/5" method="post">
										<div class="form-group" style="display: none">
											<label for="t_key">API key</label>
											<input type="text" class="form-control t_key" id="t_key" name="t_key" placeholder="제공받은 APIKEY" value="">
										</div>
										<div class="form-group" style="display: none">
											<label for="t_code">택배사 코드</label>
											<input type="text" class="form-control" name="t_code" id="t_code" placeholder="택배사 코드" value="04">
										</div>
										<div class="form-group" style="display: none">
											<label for="t_invoice">운송장 번호</label> <input type="text" class="form-control" name="t_invoice" id="t_invoice" placeholder="운송장 번호" value="${ boxOrderMypageDTO.tracking_no }">
										</div>
										<button class="btn-default invoice btn-black" data-invo="${ boxOrderMypageDTO.tracking_no }" data-status="2">배송조회</button>
									</form>
								</c:when>
								<c:otherwise>
									<button class="btn-default btn-black btn-cancel" data-status="0" data-order-no="${ boxOrderMypageDTO.box_order_no }">주문취소</button>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>