<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="doc-style-table height disabled">
	<colgroup>
		<col width="105px">
		<col width="354px">
		<col width="108px">
		<col width="*">
	</colgroup>
	
	<tbody>
		<tr>
			<th>담당가맹점</th>
			<td>서초홈가맹점</td>
			<th>주문구분</th>
			<td>홈페이지 주문</td>
		</tr>
									
		<tr>
			<th>가맹점연락처</th>
			<td>
				<ul class="addiction-info">
					<li class="tel">
						<label></label>
						<p data-call-text="">02-547-0519</p>
					</li>
					<li class="phone">
						<label></label>
						<p data-call-text="">010-3899-4569</p>
					</li>
				</ul>
			</td>
										
			<th>음용시작일</th>
			<td class="between" style="position: relative ; display: table-cell">
				<span>2023.10.04</span>
			</td>
		</tr>
									
		<tr>
			<th>결제수단</th>
			<td colspan="3">카드 결제
				<a href="/mypage/drink/paymethod" class="button-text">결제수단관리</a>
			</td>
		</tr>
		
		<tr>
			<th>배송정보</th>
			<td colspan="3">
				임재석(자택) / 010-1234-1234 / 서울 서초구 청두곶2길 6 303호
				<a href="/mypage/order/daily/stop.do?orderNo=1" class="button-text">음용중지</a>
			</td>
		</tr>
	</tbody>
</table>