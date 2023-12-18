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
						<a href="/mypage/action/review">작성 가능한 리뷰</a> <a class="active" href="/mypage/action/review/myWriteReview">내가 작성한 리뷰</a>
					</div>
				</div>
				<div class="page-addiction-wrapper" style="align-items: center; margin-bottom: 20px;">
					<div class="info-copy description" style="margin-top: 10px;">
						<p>
							총 <b>${fn:length(myreviewlist) }</b>건
						</p>
					</div>
				</div>
				<div class="review-list">
					<ul id="pagable-list" data-list-object="append">
						<c:forEach items="${myreviewlist }" var="dto">
							<li style="cursor: pointer" class="item" data-idx="695" data-regdate="${dto.reg_date }" data-review="${dto.content }" data-image="/product/${system_name }" data-title="${dto.products_name }" id="detailReview">
								<div class="thumb">
									<img src="/file/download/product/${dto.system_name }" onerror="this.src='/resources/assets/images/common/no_img.png'">
								</div>
								<div class="content">
									<h5 class="title">${dto.products_name }</h5>
									<p style="text-overflow: ellipsis; overflow: hidden; word-break: break-word; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical">${dto.content }</p>
									<div class="span-tie">
										<span>${auth.getName() }</span> <span>${dto.reg_date }</span>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
</div>
</main>
</div>
</html>
