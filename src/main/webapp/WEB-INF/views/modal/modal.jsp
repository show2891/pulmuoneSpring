<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="alertModal" tabindex="-1" aria-labelledby="alertModalLabel" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="alertModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body"></div>
			<button type="button" class="modal-footer" data-dismiss="modal">확인</button>
		</div>
	</div>
</div>
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="confirmModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">삭제하시겠습니까?</div>
			<div class="modal-footer">
				<button type="button" class="cancel" data-dismiss="modal">취소</button>
				<button type="button" class="confirm">확인</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade show" id="reviewModal" tabindex="-1" aria-labelledby="reviewModal" style="display: block;" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-review modal-dialog-centered">
		<div class="modal-content" style="border-radius: 40px;">
			<div class="modal-body horizontal-layer">
				<div class="thumb-area" id="imagePlace">
					<img onerror="this.src='/resources/images/common/no_img.png'" alt="" src="/file/download/product/685a5aac-588c-4366-82c4-217f17b38067.jpg">
				</div>
				<div class="review-contents-area">
					<div class="modal-header" style="border: 0;">
						<h4 id="titlePlace">핸디밀 그린샐러드 식이섬유플랜</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="scrollable" style="padding-top: 0;">
						<p id="contentsPlace">샤인 머스켓 맛이 나, 부담없이 먹을 수 있어 좋습니다.</p>
						<div class="span-tie">
							<span>신종혁</span> <span id="datePlace">2023-11-03</span>
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