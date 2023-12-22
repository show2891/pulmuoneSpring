<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal show fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModal" aria-hidden="true">
	<div class="modal-dialog modal-review modal-dialog-centered">
		<div class="modal-content" style="border-radius: 40px;">
			<div class="modal-body horizontal-layer">
				<div class="thumb-area" id="imagePlace">
					<img onerror="this.src='/resources/assets/images/common/no_img.png'" alt="">
				</div>
				<div class="review-contents-area">
					<div class="modal-header" style="border: 0;">
						<h4 id="titlePlace"></h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="scrollable" style="padding-top: 0;">
						<p id="contentsPlace"></p>
						<div class="span-tie">
							<span></span> <span id="datePlace"></span>
						</div>
						<div class="foot-case">
							<button class="modify rounded-button">수정</button>
							<button class="delete rounded-button">삭제</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>