<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="box-partition">
	<ul class="drinkchange-list ch">
		<c:forEach items="${ drkOrderMypageProducts }" var="p">
			<li class="product_list" data-prd-no=${ p.products_no }>
				<div class="item" href="/">
					<div class="thumb">
						<img src="/${ p.img_path }/${ p.system_name }" alt="">
					</div>
					<div class="contents">
						<span><fmt:formatDate value="${ p.drk_start_date }" pattern="MM.dd" /> 부터 </span>
						<p class="prd-title">${ p.products_name }<b style="font-size: 16px" class="pause"></b></p>
						<b class="price" data-price="${ p.price }">
							<fmt:formatNumber value="${ p.price }" type="number" />
							<span> 원(${ p.products_size })</span>
						</b>
					</div>
				</div>
							
				<div class="select-group">
					<div class="select-item">
	                    <span>월</span>
	                    <div class="unit">
	                        <button type="button" class="plus"></button>
	                        <em>${ p.mon_cnt }</em>
	                        <button type="button" class="minus"></button>
	                    </div>
	                </div>
	                <div class="select-item">
	                    <span>화</span>
	                    <div class="unit">
	                        <button type="button" class="plus"></button>
	                        <em>${ p.tue_cnt }</em>
	                        <button type="button" class="minus"></button>
	                    </div>
	                </div>
					<div class="select-item">
				        <span>수</span>
						<div class="unit">
				            <button type="button" class="plus"></button>
				            <em>${ p.wed_cnt }</em>
				            <button type="button" class="minus"></button>
				    	</div>
				    </div>
					<div class="select-item">
						<span>목</span>
						<div class="unit">
							<button type="button" class="plus"></button>
	                       	<em>${ p.thu_cnt }</em>
	                        <button type="button" class="minus"></button>
	                    </div>
	               </div>
	                <div class="select-item">
	                    <span>금</span>
	                    <div class="unit">
	                        <button type="button" class="plus"></button>
	                        <em>${ p.fri_cnt }</em>
	                        <button type="button" class="minus"></button>
	                   </div>
	            	</div>
	            </div>
				<button type="button" class="btn-delete btn-stop">
					<i class="ico ico-prd-delete"></i>
					<span class="hide">카트에서 삭제</span>
				</button>
			</li>
		</c:forEach>
	</ul>
						
	<div class="empty-area" style="padding: 160px 0; display: none">
		<img src="/resources/assets/images/ui/ico-alert.png" alt="empty">
		<b>변경할 상품이 없습니다.</b>
	</div>
</div>