<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	#schedule{
		margin-left: 20px;
		font-size: 16px;
		font-weight: bold;
	}
</style>
</head>
<body>
	<input type="button" value="대실" id="Time">
	<input type="button" value="숙박" id="Stay">
	<input type="text" id="reserveDate" placeholder="날짜 및 시간 선택" size="30">
	<span id="schedule"></span>
	
<script>
    let calendar;

    // 대실 버튼 클릭
    $('#Time').on('click', function() {
        // 기존 달력 파괴
        if (calendar) calendar.destroy();

        // 대실용 달력 설정 (단일 날짜, 시간만 선택)
        calendar = flatpickr('#reserveDate', {
            dateFormat: "Y-m-d H:i",
            enableTime: true,
            mode: "single",  // 대실은 날짜 범위가 아닌 단일 날짜만 선택
            minDate: "today",
            time_24hr: true,
            locale: "ko",
            onChange: function(selectedDates, dateStr, instance) {
                if (selectedDates.length === 1) {
                    const selectedDate = selectedDates[0];

                    // flatpickr의 formatDate 사용하여 월, 일, 시간 추출
                    const monthDay = instance.formatDate(selectedDate, "m-d");  // 월-일
                    const time = instance.formatDate(selectedDate, "H:i");     // 시간

                    // 요일을 getDay()로 구하고 한글로 변환
                    const weekday = new Date(selectedDate).getDay(); 
                    const weekdays = ["일", "월", "화", "수", "목", "금", "토"];
                    const weekdayText = weekdays[weekday];

                    const scheduleText = `${monthDay} ${time}(${weekdayText})`;
                    $('#schedule').text(scheduleText);
                }
            }
        });
    });

    // 숙박 버튼 클릭
    $('#Stay').on('click', function() {
        // 기존 달력 파괴
        if (calendar) calendar.destroy();

        // 숙박용 달력 설정 (날짜 범위 선택)
        calendar = flatpickr('#reserveDate', {
            dateFormat: "Y-m-d H:i",
            enableTime: true,
            mode: "range",  // 숙박은 날짜 범위 선택
            minDate: "today",
            time_24hr: true,
            locale: "ko",
            onChange: function(selectedDates, dateStr, instance) {
                if (selectedDates.length === 2) {
                    const startDate = selectedDates[0];
                    const endDate = selectedDates[1];

                    // flatpickr의 formatDate 사용하여 월, 일, 시간 추출
                    const startMonthDay = instance.formatDate(startDate, "m-d");  // 시작 월-일
                    const startTime = instance.formatDate(startDate, "H:i");     // 시작 시간

                    const startWeekday = new Date(startDate).getDay();            // 시작 요일
                    const weekdays = ["일", "월", "화", "수", "목", "금", "토"];
                    const startWeekdayText = weekdays[startWeekday];

                    const endMonthDay = instance.formatDate(endDate, "m-d");  // 종료 월-일
                    const endTime = instance.formatDate(endDate, "H:i");     // 종료 시간

                    const endWeekday = new Date(endDate).getDay();            // 종료 요일
                    const endWeekdayText = weekdays[endWeekday];

                    const scheduleText = `${startMonthDay} ${startTime}(${startWeekdayText}) ~ ${endMonthDay} ${endTime}(${endWeekdayText})`;
                    $('#schedule').text(scheduleText);
                }
            }
        });
    });
</script>

</body>

</html>