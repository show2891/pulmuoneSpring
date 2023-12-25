const weekDays = ['월','화','수','목','금'];

function getUrlParams() {
	let params = {};
	window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
	return params;
}

function getDrinkingType() {
	let params = getUrlParams();
	let drinkingType = params.drinkingType;
		
	if (drinkingType == '1') $("#searchText").text("음용중지");
	else $("#searchText").text("음용중");
}

function getStopDay(startDate){
	let date = new Date(startDate);
	date.setDate(date.getDate()+3);
		
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	
	day = getWeekDay(date);

	// 월과 일을 2자리로 만듦
	month = month < 10 ? '0' + month : month;
	day = day < 10 ? '0' + day : day;
	
	// "YYYY. MM. DD" 형식으로 출력
	let formattedDate = year + '. ' + month + '. ' + day;
	return formattedDate;
}

function getWeekDay(date){
	let dayOfWeek = date.getDay();
	if (dayOfWeek == 6) date.setDate(date.getDate()+2);
	else if (dayOfWeek == 0) date.setDate(date.getDate()+1);
	
	return date.getDate();
}


function calculatePrice() {
	let totalPrice = 0;
	$(".price").each(function () {
		let price = $(this).data("price");
		$(this).parent().parent().next().find("em").each(function () {
			let cnt = parseInt($(this).text());
			totalPrice += price*cnt;
		});
	});
	totalPrice*=4;
	$(".total").text(totalPrice.toLocaleString());
		
	let finalPrice = totalPrice;
	let discount = parseInt($(".discount").text());
	let shippingFee = parseInt($(".shipping-fee").text());
	finalPrice -= discount;
	finalPrice += shippingFee;
	$(".final").text(finalPrice.toLocaleString());
};

// 만약 모든 cnt가 0이면 음용 중지 예정 문구 출력, 아니면 삭제
function informStop(li){
	let allCnt = 0;
	li.find("em").each(function () {
		allCnt += parseInt($(this).text());
	});
	let title = li.find(".prd-title");
	if (allCnt >= 1) {
		title.find("b").remove();
	}
	if (allCnt == 0){
		let bTag = `<b style="font-size: 16px" class="pause">(음용중지예정)</b>`;
		let prdName = title.text();
		title.html(prdName+bTag);
	} // if
}



// 상품 추가하기 버튼
function addProduct(searchKeyword, pageNo){
	console.log("addProduct()... 키워드 : "+searchKeyword+", pageNo : "+pageNo);
	
	$("#addProductModal").addClass("loading");
	
	let root = $('.product-content-list');
	
	if(searchKeyword != null) {
		root.children().remove();
	}
	
	$.ajax({
		url:"/order/productList"
		, method: "GET"
		, data: { searchKeyword : searchKeyword, pageNo : pageNo }
		, dataType: "json"
		, cache: false
		, success: function(data, callback, xhr) {
				let tpl = null;
				
				$.each(data, function(i, prd){
					tpl = `<li class="product-add" style="cursor:pointer;" data-available="null" data-products-no="${prd.products_no}" data-price="${prd.price}" data-img-path="${prd.img_path}" data-system-name="${prd.system_name}" data-products-name="${prd.products_name}" data-products-size="${prd.products_size}">`;
						tpl += `<div class="thumb"><img src="/${ prd.img_path }/${ prd.system_name }" alt=""></div>`;
							tpl += `<div class="contents">`;
								tpl += `<p class="name">${ prd.products_name }<span>(${prd.products_size})</span></p>`
								tpl += `<div class="info">`
								tpl += `<span class="price"><b>${ prd.price.toLocaleString() }</b> 원</span>`
							tpl += `</div>`
						tpl += `</div>`
					tpl += `</li>`;
					
					root.append(tpl);
				}); // each
				
				$("#addProductModal").modal('show');
				
				root.off("click", ".product-add");
				root.on("click", ".product-add", function (e) {
					e.stopPropagation();
					$("#addProductModal").modal('hide');
					appendPrd($(this));
				});
				
				root.next().off("click");
				let hasNext = data[0].total_count > $("#addProductModal").find('.product-add').length;
				if (hasNext) {
					root.next().show().click(function () {
						addProduct(searchKeyword, pageNo + 1);
					});
				} else {
					root.next().hide();
				} // if
				
			$("#addProductModal").removeClass("loading");
			
		} // success
		, error: function(xhr, errorType){
			console.log(errorType);
		} // error
	});
};

// 선택한 상품 추가
function appendPrd(prd){
	let products_no = prd.data("products-no");
	let price = prd.data("price")
	let img_path = prd.data("img-path");
	let system_name = prd.data("system-name");
	let products_name = prd.data("products-name");
	let products_size = prd.data("products-size");
	
	let date = new Date();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	if(month < 10) month = '0' + month;
	if(day < 10) day = '0' + day;
	
	let drk_start_date = month+"."+day;
	 
	
	let tpl = null;
	
	tpl = `<li class="product_list" data-prd-no=${ products_no }>`;
		tpl += `<div class="item" href="/">`;
			tpl += `<div class="thumb">`;
				tpl += `<img src="/${ img_path }/${ system_name }" alt="">`;
			tpl += `</div>`;
			tpl += `<div class="contents">`;
				tpl += `<span></span>`;
				tpl += `<p class="prd-title">${ products_name }<b style="font-size: 16px" class="pause"></b></p>`;
				tpl += `<b class="price" data-price="${ price }">`;
					tpl += `${ price.toLocaleString() }`;
					tpl += `<span> 원(${ products_size })</span>`;
				tpl += `</b>`;
			tpl += `</div>`;
		tpl += `</div>`;
		tpl += `<div class="select-group">`;
		for(let i = 0; i<5; i++) {
			tpl += '<div class="select-item">'
				tpl += '<span>'+weekDays[i]+'</span>'
				tpl += '<div class="unit">'
					tpl += '<button type="button" class="plus"></button>'
					tpl += '<em>1</em>'
					tpl += '<button type="button" class="minus"></button>'
				tpl += '</div>'
			tpl += '</div>'
		} // for
		tpl += `</div>`;
		tpl += `<button type="button" class="btn-delete btn-remove">`;
			tpl += `<i class="ico ico-prd-delete"></i>`;
			tpl += `<span class="hide">카트에서 삭제</span>`;
		tpl += `</button>`;
	tpl += `</li>`;
	
	let root = $(".drinkchange-list");
	root.append(tpl);
	
	calculatePrice();
};












// 달력 출력 -> 나중에
/*
let today = new Date();
let date = new Date();

let historyList = []
let nextWeekDlv = []
					
historyList = {
		"data":[{
			"totalCount":3
			,"fdCustId":"230000234094"
			,"prtnId":"23812"
			,"itemName":"하트러브 엑스투"
			,"itemCnt":6
			,"drinkDay":0
			,"orderNum":"20231102118440"
			,"orderType":"PAUS"
			,"prtnChkYn":"Y"
			,"orderSeq":0
			,"createDate":"2023.11.02"
			,"changeHistoryList":[{
				"totalCount":0
				,"orderNum":"20231102118440"
				,"prtnChkYn":"Y"
				,"createDate":"2023.11.02"
				,"orderType":"PAUS"
				,"orderSeq":"0"
				,"holdResnCd":"MR05"
				,"drnkPtrn":"EK02"
				,"startDate":"2023.11.07"
				,"endDate":"2023.11.13"
				,"orderQty":0
				,"monQty":0
				,"tueQty":0
				,"wedQty":0
				,"thuQty":0
				,"friQty":0
				,"thumbnail":"/product/20220310/4cf0398b-fb18-45f9-97c4-be8b5bd2edd6.jpg"
				,"fnshYn":"Y"
			}
			, {
				"totalCount":0
				,"orderNum":"20231102118440"
				,"prtnChkYn":"Y"
				,"createDate":"2023.11.02"
				,"orderType":"PAUS"
				,"orderSeq":"1"
				,"holdResnCd":"MR05"
				,"drnkPtrn":"EK02"
				,"startDate":"2023.11.07"
				,"endDate":"2023.11.13"
				,"orderQty":0
				,"monQty":0
				,"tueQty":0
				,"wedQty":0
				,"thuQty":0
				,"friQty":0
				,"satQty":0
				,"thumbnail":"/product/20230302/685a5aac-588c-4366-82c4-217f17b38067.jpg"
				,"fnshYn":"Y"
			}
		]}]}.data

console.log(historyList)
					
nextWeekDlv = {
	"data":[{"totalCount":0,"idx":0,"existsPauseHistory":false,"existsChangeProductHistory":false,"existsMoifyDlviHistory":false,"isPay":false,"isProductChange":false,"isPause":false,"cnpc":"4500","itemNm":"바이탈엑스투 메모리케어","delvCfrmQty":"1","delvDt":"20231225","thumbnail":"/product/20230220/5e98ba4b-ea3d-4b14-b7db-a6b4399981dc.png","ordAcqrRoute":"60","itemCd":"0073397"},{"totalCount":0,"idx":0,"existsPauseHistory":false,"existsChangeProductHistory":false,"existsMoifyDlviHistory":false,"isPay":false,"isProductChange":false,"isPause":false,"cnpc":"3300","itemNm":"아이러브 엑스투","delvCfrmQty":"1","delvDt":"20231226","thumbnail":"/product/20221108/abde0d7f-d546-4cc4-acec-99a4b895b9d7.jpg","ordAcqrRoute":"60","itemCd":"0073271"},{"totalCount":0,"idx":0,"existsPauseHistory":false,"existsChangeProductHistory":false,"existsMoifyDlviHistory":false,"isPay":false,"isProductChange":false,"isPause":false,"cnpc":"3300","itemNm":"하트러브 엑스투","delvCfrmQty":"1","delvDt":"20231227","thumbnail":"/product/20220310/4cf0398b-fb18-45f9-97c4-be8b5bd2edd6.jpg","ordAcqrRoute":"60","itemCd":"0073080"},{"totalCount":0,"idx":0,"existsPauseHistory":false,"existsChangeProductHistory":false,"existsMoifyDlviHistory":false,"isPay":false,"isProductChange":false,"isPause":false,"cnpc":"3300","itemNm":"핸디밀 블랙콩콩 비타D플랜","delvCfrmQty":"1","delvDt":"20231228","thumbnail":"/product/20230302/42da9a6f-9889-4ff7-bf77-af5989855f86.jpg","ordAcqrRoute":"60","itemCd":"0073155"},{"totalCount":0,"idx":0,"existsPauseHistory":false,"existsChangeProductHistory":false,"existsMoifyDlviHistory":false,"isPay":false,"isProductChange":false,"isPause":false,"cnpc":"3300","itemNm":"핸디밀 그린샐러드 식이섬유플랜","delvCfrmQty":"1","delvDt":"20231229","thumbnail":"/product/20230302/685a5aac-588c-4366-82c4-217f17b38067.jpg","ordAcqrRoute":"60","itemCd":"0073184"},{"totalCount":0,"idx":0,"existsPauseHistory":false,"existsChangeProductHistory":false,"existsMoifyDlviHistory":false,"isPay":false,"isProductChange":false,"isPause":false,"cnpc":"2300","itemNm":"식물성유산균쌀","delvCfrmQty":"1","delvDt":"20231229","thumbnail":"/product/20230417/0512477d-b440-4adf-9cd3-3cc9ca29ab98.png","ordAcqrRoute":"60","itemCd":"0070445"}]
	}.data
	
console.log(nextWeekDlv);

let history = [{
  "startDate": "2023.12.01"
}];
var pauseList = [];

function autoLeftPad(num, digit) {
	if(String(num).length < digit) {
    	num = new Array(digit - String(num).length + 1).join("0") + num;
	}
    return num;
}
				
historyList.forEach(d => pauseList.push(...d.changehistoryList));

function buildCalendar() {
	let firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
	let lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);

	let tbCalendar = document.querySelector(".scriptCalendar > tbody");
	document.getElementById("calYear").innerText = today.getFullYear();                                  // YYYY월
	document.getElementById("calMonth").innerText = autoLeftPad((today.getMonth() + 1), 2);   // MM월

	// 이전 캘린더의 출력결과가 남아있다면, 이전 캘린더를 삭제한다.
	while (tbCalendar.rows.length > 0) {
		tbCalendar.deleteRow(tbCalendar.rows.length - 1);
	}
	
	// 첫번째 개행
	let row = tbCalendar.insertRow();
	
	// 날짜가 표기될 열의 증가값
	let dom = 1;
	
	// 시작일의 요일값( firstDate.getDay() ) + 해당월의 전체일( lastDate.getDate())을 더해준 값에서
	// 7로 나눈값을 올림( Math.ceil() )하고 다시 시작일의 요일값( firstDate.getDay() )을 빼준다.
	let daysLength = (Math.ceil((firstDate.getDay() + lastDate.getDate()) / 7) * 7) - firstDate.getDay();
	
	const list = history.filter(v => {
		console.log(v.startDate)
		const d = moment(v.startDate).toDate();
		return (d.getMonth() === today.getMonth() && d.getFullYear() === today.getFullYear())
	})

	let thisMonthPauseList = pauseList.filter(v => {
		v.startDate = v.startDate.replaceAll('.', '')
		v.endDate = v.endDate.replaceAll('.', '')
		const startDate = moment(v.startDate).toDate();
		const endDate = moment(v.endDate).toDate();
		return v.prtnChkYn === 'Y' && ((startDate.getFullYear() === today.getFullYear() && startDate.getMonth() === today.getMonth()) || (endDate.getFullYear() === today.getFullYear() && endDate.getMonth() === today.getMonth()))
	})

	// 달력 출력
	// 시작값은 1일을 직접 지정하고 요일값( firstDate.getDay() )를 빼서 마이너스( - )로 for문을 시작한다.
	for (let day = 1 - firstDate.getDay(); daysLength >= day; day++) {
		let column = row.insertCell();
		column.style.color = "#666666";
		column.style.lineHeight = 3;
		const exists = list.filter(v => moment(v.startDate).date() === day)
		const pauseExists = thisMonthPauseList.filter(v => {
			return moment(new Date(today.getFullYear(), today.getMonth(), day)).isBetween(moment(v.startDate).add(-1, 'days'), moment(v.endDate).add(1, 'days')) && (v.orderType == 'PAUS' || v.orderType == 'CHGDT')
		});

		// 평일( 전월일과 달익월일의 데이터 제외 )
		if (Math.sign(day) == 1 && lastDate.getDate() >= day) {
			// 평일 날짜 데이터 삽입
			column.innerText = autoLeftPad(day, 2);
			if (exists.length > 0) {
				column.innerHTML = `<div style="cursor:pointer;" class="checkDrink day-with-symbol"><span>${column.innerText}</span></div>`
				$(column).find('div').data('date', moment(new Date(today.getFullYear(), today.getMonth(), day)).format('YYYYMMDD'));
			} else if (pauseExists.length > 0) {
				if(pauseExists.filter(v=>v.orderType=='PAUS').length>0) {
                	column.innerHTML = `<div class="day-with-symbol"> <span class="change-shipping" >${column.innerText}</span></div>`
                } else {
					column.innerHTML = `<div class="day-with-symbol"> <span class="not-shipping" >${column.innerText}</span></div>`
				}
			}
		}
	}
}
*/