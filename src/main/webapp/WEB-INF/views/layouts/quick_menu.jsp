<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="footer-wrapper">
<button id="top_move_pointer" class="top-nav">
	<img src="/resources/assets/images/ui/left_short_arrow.png">
	<p>TOP</p>
</button>
<script>
	$(window).scroll(function() {
		if ($(window).scrollTop() > 0) {
			if (!$("#top_move_pointer").hasClass("st")) {
				$("#top_move_pointer").addClass("st");
			}
		} else {
			if ($("#top_move_pointer").hasClass("st")) {
				$("#top_move_pointer").removeClass("st");
			}
		}
	})

	$(document).on("click", "#top_move_pointer", function() {
		$(document).scrollTop(0);
	});
</script>
	<script>

var windowRef = null;
function openWindowPop(url, name){
  var image = document.getElementById('guideImage');
  var w = image.width;
  var h = image.height;
  var options = "width='"+w+"',height='"+h+"', status=no, menubar=no, toolbar=no, resizable=no";
	if(windowRef===null|| windowRef.closed){

  windowRef = window.open('', name, options);
//   windowRef.document.write(`<img src="${url}" width="${w}" maxWidth=100vw />`);
  windowRef.document.write("<img src='"+url+"' width='"+w+"' maxWidth=100vw />");
  windowRef.document.body.style.margin=0;
	}else {
  windowRef.focus();
	}

}
// https://greenjuice.pulmuone.com/user_summary/default  ||  https://greenjuice.pulmuone.com/user_summary
// 위 주소 처럼 따로 페이지 구성이 되어야 효과를 발휘하기떄문에 주석처리 밑 스크립트 오류로인해 메인페이지 계속 호출 
// $(function(){

//   axios.get('/axios/mypage').then(function (response) {

//     const {info, customerVo} = response.data.RESULT_MSG;

// 		const ec = ( !info.overEnd) && (info.complex||info.hasHp) && customerVo.phiCustomerVo.intfacId == '0' && customerVo.phiCustomerVo.dlvyCustYn==='Y'
//       if(ec&&customerVo){
//         $('#quickChangeDrink').attr('href', `/mypage/drink/drink/change/${customerVo.custnumber}/${customerVo.prtnId}`)
//         $('#quickChangeSchedule').attr('href', `/mypage/drink/drink/pause/${customerVo.custnumber}/${customerVo.prtnId}`)
//       }else {
//         $('#quickChangeDrink').attr('href', `/mypage?with=01`)
//         $('#quickChangeSchedule').attr('href', `/mypage?with=01`)
//       }
//       console.log(window.innerWidth)
//       if(window.innerWidth>1450){
//         $('#mini-side-nav').show();
//       }
//   }).catch(function (error) {
//     if(window.innerWidth>1450) {
//       $('#mini-side-nav').show()
//     }
// 	});
//   window.addEventListener('resize', function(){
// 	  if(window.innerWidth>1450){
// 		$('#mini-side-nav').show();
// 	  }else {
// 		$('#mini-side-nav').hide();
// 	  }
// 	})

// })
</script>


	<div style="" id="mini-side-nav">
		<a href="/mypage/drink/drink"><img src="/resources/assets/images/ui/quick1.png" alt=""></a> <a id="quickChangeDrink" href="/mypage/drink/drink"><img src="/resources/assets/images/ui/quick2.png" alt=""></a> <a id="quickChangeSchedule" href="/mypage/drink/drink"><img src="/resources/assets/images/ui/quick3.png" alt=""></a> <a href="/mypage/drink/bill"><img src="/resources/assets/images/ui/quick4.png" alt=""></a> <a href="#"><img src="/resources/assets/images/ui/quickTop.png" alt=""></a>
	</div>
</div>