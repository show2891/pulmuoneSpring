<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resources/assets/js/jquery-2.1.4.min.js"></script>
<script src="/resources/assets/js/jquery.form.min.js"></script>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>


<button id="allOrderBtn">버튼</button>


<script>
$("#allOrderBtn").click(function () {
	let order = { item: [] };
	
	let item = {
		itemCode: "0073561"
	}
	item.qty = 1;
	order.item.push(item);
	
	item = {
			itemCode: "0072964"
	}
	item.qty = 2;
	order.item.push(item);

	location.href = "/order/box/step1?item=" + encodeURIComponent(JSON.stringify(order));
});
</script>
</body>
</html>
