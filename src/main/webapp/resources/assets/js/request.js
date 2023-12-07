/* 장민창  - ex2i */
var MD5 = function (d) {
    var r = M(V(Y(X(d), 8 * d.length)));
    return r.toLowerCase()
};

function M(d) {
    for (var _, m = "0123456789ABCDEF", f = "", r = 0; r < d.length; r++)
        _ = d.charCodeAt(r), f += m.charAt(_ >>> 4 & 15) + m.charAt(15 & _);
    return f
}

function X(d) {
    for (var _ = Array(d.length >> 2), m = 0; m < _.length; m++)
        _[m] = 0;
    for (m = 0; m < 8 * d.length; m += 8)
        _[m >> 5] |= (255 & d.charCodeAt(m / 8)) << m % 32;
    return _
}

function V(d) {
    for (var _ = "", m = 0; m < 32 * d.length; m += 8)
        _ += String.fromCharCode(d[m >> 5] >>> m % 32 & 255);
    return _
}

function Y(d, _) {
    d[_ >> 5] |= 128 << _ % 32, d[14 + (_ + 64 >>> 9 << 4)] = _;
    for (var m = 1732584193, f = -271733879, r = -1732584194, i = 271733878, n = 0; n < d.length; n += 16) {
        var h = m, t = f, g = r, e = i;
        f = md5_ii(f = md5_ii(f = md5_ii(f = md5_ii(f = md5_hh(
                    f = md5_hh(f = md5_hh(f = md5_hh(f = md5_gg(f = md5_gg(
                                    f = md5_gg(f = md5_gg(f = md5_ff(f = md5_ff(f = md5_ff(
                                                f = md5_ff(f, r = md5_ff(r, i = md5_ff(i,
                                                            m = md5_ff(m, f, r, i, d[n + 0], 7,
                                                                -680876936), f, r, d[n + 1],
                                                            12, -389564586), m, f, d[n + 2], 17,
                                                        606105819), i, m, d[n + 3], 22,
                                                    -1044525330), r = md5_ff(r, i = md5_ff(
                                                        i, m = md5_ff(m, f, r, i, d[n + 4], 7,
                                                            -176418897), f, r, d[n + 5],
                                                        12, 1200080426), m, f, d[n + 6], 17,
                                                    -1473231341), i, m, d[n + 7], 22,
                                                -45705983), r = md5_ff(r,
                                                i = md5_ff(i, m = md5_ff(m, f, r, i, d[n + 8],
                                                        7, 1770035416), f, r, d[n + 9], 12,
                                                    -1958414417), m, f, d[n + 10], 17,
                                                -42063), i, m, d[n + 11], 22, -1990404162),
                                            r = md5_ff(r, i = md5_ff(i, m = md5_ff(m, f, r,
                                                        i, d[n + 12], 7, 1804603682), f, r,
                                                    d[n + 13], 12, -40341101), m, f,
                                                d[n + 14], 17, -1502002290), i, m,
                                            d[n + 15], 22, 1236535329), r = md5_gg(r,
                                            i = md5_gg(i, m = md5_gg(m, f, r, i, d[n + 1],
                                                    5, -165796510), f, r, d[n + 6], 9,
                                                -1069501632), m, f, d[n + 11], 14,
                                            643717713), i, m, d[n + 0], 20, -373897302),
                                        r = md5_gg(r, i = md5_gg(i, m = md5_gg(m, f, r,
                                                    i, d[n + 5], 5, -701558691), f, r,
                                                d[n + 10], 9, 38016083), m, f,
                                            d[n + 15], 14, -660478335), i, m,
                                        d[n + 4], 20, -405537848), r = md5_gg(r,
                                        i = md5_gg(i, m = md5_gg(m, f, r, i, d[n + 9],
                                                5, 568446438), f, r, d[n + 14], 9,
                                            -1019803690), m, f, d[n + 3], 14,
                                        -187363961), i, m, d[n + 8], 20, 1163531501),
                                r = md5_gg(r, i = md5_gg(i, m = md5_gg(m, f, r, i,
                                        d[n + 13], 5, -1444681467), f, r, d[n + 2], 9,
                                    -51403784), m, f, d[n + 7], 14, 1735328473), i,
                                m, d[n + 12], 20, -1926607734), r = md5_hh(r,
                                i = md5_hh(i, m = md5_hh(m, f, r, i, d[n + 5], 4,
                                    -378558), f, r, d[n + 8], 11, -2022574463), m,
                                f, d[n + 11], 16, 1839030562), i, m, d[n + 14], 23,
                            -35309556), r = md5_hh(r, i = md5_hh(i, m = md5_hh(m,
                                f, r, i, d[n + 1], 4, -1530992060), f, r, d[n + 4], 11,
                            1272893353), m, f, d[n + 7], 16, -155497632), i, m,
                        d[n + 10], 23, -1094730640), r = md5_hh(r, i = md5_hh(
                            i, m = md5_hh(m, f, r, i, d[n + 13], 4, 681279174), f,
                            r, d[n + 0], 11, -358537222), m, f, d[n + 3], 16,
                        -722521979), i, m, d[n + 6], 23, 76029189), r = md5_hh(
                        r, i = md5_hh(i, m = md5_hh(m, f, r, i, d[n + 9], 4,
                            -640364487), f, r, d[n + 12], 11, -421815835),
                        m, f, d[n + 15], 16, 530742520), i, m, d[n + 2], 23,
                    -995338651), r = md5_ii(r, i = md5_ii(i, m = md5_ii(m, f, r, i,
                        d[n + 0], 6, -198630844), f, r, d[n + 7], 10, 1126891415), m,
                    f, d[n + 14], 15, -1416354905), i, m, d[n + 5], 21, -57434055),
                r = md5_ii(r, i = md5_ii(i, m = md5_ii(m, f, r, i, d[n + 12],
                        6, 1700485571), f, r, d[n + 3], 10, -1894986606), m, f,
                    d[n + 10], 15, -1051523), i, m, d[n + 1], 21,
                -2054922799), r = md5_ii(r, i = md5_ii(i, m = md5_ii(m, f, r,
                    i, d[n + 8], 6, 1873313359), f, r, d[n + 15], 10, -30611744),
                m, f, d[n + 6], 15, -1560198380), i, m, d[n + 13], 21,
            1309151649), r = md5_ii(r, i = md5_ii(i, m = md5_ii(m, f, r, i,
                d[n + 4], 6, -145523070), f, r, d[n + 11], 10, -1120210379), m,
            f, d[n + 2], 15, 718787259), i, m, d[n + 9], 21, -343485551),
            m = safe_add(m, h), f = safe_add(f, t), r = safe_add(r, g),
            i = safe_add(i, e)
    }
    return Array(m, f, r, i)
}

function md5_cmn(d, _, m, f, r, i) {
    return safe_add(bit_rol(safe_add(safe_add(_, d), safe_add(f, i)), r), m)
}

function md5_ff(d, _, m, f, r, i, n) {
    return md5_cmn(_ & m | ~_ & f, d, _, r, i, n)
}

function md5_gg(d, _, m, f, r, i, n) {
    return md5_cmn(_ & f | m & ~f, d, _, r, i, n)
}

function md5_hh(d, _, m, f, r, i, n) {
    return md5_cmn(_ ^ m ^ f, d, _, r, i, n)
}

function md5_ii(d, _, m, f, r, i, n) {
    return md5_cmn(m ^ (_ | ~f), d, _, r, i, n)
}

function safe_add(d, _) {
    var m = (65535 & d) + (65535 & _);
    return (d >> 16) + (_ >> 16) + (m >> 16) << 16 | 65535 & m
}

function bit_rol(d, _) {
    return d << _ | d >>> 32 - _
}

function setEnterEvent(domIdOrClass, callback) {
    $(domIdOrClass).keyup(function (e) {
        if (e.keyCode == 13) {
            callback();
        }
    });
}

// function setNumberEvent(domIdOrClass) {
//     $(domIdOrClass).keydown(function (e) {
//         // console.log(e)
//         if ("1234567890".indexOf(e.key) == -1 && e.key != "Backspace" && e.key != "Delete" && e.key.indexOf("Arrow") == -1) {
//             return false;
//         }
//     });
// }

// $(document).ajaxStart(function () {
//     $("body").append($("<div class='spinner_panel'><div>"));
//     $("body").append($("<div class='loading-wrap'><div class='loader'></div></div>"));
// });

// $(document).ajaxComplete(function () {
//     $("div.spinner_panel, div.loading-wrap").remove();
// });

var movePage = function (pageNo) {
    if (document.getElementById('searchForm').pageNo != undefined) {
        document.getElementById('searchForm').pageNo.value = pageNo;
    }
    document.getElementById('searchForm').action = '';
    document.getElementById('searchForm').method = 'get';

    document.getElementById('searchForm').submit();
}

//팝업형 페이지 이동
var loadPage = function (pageNo) {
    document.getElementById('dialogSearchForm').pageNo.value = pageNo;
    $("#popupDialogContent910").load(document.getElementById('dialogSearchForm').action, $("#dialogSearchForm").serialize(), function () {
    });
}

// 팝업형 페이지 이동 스크립트 변경
var replaceLoadPage = function () {
    $("#dialogSearchForm").find(".paging").find("a").each(function () {
        var cls = $(this).attr("class");
        var isDisabled = cls != undefined && cls.indexOf("is-disabled") > -1;
        if (isDisabled) {
            $(this).attr("href", "javascript:void(-1);");
        } else {
            var href = $(this).attr("href").replace("movePage", "loadPage");
            $(this).attr("href", href);
        }
    });
}

function getUUID() { // UUID v4 generator in JavaScript (RFC4122 compliant)
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {	 	               
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 3 | 8);
        return v.toString(16);
    });
}

var sessionId = getUUID();

var postForm = function (formId, url, callback, failCallback) {

    var duplicateValue = MD5(sessionId) + "UUID";

    $(formId).append($("<input type='hidden' name='__duplicate__' value='" + duplicateValue + "' />"));

    var form = $(formId)[0];
    var formData = new FormData(form);

    $(formId).ajaxForm({
        url: url,
        contentType:false,
        processData:false,
        beforeSend:()=> $('#loading').modal('show'),
        complete: ()=>$('#loading').modal('hide'),
        success: function (response) {

            if (typeof callback === 'function') {

                try {
                    response = JSON.parse(response);
                } catch (e) {
                    console.log(e)
                }

                if (response.RESULT_ST == "PASS") {
                    callback(response);
                } else if (response.RESULT_ST == "REDIRECT") {
                    callback(response);
                    location.href = response.REDIRECT_URL;
                } else if (response.RESULT_ST == "REFRESH") {
                    callback(response);
                    location.reload();
                } else if (response.RESULT_ST == "POPUP") {
                    callback(response);

                    $("#popupDialogContent").load(response.POPUP_URL, function () {
                        $(".ui-dialog-title").text(response.POPUP_NAME)
                        $('.popup').dialog('open');
                    });
                } else if (response.RESULT_ST == "FAIL") {
                    failProcess(response, failCallback);
                }
            }
        }
    });

    $(formId).submit();
};

const postMultipart = function (option,callback,failCallback){
    if ( option.duplicate == undefined ) {
        // false : 중복 방지해야 함.
        // true : 중복 허용 해야 함.
        option.duplicate = false;
    }
    console.log(option);
    console.log(failCallback);
    if ( !option.data ) {
        option.data = {};
    }

    var duplicateValue = option.duplicate ? getUUID() : sessionId + option.url + encodeURIComponent(option.data);

    option.data["__duplicate__"] = MD5(duplicateValue) + "UUID";

    $.ajax({

        url: option.url,
        data:option.data,
        enctype:'multipart/form-data',
        contentType: false,
        method:'POST',
        processData: false,
        beforeSend:()=> $('#loading').modal('show'),
        cache:false,
        success:
            function ( response ) {

                try {
                    response = JSON.parse(response);
                }
                catch(e) {}

                if ( response.RESULT_ST == "PASS" ) {
                    callback(response);
                }
                else if ( response.RESULT_ST == "REDIRECT" ) {
                    callback(response);
                    location.href = response.REDIRECT_URL;
                }
                else if ( response.RESULT_ST == "REFRESH" ) {
                    callback(response);
                    location.reload();
                }
                else if ( response.RESULT_ST == "DUPLICATE" ) {
                    callback = function() {};
                    callback();
                }
                else if ( response.RESULT_ST == "POPUP" ) {
                    callback(response);

                    $("#popupDialogContent").load(response.POPUP_URL, function() {
                        $(".ui-dialog-title").text(response.POPUP_NAME)
                        $('.popup').dialog('open');
                    });
                }
                else if ( response.RESULT_ST == "FAIL" ) {
                    failProcess(response, failCallback);
                }


            },
        fail: failCallback,
        complete: ()=>$('#loading').modal('hide')
    });
}



var postAjaxForm = function (formId, url, callback, failCallback) {
    var duplicateValue = MD5(sessionId) + "UUID";
    $(formId).append($("<input type='hidden' name='__duplicate__' value='" + duplicateValue + "' />"));

    var form = $(formId)[0];
    var formData = new FormData(form);

    $.ajax({
        url:"/inquiry/inquiry/write",
        type:'POST',
        data:formData,
        contentType:false,
        processData:false

    }).done(function(response){
        if (typeof callback === 'function') {
            try {
                response = JSON.parse(response);
            } catch (e) {
            }

            if (response.RESULT_ST == "PASS") {
                callback(response);
            } else if (response.RESULT_ST == "REDIRECT") {
                callback(response);
                location.href = response.REDIRECT_URL;
            } else if (response.RESULT_ST == "REFRESH") {
                callback(response);
                location.reload();
            } else if (response.RESULT_ST == "POPUP") {
                callback(response);

                $("#popupDialogContent").load(response.POPUP_URL, function () {
                    $(".ui-dialog-title").text(response.POPUP_NAME)
                    $('.popup').dialog('open');
                });
            } else if (response.RESULT_ST == "FAIL") {
                failProcess(response, failCallback);
            }
        }
    });
};

const newPost = function (option, callback, failCallback,cCallback) {

    if (option.duplicate == undefined) {
        // false : 중복 방지해야 함.
        // true : 중복 허용 해야 함.
        option.duplicate = false;
    }

    if (!option.data) {
        option.data = {};
    }

    var duplicateValue = option.duplicate ? getUUID() : sessionId + option.url + encodeURIComponent(JSON.stringify(option.data));

    option.data["__duplicate__"] = MD5(duplicateValue) + "UUID";

    $.ajax({

        url: option.url,
        param: $.param({'__duplicate__': MD5(duplicateValue) + "UUID"}),
        data: JSON.stringify(option.data),
        contentType: 'application/json',
        method: 'POST',
        datatype: 'json',
        beforeSend:()=> $('#loading').modal('show'),
        success:
            function (response) {

                $('#loading').modal('hide')

                try {
                    response = JSON.parse(response);
                } catch (e) {
                }

                if (response.RESULT_ST == "PASS") {
                    callback(response);
                } else if (response.RESULT_ST == "REDIRECT") {
                    callback(response);
                    location.href = response.REDIRECT_URL;
                } else if (response.RESULT_ST == "REFRESH") {
                    callback(response);
                    location.reload();
                } else if (response.RESULT_ST == "DUPLICATE") {
                    callback = function () {
                    };
                    callback();
                } else if (response.RESULT_ST == "POPUP") {
                    callback(response);

                    $("#popupDialogContent").load(response.POPUP_URL, function () {

                        $(".ui-dialog-title").text(response.POPUP_NAME)					

                        $('.popup').dialog('open');
                    });
                } else if (response.RESULT_ST == "FAIL") {
                    failProcess(response, failCallback);
                }


            },

        fail: failCallback,
        complete: cCallback
    });


}

const newPut = function (option, callback, failCallback) {
    if (option.duplicate == undefined) {
        // false : 중복 방지해야 함.
        // true : 중복 허용 해야 함.
        option.duplicate = false;
    }

    if (!option.data) {
        option.data = {};
    }

    var duplicateValue = option.duplicate ? getUUID() : sessionId + option.url + encodeURIComponent(option.data);

    option.data["__duplicate__"] = MD5(duplicateValue) + "UUID";

    $.ajax({

        url: option.url,
        data: JSON.stringify(option.data),
        contentType: 'application/json',
        method: 'PUT',
        datatype: 'json',
        success:
            function (response) {

                try {
                    response = JSON.parse(response);
                } catch (e) {
                }

                if (response.RESULT_ST == "PASS") {
                    callback(response);
                } else if (response.RESULT_ST == "REDIRECT") {
                    callback(response);
                    location.href = response.REDIRECT_URL;
                } else if (response.RESULT_ST == "REFRESH") {
                    callback(response);
                    location.reload();
                } else if (response.RESULT_ST == "DUPLICATE") {
                    callback = function () {
                    };
                    callback();
                } else if (response.RESULT_ST == "POPUP") {
                    callback(response);

                    $("#popupDialogContent").load(response.POPUP_URL, function () {
                        $(".ui-dialog-title").text(response.POPUP_NAME)
                        $('.popup').dialog('open');
                    });
                } else if (response.RESULT_ST == "FAIL") {
                    failProcess(response, failCallback);
                }


            },
        fail: failCallback,
    });


}

const newDelete = function (option, callback, failCallback) {
    if (option.duplicate == undefined) {
        // false : 중복 방지해야 함.
        // true : 중복 허용 해야 함.
        option.duplicate = false;
    }

    if (!option.data) {
        option.data = {};
    }

    var duplicateValue = option.duplicate ? getUUID() : sessionId + option.url + encodeURIComponent(option.data);

    option.data["__duplicate__"] = MD5(duplicateValue) + "UUID";

    $.ajax({

        url: option.url,
        data: JSON.stringify(option.data),
        contentType: 'application/json',
        method: 'DELETE',
        datatype: 'json',
        success:
            function (response) {

                try {
                    response = JSON.parse(response);
                } catch (e) {
                }

                if (response.RESULT_ST == "PASS") {
                    callback(response);
                } else if (response.RESULT_ST == "REDIRECT") {
                    callback(response);
                    location.href = response.REDIRECT_URL;
                } else if (response.RESULT_ST == "REFRESH") {
                    callback(response);
                    location.reload();
                } else if (response.RESULT_ST == "DUPLICATE") {
                    callback = function () {
                    };
                    callback();
                } else if (response.RESULT_ST == "POPUP") {
                    callback(response);

                    $("#popupDialogContent").load(response.POPUP_URL, function () {
                        $(".ui-dialog-title").text(response.POPUP_NAME)
                        $('.popup').dialog('open');
                    });
                } else if (response.RESULT_ST == "FAIL") {
                    failProcess(response, failCallback);
                }


            },
        fail: failCallback,
    });


}


var post = function (option, callback, failCallback) {
    $('.confirm').prop('disabled',true)
    $('#loading').modal('show')
    if (option.duplicate == undefined) {
        // false : 중복 방지해야 함.
        // true : 중복 허용 해야 함.
        option.duplicate = false;
    }

    if (!option.param) {
        option.param = {};
    }

    var duplicateValue = option.duplicate ? getUUID() : sessionId + option.url + encodeURIComponent(option.param);
    if (typeof option.param == "object") {
        option.param["__duplicate__"] = MD5(duplicateValue) + "UUID";
    } else {
        option.param += "&__duplicate__=" + MD5(duplicateValue) + "UUID";
    }

    ;
    $.post(option.url, option.param, function (response) {
        $('.confirm').prop('disabled',false);
        try {
            response = JSON.parse(response);
        } catch (e) {
            $('#loading').modal('hide')
        }
        $('#loading').modal('hide')
        if (response.RESULT_ST == "PASS") {
            callback(response);
        } else if (response.RESULT_ST == "REDIRECT") {
            callback(response);
            location.href = response.REDIRECT_URL;
        } else if (response.RESULT_ST == "REFRESH") {
            callback(response);
            location.reload();
        } else if (response.RESULT_ST == "DUPLICATE") {
            callback = function () {
            };
            callback();
        } else if (response.RESULT_ST == "POPUP") {
            callback(response);

            $("#popupDialogContent").load(response.POPUP_URL, function () {
                $(".ui-dialog-title").text(response.POPUP_NAME)
                $('.popup').dialog('open');
            });
        } else if (response.RESULT_ST == "FAIL") {

            failProcess(response, failCallback);
        }


    });

};

var get = function (option, callback, failCallback) {

    if (option.duplicate == undefined) {
        // false : 중복 방지해야 함.
        // true : 중복 허용 해야 함.
        option.duplicate = false;
    }

    if (!option.param) {
        option.param = {};
    }

    var duplicateValue = option.duplicate ? getUUID() : sessionId + option.url + encodeURIComponent(option.param);
    if (typeof option.param == "object") {
        option.param["__duplicate__"] = MD5(duplicateValue) + "UUID";
    } else {
        option.param += "&__duplicate__=" + MD5(duplicateValue) + "UUID";
    }

    $.get(option.url, option.param, function (response) {
        try {
            response = JSON.parse(response);
        } catch (e) {
        }

        if (response.RESULT_ST == "PASS") {
            callback(response);
        } else if (response.RESULT_ST == "REDIRECT") {
            callback(response);
            location.href = response.REDIRECT_URL;
        } else if (response.RESULT_ST == "REFRESH") {
            callback(response);
            location.reload();
        } else if (response.RESULT_ST == "DUPLICATE") {
            callback = function () {
            };
            callback();
        } else if (response.RESULT_ST == "POPUP") {
            callback(response);

            $("#popupDialogContent").load(response.POPUP_URL, function () {
                $(".ui-dialog-title").text(response.POPUP_NAME)
                $('.popup').dialog('open');
            });
        } else if (response.RESULT_ST == "FAIL") {
            failProcess(response, failCallback);
        }
        $('#loading').modal('hide')
    });

};


function failProcess(response, failCallback) {

    var message = response.RESULT_MSG.error;
    if (!message) {
        message = response.RESULT_MSG;
    }
    // 세션 만료
    if (response.RESULT_CODE == "9999") {
        location.href = "/login";
    }
    // 인증정보 틀림
    else if (response.RESULT_CODE == "9998") {
        if (failCallback) {
            failCallback(response);
        } else {
//			alert(response.RESULT_MSG);
            alert(message);
        }
    }
    // 접근 권한 없음
    else if (response.RESULT_CODE == "9997") {
        if (failCallback) {
            failCallback(response);
        } else {
//			alert(response.RESULT_MSG);
            alert(message);
        }
    }
    // 필수 입력 파라미터 미전달
    else if (response.RESULT_CODE == "1000") {
        if (failCallback) {
            failCallback(response);
        } else {
            //alert(response.RESULT_MSG);
            alert(message);
        }
        $("#" + response.FIELD_NAME).focus();
    }
    // 패스워드 만료됨
    else if (response.RESULT_CODE == "2000") {
        if (failCallback) {
            failCallback(response);
        } else {
            alert(message);
//			alert(response.RESULT_MSG);
        }
    }
    // 계정 잠김
    else if (response.RESULT_CODE == "2001") {
//		if ( failCallback ) {
//			failCallback(response);
//		}
//		else {
        //alert(response.RESULT_MSG);
        confirmDesign("", message, function () {
            location.href = "/member/find/password";
        });
//		}
    }
    // 시스템 에러
    else if (response.RESULT_CODE == "-9999") {
//		alert(response.RESULT_MSG);
        alert(message);
    }
    // 그 외
    else {
        if (failCallback) {
            failCallback(response);
        } else {
//			alert(response.RESULT_MSG);
            alert(message);
        }
    }
}

// spinner 없는 ajax 호출
var Ajax = {
    request: function (ops) {
        if (typeof ops == 'string') ops = {url: ops};
        ops.url = ops.url || '';
        ops.method = ops.method || 'get'
        ops.data = ops.data || {};

        if (ops.duplicate == undefined) {
            // false : 중복 방지해야 함.
            // true : 중복 허용 해야 함.
            ops.duplicate = false;
        }

        var duplicateValue = ops.duplicate ? getUUID() : sessionId + ops.url + encodeURIComponent(ops.data) + "UUID";
        if (typeof ops.data == "object") {
            ops.data["__duplicate__"] = duplicateValue;
        } else {
            ops.data += "&__duplicate__=" + duplicateValue
        }

        var getParams = function (data, url) {
            var arr = [], str;
            for (var name in data) {
                arr.push(name + '=' + encodeURIComponent(data[name]));
            }
            str = arr.join('&');
            if (str != '') {
                return url ? (url.indexOf('?') < 0 ? '?' + str : '&' + str) : str;
            }
            return '';
        }
        var api = {
            host: {},
            process: function (ops) {
                var self = this;
                this.xhr = null;
                if (window.ActiveXObject) {
                    this.xhr = new ActiveXObject('Microsoft.XMLHTTP');
                } else if (window.XMLHttpRequest) {
                    this.xhr = new XMLHttpRequest();
                }
                if (this.xhr) {
                    this.xhr.onreadystatechange = function () {
                        if (self.xhr.readyState == 4 && self.xhr.status == 200) {
                            var result = self.xhr.responseText;
                            if (ops.json === true && typeof JSON != 'undefined') {
                                result = JSON.parse(result);
                            }
                            self.doneCallback && self.doneCallback.apply(self.host, [result, self.xhr]);
                        } else if (self.xhr.readyState == 4) {
                            self.failCallback && self.failCallback.apply(self.host, [self.xhr]);
                        }
                        self.alwaysCallback && self.alwaysCallback.apply(self.host, [self.xhr]);
                    }
                }
                if (ops.method == 'get') {
                    this.xhr.open("GET", ops.url + getParams(ops.data, ops.url), true);
                } else {
                    this.xhr.open(ops.method, ops.url, true);
                    this.setHeaders({
                        'X-Requested-With': 'XMLHttpRequest',
                        'Content-type': 'application/x-www-form-urlencoded'
                    });
                }
                if (ops.headers && typeof ops.headers == 'object') {
                    this.setHeaders(ops.headers);
                }
                setTimeout(function () {
                    ops.method == 'get' ? self.xhr.send() : self.xhr.send(getParams(ops.data));
                }, 20);
                return this;
            },
            done: function (callback) {
                this.doneCallback = callback;
                return this;
            },
            fail: function (callback) {
                this.failCallback = callback;
                return this;
            },
            always: function (callback) {
                this.alwaysCallback = callback;
                return this;
            },
            setHeaders: function (headers) {
                for (var name in headers) {
                    this.xhr && this.xhr.setRequestHeader(name, headers[name]);
                }
            }
        }
        return api.process(ops);
    }
};


