<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${ empty boxOrderMypageList }">
		<div class="empty-area " style="padding: 160px 0">
			<img src="/resources/assets/images/ui/ico-alert.png" alt="empty">
			<b>주문내역이 없습니다.</b>
		</div>
	</c:when>
	<c:otherwise>
		<div class="delivery-order-list">
			<ul id="post-items" data-list-object="append">
				<c:forEach items="${ boxOrderMypageList }" var="list">
					<li>
						<div class="addiction">
							<div class="copy">
								<span>${ list.box_order_date }</span>
									<span><label>주문번호</label>${ list.box_order_no }</span>
							</div>
							<div class="status">
								<a style="width:115px; padding:2px 0 0; height:41px;" href="/mypage/order/box/${ list.box_order_no }" class="rounded-button">주문내역조회</a>
							</div>
						</div>
						
						<c:forEach items="${ list.boxOrderProductsList }" var="pList">
							<div class="box-item">
								<div class="item-wrapper">
									<div class="thumb">
										<img src="/${ pList.img_path }/${ pList.system_name }" alt="">
									</div>
									
									<div class="contents">
										<a href="/product/box/${ pList.products_tag }??eventIdx=" class="prd-info">
											<div class="product">
												<label class="category">${ pList.products_type }</label>
												<div style="display:flex;justify-content: space-between">
													<p class="title">${ pList.products_name }</p>
												</div>
												<span class="size">${ pList.products_size }</span>
											</div>
										</a>
										
										<div class="info" style="margin-bottom:-2px;">
											<div class="span-tie">
												<span style="padding-bottom:4px;">수량 ${ pList.products_cnt } 개</span>
													<c:choose>
														<c:when test="${ list.box_order_status eq -1 }">
															<span style="font-weight:600; color:#7acc12;">주문 취소</span>
														</c:when>
														<c:when test="${ list.box_order_status eq 0 }">
															<span style="font-weight:600; color:#7acc12;">결제대기-(가상계좌)</span>
														</c:when>
														<c:when test="${ list.box_order_status eq 1 }">
															<span style="font-weight:600; color:#7acc12;">결제완료</span>
														</c:when>
														<c:when test="${ list.box_order_status eq 2 }">
															<span style="font-weight:600; color:#7acc12;">배송준비중</span>
														</c:when>
														<c:when test="${ list.box_order_status eq 3 }">
															<span style="font-weight:600; color:#7acc12;">배송중</span>
														</c:when>
														<c:otherwise>
															<span style="font-weight:600; color:#7acc12;">배송완료</span>
														</c:otherwise>
													</c:choose>
											</div>
										
											<c:choose>
												<c:when test="${ pList.event_price eq 0 }">
													<div class="price-tag right sm">
														<p class="value" style="color:#333;"></p>
														<span class="unit"><fmt:formatNumber value="${ pList.price }" type="number" /> 원</span>
													</div>
												</c:when>
												<c:otherwise>
													<div class="before-price-tag">
														<p class="value" style="color:#333;"></p>
														<span class="unit"><fmt:formatNumber value="${ pList.price }" type="number" /> 원</span>
													</div>
													
													<div class="price-tag right sm">
													<p class="value" style="color:#333;"></p>
														<span class="unit"><fmt:formatNumber value="${ pList.event_price }" type="number" /> 원</span>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									
									<div class="button-area">
										<button class="btn-default btn-white" onclick="location.href='/forum/inquiry/write.do'">1:1 문의</button>												
										<c:choose>
											<c:when test="${ list.box_order_status eq -1 }">
												<button class="btn-default btn-black" style="background: grey" onclick="alert('주문취소된 상품입니다.')">주문취소</button>
											</c:when>
											<c:when test="${ list.box_order_status >= 3 }">
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
														<label for="t_invoice">운송장 번호</label>
														<input type="text" class="form-control" name="t_invoice" id="t_invoice" placeholder="운송장 번호" value="${ list.tracking_no }">
													</div>
													<button class="btn-default invoice btn-black" data-invo="" data-status="2" >배송조회</button>
												</form>
											</c:when>
											<c:otherwise>
												<button class="btn-default btn-black btn-cancel" data-order-no="${ list.box_order_no }">주문취소</button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</c:forEach>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:otherwise>
</c:choose>