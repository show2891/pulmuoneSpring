$(window).scroll(function () {
    if ($('.js-fixed-wrap').length) {
        fixedSidebar();//sidebar posTop
    }
});

$().ready(function () {
    console.log(1111)
    if ($('.js-fixed-wrap').length) {
        fixedSidebar();//sidebar posTop
    }
    if ($('.js-ip-autofocus').length) {
        ipAutoFocus();//sidebar posTop
    }


    // 카드 자동결제
    $(".cardNumber, #birthDay, #validYYMM").focus(function () {
        if ($("#validCardBtn").text() == "다시인증") {
            $("#validCardBtn").closest("table").find("input").prop("readonly", true);
        }
    });

    $(".cardNumber, #birthDay, #validYYMM").keydown(function () {
        if ($("#validCardBtn").text() == "다시인증") {
            return false;
        }
    });

    // 카드번호 입력
    $(".cardNumber").keyup(function () {

        if (!$("#rdo-pay-1").prop("checked")) {
            $(".js-tab-cont-0").find("input[type=text], input[type=password], input[type=hidden], select").val("");
            $(".js-tab-cont-0").find("*").removeClass("error-wrap");
            $(".js-tab-cont-0").find(".message").text("");
            return;
        }

        if ($("#validCardBtn").text() == "다시인증") {
            return false;
        }

        var cardNumber = "";

        $(".cardNumber").each(function (i, data) {
            var text = $.trim($(data).val())
            $(data).val(text);

            cardNumber += text;
        });

        if (cardNumber.length < 15) {
            showError("cardNumberErrorMessage", "카드번호를 정확히 입력해주세요.");
        } else {
            hideError("cardNumberErrorMessage");
        }

    });

    // 생년월일 입력
    $("#birthDay").keyup(function () {

        if (!$("#rdo-pay-1").prop("checked")) {
            $(".js-tab-cont-0").find("input[type=text], input[type=password], input[type=hidden], select").val("");
            $(".js-tab-cont-0").find("*").removeClass("error-wrap");
            $(".js-tab-cont-0").find(".message").text("");
            return;
        }

        if ($("#validCardBtn").text() == "다시인증") {
            return false;
        }

        var text = $.trim($(this).val());
        $(this).val(text);

        if (text.length < 6) {
            showError("birthDayErrorMessage", "생년월일을 정확히 입력해주세요.");
        } else {
            hideError("birthDayErrorMessage");
        }
    });

    // 유효기간 입력
    // $("#validYYMM").keyup(function() {
    //
    // 	if ( !$("#rdo-pay-1").prop("checked") ) {
    // 		$(".js-tab-cont-0").find("input[type=text], input[type=password], input[type=hidden], select").val("");
    // 		$(".js-tab-cont-0").find("*").removeClass("error-wrap");
    // 		$(".js-tab-cont-0").find(".message").text("");
    // 		return;
    // 	}
    //
    // 	if ( $("#validCardBtn").text() == "다시인증" ) {
    // 		return false;
    // 	}
    //
    // 	var text = $.trim($(this).val());
    // 	$(this).val(text);
    //
    // 	if ( text.length == 4 ) {
    // 		var yymm = text.replace(/[^0-9]/g, '');
    // 		if ( yymm.length == 4 ) {
    // 			text = text.replace(/(.{2})/g,"$1/")
    // 			$(this).val(text.substr(0,5));
    // 		}
    // 	}
    //
    // 	if ( text.length < 5 ) {
    // 		showError("validYYMMErrorMessage", "카드 유효기간을 정확히 입력해주세요.");
    // 	}
    // 	else if ( text.indexOf("/") != 2 ) {
    // 		showError("validYYMMErrorMessage", "카드 유효기간을 정확히 입력해주세요.");
    // 	}
    // 	else {
    // 		var yy = text.split("/")[0].replace(/[^0-9]/g, '');
    // 		var mm = text.split("/")[1].replace(/[^0-9]/g, '');
    //
    // 		if ( yy.length != 2 || mm.length != 2 ) {
    // 			showError("validYYMMErrorMessage", "카드 유효기간을 정확히 입력해주세요.");
    // 		}
    // 		else if ( mm > 12 || mm <= 0 ) {
    // 			showError("validYYMMErrorMessage", "카드 유효기간을 정확히 입력해주세요.");
    // 		}
    // 		else {
    // 			hideError("validYYMMErrorMessage");
    // 		}
    // 	}
    // });

    // 카드 인증 버튼 클릭
    $("#validCardBtn").click(function () {

        var officeCode = $("#officeCode").val();
        if (officeCode == undefined || officeCode == "" || officeCode == null) {
            alert("배달정보를 입력해주세요.");
            return;
        }

        if (!$(".nav-link[data-value=0]").hasClass('active')) {
            $(".js-tab-cont-0").find("input[type=text], input[type=password], input[type=hidden], select").val("");
            $(".js-tab-cont-0").find("*").removeClass("error-wrap");
            $(".js-tab-cont-0").find(".message").text("");
            return;
        }

        if ($(this).text() == "다시인증") {
            $(this).text("카드인증");
            $(".cardNumber").val("");
            $(".cardNumber").prop("readonly", false);
            $("#birthDay, #validYYMM").val("");
            $("#birthDay, #validYYMM").prop("readonly", false);
            $("#cardValidation").val("");
            hideError("validYYMMErrorMessage");
            return false;
        }


        $(".cardNumber").keyup();
        $("#birthDay").keyup();
        $("#validYYMM").keyup();

        //region valid for cardNumber
        var cardNum = $('.cardNumber').val()

        if (cardNum.length < 15) {
            return alert("카드번호를 정확히 입력해주세요.", () => $('._x_cardNumber').focus());

        }

        //endregion

        //region valid for birthDay
        var birthDay = $.trim($('#birthDay').val());

        if (birthDay.length < 6) {
            return alert("생년월일을 정확히 입력해주세요.", () => $('#birthDay').focus());

        }

        //endregion

        //region valid for validDate
        const validDate = $.trim($("#validYYMM").val())
        if (validDate.length < 5 || validDate.indexOf("/") != 2) {
            return alert("카드 유효기간을 정확히 입력해주세요.", () => $("#validYYMM").focus());
        } else {
            var yy = validDate.split("/")[1].replace(/[^0-9]/g, '');
            var mm = validDate.split("/")[0].replace(/[^0-9]/g, '');

            if ((yy.length != 2 || mm.length != 2) || (mm > 12 || mm <= 0)) {
                return alert("카드 유효기간을 정확히 입력해주세요.", () => $("#validYYMM").focus());
            }

        }
        
		console.log("valid1")
        $("#cardValidation").val("Y");
        alert("인증되었습니다.");
        $("#validCardBtn").closest("table").find("input").prop("readonly", true);
        $("#validCardBtn").text("다시인증");
        console.log("valid2")
//                $("#cardValidation").val("N");
//                alert("카드 인증에 실패했습니다. 정확히 입력하셨는지 확인해 주세요.");
    });

    // 계좌 자동이체
    // /*setNumberEvent*/("#accountNumber");
    var hasRequest = false;
    var isNew = false;
    // ARS인증 요청 버튼 클릭
    $("body").on("click", "#callCms", function () {

        isNew = $(this).data('isnew');
        var officeCode = $("#officeCode").val();
        if (officeCode == undefined || officeCode == "" || officeCode == null) {
            alert("배달정보를 입력해주세요.");
            return;
        }
        var phoneFirst = $(this).data('phone1')
        var phoneLast = $(this).data('phone2')
        // if ( !$("#rdo-pay-2").prop("checked") ) {
        // 	$(".js-tab-cont-1").find("input[type=text], input[type=password], input[type=hidden], select").val("");
        // 	$(".js-tab-cont-1").find("*").removeClass("error-wrap");
        // 	$(".js-tab-cont-1").find(".message").text("");
        // 	return;
        // }

        var param = {
            bankCode: $("#bankCode").val()
            , accountNumber: $("#accountNumber").val()
            , parterCode: $("#officeCode").val()
        }

        var fdCustId = $.trim($(this).data("fd-cust-id"));
        if (fdCustId != undefined && fdCustId != "") {
            param.fdCustId = fdCustId;
        }

        function callArsAlertForNew(fdCustId, agreementAgentId) {

            // $("#alertModal").modal('show');
            $('#alertModal').data('bs.modal',null); // this clears the BS modal data
            $('#alertModal').modal({backdrop:'static', keyboard:false});
            $('#alertModal .modal-footer').addClass('disabled')
            $('#alertModal .modal-footer').prop('disabled',true);

            timer= setTimeout(()=>{
                $('#alertModal .modal-footer').removeClass('disabled')
                $('#alertModal .modal-footer').prop('disabled',false);
            },5000)


                alert('<p>ARS 인증을 위한 전화연결 중입니다.<br>' +
                    '인증 완료 후 아래  <span class="fc-pos-green">[완료]</span> 버튼을 눌러주세요</p>' +
                    '<ul class="dot-list ars-auth-list">' +
                    '<li>전화가 오지 않는 경우, 휴대폰 번호와 착신 정지여부를 확인해주세요.</li>' +
                    '<li>착신 전환된 번호로는 ARS 인증이 불가합니다.</li>' +
                    '</ul>');
//                    '</ul>', () => {
                    
	                $("#beforeArs").hide();
	                $("#afterArs").show();
	                $("#accountNumber").prop("readonly", true);
	                $("input[name=bankNo]").val($("#bankCode").val());
	                $("#bankCode").prop('disabled', true)
	                $("#fdCustId").val(fdCustId);

//                });

        }

        function callArsAlert(fdCustId, agreementAgentId) {
        	
            $("#alertModal").modal('show');
            // $('#alertModal.modal').show();
            $('#alertModal').modal('hide');
            $('#alertModal').data('bs.modal',null); // this clears the BS modal data
//...
            $('#alertModal .modal-footer').addClass('disabled')
            $('#alertModal .modal-footer').prop('disabled',true);

            setTimeout(()=>{
                $('#alertModal .modal-footer').removeClass('disabled')
                $('#alertModal .modal-footer').prop('disabled',false);
            },5000)
            $('#alertModal').modal({backdrop:'static', keyboard:false});

            alert('<p>ARS 인증을 위한 전화연결 중입니다.<br>' +
                '인증 완료 후 아래  <span class="fc-pos-green">[완료]</span> 버튼을 눌러주세요</p>' +
                '<ul class="dot-list ars-auth-list">' +
                '<li>전화가 오지 않는 경우, 휴대폰 번호와 착신 정지여부를 확인해주세요.</li>' +
                '<li>착신 전환된 번호로는 ARS 인증이 불가합니다.</li>' +
                '</ul>');
//                '</ul>', () => 
                
        	    $("#beforeArs").hide();
                $("#afterArs").show();
                $("#accountNumber").prop("readonly", true);
                $("input[name=bankNo]").val($("#bankCode").val());
                $("#bankCode").prop('disabled', true)
                $("#fdCustId").val(fdCustId);
           
//            );
        }

        var url = "/fms/ars/call"
        if (isNew) url = "/fms/ars/call/pay"

        alert(` <div class="p-cont">
                    <p>회원정보에 등록된 아래 휴대폰 번호로 ARS 인증을 위한 전화를 드립니다. <br /><br /><span class="fc-pos-green">인증요청 해주세요.</span></p>

                    <dl class="ars-auth-num">
                        <dt>휴대폰번호</dt>
                        <dd>${phoneFirst}-****-${phoneLast}</dd>
                    </dl>

                    <ul class="dot-list ars-auth-list">
                        <li>휴대폰 번호는 <a href="/mypage/personal/info" class="td-underline fc-gray">HOME > MY녹즙 > 개인정보
                            > 개인정보 관리</a> 에서 변경할 수 있습니다.</li>
                    </ul>

                </div>`,
            () => {

                $('#alertModal').modal('hide');
                $('#alertModal .modal-footer').off('click');
//                get({
//                    url
//                    , param: param
//                }, function (response) {
//                    if (response.RESULT_MSG.error) {
//                        alert("ARS 요청이 실패했습니다. 잠시후 다시 요청해 주세요.");
//                    } else if (response.RESULT_MSG.result.status == "대기") {
//                        var fdCustId = response.RESULT_MSG.fdCustId;
//                        var agreementAgentId = response.RESULT_MSG.agreementAgentId;
//                        $('#hasRequest').change(function () {
//                                if ($(this).val()) {
//                                    // setTimeout(() => {
//                                    //         console.log('요기')
//                                            isNew ? callArsAlertForNew(fdCustId, agreementAgentId, '인증요청') : callArsAlert(fdCustId, agreementAgentId, '인증요청')
//                                        // }
//                                        // , 100)
//
//                                }
//                            }
//                        )
//
//                        $('#hasRequest').val('true')
//                        $('#hasRequest').trigger('change')
//
//
//                    } else if (response.RESULT_MSG.result.status != "대기") {
//                        alert(response.RESULT_MSG.result.result.message);
//                    }
//                        $('#hasRequest').off('change')
//
//                });

	        isNew ? callArsAlertForNew(fdCustId, '', '') : callArsAlert(fdCustId, '', '')
			// alert("ARS 인증이 완료되었습니다.");
        })


    });
    // alert(1)

});
