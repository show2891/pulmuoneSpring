<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- 		<div class="breadcrumb-style"> -->
<!-- 			<div class="container"> -->
<!-- 				<ul> -->
<!-- 					<li><a href="/">홈</a></li> -->
<!-- 					<li><a href="/mypage">MY녹즙</a></li> -->
<!-- 					<li><a class="" href="/mypage/personal/address.do">개인정보</a></li> -->
<!-- 					<li><a class="active" href="/mypage/personal/address.do">주소록</a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->


			<div class="container">
					<div class="border-wrapper">
						<h2 class="container-title">
							주소 등록
						</h2>
					</div>
					<div class="address-input-form">
						<div class="input-group">
							<div class="form-input">
								<dl>
									<dt><label for="nickname">애칭</label></dt>
									<dd>
										<input type="text" id="nickname" name="nickname" value="${ dto.addrName }">
									</dd>
								</dl>
							</div>
							<div class="form-input">
								<dl>
									<dt><label for="name">이름</label></dt>
									<dd>
										<input name="name" id="name" value="${ dto.name }" type="text">
									</dd>
								</dl>
							</div>
							<div class="form-input">
								<dl>
									<dt><label for="phoneNumber">휴대폰번호</label></dt>
									<dd>
										<input data-call-text="" value="" maxlength="13" class="numberOnly" name="phoneNumber" id="phoneNumber" type="hidden">
									<input value="${ dto.tel }" maxlength="13" class="numberOnly" name="_x_phoneNumber"></dd>
								</dl>
							</div>
						</div>
						<div class="input-group">
							<div class="form-input">
								<dl>
									<dt style="min-width:140px;"><label>배달장소</label></dt>
									<dd>
										<div class="radio-area radio-type2">
											<div class="radio">
												<input value="H" type="radio" id="placeTypeH" ${ dto.dest == 0 ? 'checked="checked"' : ''  } name="placeType">
												<label for="placeTypeH">가정</label>
											</div>
											<div class="radio">
												<input value="P" type="radio" id="placeTypeP" ${ dto.dest == 1 ? 'checked="checked"' : ''  } name="placeType">
												<label for="placeTypeP">회사/사무실</label>
											</div>
										</div>
									</dd>
								</dl>
							</div>
							<div class="form-input">
								<dl>
									<dt><label for="post">우편번호</label></dt>
									<dd>
										<input name="post" id="post" readonly="" value="${ dto.zipCode }" type="text">
										<button type="button" class="btn-square search-addr btn-black" style="width:121px; padding:0">
											우편번호찾기
										</button>
									</dd>
								</dl>
							</div>
							<div class="form-input">
								<dl>
									<dt><label for="addr1">주소입력</label></dt>
									<dd>
										<input readonly="" name="addr1" id="addr1" type="text" value="${ dto.addr }">
										<input readonly="" name="oldAddr" id="oldAddr" type="hidden" value="">
									</dd>
								</dl>
							</div>
							<div class="form-input">
								<dl>
									<dt><label for="addr2">상세주소</label></dt>
									<dd>
										<input name="addr2" id="addr2" value="${ dto.addrDetail }" type="text">
									</dd>
								</dl>
							</div>
							<div class="form-input">
								<dl>
									<dt><label for="memo">배송메모</label></dt>
									<dd>
										<input name="memo" id="memo" value="${ dto.memo != 'null' ? dto.memo : ''}" type="text">
									</dd>
								</dl>
							</div>
						</div>
						<div class="btn-area-right" style="border-top:1px #e5e5e5 solid; padding-top:20px; margin-top:20px;">
							<button type="button" id="cancel" class="btn-default btn-white">취소하기</button>
							<button type="button" id="register" class="btn-default">저장하기</button>
						</div>
					</div>
				</div>
			
<%-- 		<%@ include file="/WEB-INF/views/ui/footermodal.jsp"%> --%>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
 <script>
	$(function (){
        function searchPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; 		 // 참고 항목 변수

                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraRoadAddr !== '') {
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }
                    // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                    if (fullRoadAddr !== '') {
                        fullRoadAddr += extraRoadAddr;
                    }
                    $("#post").val(data.zonecode);
                    $("#addr1").val(data.roadAddress);

                    $("#oldAddr").val(data.autoJibunAddress || data.jibunAddress);
                    // 우편번호와 주소 정보를 해당 필드에 넣는다. (구 우편번호 data.postcode)


                    // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                    $("#addr2").keyup();

                    //
                    // if(data.roadAddress !== ''){
                    // 	$("#detailAddress").val(extraRoadAddr);
                    // } else {
                    // 	$("#detailAddress").val('');
                    // }
                    //
                }
            }).open();
        }
        $('.search-addr').click(searchPostcode)


		$('#cancel').click(function (){
            if(confirm('작업한 내용이 사라집니다. 취소 하시겠습니까?')){
                location.href = '/mypage/personal/address'
            }
		})
		$('#register').click(function (message){
            const formArr = $('#addressForm').serializeArray();
            let hasEmpty = false;
            var data = {}
			formArr.filter(v=>v.name==='phoneNumber')[0].value=$('input[name=_x_phoneNumber]').val();
			formArr.every(
					x => {
						if (x.name == 'memo') {
							return true;
						}
						if (x.name == 'oldAddr') {
							return true;
						}

						if (!x.value.trim()) {
							hasEmpty = true;
							const label = `label[for=${x.name}]`

							let input = '#' + x.name
							if (x.name === 'phoneNumber') input = 'input[name=_x_phoneNumber]'
							alert($('#addressForm').find(label).text() + '을(를) 입력 해주세요',
									() => $(input).focus());
							hasEmpty = true;
							return false;
						}

						if (x.name === 'phoneNumber') {
							console.log(x.value)
							if (!/^[0-9]{3}-[0-9]{3,4}-[0-9]{4}/.test(x.value)){
								hasEmpty = true;
								return alert('휴대전화 형식에 맞게 입력해주세요.',
										() => $('input[name=_x_phoneNumber]').focus())
							}

						}
						data[x.name] = x.value;
						return true;
					}
			)
			if(hasEmpty)return;

            if(""){
				post({url:'/mypage/personal/address/modify/',param:$.param(data)},function (r){
                    if(r.RESULT_MSG){
                        alert('주소록이 정상적으로 수정되었습니다.',()=>location.href = '/mypage/personal/address')
                    }else {
                        return alert('잘못된 요청입니다.')
                    }
				})
            }else {
                post({url:'/mypage/personal/address/write',param: $.param(data)},function (r){
                    if(r.RESULT_MSG){
                        alert('주소록이 정상적으로 등록되었습니다.',()=>location.href = '/mypage/personal/address')
                    }else {
                        return alert('잘못된 요청입니다.')
                    }
                })
            }
		})
	})
</script>
<div class="modal fade" id="alertModal" tabindex="-1" aria-labelledby="alertModalLabel" aria-modal="true" role="dialog">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="alertModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				</button>
			</div>
			<div class="modal-body"></div>
			<button type="button" class="modal-footer" data-dismiss="modal">확인</button>
		</div>
	</div>
</div>
