<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="product-wrapper">
	<div class="part-head" style="margin-bottom:22px;">
		<h5 style="font-weight:500;">다음주 음용상품</h5>
	</div>
														
	<div class="product-week" style="height: 100%;">
		<ul id="next-drink-label" class="header">
			<li>
				<label>월</label>
				<span class="total_cnt"></span>
			</li>
			<li>
				<label>화</label>
				<span class="total_cnt"></span>
			</li>
			<li>
				<label>수</label>
				<span class="total_cnt"></span>
			</li>
			<li>
				<label>목</label>
				<span class="total_cnt"></span>
			</li>
			<li>
				<label>금</label>
				<span class="total_cnt"></span>
			</li>
		</ul>
																		
		<ul class="contents hp" style="display: flex" id="next-drink-label2">
			<c:forEach items="${productsList}" var="pList" varStatus="status">
			    <li>
			        <c:forEach items="${pList}" var="p">
			            <div class="item">
			                <span class="number eachCnt" data-cnt="${ p.products_cnt }">${ p.products_cnt }</span>
			                <div class="thumb">
			                    <img src="/${ p.img_path }/${ p.system_name }" alt="">
			                </div>
			            </div>
			        </c:forEach>
			        <c:if test="${empty pList}">
			            <div class="item">
			            	<span class="cnt" data-cnt="0"></span>
			            </div>
			        </c:if>
			    </li>
			</c:forEach>
		</ul>
	</div>
</div>