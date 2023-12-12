<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
	<tiles:insertAttribute name="head" />
<body>

<div class="wrapper">
	<!-- header -->
	<tiles:insertAttribute name="header" />
	
  	<!-- main -->
  	<main class="page order box">
		<div class="container">
			<div class="breadcrumb-style">
	            <div class="wrap">
	                <ul>
	                    <li><a href="/">홈</a></li>
	                    <li><a href="/">장바구니</a></li>
	                    <li><a class="active">주문서작성</a></li>
	                </ul>
	            </div>
	        </div>
	             
	        <div class="page-wrap">
				<!-- content -->
				<tiles:insertAttribute name="content" />
			</div>
			
		</div>
	</main>
  	<!-- footer -->
  	<tiles:insertAttribute name="footer" />
</div>

<tiles:insertAttribute name="modal" />

<script>
		$(function () {
			// 다음 주소 api
			searchPostcode();
			
			// 고객 정보와 동일 체크박스
			let name = $("#receiver").val();
			let tel = $("#tel").val();
			chkCheck(name, tel);
			
			// 주소록 가져오기
			let member_no = ${ member.member_no };
			$("#addressBtn").on("click", function(){
				showAddress(member_no, 1);
			})
			
			// 카드 인증
			$("#validCardBtn").on("click", function () {
				validCard();
			})
			
			$(".numberOnly").on("keyup", function() {
				$(this).val($(this).val().replace(/[^0-9]/g,""));
			});
			
			
			// 계좌 인증
			$("#callCms").on("click", function () {
				callCms($(this));
			})
			
		});
</script>
</body>
</html>