<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="noAvailModal" tabindex="-1" aria-labelledby="noAvailModalLabel" aria-hidden="true">
	<div class="modal-dialog basic-width modal-dialog-centered">

		<div class="modal-content">
			<div class="modal-header" style="border: 0; padding-bottom: 16px;">
				<h5 class="modal-title" id="noAvailModalLabel"></h5>
				<button style="margin-right: -2px; margin-top: 4px;" type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
					<img src="/resources/assets/images/common/btn_close.png">
				</button>
			</div>
			<p style="text-align: center; font-size: 16px; font-weight: 500; margin-bottom: 20px; color: #333; margin-top: 10px;">
				다음 상품은 현재 주문이 불가능하여 제외되었습니다. <span data-cancelable=""><br>담은 제품을 확인하시겠습니까?</span>
			</p>
			<p data-all-ignore="" style="display: none; text-align: center; font-size: 16px; font-weight: 500; margin-bottom: 20px; color: #333; margin-top: 10px;">
				다음 상품은 현재 <br>주문이 불가능합니다.
			</p>
			<ul class="product-content-list" style="margin-bottom: 0;">
			</ul>
			<div class="button-set">
				<button class="button-basic black" data-btn="c" data-cancelable="">취소</button>
				<button class="button-basic primary" data-btn="o">확인</button>
			</div>
		</div>
	</div>
</div>