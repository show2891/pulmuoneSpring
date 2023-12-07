/*
 * name : common.ui.js
 * desc : PC
 * writer : glim
 * date : 2019/10/11
 * last : 2021/11/08
 */

/*********************
 **** 웹폰트 로딩
 - 웹폰트 깜빡임을 줄이기 위해 적용
 **********************/
WebFontConfig = {
    // google: { families: [ 'Noto+Sans::KR' ] }
    custom: {
        families:['Noto Sans KR'],
        //urls:['//fonts.googleapis.com/earlyaccess/notosanskr.css']  //20200220 구글폰트로 사용
        urls:['/resources/assets/css/notosanskr.css']//20171109 경로수정 //20200220 로컬 폰트 사용X // 20200309 ie 대응 위해 css만 내부용으로 교체(font파일은 google)
    },
    timeout: 0
};
(function() {
    var wf = document.createElement('script');
    //wf.src = ('https:' == document.location.protocol ? 'https' : 'http') +
    //'://ajax.googleapis.com/ajax/libs/webfont/1/webfont.js';
    wf.src = '/resources/assets/js/webfont.js';//20171109 경로수정
    wf.type = 'text/javascript';
    wf.async = 'true';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(wf, s);
})();


$( function() {
    if (!$.datepicker) return;
    $.datepicker.setDefaults({
        numberOfMonths: 1,
        dateFormat: 'yy.mm.dd',
        monthNames: ['01','02','03','04','05','06','07','08','09','10','11','12'],
        monthNamesShort: ['01','02','03','04','05','06','07','08','09','10','11','12'],
        dayNamesMin: ['일','월','화','수','목','금','토'], // 20200305 요일이름 한글로 수정
        showOn: 'button',
        showMonthAfterYear: true,
        buttonImage : '../images/ui/ui_ico_calendar.png',
        buttonImageOnly: false,// 수정 웹접근성 관련
        buttonText: '날짜 선택',
        currentText: '현재',
        prevText: '이전달',
        nextText: '다음달',
        beforeShow: function(input, inst) { // 위치(인풋박스 우측 하단)
            var calendar = inst.dpDiv;
            setTimeout(function() {
                calendar.position({
                    my: 'right top+5',
                    at: 'right bottom',
                    collision: 'none',
                    of: input
                });
            }, 1);
        },
        beforeShowDay:function(date){ // 일요일 날짜에 class 주기(빨강색)
            if(date.toString().indexOf('Sun')!=-1)
                return [1,'sunday'];else
                return [1];
        }
    });

    if( $('[data-role=dialog]').length > 0 ) {
        $.extend($.ui.dialog.prototype.options, {
            height: "auto",
            modal: true,
            autoOpen: false,
            closeText: "팝업 닫기",
            resize:false,
            draggable: false,
            resize:false,
            create: function( event, ui ) {
                $(event.target).dialog("option", {
                    "width" : $(event.target).data("width"),
                    "title" : $(this).find('.js-title').text(), "classes": {
                        "ui-dialog": $(event.target).data("class"),
                    }
                });//width 재설정

                if ( $(this).hasClass('is-alert')){
                    $(this).parents('.ui-widget').find('.ui-dialog-titlebar').remove();
                }
                resizePopupPos();
            },
            open: function( event, ui ) {
                resizePopupPos();

                // 음용제품 변경 신청 확인 팝업 일때 데이터 불러오는 속도 뒤에 다시한번 팝업 위치 변경 20200313
                if( $(this).hasClass("pop-order-check") ){
                    setTimeout(function() {
                        resizePopupPos();
                    }, 10);
                }

            },
            beforeClose: function( event, ui ) {
                $("html, body").css({overflow: '', height : ''}); // 20200123 추가
            }
        });

    }

})

$(document).ready(function() {

    $( window ).resize(function() {
        resizeCommon();
        resizePopupPos(); // 20200313 윈도우 리사이징 했을때도 팝업 위치 변경
    });
    resizeCommon();



    // 공통 : 팝업,달력
    if ($.fn.dialog) $("[data-role=dialog]").dialog();
    if ($.fn.datepicker) $("[data-role=datepicker]").datepicker();

    //tab
    if ($('.tab-wrap').length > 0) {
        tabOpen(0)
    }

    if($('.js-full-wrap').length > 0) {
        fullWrap();
    }

    // 제품상세 추천패키지 초기 height 주기
    // recmdPackageSize();


    if ($('.js-btn-go-top').length > 0) {
        fixedTop();
        if (window.ScrollPosStyler) {
            ScrollPosStyler.init({
                classAbove: 'sps-abv',
                classBelow: 'sps-blw',
            });
        }
    }

    //table caption 생성
    if ($("table[class*='tbl-col'], table[class*='tbl-row'], table[class*='tbl-form']").length > 0){
    	//setTableCaption();
	}



});

// 사업자정보확인
function bizOpen(){
    var url =
        "http://www.ftc.go.kr/bizCommPop.do?wrkr_no=3018198406";
    window.open(url, "bizCommPop", "width=750, height=700;");
}

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
 * name : resizePopupPos()
 * desc : 팝업 오픈시에 resize 할 경우 중앙 정렬
 * 작았을떄 위치 수정
*/
function resizePopupPos() {
    if ($('.ui-dialog-content:visible').length > 0) {
        if (document.documentElement.clientWidth < $(".ui-dialog-content:visible").innerWidth() || document.documentElement.clientHeight < $(".ui-dialog-content:visible").innerHeight() + 103) { // 화면이 클때
            $("html, body").css({overflow: '', height : "100%"});

            if (document.documentElement.clientWidth - 150 < $(".ui-dialog-content:visible").innerWidth()) {
                if (document.documentElement.clientHeight < $(".ui-dialog-content:visible").innerHeight() + 103) {
                    $(".ui-dialog-content:visible").dialog("option", "position", {
                        my: "left top",
                        at: "left top",
                        "collision": "fit",
                        of: window
                    });
                } else {
                    $(".ui-dialog-content:visible").dialog("option", "position", {
                        my: "left center",
                        at: "left center",
                        "collision": "fit",
                        of: window
                    });
                }
            } else {
                if (document.documentElement.clientHeight < $(".ui-dialog-content:visible").innerHeight() + 103) {
                    $(".ui-dialog-content:visible").dialog("option", "position", {
                        my: "center top",
                        at: "center top",
                        "collision": "fit",
                        of: window
                    });
                } else {
                    $(".ui-dialog-content:visible").dialog("option", "position", {
                        my: "center",
                        at: "center",
                        "collision": "fit",
                        of: window
                    });
                }
            }
        } else {
            $("html, body").css({overflow: 'hidden'});
            $(".ui-dialog-content:visible").dialog("option", "position", {
                my: "center",
                at: "center",
                "collision": "fit",
                of: window
            });
        }
    }
}

/*
* date : 20191011
* last : 20191011
* name : familysiteOnOff(  )
* pram :
* desc : footer family site select onoff
*/
function familysiteOnOff(){
    if ( $('.js-f-site').hasClass('is-active') ){
        $('.js-f-site').removeClass('is-active');
    }else{
        $('.js-f-site').addClass('is-active');
    }
}

/*
* date :  20191011
* last :  20200217
* name :  fixedTop (el)
* desc :   스크롤 발생시 TOP 버튼 고정
* pram :
* scroll content fix
*/
function fixedTop(){
    var headerWrap, currentScrollTop, nextScrollTop;
    currentScrollTop = 0;
    headerWrap = $('.header-wrapper');
    footerWrap = $('.footer-wrapper');
    topBtnWrap = $('.js-btn-gotop-wrap');
    topBtn = $('.js-btn-go-top');

    $(window).off('resize scroll', setScrollUp).on("resize scroll", setScrollUp);

    setScrollUp();
    function setScrollUp() {
        var windowScrollTop, offset, scrollPosition;
        if (headerWrap === undefined) {return;}

        windowScrollTop = $(window).scrollTop();
        scrollPosition = $(window).height() + $(window).scrollTop();

        var _fPosTop = $('.footer-wrapper').position().top;

        offset = headerWrap.height() / 2;
        currentScrollTop = windowScrollTop;

        if (nextScrollTop <= currentScrollTop && windowScrollTop) {
            topBtnWrap.addClass('is-active');
            if ( scrollPosition >  _fPosTop) {
                topBtn.removeClass('is-fixed');
            } else {
                topBtn.addClass('is-fixed');
            }
        } else {
            topBtnWrap.removeClass('is-active');
            if ( scrollPosition >  _fPosTop) {
                topBtn.removeClass('is-fixed');
            } else {
                topBtn.addClass('is-fixed');
            }
        }
        nextScrollTop = currentScrollTop;
    }

    topBtn.on('click', function (event) {
        event.preventDefault();
        $("html, body").animate({scrollTop: 0}, '700', 'easeInOutExpo');
    });
}


/*
* date : 20191025
* last : 20191025
* name : searchAreaOpen()
* pram :
* desc : GNB 검색버튼 클릭시 검색영역 Open
*/
function searchAreaOpen(){
    $('.js-search-wrap').addClass('is-active');
}

/*
* date : 20191025
* last : 20200103
* name : searchOff()
* pram :
* desc : 검색영역 딤드클릭시 영역 off
*/
function searchOff(){
    var tg = $(event.target);
    var searchCont = $('.js-search-wrap .js-search-inner');

    if( !(tg.is(searchCont) || tg.parents('.js-search-inner').is(searchCont)) || $(event.currentTarget).is('.js-search-close') ){ //200103 조건문 수정
        $('.js-search-wrap').removeClass('is-active');
    }
}


/*
* date : 20191025
* last : 20211108
* name : tabOpen(val)
* pram :
* desc : 탭 on/off - visualist 대분류 추가
*/
function tabOpen(val){
    var name = 'js-tab-cont';//$(el).data('href');
    var tg = $('.'+name +'-'+val);
    $(tg).siblings('.'+name).hide().end().show();

    $( '.js-tab-link[data-idx='+val+']' ).parent().addClass('is-active').siblings().removeClass('is-active');

}
/*
* date : 20211108
* last : 20211108
* name : businessTabOpen(val)
* writer : visualist arams
* desc : 창업문의 탭 on/off
*/
function businessTabOpen(val){

    var name = 'js-btab-cont';//$(el).data('href');
    var tg = $('.'+name +'-'+val);
    $(tg).siblings('.'+name).hide().end().show();

	$(window).scrollTop(0);
    $( '.js-btab-link[data-idx='+val+']' ).parent().addClass('is-active').siblings().removeClass('is-active');

	//동영상정지
	$('.video').each(function(){
		$(this).find($('iframe'))[0].contentWindow.postMessage('{"event":"command","func":"stopVideo","args":""}','*');
	});

}
/*
* date : 2021118
* last : 2021118
* name : TabOpen(val)
* writer : visualist arams
* desc : 창업 FAQ에서 개설문의 버튼 클릭했을때 localStorage에 값 저장
*/
function TabOpen(val){
	localStorage.setItem('val', val);
}
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
/*
* date : 20191011
* last : 20191011
* name : foldCont(el)
* pram : el : button
* desc : folding content on/off
*/
function foldCont(el) {
    var _closest = el.closest('.js-fold-wrap', $(this)),
        _header = el.parent('.js-fold-header', $(this)),
        _chk = el.prev('input[type=checkbox]', $(this)),
        _container = _closest.find('> .js-fold-container', $(this));

    if( el.closest('.faq-list') ) {
        if (_closest.hasClass('is-active')) {
            _closest.removeClass('is-active');
            el.find('.lb').text("내용보기");
            _container.slideUp(150);
        } else {
            _closest.addClass('is-active').siblings('li').removeClass('is-active');
            el.find('.lb').text("내용접기");
            _container.slideDown(150);
            _closest.siblings('li').find('> .js-fold-container', $(this)).slideUp(150);
        }

    } else {
        if( el.is('label')){
            if( el.prev('input[type=checkbox]').is(':checked')){
                _closest.removeClass('is-active');
                _container.slideUp(150);
                _closest.find('input[type=text]').prop('checked', true);
                el.parent('.rc-item').next('button').find('.lb').text("내용보기");
            } else{
                _closest.addClass('is-active');
                el.parent('.rc-item').next('button').find('.lb').text("내용접기");
                _container.slideDown(150);
            }
        } else {
            if (_closest.hasClass('is-active')) {
                _closest.removeClass('is-active');
                el.find('.lb').text("내용보기");
                _container.slideUp(150);
            } else {
                _closest.addClass('is-active');
                el.find('.lb').text("내용접기");
                _container.slideDown(150);
            }
        }
    }
}


/*
* date : 20191025
* last : 20191025
* name : selectEmail(el)
* pram : el : selectbox, val : 직접입력 option의 value값
* desc : 이메일 아이디 입력 시, 직접입력 선택 후 input 활성화
*/
function selectEmail(el, val){
    var _this = el;
    var _val = val;
    var _domainIp= _this.prev('input[type=text]');

    _domainIp.attr({
        "readonly" : true,
        "disabled" : true
    }).focus();

    if(_this.val() == _val){
        _domainIp.val('');
        _domainIp.attr({
            "readonly" : false,
            "disabled" : false
        }).focus();
    }
}

/*
 * date : 20191125
 * last : 20191125
 * name : fullWrap()
 * pram :
 * desc : 상품 상세 컨텐츠 가로 너비 지정
*/
function fullWrap(){
    var winWidth = $(window).width();
    var secWidth = $('.section').width();

    $('.js-full-wrap').css({ "width" : winWidth, "margin-left" : -(winWidth - secWidth) / 2 })
}

/*
 * date : 20190404
 * last : 20190404
 * name : setDropdown()
 * pram : wrap(필수) : 툴팁을 전체 감싸고 있는 영역
 * desc : 드롭다운 팝업 기본 설정
 ** [ 공통 소스 ]
 **** 클릭 버튼 클래스 : fn-btn / 활성화 클래스 : active
 **** 드롭다운 컨텐츠 : js-tooltip-container
*/
function setDropdown() {
    if( $('[data-role=tooltip]').length > 0 ) {
        $('[data-role=tooltip]').each(function() {
            // content width setting
            if( !($('.js-tooltip-container', this).attr('data-width') =='undefined') && $('.js-tooltip-container', this).attr('data-width') > 0 ) {
                $('.js-tooltip-container', this).css('width', $('.js-tooltip-container', this).attr('data-width'));
            }
        });

        // open focus
        $(window).on('ariaDropdown.show', function(event, dropdown) {
            //$('.'+dropdown.settings.menuClass, dropdown.element).find('input, button, a').first().focus();
            $('.'+dropdown.settings.menuClass, dropdown.element).find('.js-btn-tooltip-close').on('click', function() {
                $(dropdown.element).ariaDropdown('hide');
                //$('.'+dropdown.settings.btnClass, dropdown.element).focus();
            });
        });


    }
}

(function (factory) {
    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory); //AMD
    } else if (typeof exports === 'object') {
        module.exports = factory(require('jquery')); //CommonJS
    } else {
        factory(jQuery, window);
    }
}(function ($, window) {
    'use strict';
    var pluginName = 'ariaDropdown', // the name of the plugin
        a = {
            aCs: 'aria-controls',
            aHi: 'aria-hidden',
            aOw: 'aria-owns',
            aEx: 'aria-expanded',
            aHp: 'aria-haspopup',
            t: 'true',
            f: 'false'
        },
        count = 0,
        win = $(window);


    //-----------------------------------------
    //Private functions

    /*
    * set id of the element passed along
    * if the element does not have one
    * and return the id of the element
    */
    function setId(element, idPrefix, idSuffix) {
        if (!element.is('[id]')) element.attr('id', idPrefix + idSuffix);
        return element.attr('id');
    }


    //-----------------------------------------
    // The actual plugin constructor
    function AriaDropdown(element, userSettings) {
        var self = this;
        self.settings = $.extend({}, $.fn[pluginName].defaultSettings, userSettings);
        self.element = $(element); //the dropdown element
        self.btn = self.element.find('> .' + self.settings.btnClass).first(); //the dropdown button
        self.menu = self.element.find('> .' + self.settings.menuClass).first(); //the dropdown menu
        self.elementStatus = false; //the status of the element (false = collapsed, true = expanded)

        // disabled 설정
        if( !self.btn.prop('disabled') ) self.init();

    }

    // Avoid Plugin.prototype conflicts
    $.extend(AriaDropdown.prototype, {
        init: function () {
            var self = this,
                settings = self.settings,
                menu = self.menu,
                btn = self.btn,
                element = self.element;

            //if( self.btn.length !== 1 && self.menu.length !== 1 ) return;

            self.btnId = setId(self.btn, 'dropdown-btn-', count);
            self.menuId = setId(self.menu, 'dropdown-menu-', count);

            if( element.data('effect') == 'slide' ) self.settings.effect = 'slide';

            //Set accessibility attributes on menu
            menu.attr(a.aHi, a.t).attr(a.aOw, self.btnId);

            //Set attributes on btn
            btn.attr(a.aHp, a.t).attr(a.aCs, self.menuId);

            //Initialise the dropdown by hiding the menu
            self.hide(false);

            win.on('click.' + pluginName, function (event) {
                //event.stopPropagation();
                if (self.elementStatus) self.hide(true);
            });

            element.on('click.' + pluginName, function (event) {
                event.stopPropagation();
                self.toggle(true);
            });

            menu.on('click.' + pluginName, function (event) { event.stopPropagation(); });

            // 다른 툴팁이 열렸는치 체크 후 닫기
            win.on(pluginName + '.show', function (event, el) {
                if (self.elementStatus) { self.hide(true); }
            });

            //trigger init event on windown for developer to listen for
            win.trigger(pluginName + '.initialised', [self]);

            //increment count by 1
            count = count + 1;
        },
        toggle: function () {
            var self = this;
            if (self.elementStatus) {
                self.hide(true);
            } else {
                self.show(true);
            }
        },
        show: function (animate) {
            /*
            * This methos performs the slide down animation on the menu,
            * but only if cssTransitions is set to false
            */
            var self = this,
                slideSpeed = animate ? self.settings.slideSpeed : 0,
                settings = self.settings;

            self.element.css('z-index', self.settings.expandZIndex)

            if( !(self.settings.effect == 'slide') ) {
                self.menu.stop().show();
            }else{
                self.menu.stop().slideDown(slideSpeed, self.settings.easing);
            };

            /*
            * Trigger global custom event on window.
            * This event is needed to collapse every expanded dropdowns with expandOnlyOne set to true
            * when a dropdon is triggered by the user
            * Also authors can listen for this custom event in order to execute operations when
            * a specific dropdown is expanded
            */
            win.trigger(pluginName + '.show', [self]);

            self.element.addClass(settings.dropdownExpandedClass);

            self.btn
                .addClass(settings.btnExpandedClass)
                .attr(a.aEx, a.t);

            self.menu.attr(a.aHi, a.f);

            //dynamic btn label
            if (self.dynamicBtnEl) self.dynamicBtnEl.text(self.collapseLabel);

            //Update widget status
            self.elementStatus = true;
        },
        hide: function (animate) {
            /*
            * This methos performs the slide up animation on the menu,
            * but only if cssTransitions is set to false
            */
            var self = this,
                slideSpeed = animate ? self.settings.slideSpeed : 0,
                settings = self.settings;

            self.element.css('z-index', self.settings.collapseZIndex);

            if( !(self.settings.effect == 'slide') ) {
                self.menu.stop().hide();
            }else{
                self.menu.stop().slideUp(slideSpeed, self.settings.easing);
            };

            /*
            * Authors can listen for this custom event in order to execute operations when
            * a specific dropdown is expanded
            */
            win.trigger(pluginName + '.hide', [self]);

            self.element.removeClass(settings.dropdownExpandedClass);

            self.btn
                .removeClass(settings.btnExpandedClass)
                .attr(a.aEx, a.f);

            self.menu.attr(a.aHi, a.t);

            //dynamic btn label
            if (self.dynamicBtnEl) self.dynamicBtnEl.text(self.expandLabel);

            //Update widget status
            self.elementStatus = false;
        },
        destroy: function () {
            var self = this,
                settings = self.settings;

            //remove attributes and classes, unbind event listeners, remove jQuery data
            self.btn
                .removeAttr(a.aHp)
                .removeAttr(a.aCs)
                .removeAttr(a.aEx)
                .removeClass(settings.btnExpandedClass);

            self.menu
                .removeAttr(a.aOw)
                .removeAttr(a.aHi)
                .off('click.' + pluginName);

            self.element
                .removeClass(settings.dropdownExpandedClass)
                .off('click.' + pluginName + ' mouseenter.' + pluginName + ' mouseleave.' + pluginName)
                .removeData('plugin-' + pluginName);

            win.trigger(pluginName + '.destroyed', [self]);

        },
        methodCaller: function (methodName) {
            var self = this;
            switch (methodName) {
                case 'toggle':
                    self.toggle(true);
                    break;
                case 'show':
                    if (!self.elementStatus) self.show(true);
                    break;
                case 'hide':
                    if (self.elementStatus) self.hide(true);
                    break;
                case 'destroy':
                    self.destroy();
            }
        }
    });

    // A really lightweight plugin wrapper around the constructor,
    // preventing against multiple instantiations
    $.fn[pluginName] = function (userSettings) {
        return this.each(function (i) {
            var self = this;
            if (!$.data(self, 'plugin-' + pluginName) && (typeof userSettings === 'object' || typeof userSettings === 'undefined')) {
                $.data(self, 'plugin-' + pluginName, new AriaDropdown(self, userSettings));
            } else if (typeof userSettings === 'string') {
                $.data(self, 'plugin-' + pluginName).methodCaller(userSettings);
            }
        });
    };

    //Define default settings
    $.fn[pluginName].defaultSettings = {
        btnClass: 'js-btn-tooltip-open',
        menuClass: 'js-tooltip-container',
        dropdownExpandedClass: 'is-active',
        btnExpandedClass: 'is-active',
        slideSpeed: 350,
        easing: 'swing',
        expandZIndex: 5,
        collapseZIndex: 1,


        collapseOnOutsideClick: true,
        collapseOnMenuClick: false,
        expandOnlyOne: true,
        cssTransitions: true,
        mouse: false,
        dynamicBtnLabel: false


    };
}));

/*
* date : 20200227
* last : 20200227
* name : setTableCaption()
* pram :
* desc : table caption 생성
*/
function setTableCaption() {
	$("table[class*='tbl-col'], table[class*='tbl-row'], table[class*='tbl-form']").each(function (index) {
		var table, tableClass, captionText, captionComplex, theadHeader, tbodyHeader, bodyToHeadIdxs, hasThead,
			captionSubFix;
		table = $(this);
		tableClass = $(this).attr("class");
		captionTextOrigin = $(this).find("caption").text();
		captionComplex = "";
		captionSubFix = "";
		theadHeader = [];
		tbodyHeader = [];
		bodyToHeadIdxs = [];
		hasThead = false;
		// 180123 수정 : match 값 수정
		if (tableClass.match("tbl-form") && tableClass.match("form-view") !== null) {
			/* console.log("has _ip!!"); */
			captionSubFix = "을(를) 입력하는 표입니다.";
		} else {
			/* console.log("no _ip!!"); */
			captionSubFix = "을(를) 나타낸 표입니다.";
		}


		// thead th값 추출
		if ($(this).find("thead th").length > 0) {
			$(this).find("thead th").each(function (index) {
				theadHeader.push($(this).text());
			});
		}
		// tbody th값 추출
		if ($(this).find("tbody th").length > 0) {
			$(this).find("tbody th").each(function (index) {
				// tbody th가 thead th의 서브 헤더인 경우(thead th와 tbody th가 둘 다 존재하는 경우)
				if (theadHeader.length > 0) {
					if (tbodyHeader[$(this).index()] === undefined) {
						tbodyHeader[$(this).index()] = theadHeader[$(this).index()] + " 컬럼의 하위로";
					}
					tbodyHeader[$(this).index()] += " " + $(this).text();
				} else {
					tbodyHeader.push($(this).text());
				}
			});

			tbodyHeader = tbodyHeader.filter(function (n) {
				return n != undefined
			});
		}
		// console.log(theadHeader);
		// console.log(tbodyHeader);

		if (theadHeader.length > 0 && tbodyHeader.length > 0) {
			captionComplex += theadHeader.join(", ") + " " + tbodyHeader.join(", ");
		} else if (theadHeader.length > 0) {
			captionComplex += theadHeader.join(", ");
		} else if (tbodyHeader.length > 0) {
			captionComplex += tbodyHeader.join(", ");
		}

		//console.log(captionTextOrigin + " 목록이며 " + captionComplex +  " 을(를) 나타낸 표입니다.");
		//$(this).find("caption").text(captionTextOrigin + " 테이블로 " + captionComplex + captionSubFix);
		$(this).find("caption").text(captionComplex + captionSubFix);
	});
}
