<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="dual-layout">
		<div>
			<table class="doc-style-table h40">
				<tbody>
					<tr>
						<th>총 상품금액 (4주 기준)</th>
						<td class="np">
							<div class="price-tag xs right">
								<p class="value total">0</p>
								<span class="unit" style="font-weight:300; color:#333;">원</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>총 할인금액</th>
						<td class="np">
							<div class="price-tag xs right">
								<p class="value discount">0</p>
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
								<p class="value shipping-fee">0</p>
								<span class="unit" style="font-weight:300; color:#333;">원</span>
							</div>
						</td>
					</tr>
					<tr>
						<th class="bigger">최종결제금액</th>
						<td class="np">
							<div class="price-tag  sm right">
								<p class="value final" style="color:#333">0</p>
								<span class="unit" style="font-size:20px;font-weight:300; color:#333;">원</span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>