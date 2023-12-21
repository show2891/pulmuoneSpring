<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  $(document).ready(function () {
    //선물 메세지
    $('#orderMemo').keyup(function (e) {
      var max = $(this).attr('maxlength');
      var txtLen = $(this).val().length;
      var txtCnt = $(this).closest("div").find('#memoLength > span');
      txtCnt.text(txtLen);
      if (txtLen > max) {
        txtCnt.text(max);
      }
    });

    // region numberOnly
    $(".numberOnly").on("keyup", function () {
      $(this).val($(this).val().replace(/[^0-9]/g, ""));
    });
    //
    //    region sendMethod
    $("[name='sendMethod']").click(function () {
      var v = $("[name='sendMethod']:checked").val();
      if (v == "SMS") {
        $("#phone-area").show();
        $("#phone-area input").attr("data-required", "true");
        // $('.kakao-guide').hide();
      } else {
        $("#phone-area").hide();
        $("#phone-area input").attr("data-required", "false");
        // $('.kakao-guide').show();
      }
    });

    $("[name='sendMethod']:checked").click();

    $('.theme-btn').click(function () {
      $(this).parent().addClass('active').siblings().removeClass('active')
      $('.message-area').hide()
      const idx = $(this).parent().index() + 1
      console.log(this)
      $(`.message-area:nth-of-type(${idx})`).show()
      $(this).next().click()
    })
    //	endregion


  });

  $(document).on("submit", "#orderForm", function (e) {
    e.preventDefault();
    var errorFields = [];
    $("[data-required='true']").each(function (i, el) {
      var $el = $(el);
      if (!$el.val()) {
        errorFields.push({el, msg: $el.attr("data-validate")});
      }
    })

    if (errorFields.length > 0) {
      // Error Fields..
      var $el = $(errorFields[0].el);
      alert(errorFields[0].msg);
      if ($el.parents(".folding-pane").length) {
        $el.parents(".folding-pane").removeClass("fold");
      }
      var t = $el.offset().top;
      // $(window).scrollTop(t - 80);
      return false;
    }

    let phoneFlag = $(this).find("#smsPhoneNum").attr('data-required');
    if (phoneFlag == 'true') {
      const cl = $(this).find("#smsPhoneNum").val().replace(/[^0-9]+/g, "");
      const tempNum = Number(cl);
      if (isNaN(tempNum) || cl.length < 11 || cl.length > 11) {
        alert("올바른 번호를 입력해주세요");
        return false;
      }
    }

    if (!$("#agree:checked").length) {
      alert('결제 이용약관에 동의 해주세요');
      return false;
    }

    var params = $(this).serializeObject();
    newPost({
      url: '/order/taste/step1',
      data: params
    }, function (r) {
      callInipay(r.RESULT_MSG, params, {
        title: "이달의녹즙",
        amount: '5000',
      });
    }, function (r) {
      alert(r.RESULT_MSG);
    })
    return false;
  });
</script>
<div class="contents-area">
        <div class="container">
            <div class="location">
                <a href="/">홈</a>
                <a href="/taste/taste">시음선물</a>
                <a href="#">주문서 작성</a>
            </div>
            <div class="cont-checkout-area">
                <div class="title-tab-area">
                    <h2 class="cont-title" style="font-weight: 400">주문서 작성</h2>
                </div>
                <div class="prd-cart-area">
                    <div class="prd-cart-list-area gift-cart-type">
                        <div>
                            <div class="checkout-gift-item">
                                <h3 class="checkout-input-title" style="font-weight: 500">선물수단</h3>
                                
                                    <div class="form-input">
                                        <dl>
                                            <dt>
                                                <label>선물수단</label>
                                            </dt>
                                            <dd>
                                                <div class="radio-area radio-type2" style="padding-left: 47px">
                                                    <div class="radio">
                                                        <input type="radio" value="KAKAO" disabled="" id="radio1" name="sendMethod">
                                                        <label for="radio1">카카오톡</label>
                                                    </div>
                                                    <div class="radio">
                                                        <input type="radio" id="radio2" value="SMS" checked="" name="sendMethod">
                                                        <label for="radio2">SMS</label>
                                                    </div>
                                                </div>
                                            </dd>
                                        </dl>
                                        <span class="kakao-guide input-text">* 카카오톡 선물하기는 모바일에서만 가능합니다.</span>
                                    </div>
                                    <div class="form-input">
                                        <dl>
                                            <dt><label for="sender">보내는분</label></dt>
                                            <dd>
                                                <input type="text" id="sender" name="sender" value="~~~" data-required="true" placeholder="이름을 입력하세요" data-validate="보내는분의 이름을 입력하세요" maxlength="10" style="margin-left: 26px">
                                            </dd>
                                        </dl>
                                    </div>
                                    <div class="form-input">
                                        <dl>
                                            <dt><label for="receiver">받는분</label></dt>
                                            <dd>
                                                <input type="text" id="receiver" name="receiver" data-required="true" placeholder="이름을 입력하세요" style="margin-left: 26px" maxlength="10" data-validate="받는분의 이름을 입력하세요">
                                            </dd>
                                        </dl>
                                    </div>
                                    <div style="" class="form-input for-sms" id="phone-area">
                                        <dl>
                                            <dt><label for="smsPhoneNum">휴대폰번호</label></dt>
                                            <dd>
                                                <input style="margin-left: 26px" type="hidden" data-call-text="" id="smsPhoneNum" name="smsPhoneNum" data-required="true" placeholder="휴대폰 번호를 입력하세요 (-빼고 입력)" maxlength="13" data-validate="휴대폰번호를 입력하세요">
                                            <input style="margin-left: 26px" type="tel" name="_x_smsPhoneNum" data-required="true" placeholder="휴대폰 번호를 입력하세요 (-빼고 입력)" maxlength="13" data-validate="휴대폰번호를 입력하세요"></dd>
                                        </dl>
                                    </div>
                                
                            </div>
                            <div class="checkout-gift-item">
                                <h3 class="checkout-input-title" style="font-weight: 500">시음상품</h3>
                                <div class="checkout-gift-select">
                                    <div class="gift-daily-area">
                                        <div class="prd-list2">
                                            <ul>
                                            
                                                <li>
                                                    <em>월</em>
                                                    <span class="thumb">
                                                        <img src="/file/download/product/b7323a61-8792-488b-9a32-571fe276bcea.png" alt="">
                                                    </span>
                                                    <b class="prd-title">비타맥스 엑스투</b>
                                                </li>
                                            
                                                <li>
                                                    <em>화</em>
                                                    <span class="thumb">
                                                        <img src="/file/download/product/b7323a61-8792-488b-9a32-571fe276bcea.png" alt="">
                                                    </span>
                                                    <b class="prd-title">비타맥스 엑스투</b>
                                                </li>
                                            
                                                <li>
                                                    <em>수</em>
                                                    <span class="thumb">
                                                        <img src="/file/download/product/b7323a61-8792-488b-9a32-571fe276bcea.png" alt="">
                                                    </span>
                                                    <b class="prd-title">비타맥스 엑스투</b>
                                                </li>
                                            
                                                <li>
                                                    <em>목</em>
                                                    <span class="thumb">
                                                        <img src="/file/download/product/b7323a61-8792-488b-9a32-571fe276bcea.png" alt="">
                                                    </span>
                                                    <b class="prd-title">비타맥스 엑스투</b>
                                                </li>
                                            
                                                <li>
                                                    <em>금</em>
                                                    <span class="thumb">
                                                        <img src="/file/download/product/b7323a61-8792-488b-9a32-571fe276bcea.png" alt="">
                                                    </span>
                                                    <b class="prd-title">비타맥스 엑스투</b>
                                                </li>
                                            
                                            </ul>
                                        </div>
                                        <div class="gift-daily-text">
                                            <div class="gift-daily-title">
                                                <b>이달의녹즙</b>
                                                <span>유기농 과채와 멀티비타민, 미네랄로 하루 영양 채우기!</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="gift-card-area">
                                        <div class="gift-card-select">
                                            
                                            <div class="message-area heal">
                                                <img src="/resources/assets/images/gift/message_heal_details.png">
                                                <h4>시음 선물을 보냈습니다.</h4>
                                            </div>
                                            
                                            <div class="message-area thanks" style="display: none;">
                                                <img src="/resources/assets/images/gift/message_thanks_details.png">
                                                <h4>시음 선물을 보냈습니다.</h4>
                                            </div>
                                            
                                            <div class="message-area love" style="display: none;">
                                                <img src="/resources/assets/images/gift/message_love_details.png">
                                                <h4>시음 선물을 보냈습니다.</h4>
                                            </div>
                                            
                                            <div class="message-area thumbsup" style="display: none;">
                                                <img src="/resources/assets/images/gift/message_thumbsup_details.png">
                                                <h4>시음 선물을 보냈습니다.</h4>
                                            </div>
                                            
                                            <div class="message-area cong" style="display: none;">
                                                <img src="/resources/assets/images/gift/message_cong_details.png">
                                                <h4>시음 선물을 보냈습니다.</h4>
                                            </div>

                                            <ul>
                                                <li class="active">
                                                    <button class="theme-btn" type="button">
                                                        <i>
                                                            <img class="on" src="/resources/assets/images/gift/gift-card-thumb-item01_on.png" alt="">
                                                            <img class="off" src="/resources/assets/images/gift/gift-card-thumb-item01.png" alt="">
                                                        </i>
                                                        <span>건강</span>
                                                    </button>
                                                    <input style="display: none" id="t-health" checked="" type="radio" name="msgTheme" value="HEALTH">
                                                </li>
                                                <li>
                                                    <button class="theme-btn" type="button">
                                                        <i>
                                                            <img class="on" src="/resources/assets/images/gift/gift-card-thumb-item02_on.png" alt="">
                                                            <img class="off" src="/resources/assets/images/gift/gift-card-thumb-item02.png" alt="">
                                                        </i>
                                                        <span>감사</span>
                                                    </button>
                                                    <input style="display: none" id="t-thanks" type="radio" name="msgTheme" value="THANKS">
                                                </li>
                                                <li>
                                                    <button class="theme-btn" type="button">
                                                        <i>
                                                            <img class="on" src="/resources/assets/images/gift/gift-card-thumb-item03_on.png" alt="">
                                                            <img class="off" src="/resources/assets/images/gift/gift-card-thumb-item03.png" alt="">
                                                        </i>
                                                        <span>사랑</span>
                                                    </button>
                                                    <input style="display: none" type="radio" id="t-love" name="msgTheme" value="LOVE">
                                                </li>
                                                <li>
                                                    <button class="theme-btn" type="button">
                                                        <i>
                                                            <img class="on" src="/resources/assets/images/gift/gift-card-thumb-item04_on.png" alt="">
                                                            <img class="off" src="/resources/assets/images/gift/gift-card-thumb-item04.png" alt="">
                                                        </i>
                                                        <span>응원</span>
                                                    </button>
                                                    <input style="display: none" type="radio" id="t-cheer" name="msgTheme" value="CHEER">
                                                </li>
                                                <li>
                                                    <button class="theme-btn" type="button">
                                                        <i>
                                                            <img class="on" src="/resources/assets/images/gift/gift-card-thumb-item05_on.png" alt="">
                                                            <img class="off" src="/resources/assets/images/gift/gift-card-thumb-item05.png" alt="">
                                                        </i>
                                                        <span>축하</span>
                                                    </button>
                                                    <input style="display: none" id="t-congrats" type="radio" name="msgTheme" value="CONGRATS">
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="gift-card-text">
                                            <span>선물메시지</span>
                                            <textarea id="orderMemo" maxlength="100" name="msg" placeholder="선물메시지를 입력하세요." data-required="true" data-validate="선물과 함께 보낼 메시지를 입력하세요."></textarea>
                                            <em id="memoLength"><span>0</span>/100</em>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="prd-checkout-area creat-checkout2">
                        <div class="taste-gift-wrapper">
                            <section class="taste-gift-guide-wrapper" style="padding-bottom: 50px">
                                <div class="taste-gift-guide-habit-title-text">
                                    <p><strong>!</strong>&nbsp;주문 시 유의사항</p>
                                </div>
                                <ul class="list dotted-list" style=" list-style-type: disc;">
                                    <li>
                                        시음선물하기는 횟수 제한 없이 가능합니다. 단, 시음선물 수신은 인 당 연 2회(상/하반기 각 1회) 가능합니다.
                                    </li>
                                    <li style="color: red; font-weight: bold;">
                                        시음선물은 중복 주소지로의 이용이 불가합니다. 수신자 정보가 상이하더라도 중복 주소지로의 이용은 불가하며,
                                        시음선물 주문이 사전 안내 없이 취소 될 수 있습니다.
                                    </li>
                                    <li style="color: red;font-weight: bold">
                                        배송가맹점의 사정(주문량 급증, 배송사원 부재 등)에 따라 첫 배송일, 배송횟수 등이 변경될 수 있으며, 배송이 어려운 경우 주문이 취소될 수 있습니다.
                                    </li>
                                    <li>
                                        시음선물 재발송은 MY녹즙 &gt; 시음선물내역에서 가능하며, LMS는 1회 가능합니다. (카카오톡의 경우 모바일에서만 가능합니다.)
                                    </li>
                                    <li>
                                        시음선물 수락 이후에는 전체/부분 취소 및 환불이 불가합니다.
                                    </li>
                                    <li>
                                        시음 선물 체험 후 매일 배송 상품을 신규 주문 시 첫 배송일 이후 선물한 분과 받으신 분께 5,000원 쿠폰이 첫 배송일에 발행됩니다.
                                        (단, 시음 첫 배송일로부터 4주 이내에 주문 시 해당되며, 선물 받으신 분은 회원 가입이 되어 있어야 정상 발급됩니다.)
                                    </li>
                                    <li>
                                        본인에게 시음선물 후 정기주문 시에는 5,000원 쿠폰이 1매 발행됩니다.
                                    </li>
                                    <li>
                                        해당 선물하기 이벤트는 당사의 사정에 따라 사전 고지없이 변경 또는 중단될 수 있습니다.
                                    </li>
                                </ul>
                            </section>
                        </div>
                        <dl>
                            <dt>
                                <span>상품 금액</span>
                                <b>
                                    <div class="now-price">
                                        17,500
                                        <span>원</span>
                                    </div>
                                </b>
                            </dt>
                            <dd>
                                <span>결제수단</span>
                                <b><span class="paymethod-text" style="display: inline-block; margin-left: 3px; font-size: 14px; font-weight: 500; letter-spacing: -1.05px; color: #666;">카드결제</span></b>
                            </dd>
                            <dd class="checkout-sum">
                                <span>최종결제금액</span>
                                <b>
                                    <div class="now-price">
                                        5,000
                                        <span>원</span>
                                    </div>
                                </b>
                            </dd>
                        </dl>
                        
                            

                        <div class="checkbox chk-type4">
                            <input type="checkbox" id="agree" value="true">
                            <label for="agree">구매조건 확인 및 결제진행 동의</label>
                        </div>
                        <button id="orderBtn" class="btn-default">주문신청</button>
                    </div>
                </div>
            </div>
        </div>
    </div>