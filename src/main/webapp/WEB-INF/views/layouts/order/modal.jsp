<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal" id="addressModal" tabindex="-1" aria-labelledby="addressModal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content ">
			<div class="modal-header">
				<h5 class="modal-title" id="emailPolicyLabel">주소록</h5>
				<button type="button" class="close" data-dismiss="modal"	aria-label="Close"></button>
			</div>

			<div class="modal-body" style="max-height: 80vh; overflow-y: scroll">
				<div class="drinking-list address" style="margin: 0"></div>
				<div style="padding: 20px; display: none" id="showMoreBtn">
					<button class="btn-default btn-white" style="width: 100%">더보기</button>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="alertModal" tabindex="-1" aria-labelledby="alertModalLabel" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="alertModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				</button>
			</div>
			<div class="modal-body">
				<div class="drinking-list address" style="margin: 0"></div>
			</div>
			<button type="button" class="modal-footer" data-dismiss="modal">확인</button>
		</div>
	</div>
</div>