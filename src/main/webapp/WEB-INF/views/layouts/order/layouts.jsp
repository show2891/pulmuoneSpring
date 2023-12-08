<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
	<tiles:insertAttribute name="head" />
	<script src="/resources/assets/js/order/dailyOrder.js"></script>
	<script src="/resources/assets/js/order/order.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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

<script>
		$(function () {
			// 다음 주소 api
			searchPostcode();
			
			// 고객 정보와 동일 체크박스
			let name = "name";
			let tel = "010-1234-1234";
			chkCheck(name, tel);
		});
</script>
</body>
</html>