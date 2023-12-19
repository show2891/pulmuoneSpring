<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/resources/assets/js/order/apiKey.js"></script>

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

	<div class="delivery-order-list">
		<ul id="post-items" data-list-object="append">
			<li>
				<div class="addiction">
					<div class="copy">
						<span></span>
							<span><label>주문번호</label></span>
					</div>
					<div class="status">
						<a style="width:115px; padding:2px 0 0; height:41px;" href="/mypage/order/box/orderBoxView.do?boxOrderNo=" class="rounded-button">주문내역조회</a>
					</div>
				</div>
				<div class="box-item">
					<div class="item-wrapper">
						<div class="thumb">
							<img src="//" alt="">
						</div>
						
						<div class="contents">
							<a href="/product/box/view.do?tag=" class="prd-info">
								<div class="product">
									<label class="category"></label>
									<div style="display:flex;justify-content: space-between">
										<p class="title"></p>
									</div>
									<span class="size"></span>
								</div>
							</a>
							
							<div class="info" style="margin-bottom:-2px;">
								<div class="span-tie">
									<span style="padding-bottom:4px;">수량  개</span>
									<span style="font-weight:600; color:#7acc12;"></span>
								</div>
							
								<div class="before-price-tag">
									<p class="value" style="color:#333;"></p>
									<span class="unit">원</span>
								</div>
								
								<div class="price-tag right sm">
								<p class="value" style="color:#333;"></p>
									<span class="unit">원</span>
								</div>
								
								<div class="price-tag right sm">
									<p class="value" style="color:#333;"></p>
									<span class="unit">원</span>
								</div>
								
							</div>
						</div>
						
						<div class="button-area">
							<button class="btn-default btn-white" onclick="location.href='/forum/inquiry/write.do'">1:1 문의</button>												
							<button class="btn-default btn-black" style="background: grey" onclick="alert('주문취소된 상품입니다.')">주문취소</button>
							<button class="btn-default btn-black" data-status="0" data-order-no="">주문취소</button>
							<form action="http://info.sweettracker.co.kr/tracking/5" method="post">
								<div class="form-group" style="display: none">
									<label for="t_key">API key</label>
								    <input type="text" class="form-control" id="t_key" name="t_key" placeholder="제공받은 APIKEY" value="">
								</div>
								            
								<div class="form-group" style="display: none">
									 <label for="t_code">택배사 코드</label>
									 <input type="text" class="form-control" name="t_code" id="t_code" placeholder="택배사 코드" value="04">
								</div>
								        
								<div class="form-group" style="display: none">
									<label for="t_invoice">운송장 번호</label>
									<input type="text" class="form-control" name="t_invoice" id="t_invoice" placeholder="운송장 번호" value="">
								</div>
									        
								<button class="btn-default invoice btn-black" data-invo="" data-status="2" >배송조회</button>
							</form>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>

<script>
	$(function () {
		$("#t_key").val(trackingKey);
	})
</script>