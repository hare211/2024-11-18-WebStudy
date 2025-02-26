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
.weekend {
	color: red !important;
}
.custom-ok-btn {
	display: block;
	width: 100%;
	height: 30px;
	padding: 5px;
	background-color: #007bff;
	color: white;
	text-align: center;
	border: none;
	cursor: pointer;
	margin-top: 5px;
	border-radius: 5px;
}
</style>
</head>
<body>
	<input type="button" value="대실" id="Time">
	<input type="button" value="숙박" id="Stay">
	<input type="text" id="reserveDate" placeholder="날짜 및 시간 선택" size="30">
	<span id="schedule">테스트</span>
	
<script>
/*
 	달력:
 		이전 달이나 다음 달에서 날짜 선택 시 자동으로 해당 달로 넘어가기 => 대실 달력에서는 넘어가는데 숙박 달력에서는 될 때가 있고 안 될 때가 있음
 		기본 디폴트 달력도 한글화
 */
    let instance = flatpickr("#reserveDate");
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
            onDayCreate: function(dObj, dStr, fp, dayElem) {
				let date = dayElem.dateObj;
	            let day = date.getDay(); // 0: 일요일, 6: 토요일
	            let currentMonth = fp.currentMonth; // 현재 보고 있는 달 (0: 1월, 1: 2월, ...)
	            let dateMonth = date.getMonth(); // 날짜의 달 (0: 1월, 1: 2월, ...)
	            let minDate = new Date(fp.config.minDate); // minDate를 Date 객체로 변환

	            /*
	            console.log('date:' + date);
	            console.log('day:' + day);
	            console.log('currentMonth:' + currentMonth);
	            console.log('dateMonth:' + dateMonth);
	            console.log('mindDate:' + minDate);
	            */
	            if (date >= minDate && dateMonth === currentMonth && (day === 0 || day === 6)) {
	                dayElem.classList.add("weekend"); // 현재 달의 주말만 빨간색
	            }
	        },
            onReady: (selectedDates, dateStr, instance) => {
				if (!document.querySelector(".custom-ok-btn")) {
					let okButton = document.createElement("button");
					okButton.innerText = "OK";
					okButton.className = "custom-ok-btn";
					okButton.addEventListener("click", () => instance.close());
					
					instance.calendarContainer.appendChild(okButton);
				}
			},
            onChange: function(selectedDates, dateStr, instance) {
            	//console.log("선택날짜:" + selectedDates);
            	//console.log("날짜문자열:" + dateStr);
            	//console.log("인스턴스:" + instance);
            	
                if (selectedDates.length === 1) {
                	let selectedDate = selectedDates[0];

                    // flatpickr의 formatDate 사용하여 월, 일, 시간 추출
                    let monthDay = instance.formatDate(selectedDate, "m-d");  // 월-일
                    let time = instance.formatDate(selectedDate, "H:i");     // 시간

                    // 요일을 getDay()로 구하고 한글로 변환
                    let weekday = new Date(selectedDate).getDay(); 
                    let weekdays = ["일", "월", "화", "수", "목", "금", "토"];
                    let weekdayText = weekdays[weekday];
					
                    //console.log("monthDay:", monthDay);
                    //console.log("time:", time);
                    //console.log("weekdayText:", weekdayText);
                    //console.log(calendar);
                    
                    //const scheduleText = `${monthDay} ${time}(${weekdayText})`;
                    //let scheduleText = `${monthDay} ${time}(${weekdayText})`;
                    let scheduleText = monthDay + ' ' + time + '(' + weekdayText + ')';
                    $('#schedule').text(scheduleText);
                    //$('#schedule').text(dateStr);
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
            onDayCreate: function(dObj, dStr, fp, dayElem) {
				let date = dayElem.dateObj;
	            let day = date.getDay(); // 0: 일요일, 6: 토요일
	            let currentMonth = fp.currentMonth; // 현재 보고 있는 달 (0: 1월, 1: 2월, ...)
	            let dateMonth = date.getMonth(); // 날짜의 달 (0: 1월, 1: 2월, ...)
	            let minDate = new Date(fp.config.minDate); // minDate를 Date 객체로 변환

	            /*
	            console.log('date:' + date);
	            console.log('day:' + day);
	            console.log('currentMonth:' + currentMonth);
	            console.log('dateMonth:' + dateMonth);
	            console.log('mindDate:' + minDate);
	            */
	            if (date >= minDate && dateMonth === currentMonth && (day === 0 || day === 6)) {
	                dayElem.classList.add("weekend"); // 현재 달의 주말만 빨간색
	            }
	        },
            onChange: function(selectedDates, dateStr, instance) {
            	//console.log("선택날짜:" + selectedDates);
            	//console.log("날짜문자열:" + dateStr);
            	//console.log("인스턴스:" + instance);
            	
                if (selectedDates.length === 2) {
                    let startDate = selectedDates[0];
                    let endDate = selectedDates[1];

                    // flatpickr의 formatDate 사용하여 월, 일, 시간 추출
                    let startMonthDay = instance.formatDate(startDate, "m-d");  // 시작 월-일
                    let startTime = instance.formatDate(startDate, "H:i");     // 시작 시간

                    let startWeekday = new Date(startDate).getDay();            // 시작 요일
                    let weekdays = ["일", "월", "화", "수", "목", "금", "토"];
                    let startWeekdayText = weekdays[startWeekday];

                    let endMonthDay = instance.formatDate(endDate, "m-d");  // 종료 월-일
                    let endTime = instance.formatDate(endDate, "H:i");     // 종료 시간

                    let endWeekday = new Date(endDate).getDay();            // 종료 요일
                    let endWeekdayText = weekdays[endWeekday];

                    //console.log("startMonthDay:", startMonthDay);
                    //console.log("startTime:", startTime);
                    //console.log("startWeekdayText:", startWeekdayText);
                    //console.log("endMonthDay:", endMonthDay);
                    //console.log("endTime:", endTime);
                    //console.log("endWeekdayText:", endWeekdayText);
                    //console.log(calendar);

                    //const scheduleText = `${startMonthDay} ${startTime}(${startWeekdayText}) ~ ${endMonthDay} ${endTime}(${endWeekdayText})`;
                    //let scheduleText = `${startMonthDay} ${startTime}(${startWeekdayText}) ~ ${endMonthDay} ${endTime}(${endWeekdayText})`;
                    let scheduleText = startMonthDay + '(' + startWeekdayText + ') ~ ' + endMonthDay + '(' + endWeekdayText + ')';
                    $('#schedule').text(scheduleText);
                    //$('#schedule').text(dateStr);
                }
            }
        });
    });
</script>

</body>

</html>