<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="part" style="border-bottom: none;padding-bottom: 35px">
	<div class="part-head" style="margin-bottom: 16px;">
		<a href="/mypage/order/box">택배배송 주문내역</a>
	</div>
	
	<ul class="delivery-process">
		<li class="">
			<div class="item">
				<div class="thumb">
					<img src="/resources/assets/images/common/ico_delivery_process_img01.png">
				</div>
				<p>
					<c:out value="${ boxOrderStatus[0] }"></c:out>
				</p>
				<span>결제완료</span>
			</div>
		</li>
		
		<li class="">
			<div class="item">
				<div class="thumb">
					<img src="/resources/assets/images/common/ico_delivery_process_img02.png">
				</div>
				<p>
					<c:out value="${ boxOrderStatus[1] }"></c:out>
				</p>
				<span>배송준비중</span>
			</div>
		</li>
		
		<li class="">
			<div class="item">
				<div class="thumb">
					<img src="/resources/assets/images/common/ico_delivery_process_img03.png">
				</div>
				<p>
					<c:out value="${ boxOrderStatus[2] }"></c:out>
				</p>
				<span>배송중</span>
			</div>
		</li>
		
		<li class="">
			<div class="item">
				<div class="thumb">
					<img src="/resources/assets/images/common/ico_delivery_process_img04.png">
				</div>
				<p>
					<c:out value="${ boxOrderStatus[3] }"></c:out>
				</p>
				<span>배송완료</span>
			</div>
		</li>
	</ul>
</div>