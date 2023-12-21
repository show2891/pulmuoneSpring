<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="personalInfo" tabindex="-1" aria-labelledby="personalInfo" aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="personalInfoLabel">개인정보 수집 및 이용</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<script type="text/javascript">
					$().ready(function() {
						// header - 공유버튼 숨김
						$(".sns-share").hide();
					});
				</script>

				<style>
.agreement-wrap th, td {
	word-break: break-all;
	width: auto;
}

.agreement-wrap table {
	border-right: 1px #eee solid;
	border-top: 1px #eee solid;
	margin-bottom: 15px;
}

.agreement-wrap th {
	padding: 10px;
	border-bottom: 1px #333 solid;
	border-left: 1px #eee solid;
}

.agreement-wrap td {
	padding: 10px;
	border-bottom: 1px #eee solid;
	border-left: 1px #eee solid;
}
</style>

				<div class="container" style="width: 100%; min-width: auto; padding: 30px;">
					<div class="agreement-wrap">
						<table>
							<tbody>
								<tr>
									<th>수집•이용 목적</th>
									<th>수집 항목</th>
									<th>보유기간</th>
								</tr>
								<tr>
									<td style="font-weight: bold" rowspan="3">회원가입, 부정이용 및 비인가 사용방지, 서비스 이용에 따른 본인식별 및 연령확인</td>
									<td>아이디, 패스워드, 이메일</td>
									<td style="font-weight: bold" rowspan="8">회원탈퇴 시 파기, CI값은 무분별한 재가입 방지를 위해 30일간 보관 후 파기<br> (단, 관계 법률에 의해 보존할 필요가 있는 경우 법률에 따른 보관 기간 동안 보관)
									</td>
								</tr>
								<tr>
									<td>이름, 휴대전화번호, 생년월일, 성별,</td>
								</tr>
								<tr>
									<td>CI(연계정보), DI(중복확인정보)</td>
								</tr>
								<tr>
									<td style="font-weight: bold" rowspan="2">SNS 간편로그인(네이버, 카카오톡)</td>
									<td>이름, 이메일 주소, 성별, 생일</td>
								</tr>
								<tr>
									<td>닉네임, 프로필사진</td>
								</tr>
								<tr>
									<td style="font-weight: bold" rowspan="3">주문/결제/배송</td>
									<td>이름, 휴대전화번호, 배송지주소</td>
								</tr>
								<tr>
									<td>출입정보</td>
								</tr>
								<tr>
									<td>은행계좌번호, 신용카드번호</td>
								</tr>
							</tbody>
						</table>
						<p>※ 풀무원녹즙 회원가입을 위해 개인정보 수집 및 이용에 거부할 권리가 있으나, 동의를 거부 할 경우 풀무원녹즙 서비스 이용이 불가합니다.</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>