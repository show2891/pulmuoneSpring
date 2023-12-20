let searchMonth = '';
if(searchMonth != "" && searchMonth != undefined){
	$("#searchMonth").val(searchMonth);
	let target = "." + searchMonth;
	$('.dropdown-toggle').data('value', searchMonth)
	$('.dropdown-toggle').text($(target).text())
}

function dropdownClick (item){
	const value = item.data('value');
    const text = item.text();
    $('.dropdown-toggle').data('value', value)
    $('.dropdown-toggle').text(text)
	
	if (value === '3m') {
		$("#searchMonth").val("3m");
        var startDate = new Date();
        var endDate = new Date();
        startDate.setMonth(startDate.getMonth() - 3);
	} else if (value === '6m') {
		$("#searchMonth").val("6m");
        var startDate = new Date();
        var endDate = new Date();
        startDate.setMonth(startDate.getMonth() - 6);
	} else {
		$("#searchMonth").val("1y");
        var startDate = new Date();
        var endDate = new Date();
        startDate.setMonth(startDate.getMonth() - 12);
	}
	$('input[name="daterange"]').daterangepicker({startDate, endDate});
	
	const selectedRange = $('input[name=daterange]').val()
	$('input[name=startSearchDate]').val(selectedRange.split('~')[0].trim().replaceAll(' ', ''))
    $('input[name=endSearchDate]').val(selectedRange.split('~')[1].trim().replaceAll(' ', ''))
	$('#searchForm').submit();
	
}

function searchBtnClick () {
	const selectedRange = $('input[name=daterange]').val()
    console.log(selectedRange)
    $('input[name=startSearchDate]').val(selectedRange.split('~')[0].trim().replaceAll(' ', ''))
    $('input[name=endSearchDate]').val(selectedRange.split('~')[1].trim().replaceAll(' ', ''))
	$('#searchForm').submit();
}

function calculateBoxPrice(propThis) {
	let qty;
	if(propThis.is('.btn-plus')) qty = propThis.prev().text();
	else if(propThis.is('.btn-minus')) qty = propThis.next().text();
	
	// 가격 변동
	const eachPrice = propThis.parent().parent().parent().find('.value.origin').data('eachprice');
	propThis.parent().parent().parent().find('.value.origin').text(formatter.format(eachPrice * qty));
	
	// 세일가격 변동
	if(propThis.parent().parent().parent().find('.value').is('.value.sale')){
		const eachSalePrice = propThis.parent().parent().parent().find('.value.sale').data('eachprice');
		propThis.parent().parent().parent().find('.value.sale').text(formatter.format((eachPrice * qty)-(eachSalePrice * qty)));
	} // if
}


function cancel(orderNo, payNo) {
	let checked = $('input[name=select-product]:checked');
			
	if(checked.length===0){
		return alert('취소할 상품을 선택해주세요');
	} // if
			
	confirmDesign('','선택하신 상품 주문을 취소 하시겠습니까?',function (){
		let url = "/mypage/order/box/canceled/" + orderNo;
		alert('취소가 완료되었습니다.');
		location.href = url;
	})
}
