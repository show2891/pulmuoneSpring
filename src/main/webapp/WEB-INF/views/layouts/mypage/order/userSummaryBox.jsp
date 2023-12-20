<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="basic-table-style cell-narrow" style="margin-top:8px;">
	<thead></thead>
		<colgroup>
			<col width="147">
			<col width="*">
			<col width="127">
			<col width="63">
			<col width="130">
			<col width="120">
		</colgroup>
		
	<tbody>
		<tr>
			<th class="t-left">주문번호</th>
			<th class="t-left">상품명</th>
			<th>결제금액</th>
			<th>주문일자</th>
			<th>주문상태</th>
			<th>상세보기</th>
		</tr>
		
		<c:choose>
			<c:when test="${ empty boxOrderMypageList }">
				<tr>
					<td colspan="6" style="background-color: #fff ;border-radius: 10px">
						<div class="empty-area mypage" style="padding: 50px 0px">
							<img src="/resources/assets/images/ui/ico-alert.png" alt="empty">
							<b>택배배송 주문내역이 없습니다. </b>
						</div>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${ boxOrderMypageList }" var="boml">
					<tr style="border-radius: 10px; font-size: 13px;">
						<td class="t-left">${ boml.box_order_no }</td>
						<td class="t-left">${ boml.products_name }</td>
						<td><fmt:formatNumber value="${ boml.box_final_price }" type="number"></fmt:formatNumber> 원</td>
						<td>
							<fmt:parseDate value="${ boml.box_order_date }" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
				 			<fmt:formatDate value="${ parsedDate }" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<b>
								<c:choose>
									<c:when test="${ boml.box_order_status eq -1 }">주문취소</c:when>
									<c:when test="${ boml.box_order_status eq 0 }">결제대기-(가상계좌)</c:when>
									<c:when test="${ boml.box_order_status eq 1 }">결제완료</c:when>
									<c:when test="${ boml.box_order_status eq 2 }">배송준비중</c:when>
									<c:when test="${ boml.box_order_status eq 3 }">배송중</c:when>
									<c:when test="${ boml.box_order_status eq 4 }">배송완료</c:when>
								</c:choose>
							</b>
						</td>
						<td>
							<a href="/mypage/order/box/${ boml.box_order_no }" class="rounded-button sm">상세보기</a>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
	</tbody>
</table>