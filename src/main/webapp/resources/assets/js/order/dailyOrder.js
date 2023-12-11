const weekDays = ['월','화','수','목','금'];

function weekDayText () {
	$(".weekDays").each(function () {
		let count = $(this).data("count");
		let weekDay = weekDays[count];
		$(this).text(weekDay);
	});
};

// 영수증 계산
function calculateReceipt() {

	// 초기화
    var totalPrice = 0;
    var totalCount = 0;
    var count = 0;
    var totalPay = 0;
		    
    // 1주 예상 금액 계산
    $("#order_targets [data-itemcode][data-price]").each(function () {
    	count = 0;
		$(this).find("input[data-count]").each(function () {
        	count += parseInt($(this).val(), 10);
     	});
      	totalCount += count;
      	totalPrice += parseInt($(this).attr("data-price"), 10) * count;
    });
	
    // 4주 예상 금액 계산
    totalPay = totalPrice*4;
    
    // 적용
    $("[data-price-view='origin']").text(totalPay).toLocaleString();
    $("[data-price-view='sale']").text(totalPay).toLocaleString();
    $("[data-price-view='payment']").text(totalPay).toLocaleString();
    $("input[name='payPrice']").val(totalPay);
    
} // calculateReceipt


// 상품 + 버튼
function plusBtn(btn){
	let em = btn.next("em");
	let input = btn.parent().prev().prev();
	let cnt = parseInt(em.text())+1;
	em.text(cnt);
	input.val(cnt);
	calculateReceipt();
};

// 상품 - 버튼
function minusBtn(btn){
	let em = btn.prev("em")
	let input = btn.parent().prev().prev();
	let cnt = parseInt(em.text())-1;
	if (cnt == -1) return;
	em.text(cnt);
	input.val(cnt);
	calculateReceipt();
};


// 카드 인증 버튼
function validCard(){
	// 입력한 정보 가져오기
	let cardNumber = $("#cardNumber").val();
	let birthDay = $("#birthDay").val();
	let validYYMM = $("#validYYMM").val();
	
	if(!cardNumber || cardNumber.length < 16) {
		alert("카드 번호를 확인해주세요.");
		return;
	}
	if(!birthDay || birthDay.length < 6) {
		alert("생년월일을 확인해주세요.");
		return;
	}
	if(!validYYMM || validYYMM.length < 5) {
		alert("유효기간을 확인해주세요.");
		return;
	}
	
	alert("인증이 완료되었습니다.");
	$("#validCardBtn").text("다시인증");
};


function inputValidThru(period) {
	// replace 함수를 사용하여 슬래시( / )을 공백으로 치환한다.
    var replaceCard = period.value.replace(/\//g, "");

	// 텍스트박스의 입력값이 4~5글자 사이가 되는 경우에만 실행한다.
    if(replaceCard.length >= 4 && replaceCard.length < 5) {

		var inputMonth = replaceCard.substring(0, 2);    // 선언한 변수 month에 월의 정보값을 담는다.
        var inputYear = replaceCard.substring(2, 4);       // 선언한 변수 year에 년의 정보값을 담는다.

        // 현재 날짜 값을 구한다.
		var nowDate = new Date();
        var nowMonth = autoLeftPad(nowDate.getMonth() + 1, 2);
        var nowYear = autoLeftPad(nowDate.getFullYear().toString().substr(2, 2), 2);

        // isFinite함수를 사용하여 문자가 선언되었는지 확인한다.
        if(isFinite(inputMonth + inputYear) == false) {
            alert("문자는 입력하실 수 없습니다.");
            period.value = "";
            return false;
        } // if

        // 입력한 월이 12월 보다 큰 경우
        if(inputMonth > 12) {
            alert("12월보다 큰 월수는 입력하실 수 없습니다. ");
            period.value = "";
            return false;
        } // if

        // 입력한 유효기간을 현재날짜와 비교하여 사용 가능 여부를 판단한다.
        if((inputYear + inputMonth) <= (nowYear + nowMonth)) {
            alert("유효기간이 만료된 카드는 사용하실 수 없습니다.");
            period.value = "";
            return false;
        } // if

        period.value = inputMonth + "/" + inputYear;
    } // if
} // inputValidThru

// 1자리 문자열의 경우 앞자리에 숫자 0을 자동으로 채워 00형태로 출력하기위한 함수
function autoLeftPad(num, digit) {
    if(String(num).length < digit) {
        num = new Array(digit - String(num).length + 1).join('0') + num;
    } // if
    return num;
} // autoLeftPad


// ARS 인증 버튼
function callCms(btn){
	
	// 값 가져오기
	let name = $("#input01").val();
	let bank = $("#bankCode").val();
	let account = $("#accountNumber").val();
	
	if(!bank || bank == "") {
		alert("은행을 선택해주세요.");
		return;
	}
	if(!account || account.length < 10 || account.length > 14) {
		alert("계좌번호를 확인해주세요.");
		return;
	}
	
	// 휴대폰 번호 가져오기
	let phone = btn.data("phone");
	let phone1 = phone.substr(0,3);
	let phone2 = phone.substr(9,12);
	
	// 알람창 띄우기
	let content = `<div class="p-cont">
                    		<p>회원정보에 등록된 아래 휴대폰 번호로 ARS 인증을 위한 전화를 드립니다. <br><br><span class="fc-pos-green">인증요청 해주세요.</span></p>
                    		<dl class="ars-auth-num">
                        		<dt>휴대폰번호</dt>
                        		<dd>${phone1}-****-${phone2}</dd>
                    		</dl>
                    		<ul class="dot-list ars-auth-list">
                        		<li>휴대폰 번호는 <a href="/mypage/personal/info" class="td-underline fc-gray">HOME &gt; MY녹즙 &gt; 개인정보
                            		&gt; 개인정보 관리</a> 에서 변경할 수 있습니다.</li>
                    		</ul>
                		</div>`;
	alert(content);
	
	$(".modal-footer").on("click", function(){
		setTimeout(()=> {
			$("#afterArs").css("display","block");
		}, 3000);
	})
};