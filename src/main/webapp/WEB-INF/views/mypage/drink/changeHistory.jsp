<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/assets/js/order/mypageDrink.js"></script>

<div class="container">
	<div class="breadcrumb-style">
		<div class="wrap">
			<ul>
		    	<li><a href="/">홈</a></li>
		        <li><a href="/mypage">My녹즙</a></li>
		        <li><a href="/mypage/drink/drink">매일배송 음용내역</a></li>
		        <li><a href="/mypage/drink/drinks/${ drkOrderMypageDTO.drk_order_no }">음용내역</a></li>
		        <li><a class="active">변경내역</a></li>
			</ul>
		</div>
	</div>
	
	<div class="border-wrapper">
		<h2 class="container-title">홈페이지 변경내역</h2>
	</div>
							
	<div class="page-addiction-wrapper">
		<div class="info-copy">
			<div class="box" style="flex:1; margin-bottom:13px;">
				<div class="nickname-format">
					<h5>${ drkOrderMypageDTO.drk_order_name }</h5>
					<span>${ drkOrderMypageDTO.drk_order_no }</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="nickname-list change">
		<ul>
			<li>
				<div class="flex-full nickname-format">
					<h5>배송일정변경</h5>
					<span>2023.11.02 신청</span>
				</div>
				<div class="flex">
					<button class="detailBtn rounded-button">상세보기</button>
				</div>
			</li>
								
			<li>
				<div class="flex-full nickname-format">
					<h5>음용상품변경</h5>
					<span>2023.11.01 신청</span>
				</div>
				<div class="flex">
					<button class="detailBtn rounded-button">상세보기</button>
				</div>
			</li>
								
			<li>
				<div class="flex-full nickname-format">
					<h5>음용상품변경</h5>
					<span>2023.11.01 신청</span>
				</div>
				<div class="flex">
					<button class="detailBtn rounded-button">상세보기</button>
				</div>
			</li>
		</ul>
	</div>
	
	<div class="alert-area lg">
		<h4>안내사항</h4>
		<ul>
			<li>배송일정변경 요청 시점과 조회 시점에 따라 일부 상이할 수 있습니다.</li>
			<li>변경하신 내역과 상이하신 경우 배송가맹점 또는
				홈페이지 1:1문의 게시판을 통해 문의해주세요.</li>
		</ul>
	</div>
</div>

<script>
	$(function () {
		
	})
</script>