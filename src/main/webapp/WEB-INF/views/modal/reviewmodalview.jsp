<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
	<div class="thumb-normal">
		<img src="/file/download/product/${list[0].system_name }">
	</div>
</div>
<div class="modal-body">
	<div class="info-area">
		<h2>${list[0].products_name }</h2>
		<p>${list[0].products_sub_name}</p>
		<div class="product-addiction" style="border-bottom: none">
			<div class="price-item">
				<p>${list[0].price}<span>원</span>
				</p>
				<span>(${list[0].products_size })</span>
			</div>
		</div>
	</div>
	<div class="foot-case">
		<button class="modify rounded-button">수정</button>
		<button class="delete rounded-button">삭제</button>
	</div>
</div>
<div class="modal show fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModal" aria-hidden="true">
	<div class="modal-dialog modal-review modal-dialog-centered">
		<div class="modal-content" style="border-radius: 40px;">
			<div class="modal-body horizontal-layer">
				<div class="thumb-area" id="imagePlace">
					<img onerror="this.src='/resources/assets/images/common/no_img.png'" alt="">
				</div>
				<div class="review-contents-area">
					<div class="modal-header" style="border: 0;">
						<h4 id="titlePlace">채소습관 케일</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="scrollable" style="padding-top: 0;">
						<p id="contentsPlace">웬만해선 리뷰 안 다는 사람인데, 월요일부터 금요일까지 매일 마시고 있는데 아침 컨디션이 정말 어제 오늘이 다르게 매일 매일 좋아지고 있습니다. 웬만해선 리뷰 안 다는 사람인데, 월요일부터 금요일까지 매일 마시고 있는데 아침 컨디션이 정말 어제오늘이 다르게 매일매일 좋아지고 있습니다.</p>
						<div class="span-tie">
							<span>신종혁</span> <span id="datePlace">2022. 06. 07</span>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>