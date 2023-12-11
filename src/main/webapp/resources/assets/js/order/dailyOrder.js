const weekDays = ['월','화','수','목','금'];

function weekDayText () {
	$(".weekDays").each(function () {
		let count = $(this).data("count");
		let weekDay = weekDays[count];
		$(this).text(weekDay);
	});
};

// 영수증 계산
function calculateReceipt() {

	// 초기화
    var totalPrice = 0;
    var totalCount = 0;
    var count = 0;
    var totalPay = 0;
		    
    // 1주 예상 금액 계산
    $("#order_targets [data-itemcode][data-price]").each(function () {
    	count = 0;
		$(this).find("input[data-count]").each(function () {
        	count += parseInt($(this).val(), 10);
     	});
      	totalCount += count;
      	totalPrice += parseInt($(this).attr("data-price"), 10) * count;
    });
	
    // 4주 예상 금액 계산
    totalPay = totalPrice*4;
    
    // 적용
    $("[data-price-view='origin']").text(totalPay).toLocaleString();
    $("[data-price-view='sale']").text(totalPay).toLocaleString();
    $("[data-price-view='payment']").text(totalPay).toLocaleString();
    $("input[name='payPrice']").val(totalPay);
    
} // calculateReceipt


// 상품 + 버튼
function plusBtn(btn){
	let em = btn.next("em");
	let input = btn.parent().prev().prev();
	let cnt = parseInt(em.text())+1;
	em.text(cnt);
	input.val(cnt);
	calculateReceipt();
};

// 상품 - 버튼
function minusBtn(btn){
	let em = btn.prev("em")
	let input = btn.parent().prev().prev();
	let cnt = parseInt(em.text())-1;
	if (cnt == -1) return;
	em.text(cnt);
	input.val(cnt);
	calculateReceipt();
};
