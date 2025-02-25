<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%--
	함수 -> 명령문의 집합, 한 가지 일만 수행
	선언적 함수
	** 리턴형 선언
	** 매개변수의 데이터형을 사용하지 않는다
	=> 국어, 영어, 수학 점수를 받는다
		function cal(kor, eng, math)
					---------------- 변수명만 사용
	function 함수명(매개변수) => 선언부 {
		구현부
	}
	익명 함수
	let 함수명 = function(매개변수){}
	let 함수명 = (매개변수)
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<style>
    .custom-ok-btn {
        display: block;
        width: 100%;
        padding: 5px;
        background-color: #007bff;
        color: white;
        text-align: center;
        border: none;
        cursor: pointer;
        margin-top: 5px;
        border-radius: 5px;
    }
    .weekend {
            color: red !important;
        }
</style>
</head>
<body>
	<input type="button" value="대실" id="Time">
	<input type="button" value="숙박" id="Stay">
	<input type="date" id="reserveDate" placeholder="날짜 및 시간 선택" size="30" style="display: none">
	
	<p id="selectedDateTime"></p>
</body>
<script>
/*
 	달력: 
 		주말 빨간색 표시
 		국경일 빨간색 표시
 		OK 버튼
 	대실:
 		시간 ~ 시간 형식으로 대실 시간 지정
 		시간 선택하는 select 옵션
 	숙박: 
 		
 */


	const showCalendar = (options) => {
		let reserveDate = document.getElementById("reserveDate");
		reserveDate.style.display = "inline";
		flatpickr(reserveDate, {
			locale: "ko",
			...options,
			onReady: (selectedDates, dateStr, instance) => {
				if (!document.querySelector(".custom-ok-btn")) {
					let okButton = document.createElement("button");
					okButton.innerText = "OK";
					okButton.className = "custom-ok-btn";
					okButton.addEventListener("click", () => instance.close());
					
					instance.calendarContainer.appendChild(okButton);
				}
			},
			onDayCreate: function(dObj, dStr, fp, dayElem) {
				let date = dayElem.dateObj;
	            let day = date.getDay(); // 0: 일요일, 6: 토요일
	            let currentMonth = fp.currentMonth; // 현재 보고 있는 달 (0: 1월, 1: 2월, ...)
	            let dateMonth = date.getMonth(); // 날짜의 달 (0: 1월, 1: 2월, ...)
	            let minDate = new Date(fp.config.minDate); // minDate를 Date 객체로 변환

	            if (date >= minDate && dateMonth === currentMonth && (day === 0 || day === 6)) {
	                dayElem.classList.add("weekend"); // 현재 달의 주말만 빨간색
	            }
	        }
		});
	};
	
	document.getElementById("Time").addEventListener("click", () => {
		showCalendar({
			enableTime: true,
			noCalendar: false,
			dateFormat: "Y-m-d H:i",
			minDate: "today",
			//maxDate: "today",
			defaultHour: 12,
		});
	});
	
	document.getElementById("Stay").addEventListener("click", () => {
		showCalendar({
			enableTime: false,
			noCalendar: false,
			dateFormat: "Y-m-d",
			minDate: "today",
			mode: "range",
		});
	});
	
</script>
</html>
