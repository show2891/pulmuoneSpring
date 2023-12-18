<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="wrapper">
	<main class="page forum">
		<div class="breadcrumb-style">
			<div class="container">
				<ul>
					<li><a href="/">홈</a></li>
					<li><a href="/mypage">MY녹즙</a></li>
					<li><a class="" href="/mypage/product/list">활동정보</a></li>
					<li><a class="active" href="/mypage/action/review">리뷰</a></li>
				</ul>
			</div>
		</div>
		<div class="container aside-layout" style="padding-bottom: 120px;">
			<div class="container">
				<div class="border-wrapper">
					<h2 class="container-title">리뷰</h2>
					<div class="page-tab">
						<a class="active" href="/mypage/action/review">작성 가능한 리뷰</a> <a href="/mypage/action/review/myWriteReview">내가 작성한 리뷰</a>
					</div>
				</div>
				<div class="page-addiction-wrapper" style="align-items: center; margin-bottom: 20px;">
					<div class="info-copy description" style="margin-top: 10px;">
						<p>
							총 <b>${fn:length(reviewlist) }</b>건
						</p>
					</div>
				</div>
				<div class="box-partition">
					<div class="drinkchange-list review">
						<ul>
							<c:forEach items="${reviewlist }" var="dto">
								<li><a class="item productMove" data-idx="316" data-goodtype="daily" data-issale="Y" style="cursor: pointer">
										<div class="thumb">
											<img src="/file/download/product/${dto.system_name }" onerror="this.src='/resources/assets/images/common/no_img.png'" alt="">
										</div>
										<div class="contents">
											<p class="prd-title title">${dto.products_name }</p>
											<b class="price">${dto.price }<span class="unit-section">원 (${dto.products_size })</span>
											</b>
										</div>
								</a>
									<div class="button-area">
										<a href="" class="btn-default write btn-white">리뷰작성</a>
									</div></li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</div>
		</div>
	</main>
</div>
</html>
