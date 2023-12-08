const weekDays = ['월','화','수','목','금'];

function weekDayText () {
	$(".weekDays").each(function () {
		let count = $(this).data("count");
		let weekDay = weekDays[count];
		$(this).text(weekDay);
	});
};