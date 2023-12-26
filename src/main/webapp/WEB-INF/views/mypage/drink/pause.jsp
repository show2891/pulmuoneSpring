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
		        <li><a class="active">배송일정변경</a></li>
			</ul>
		</div>
	</div>
	
	<div class="border-wrapper">
		<h2 class="container-title">배송일정변경</h2>
	</div>
							
	<div class="page-addiction-wrapper">
		<div class="info-copy">
			<div class="box" style="flex:1; margin-bottom:10px;">
				<div class="nickname-format">
					<label class="basic">기본</label>
					<h5>${ drkOrderMypageDTO.drk_order_name }</h5>
					<span>${ drkOrderMypageDTO.drk_order_no }</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="box-partition">
		<div class="part" style="padding:29px 29px 30px;">
			<div>
				<div class="form-input select">
					<dl style="display: none">
						<dt style="width:180px; padding-left:20px;"><label>배송일정 변경사유</label></dt>
						<dd>
							<div class="dropdown">
								<button class="btn dropdown-toggle" data-value="MR05" type="button" data-toggle="dropdown" aria-expanded="false">
									<em class="placeholder">사유를 선택해주세요</em>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="javascript:void(0)" data-value="MR01">출장</a>
									<a class="dropdown-item" href="javascript:void(0)" data-value="MR02">휴가</a>
									<a class="dropdown-item" href="javascript:void(0)" data-value="MR03">방학</a>
									<a class="dropdown-item" href="javascript:void(0)" data-value="MR04">병가</a>
									<a class="dropdown-item" href="javascript:void(0)" data-value="MR05">기타</a>
									</div>
							</div>
						</dd>
					</dl>
				</div>
				<div class="form-input period">
					<dl class="date">
						<dt style="width:180px; padding-left:20px;"><label>변경 기간(시작일~종료일)</label></dt>
						<dd>
							<input style="padding-left:16px;" id="rangePicker" type="text" name="daterange" placeholder="기간을 선택하세요">
						</dd>
					</dl>
					<p id="ing-text" class="ing-txt">영업일 1일</p>
				</div>
			</div>
		</div>
	</div>
	
	<div class="addiction-foot" style="margin-top:0; border:0; padding-top:21px;">
		<p class="foot-copy"></p>
		<div class="btn-area-right">
			<a href="/mypage/drink/drinks/${ drkOrderMypageDTO.drk_order_no }"  id="cancel" style="cursor:pointer;padding:0; width:140px;" class="btn-default btn-white">취소</a>
			<button type="button" style="padding:0; width:140px;" id="pauseBtn" class="btn-default btn-default">배송일정변경</button>
		</div>
	</div>
	
	<div class="alert-area lg">
		<h4>안내사항</h4>
		<ul>
			<li>배송 일정 변경은 신청일로부터 3일 이후부터 변경 가능합니다. (영업일 기준)</li>
			<li>배송 일정 변경은 영업일 기준 매 월 총 10일간 가능합니다. (1달 기간 중 합산하여 총 10일간 변경 가능)</li>
			<li>후불 정기 결제 고객님은 배송 일정 변경으로 미음용하신 내역은 당월 청구 결제 내역에서 제외되고 결제됩니다.</li>
			<li>후불 정기 결제로 이용하시면서 배송일 변경을 하신 경우 해당 내역에 대한 취소가 불가합니다. 재음용을 원하시면 스케줄 변경 또는 담당 가맹점을 통해 문의해주세요.</li>
			<li>타 쇼핑몰 주문 건은 변경하시고자 하는 배송 일정 기간 동안의 상품이 선택하신 배송일 뒤로 연장되어 순차 배송됩니다.</li>
			<li>배송일정 변경에 따라 현재 음용 중이신 주문 패턴이 일부 변경될 수 있습니다.</li>
			<li>추가 궁금한 사항은 배송가맹점 또는 고객기쁨센터를 통해 문의해주세요.</li>
		</ul>
	</div>
</div>

<script>
	$(function () {
		let today = new Date();
		let startDate = new Date()
		let endDate = new Date()
		
		startDate.setDate(today.getDate()+3);
		endDate.setMonth(today.getMonth()+1);
		
		startDate = getWeekDay(startDate);
		endDate = getWeekDay(endDate);
		
		$('input[name="daterange"]').daterangepicker({
	          opens: 'left',
	          minDate: startDate,
	          maxDate: endDate
	      });
	})
</script>