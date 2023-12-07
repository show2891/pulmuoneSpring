/*
 * name : front.ui.js
 * desc : PC
 * writer : glim
 * date : 2019/10/11
 * last : 2019/10/11
 */

$( function() {


});

$(document).ready(function() {
    $( window ).resize(function() { resizeCommon(); });
    resizeCommon();

    //tab 초기화
    if ($('.js-m-tab-wrap').length > 0) {
        mainTabOpen(0)
    }

    // 메인 비주얼, 띠배너 슬라이더
    if ( $('.js-swiper-wrapper').length > 0 ){
        $('.js-swiper-wrapper').each(function(n){
            if ( $(this).find('.swiper-slide').length > 1 ){ // slider 1개일때 작동 안되게 20200221
                var _this = $(this),
                    _swiper = null,
                    _swiperEl = $('.js-swiper-banner', this),
                    _pages = $('.js-swiper-pages', this),
                    _btnPrev = $('.js-btn-prev', this),
                    _btnNext = $('.js-btn-next', this),
                    _loop = _this.data('loop'),
                    _speed = Number(_this.data('speed')),
                    _viewNum = Number(_this.data('view')),
                    _type = _this.data('ctrl-type'); // 0: 페이징 dot(fade), 1: 페이징 숫자, 2: 페이징 숫자, 양쪽화살표, 3 : 양쪽 화살표, 4: 슬라이드 dot(slide)

                if(_type == '0'){
                    _swiper = new Swiper(_swiperEl, {
                        slidesPerView : _viewNum,
                        slidesPerGroup : _viewNum,
                        effect : 'fade',
                        loop : _loop, // 20200217 loop 설정 수정
                        autoplay: {
                            delay: _speed, // 20200217 speed 설정 수정
                        },
                        auto : true,
                        pagination: {
                            el: _pages,
                            type: 'bullets',
                            clickable : true
                        },
                    });
                    _btnPrev.hide();
                    _btnNext.hide();
                } else if(_type == '1'){
                    _swiper = new Swiper(_swiperEl, {
                        slidesPerView : _viewNum,
                        slidesPerGroup : _viewNum,
                        effect : 'fade',
                        loop : _loop, // 20200217 loop 설정 수정
                        autoplay: {
                            delay: _speed, // 20200217 speed 설정 수정
                        },
                        auto : true,
                        pagination: {
                            el: _pages,
                            type: 'fraction',
                        },
                    });
                    _btnPrev.hide();
                    _btnNext.hide();
                } else if(_type == '2'){
                    _swiper = new Swiper(_swiperEl, {
                        slidesPerView : _viewNum,
                        slidesPerGroup : _viewNum,
                        effect : 'fade',
                        loop : _loop, // 20200217 loop 설정 수정
                        autoplay: {
                            delay: _speed, // 20200217 speed 설정 수정
                        },
                        auto : true,
                        pagination: {
                            el: _pages,
                            type: 'fraction',
                        },
                        navigation: {
                            nextEl: _btnNext,
                            prevEl: _btnPrev,
                        },
                    });
                } else if(_type == '3'){ // effect : slide + arrow
                    _swiper = new Swiper(_swiperEl, {
                        slidesPerView : _viewNum,
                        slidesPerGroup : _viewNum,
                        effect : 'slide',
                        loop : _loop, // 20200217 loop 설정 수정
                        autoplay: {
                            delay: _speed, // 20200217 speed 설정 수정
                        },
                        auto : true,
                        navigation: {
                            nextEl: _btnNext,
                            prevEl: _btnPrev,
                        },
                    });
                    _pages.hide();
                } else if(_type == '4'){ // effect : slide + dot
                    _swiper = new Swiper(_swiperEl, {
                        slidesPerView : _viewNum,
                        slidesPerGroup : _viewNum,
                        effect : 'slide',
                        loop : _loop, // 20200217 loop 설정 수정
                        autoplay: {
                            delay: _speed, // 20200217 speed 설정 수정
                        },
                        pagination: {
                            el: _pages,
                            type: 'bullets',
                            clickable : true
                        },
                    });
                    _btnPrev.hide();
                    _btnNext.hide();
                } else if(_type == '4'){
                    _swiper = new Swiper(_swiperEl, {
                        slidesPerView : _viewNum,
                        slidesPerGroup : _viewNum,
                        effect : 'slide',
                        loop : _loop, // 20200217 loop 설정 수정
                        autoplay: {
                            delay: _speed, // 20200217 speed 설정 수정
                        },
                        pagination: {
                            el: _pages,
                            type: 'bullets',
                            clickable : true
                        },
                    });
                    _btnPrev.hide();
                    _btnNext.hide();
                }
            }else{
            	$(this).find('.js-btn-prev, .js-btn-next').hide(); // 20200228 해당 영역 버튼만 사라지게
            }

        });
    };

});


/* RESIZE  */
function resizeCommon (){

}

/****************************************
 * Common Js
 *****************************************/
/* $('.quickwrap').pixels('right') */
$.fn.pixels = function(property) {
    return parseInt(this.css(property).slice(0,-2));
};






/*
* date : 20191011
* last : 20191011
* name : mainTabOpen(val)
* pram :
* desc : footer family site select onoff
*/

function mainTabOpen(val){
    var name = 'js-m-tab-cont';//$(el).data('href');
    var bg = ('.js-m-tab-bg');
    var tg = $('.'+name +'-'+val);
    $(tg).siblings('.'+name).hide().end().show();

    $( '.js-m-tab-link[data-href='+name+']' ).parent().removeClass('is-active');
    $( '.js-m-tab-link[data-idx='+val+']' ).parent().addClass('is-active');

    $(bg).css('transform', 'translate3d(0,' + val * 100 + '%,0)');
}


/*
* date : 20191011
* last : 20191011
* name : detailImg(val)
* pram : val 이미지 순서
* desc : 접기/펼치기
*/
function detailImg(val){
    var _imgBig = $('.js-prd-img-large > img');
    var _imgSmall = $('.js-prd-img-small ul li').eq(val);
    var _imgHref = _imgSmall.find('a').data("img-href");

    _imgSmall.addClass('is-active').siblings().removeClass('is-active');
    _imgBig.fadeOut(150,function(){
        _imgBig.attr('src', _imgHref).fadeIn(150);
    })
}


/*
* date : 20191011
* last : 20191217
* name : singleFoldOnOff(el)
* pram : el - element
* desc : 단일 fold OnOff
*/
// 191217 수정 : 기존 autoHeight 함수 수정
function singleFoldOnOff(el){
    var _el = el,
        _wrap = el.closest('.js-folding-wrap'),
        _cont = _wrap.find('.js-folding-cont');

    if( _wrap.hasClass('is-active')){
        _wrap.removeClass('is-active');
        el.find('.offscreen').text("내용보기");

    } else {
        _wrap.addClass('is-active').siblings().removeClass('is-active').find('.offscreen').text("내용보기");
        el.find('.offscreen').text("내용접기");
    }
}



/*
* date : 20191011
* last : 20200220
* name : packageView(el, wrap, btn)
* pram : wrap = 패키지 제품 wrap / el = 패키지 제품 box(추천1/추천2 등) / btn = 추천 접기/더보기 버튼
* desc : 접기/펼치기
*/
function packageView(el, wrap, btn){
    var pboxHeight = $(wrap).find(el).outerHeight() + 40;
    var pboxAllHeight = 0;
    $(wrap).find(el).each(function(index) {
        pboxAllHeight += $(this).outerHeight() + 30;
    });

    var toggleHeight = $(wrap).height() == pboxAllHeight ? pboxHeight : pboxAllHeight;
    $(wrap).animate({ height: toggleHeight });
    $(btn).toggleClass('is-active').find('.lb').text(function(i, oldText) {
        return oldText === '추천 접기' ? '추천 더보기' : "추천 접기"; /* 191120 수정 : 접기 > 옵션접기 텍스트 수정 */ /* 20200131 수정 : 접기 > 옵션접기 텍스트 수정 */
    });
}



/*
 * date : 20191011
 * last : 20191011
 * name : fixedSidebar()
 * pram :
 * desc : 주문화면 오른쪽 영역 고정
 * exam :
 *
*/
function fixedSidebar() {
    var el= $(".js-fixed-wrap"),
        _elHeight = el.height(),
        _eloffset = el.offset().top,
        _footerHeight = $('.footer-wrapper').outerHeight(),
        _fixTop = $('.cbody-wrap').offset().top - $('.header-wrapper').outerHeight() + $('.order-form-cont .stitle').eq(0).outerHeight(),
        _fixBottom = $('.footer-wrapper').offset().top,
        _scrollPos = $(window).scrollTop(),
        _winHeight = $(window).height(),
        _scrollHeight = _scrollPos + _winHeight;

    if( _elHeight < _winHeight ){ // 오른쪽el 보다 윈도우가 클 때
        if( _fixTop < _scrollPos && _fixBottom > _scrollHeight){
            el.addClass('is-fixed').removeClass('is-absolute');
        } else if( _fixTop > _scrollPos ){
            el.removeClass('is-fixed').removeClass('is-absolute');
        }else if( _fixBottom + 120 < _scrollHeight ){
            el.removeClass('is-fixed').addClass('is-absolute');
        }
    }
}

/*
 * date : 20211029
 * last : 20211029
 * writer : arams
 * name : fixedBottom()
 * pram :
 * desc : 창업문의 하단 문의버튼
 *
*/
function fixedBottom() {
	
    var el= $(".js-footer-fixed"),
        _footerHeight = $('.footer-wrapper').outerHeight(),
        _fixBottom = $('.footer-wrapper').offset().top,
        _scrollPos = $(window).scrollTop(),
        _winHeight = $(window).height(),
        _scrollHeight = _scrollPos + _winHeight;

		if( _fixBottom > _scrollHeight ){
            el.addClass('is-fixed').removeClass('is-absolute');
        }else if (_fixBottom  < _scrollHeight)
        {
			el.removeClass('is-fixed').addClass('is-absolute');
        }

}

/*
* date : 20191111
* last : 20191111
* name : ipAutoFocus()
* pram :
* desc : input auto focus
*/
function ipAutoFocus(){
    $('.js-ip-autofocus input').on("keyup", function(){
        var maxLength = $(this).attr("maxlength");
        $(this).val($(this).val().replace(/[^0-9]/g,""));
        if (this.value.length >= maxLength) {
            $(this).next().next("input").focus();
            return false;
        }
    });
}

/*
* date : 20191111
* last : 20191111
* name : prdCategoryOnOFF()
* pram :
* desc : 제품목록 카테고리 탭 on/off
*/
function prdCategoryOnOff(el, wrap) {
    var idx = $(this).data('idx');
    $(wrap).find(el).parent().addClass('is-active').siblings().removeClass('is-active');
}


/*
* date : 20191114
* last : 20191114
* name : rcRandomColor()
* pram :
* desc : 추천정보 입력 랜덤(1-3번 순차)  컬러 먹이기
*/
function rcRandomColor() {
    var count = 1;
    $('.js-random-color input').on("click", function(){
        var _this = $(this),
            substr = ["#4e9bab","#4e7cab","#4e85ab"],
            _default = ["#ccc"];
        if( _this.is(':checked')){
            if(++count > 2) count = 0;
            if (count == 0) {
                _this.next('label').css({"border-color" : substr[count], "background" : substr[count]});
            } else if (count == 1) {
                _this.next('label').css({"border-color" : substr[count], "background" : substr[count]});
            } else {
                _this.next('label').css({"border-color" : substr[count], "background" : substr[count]});
            }
        } else {
            _this.next('label').css({"border-color" : _default, 'background' : ""});
        }
    })
}



/*
* date : 20191114
* last : 20191226 :
* name : 
* pram :
* desc : 메인 고객넘버별 이름 선택
*/

function pnumSelect(){
    var btn = $('.js-btn-pnum-name'),
        list = $('.pnum-name-list ul li'),
        text = list.find('button'),
        name = $('.pnum-name-wrap p strong');

    btn.parents('.js-pnum-info-wrap').toggleClass('is-active');

    text.on("click", function(){
        $(this).parent('li').addClass('is-active').siblings('li').removeClass('is-active');
        name.text($(this).text());
        btn.parents('.js-pnum-info-wrap').removeClass('is-active');
    })
}


/*
* date : 20191025
* last : 20191025
* name : fitInputToImg(elm)
* pram :
* desc : 첨부 이미지의 썸네일 노출 및 삭제
*/
function fitInputToImg(el) {
    var fileUpload = el;

    fileUpload.on('change', function() {

        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader) return;
        if (/^image/.test( files[0].type)){
            var reader = new FileReader();
            reader.readAsDataURL(files[0]);
            reader.onloadend = function(){
                el.parent().find(".file-thumb-preview").show().attr("src", this.result)
                el.parent().find(".js-btn-del-file").show();
                el.parent().find("label").css("background", "#fff");
                el.parent().find("js-input-file").css("min-width", "auto");
            }
        }

        $('.js-btn-del-file').on("click", function(){
            $(this).parent().find(".file-thumb-preview").attr("src", "").hide();
            $(this).hide();
            el.parent().find("label").css("background", "#ddd");
        })
    });
}


/*
* date : 20200213
* last : 20200213
* name : recmdPackageSize()
* pram :
* desc : 제품상세 > 추천 패키지 제품 초기 height 지정
*/
function recmdPackageSize(){
    if ( $('.ptype-package .ptype-box').length > 0 ){
        var pBoxHeight = $('.ptype-package .ptype-box').outerHeight() + 48;
        $('.ptype-package').css('height', pBoxHeight );
    }
}