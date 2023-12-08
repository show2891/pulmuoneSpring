<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<button id="boxOrderBtn">boxOrder</button>
<button id="dailyOrderBtn">dailyOrder</button>


<script>
$("#boxOrderBtn").click(function () {
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
<script>
$("#dailyOrderBtn").click(function () {
	let order = { item: [] };
	
	let item = {
		itemCode: "0071654"
	}
	item.dayQty = [1,0,1,0,1];
	order.item.push(item);
	
	item = {
			itemCode: "0070776"
	}
	item.dayQty = [0,1,0,1,0];
	order.item.push(item);

	location.href = "/order/daily/step1?item=" + encodeURIComponent(JSON.stringify(order));
});
</script>
</body>
</html>
