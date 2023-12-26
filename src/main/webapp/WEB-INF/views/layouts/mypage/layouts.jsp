<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
	<tiles:insertAttribute name="head" />
<body>

<div class="wrapper">

	<!-- header -->
	<tiles:insertAttribute name="header" />
			<div class="breadcrumb-style">
			<div class="container">
				<ul>
					<li><a href="/">홈</a></li>
					<li><a class="/mypage">MY녹즙</a></li>					
					<li class="depth1"><a class="first"></a></li>
					<li class="depth2"><a class="second"></a></li>
				</ul>
			</div>
		</div>
		<div class="container aside-layout main" style="padding-bottom:100px; ">
			<tiles:insertAttribute name="aside" />
			
			<!-- main -->
		  	<tiles:insertAttribute name="content" />
	  	</div>
	  	
  	<!-- footer -->
  	<tiles:insertAttribute name="footer" />
  	<tiles:insertAttribute name="footermodal" />
</div>
<!-- modal -->
<tiles:insertAttribute name="alertModal" />
<tiles:insertAttribute name="confirmModal" />
<tiles:insertAttribute name="confirmDesignModal" />
<tiles:insertAttribute name="loading" />
<script>
$(function(){
	let li_len = $(".lnb-style li.active").length;
	let li_len2 = $(".sub-navigation li.active").length;
	let first_text = $(".lnb-style li.indepth.active > a").text();
	let second_text = $(".sub-navigation li.active > a").text();
	let box_text = $(".container-title").text();
	if( li_len == 0 && li_len2 == 0 ) {
		$(".depth1").remove();
		$(".depth2").remove();
	}else if(li_len != 0 && li_len2 == 0 ){
		$(".breadcrumb-style .first").text(box_text);
		$(".depth2").remove();
	}else{
		$(".breadcrumb-style .first").text(first_text);
		$(".breadcrumb-style .second").text(second_text);	
	}	
})
		
		
</script>




</body>
</html>