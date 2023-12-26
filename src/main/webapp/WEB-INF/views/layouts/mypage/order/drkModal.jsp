<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="modal fade" id="nicknameModal" tabindex="-1" aria-labelledby="nicknameModal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog basic-width modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header" style="border:0; padding:22px 28px 14px 30px;">
				<h5 class="modal-title" id="nicknameModalLabel" style="margin-top:2px;">애칭수정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				</button>
			</div>
			<div style="padding:0 30px 48px">
				<div class="form-input none-dt">
					<dl>
						<dt>
							<label>변경할 애칭</label>
						</dt>
						<dd>
							<input id="nickname-edit" name="nickname" value="${ drkOrderMypageDTO.drk_order_name }" placeholder="변경할 애칭" type="text">
						</dd>
					</dl>
				</div>
			</div>
			<div class="button-set">
				<button id="nickname-edit-btn" class="button-basic primary">수정하기</button>
			</div>
		</div>
	</div>
</div>