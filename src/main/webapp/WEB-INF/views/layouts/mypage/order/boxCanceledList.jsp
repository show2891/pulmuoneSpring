<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box-partition">
	<div class="delivery-order-list check-style details cancel">
		<ul>
			<c:forEach items="${ boxOrderMypageDTO.boxOrderProductsList }" var="list">
				<li>
					<label class="item-wrapper">
						<div class="item" href="/">
							<a class="thumb">
								<img src="/${ list.img_path }/${ list.system_name }" alt="">
							</a>
							<div class="contents">
								<div class="product">
									<label class="category">${ list.products_type }</label>
									<a class="title">${ list.products_name }</a>
								</div>
								<span>${ list.products_size }</span>
								<div class="info">
									<div class="select-item horizontal">
										<span>수량</span>
										<div class="unit">
											<em data-max-qty="" class="box-qty">${ list.products_cnt }</em>
										</div>
									</div>
									
									<c:choose>
										<c:when test="${ list.event_price eq 0 }">
											<div class="price-tag right sm" style="margin-left: auto">
												<p class="value origin" style="color:#333;" data-eachprice=""></p>
												<span class="unit"><fmt:formatNumber value="${ list.price }" type="number" />원</span>
											</div>
										</c:when>
										<c:otherwise>
											<div class="before-price-tag" style="margin-left: auto">
												<p class="value origin" style="color:#333;" data-eachprice=""></p>
												<span class="unit"><fmt:formatNumber value="${ list.price }" type="number" />원</span>
											</div>
											<div class="price-tag right sm">
												<p class="value sale" style="color:#333;" data-eachprice=""></p>
												<span class="unit"><fmt:formatNumber value="${ list.event_price }" type="number" />원</span>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</label>
					<div class="foot"></div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>