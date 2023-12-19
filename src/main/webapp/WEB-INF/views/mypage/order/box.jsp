<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/assets/js/order/apiKey.js"></script>
<script src="/resources/assets/js/order/mypageBox.js"></script>

<div class="container">
	<div class="breadcrumb-style">
		<div class="wrap">
			<ul>
		    	<li><a href="/">홈</a></li>
		        <li><a href="/mypage">My녹즙</a></li>
		        <li><a href="/mypage/order/box">택배배송 주문내역</a></li>
			</ul>
		</div>
	</div>
	
	<div class="border-wrapper">
		<h2 class="container-title">택배배송 주문내역</h2>
	</div>
							
	<%@ include file="/WEB-INF/views/layouts/mypage/order/orderBoxSearchForm.jsp"%>

	<%@ include file="/WEB-INF/views/layouts/mypage/order/orderBoxList.jsp"%>
	
</div>

<script>
	$(function () {
		$(".t_key").val(trackingKey);
	})
</script>