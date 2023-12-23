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
			<td>${ drkOrderMypageDTO.fc_name }</td>
			<th>주문구분</th>
			<c:choose>
				<c:when test="${ drkOrderMypageDTO.drk_order_type eq 0 }">
					<td>홈페이지 주문</td>
				</c:when>
				<c:otherwise>
					<td>타 쇼핑몰 주문</td>
				</c:otherwise>
			</c:choose>
		</tr>
									
		<tr>
			<th>가맹점연락처</th>
			<td>
				<ul class="addiction-info">
					<li class="tel">
						<label></label>
						<p data-call-text="">${ drkOrderMypageDTO.fc_tel }</p>
					</li>
					<li class="phone">
						<label></label>
						<p data-call-text="">${ drkOrderMypageDTO.fc_phone }</p>
					</li>
				</ul>
			</td>
										
			<th>음용시작일</th>
			<td class="between" style="position: relative ; display: table-cell">
				<span>${ drkOrderMypageDTO.drk_start_date }</span>
			</td>
		</tr>
									
		<tr>
			<th>결제수단</th>
			<td colspan="3">
				<c:choose>
					<c:when test="${ drkOrderMypageDTO.drk_pay_method eq 0 }">
						카드결제
					</c:when>
					<c:when test="${ drkOrderMypageDTO.drk_pay_method eq 1 }">
						계좌결제
					</c:when>
					<c:otherwise>
						현장결제
					</c:otherwise>
				</c:choose>
				<a href="/mypage/drink/paymethod" class="button-text">결제수단관리</a>
			</td>
		</tr>
		
		<tr>
			<th>배송정보</th>
			<td colspan="3">
				${ drkShipDTO.drk_receiver }
				(<c:choose>
					<c:when test="${ drkShipDTO.drk_ship_loc eq 0 }">
						자택
					</c:when>
					<c:otherwise>
						회사/사무실
					</c:otherwise>
				</c:choose>) / ${ drkShipDTO.drk_tel } / ${ drkShipDTO.drk_addr } ${ drkShipDTO.drk_addr_detail } 
				<c:if test="${ drkOrderMypageDTO.drk_status eq 0 }">
					<a href="/mypage/order/daily/stop/${ drkOrderMypageDTO.drk_order_no }" class="button-text">음용중지</a>
				</c:if>
			</td>
		</tr>
	</tbody>
</table>