<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="checkout-input-area">
	<div class="checkout-input-title-area">
		<h3 class="checkout-input-title">결제정보</h3>
	</div>
	
	<div class="select-checkout-area">
		<ul class="nav nav-tabs nav-justified" id="myTab-area" role="tablist">
			<li data-code="CARD" class="nav-item" role="presentation">
				<a href="#" class="nav-link active" id="checkout-type-tab1" data-target="#checkout-type1" data-toggle="tab"
					data-value="CARD" role="tab" aria-controls="checkout-type1" aria-selected="true">
					<img src="/resources/assets/images/ui/checkout-item01.png" alt="">
					<span>카드결제</span>
				</a>
			</li>
			<li data-code="BANK" class="nav-item" role="presentation">
				<a href="#" data-value="BANK" class="nav-link" id="checkout-type-tab2" data-target="#checkout-type2"
					data-toggle="tab" role="tab" aria-controls="checkout-type2" aria-selected="false">
					<span>계좌결제</span>
				</a>
			</li>
			<li data-code="CASH" class="nav-item" role="presentation">
				<a href="#" data-value="CASH" class="nav-link" id="checkout-type-tab3" data-target="#checkout-type3"
					data-toggle="tab" role="tab" aria-controls="checkout-type3" aria-selected="false">
				<span>현장결제</span>
				</a>
			</li>
		</ul>
		
		<div class="tab-content checkout-input-box" id="myTabContent">
			<div class="tab-pane fade show active" style="margin-top: 32px;" role="tabpanel" id="checkout-type1" aria-labelledby="checkout-type-tab1">
				<div class="input-area">
					<div class="form-input">
						<dl>
							<dt>
								<label for="input01">카드번호</label>
							</dt>
							<dd>
								<input type="text" name="cardNumber" id="cardNumber" class="numberOnly" maxlength="19" title="카드번호 입력" placeholder="카드번호 입력">
							</dd>
						</dl>
					</div>
					
					<div class="form-input">
						<dl>
							<dt>
								<label for="input01">생년월일</label>
							</dt>
							<dd>
								<input type="text" id="birthDay" class="numberOnly" maxlength="6" name="birthDay" title="생년월일 입력 (YYMMDD 6자리)" placeholder="생년월일 입력 (예: 990101)" value="">
							</dd>
						</dl>
					</div>
					
					<div class="form-input">
						<dl>
							<dt>
								<label for="input01">유효기간</label>
							</dt>
							<dd>
								<input type="text" id="validYYMM" name="validYYMM" title="유효기간 입력" placeholder="유효기간 입력 (MM/YY)" maxlength="5" value="" onKeyup="inputValidThru(this);">
								<button type="button" id="validCardBtn" class="btn-square btn-black">카드인증</button>
							</dd>
						</dl>
					</div>
				</div>
			</div>
			
			<div class="tab-pane fade" style="margin-top: 32px;" role="tabpanel" id="checkout-type2" aria-labelledby="checkout-type-tab2">
				<div class="input-area">
					<div class="form-input">
						<dl>
							<dt>
								<label for="input01">예금주</label>
							</dt>
							<dd>
								<input type="text" id="input01" name="input01" title="예금주 입력" placeholder="" readonly value="${ member.name }">
							</dd>
						</dl>
					</div>
													
					<div class="form-input">
						<dl>
							<dt>
								<label for="bankCode">은행</label>
							</dt>
							<dd>
								<select name="bankCode" id="bankCode" class="form-select form-select-lg" aria-label=".form-select-lg example">
									<option value="">은행을 선택해주세요.</option>
									<option value="002">산업은행</option>
									<option value="003">기업은행</option>
									<option value="004">국민은행</option>
									<option value="007">수협중앙회</option>
									<option value="011">농협은행</option>
									<option value="020">우리은행</option>
									<option value="023">SC은행</option>
									<option value="027">한국씨티은행</option>
									<option value="031">대구은행</option>
									<option value="032">부산은행</option>
									<option value="034">광주은행</option>
									<option value="035">제주은행</option>
									<option value="037">전북은행</option>
									<option value="039">경남은행</option>
									<option value="045">새마을금고</option>
									<option value="048">신협중앙회</option>
									<option value="071">우체국</option>
									<option value="081">KEB하나은행</option>
									<option value="088">신한은행</option>
									<option value="209">유안타증권</option>
									<option value="240">삼성증권</option>
								</select>
								<input type="hidden" name="fdCustId" id="fdCustId">
								<input type="hidden" name="cmsId" id="cmsId">
							</dd>
						</dl>
					</div>
													
					<div class="form-input">
						<dl>
							<dt>
								<label for="accountNumber">계좌번호</label>
							</dt>
							<dd>
								<input type="text" id="accountNumber" name="accountNumber" title="계좌번호 입력" placeholder="계좌번호 입력">
								<button id="callCms" data-isnew="true" data-phone="${member.tel}" type="button" class="btn-square btn-black">ARS인증</button>
								<input id="hasRequest" value="false" type="hidden">
							</dd>
						</dl>
						<p id="afterArs" style="margin-left: 137px; margin-top: 5px; display: none;">인증이 완료되었습니다.</p>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" role="tabpanel" id="checkout-type3" aria-labelledby="checkout-type-tab3">
			</div>
		</div>
	</div>
</div>