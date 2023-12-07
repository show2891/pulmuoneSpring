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
  	<main class="page faq forum">
		<div class="container">
			<div class="breadcrumb-style">
	            <div class="wrap">
	                <ul>
	                    <li><a href="/">홈</a></li>
	                    <li><a href="/forum/faq/list?">고객기쁨센터</a></li>
	                    <li><a class="active">FAQ</a></li>
	                </ul>
	            </div>
	        </div>
	             
	        <div class="page-wrap">
	        	<!-- aside -->
	           <tiles:insertAttribute name="aside" />
				
				<!-- content -->
				<tiles:insertAttribute name="content" />
			</div>
			
		</div>
	</main>
  	<!-- footer -->
  	<tiles:insertAttribute name="footer" />
</div>

</body>
</html>