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
		        <li><a class="active">음용내역</a></li>
			</ul>
		</div>
	</div>
	
	<div class="border-wrapper">
		<h2 class="container-title">음용상품변경</h2>
	</div>
							
	<div class="page-addiction-wrapper">
		<div class="info-copy">
			<div class="box" style="flex:1; margin-bottom:13px;">
				<div class="nickname-format">
					<label>기본</label>
					<h5>${ drkOrderMypageDTO.drk_order_name }</h5>
					<span>${ drkOrderMypageDTO.drk_order_no }</span>
				</div>
			</div>
		</div>
	</div>
				
	<div class="page-addiction-wrapper" style="margin-bottom:10px;">
		<div class="info-copy description" style="margin-top:2px;">
			<p>※ 원하시는 스케줄에 따라 상품/요일/수량 변경이 가능합니다.</p>
			<p>※ 타 쇼핑몰 고객은 상품 변경이 불가합니다.</p>
		</div>
		<button class="rect-button primary" style="width:120px; padding: 0" type="button" id="addProduct">상품추가하기</button>
	</div>
				
	<div class="box-partition">
		<ul class="drinkchange-list ch">
	
			<li class="">
				<div class="item" href="/">
					<div class="thumb">
						<img src="/file/download/product/685a5aac-588c-4366-82c4-217f17b38067.jpg" alt="">
					</div>
					<div class="contents">
						<span>10.05 부터 </span>
						<p class="prd-title">핸디밀 그린샐러드 식이섬유플랜<b style="font-size: 16px" class="pause"></b>
						</p>
						<b class="price">3,300<span> 원(190ml)</span></b>
					</div>
				</div>
							
				<div class="select-group">
					<div class="select-item">
	                    <span>월</span>
	                    <div class="unit">
	                        <button type="button" class="plus"></button>
	                        <em>0</em>
	                        <button type="button" class="minus"></button>
	                    </div>
	                </div>
	                <div class="select-item">
	                    <span>화</span>
	                    <div class="unit">
	                        <button type="button" class="plus"></button>
	                        <em>0</em>
	                        <button type="button" class="minus"></button>
	                    </div>
	                </div>
					<div class="select-item">
				        <span>수</span>
						<div class="unit">
				            <button type="button" class="plus"></button>
				            <em>0</em>
				            <button type="button" class="minus"></button>
				    	</div>
				    </div>
					<div class="select-item">
						<span>목</span>
						<div class="unit">
							<button type="button" class="plus"></button>
	                       	<em>0</em>
	                        <button type="button" class="minus"></button>
	                    </div>
	                </div>
	                <div class="select-item">
	                    <span>금</span>
	                    <div class="unit">
	                        <button type="button" class="plus"></button>
	                        <em>1</em>
	                        <button type="button" class="minus"></button>
	                    </div>
	            	</div>
	            </div>
				<button type="button" class="btn-delete">
					<i class="ico ico-prd-delete"></i>
					<span class="hide">카트에서 삭제</span>
				</button>
			</li>
		</ul>
						
		<div class="empty-area" style="padding: 160px 0; display: none">
			<img src="/resources/images/ui/ico-alert.png" alt="empty">
			<b>변경할 상품이 없습니다.</b>
		</div>
	</div>
					
	<div class="form-input period" style="margin-top:10px; margin-bottom:16px;">
		<dl class="date">
			<dt style="padding-left:20px; margin-right:30px;">
				<label>변경배송 시작일</label>
				<button class="btn-question" data-toggle="modal" data-target="#tooltipModal"><i class="ico ico-question"></i><span class="hide">자세한 내용 보기</span>
				</button>
			</dt>
			<dd style="min-width:153px;">
				<input type="text" name="datepicker" data-placeholder="날짜를 입력하세요.">
			</dd>
		</dl>
	</div>
				
	<div class="dual-layout">
		<div>
			<table class="doc-style-table h40">
				<tbody>
					<tr>
						<th>총 상품금액 (4주 기준)</th>
						<td class="np">
							<div class="price-tag xs right">
								<p class="value total">80,000</p>
								<span class="unit" style="font-weight:300; color:#333;">원</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>총 할인금액</th>
						<td class="np">
							<div class="price-tag xs right">
								<p class="value">0</p>
								<span class="unit" style="font-weight:300; color:#333;">원</span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table class="doc-style-table h40">
				<tbody>
					<tr>
						<th>배송비</th>
						<td class="np">
							<div class="price-tag xs right">
								<p class="value">0</p>
								<span class="unit" style="font-weight:300; color:#333;">원</span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="bigger">최종결제금액</th>
						<td class="np">
							<div class="price-tag  sm right">
								<p class="value final" style="color:#333">80,000</p>
								<span class="unit" style="font-size:20px;font-weight:300; color:#333;">원</span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
						
	<div class="addiction-foot" style="margin-top:26px;">
		<p class="foot-copy">※ 변경시작일로부터 4주 기준 예상 음용대금으로 실제 월 청구금액과 상이할 수 있습니다.</p>
		<div class="btn-area-right">
			<a id="cancel" style="cursor:pointer;padding:0; width:140px;" class="btn-default btn-white" href="/mypage/drink/drinks/${ drkOrderMypageDTO.drk_order_no }">취소</a>
			<a style="cursor:pointer; padding:0; width:140px;" id="changeProductBtn" class="btn-default btn-default">음용상품변경</a>
		</div>
	</div>
</div>					
	

<script>
	$(function () {
		
	})
</script>