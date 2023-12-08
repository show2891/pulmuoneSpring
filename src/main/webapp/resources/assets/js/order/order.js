// 다음 주소 api
function searchPostcode () {
	$("#searchPostcode").on("click", function() {
		new daum.Postcode({
			oncomplete: function (data) {
				var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; 		 // 참고 항목 변수
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName
						: data.buildingName);
				}
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}
				$("#zipcode").val(data.zonecode);
				$("#addrRoad").val(data.roadAddress);
				$("#orderStreetAddress").val(data.jibunAddress);
			}
		}).open();
	});	
}


// 고객 정보와 동일 체크박스
function chkCheck(name, tel) {
	$("#chk-same").click(function() {
		$("#receiver").val("");
		$("#tel").val("");
		if ($(this).is(":checked")) {
			$("#receiver").val(name);
			$("#tel").val(tel);
		} // if
	});
}