/* 장민창  - ex2i */
function trim(domIdOrClass) {
	var text = $.trim($(domIdOrClass).val());
	$(domIdOrClass).val(text);
}

const pwChk = (value) => {
	// 대문자로 바뀔 수 있는 문자 따로 구분
	const word = ["qwertyuiop", "asdfghjkl", "zxcvbnm"];

	// 숫자를 제외한 모든 문자 대문자로
	const wordAll = ["1234567890", ...word, ...word.map(v => v.toUpperCase())];

	// 해당 문자 배열을 역순으로 조건 생성
	const reverseWord = [...wordAll.map(v => [...v].reverse().join(""))];

	// 생성한 조건을 합치기
	const keyboard = [...wordAll, ...reverseWord];

	for (let i = 0; i < value.length-2; i++) {
		const sliceValue = value.substring(i, i + 3);

		if (keyboard.some(code => code.includes(sliceValue))) {
			return true;
		}
	}

	return false;
}

function hasError() {
	return $(".error-wrap").length > 0;
}

function showErrorForm(target, message){
	$("#"+target).closest(".form-input").removeClass("success");
	$("#"+target).closest(".form-input").addClass("error");
	$("#"+target).text(message);
}
function hideErrorForm(target) {
	$("#"+target).closest(".form-input").removeClass("success");
	$("#"+target).closest(".form-input").removeClass("error");
	$("#"+target).text("");
}

function showSuccessForm(target, message){
	$("#"+target).closest(".form-input").removeClass("error");
	$("#"+target).closest(".form-input").addClass("success");
	$("#"+target).text(message);
}

function showError(target, message){
	$("#"+target).closest("div").removeClass("success-wrap");
	$("#"+target).closest("div").addClass("error-wrap");
	$("#"+target).text(message);
}

function hideError(target) {
	$("#"+target).closest("div").removeClass("success-wrap");
	$("#"+target).closest("div").removeClass("error-wrap");
	$("#"+target).text("");
}

function showSuccess(target, message){
	$("#"+target).closest("div").removeClass("error-wrap");
	$("#"+target).closest("div").addClass("success-wrap");
	$("#"+target).text(message);
}

var message = {
		"delete": {
			"no-item": "선택한 항목이 없습니다."
			, "confirm": "삭제하시겠습니까?"
			, "ok": "삭제되었습니다."
		}
		, "save": {
			"confirm": "등록한 내용을 저장하시겠습니까?"
			, "confirm-mod": "수정한 내용을 저장하시겠습니까?"
			, "required": "을(를) 입력해주세요."
			, "ok": "저장되었습니다."
		}
		, "list": {
			"confirm": "내용을 저장하지 않고, 목록으로 이동합니다."
		}
		, "cancel": {
			"confirm": "입력한 내용은 저장되지 않습니다."
		}
		, "find-file": {
			"miss-match": "등록할 수 없는 파일입니다." // 용량 및 확장자가 맞지 않은 경우
			, "over-count": "등록가능 파일 수를 초과하였습니다."
		}
		, "member": {
			"regist": {
				"agree": {
					"agree1": "풀무원녹즙 이용약관에 동의해주세요."
					, "agree2": "개인정보 수집·이용 동의에 동의해주세요."
					, "optional": "광고성 정보수신 동의 시, 수신방법을 하나 이상 선택해 주세요."
				}
				, "id": {
					"empty": "아이디를 입력해 주세요."
					, "space": "공백은 사용할 수 없습니다."
					, "format": "영문,숫자 조합하여 6~15자로 입력해주세요."
					, "isFormat": function(param) {
						if(!/^[a-zA-Z0-9]{6,15}$/.test(param)) {
					        return false;
					    }
						var chkNum = param.search(/[0-9]/g);
					    var chkEng = param.search(/[a-z]/ig);
					    if ( chkNum < 0 || chkEng < 0 ) {
					    	return false;
					    }
					    return true;
					}
					, "duplicate": "이미 사용중인 아이디 입니다."
					, "checkDuplicate": "아이디 중복확인을 진행해 주세요."
					, "ok": "사용할 수 있는 아이디입니다. "
				}
				, "password": {
					"empty": "비밀번호를 입력해주세요."
					, "space": "공백은 사용할 수 없습니다."
					, "format": "영문, 숫자, 특수문자를 조합하여 8~20자로 입력해주세요."
					, "isFormat": function(param) {
						var chkNum = param.search(/[0-9]/);
					    var chkEng = param.search(/[A-Za-z]/);
					    var chkSymbol = param.search(/[!@#$%^&*(),.?\\\":\\\\{}|<>\\\[\\\]]/);
						if ( param.length >= 8 && param.length <= 20 ) {
						    if ( chkNum >= 0 && chkEng >= 0 && chkSymbol >= 0) {
						    	return !pwChk(param);
						    }
						}

					    return false;
					}
					, "connect": "연속된 숫자는 사용할 수 없습니다."
					, "isConnect": function(param) {
						return /(\d)\1\1/.test(param);
					}
					, "sameId": "아이디와 동일한 비밀번호는 사용할 수 없습니다."
					, "more": "비밀번호를 한 번 더 입력해주세요."
					, "notMatch": "비밀번호가 일치하지 않습니다.",
					isContainsPersonalInfo(password, phone, email,birthday) {
						const phone1 = phone.substring(3,7);
						const phone2 = phone.substring(7,11);
						const emailId = email.split('@')[0];
						if(birthday&&birthday.toString().length===8){
							const birthdayYMD = birthday.toString().substring(2);
							const birthdayMD = birthday.toString().substring(4);
							console.log(birthdayMD)
							return password.includes(phone1) || password.includes(phone2) || password.includes(emailId)|| password.includes(birthdayMD)|| password.includes(birthdayYMD);
						}
						return password.includes(phone1) || password.includes(phone2) || password.includes(emailId);
					},
					personalInfo:'생일 및 전화번호 이메일 아이디는 비밀번호에 포함될수 없습니다.',
				}
				, "email": {
					"empty": "이메일을 입력해 주세요."
					, "format": "이메일을 정확히 입력해주세요."
					, "isFormat": function(param) {
						return /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(param);
					}
					, "duplicate": "이미 사용중인 이메일 입니다."
					, "ok": "사용할 수 있는 이메일입니다."
				}
				, "interest": {
					"empty": "관심사를 선택해주세요."
					, "max": "관심사는 최대 3개까지 선택할 수 있습니다."
				}
			}
			, "login": {
				"empty": "아이디와 비밀번호를 모두 입력해주세요."
			}
			, "password": {
				"sameId": "아이디와 동일한 비밀번호는 사용할 수 없습니다."
				, "more": "비밀번호를 한 번 더 입력해주세요."
				, "notMatch": "비밀번호가 일치하지 않습니다."
			}
		}
		, "product": {
			"no-item": "선택된 제품이 없습니다."
		}
		, "interest": {
			"login": "로그인 후 찜한 상품으로 담을 수 있습니다."
			, "put": "찜한상품에 담겼습니다."
			, "remove": "찜한상품이 해제되었습니다."
		}
		, "inquiry": {
			"title": "제목을 입력해주세요."
			, "contents": "문의사항을 입력해주세요."
		}
		, "customer" : {
			"custnumberFormat" : "고객번호를 정확히 입력해주세요."
			, "agree" : "개인정보 수집 및 이용에 동의해주세요."
			, "name" : "이름을 입력해주세요."
			, "phone" : {
				"format" : "휴대폰번호를 정확히 입력해주세요."
				, "emptyAuthNumber" : "휴대폰을 인증해주세요."
				, "sendAuthNumber" : "인증번호가 발송되었습니다."
				, "notMember" : "가입된 정보가 없습니다. 휴대폰번호를 정확히 입력해주세요."
				, "isFormat": function(param) {
					var regExp =/^(0|1){3,3}[0-9]{8,8}$/;
					if( regExp.test(param) ){
						return true;
					} else {
						return false;
					}
				}
			}
			, "authNumber" : {
				"format" : "정확한 인증번호 6자리를 입력해주세요"
				, "overTime" : "인증번호 요청 후 3분이 지났습니다. 다시 시도해주시기 바랍니다."
				, "ok" : "인증되었습니다."
			}
			, "custNumber" : {
				"regist" : "고객번호가 등록되었습니다."
				, "notFind" : "입력하신 내용과 일치하는 정보가 없습니다.<br>음용 중이신 경우 1:1 문의 게시판을 통해 문의주세요."
			}
		}
		, "address" : {
			"empty": "주소를 입력해 주세요."
			, "noItem": "주소를 선택해주세요."
		}
};