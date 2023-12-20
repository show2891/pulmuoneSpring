<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-addiction-wrapper" style="align-items: center; margin-bottom:20px;">
	<div class="info-copy description">
		<p>총 <b></b>${ boxOrderMypageList[0].total_count }건</p>
	</div>
					
	<div class="form-input select none-dt" style="width:228px;">
		<dl>
			<dt>문의유형</dt>
			<dd>
				<div class="dropdown">
					<a class="btn dropdown-toggle" href="javascript:void(0);" role="button" data-value="3m" data-toggle="dropdown" aria-expanded="false" style="height: 60px; padding-left:19px; padding-top:2px;">
						최근 3개월
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item range-item 3m" data-value="3m" href="javascript:void(0);">최근 3개월</a>
						<a class="dropdown-item range-item 6m" data-value="6m" href="javascript:void(0);">최근 6개월</a>
						<a class="dropdown-item range-item 1y" data-value="1y" href="javascript:void(0);">최근 1년</a>
					</div>
				</div>
			</dd>
		</dl>
	</div>
				
	<div class="form-input period sm none-dt date-complex" style="margin-left:10px;">
		<dl class="date" style="height: 60px">
			<dt>기간 설정</dt>
			<dd style="width:308px;">
				<input type="text" name="daterange" placeholder="기간을 선택하세요" id="datepicker">
			</dd>
		</dl>
		</div>
					
	<div class="btn-wrap">
		<button style="margin-left: 5px; height:60px; display: none" id="searchBtn" class="btn-square btn-black" type="button">조회</button>
	</div>
	
	<form id="searchForm" style="display: none" action="/mypage/order/box">
		<input type="hidden" name="searchMonth" id="searchMonth" value="3m">
		<input type="text" name="startSearchDate" value="2023-09-19">
		<input type="text" name="endSearchDate" value="2023-12-19">
	</form>
</div>