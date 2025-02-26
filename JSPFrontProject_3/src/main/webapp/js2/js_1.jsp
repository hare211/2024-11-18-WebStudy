<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	자바스크립트는 브라우저에서 실행 -> 인터프리터
	
	1. 변수 => 대입값에 따라 데이터형이 변경
		let(지역변수) / const(상수)
		=> number: 숫자형(int, double)
		=> string: 문자형(char, string)
		=> object: 배열 / 객체
				   []	 {} -> 객체 {}
	    => function: 함수
    2. 연산자
    	=> 단항 연산자
    		1) 증감 연산자: ++, --
    		2) 형변환 연산자
    			숫자변환: Number("10"), parseInt("10")
    			문자변환: String(10) -> "10"
    			논리변환: Boolean(1) -> true
    					Boolean(0) -> false
    					0, 0.0 이 아닌 수는 true
    					null 이 아닌 경우 true
				=> HTML 태그에서 읽는 값 -> String
		=> 이항 연산자
		   산술 연산자: +, /
		   
	    => 반복문: for / for-each
	    	=> 일반 for
	    		for (let i = 0; i <= 10; i++) {
	    			반복 수행문
	    		}
	    		
    		=> for-each -> 배열인 경우에만 사용 가능
    			for (변수 in 배열)
    				 --- 배열의 index 번호 -> 0
   				for (변수 in 객체)
   					 --- 객체의 key 값을 받는다
   					 
				let arr = [1, 2, 3, 4, 5]
				for (let i in arr)
					 ----- 0, 1, 2, 3, 4, 5
					 
				let sa = {sabun:1, name:"", sex:""}
				for (let key in sa)
					 ------ sabun, name, sex
					 
		 4. 함수: 기능처리(이벤트)
		 				onclick / onmouseover
		 				onchange(select) / onmouseout
		 				onkeydown / onkeyup
			=> 함수 생성 방법
				선언적 함수
				=> 리턴형 서술이 없다
				=> 매개변수에 변수명 설정 (name) -> (let name)(X)
				function 함수명(매개변수1, 매개변수2, ...) {
					function 함수명() => X
					let 함수명 = () => {} O
				}
				익명 함수
				let 함수명 = function() {
				
				}
				let 함수명 = () => {
				
				}
				
				사용자 정의 함수이기 때문에 자동 호출이 안된다 -> 언제 호출할 지 정해야 함
				
				
		6. 객체 문서 모델
			HTML 태그를 선택해서 제어
			-------------
			이벤트 처리
			속성값 변경
			CSS 적용
			
			HTML 파일 -> 전체를 가지고 있는 객체: document
			태그 1 개
				= document.getElementById("Id");
				= document.querySelector("Selector");
			태그 여러 개
				= document.getElementClassName("Class");
				= document.getElementByTagName("Tag");
				= document.querySelectorAll("Class or Tag");
				
			=> Jquery
				$('태그, 아이디명, 클래스명')
			=> Vue(<a ref="id">) => 양방향 통신
				$refs.id
			=> React -> target.id => 단방향 통신
    			
    			
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
 	Jquery: $(function(){})
 	Vue: mounted(){}
 	React: componentDidMount(){}
 		   useEffect() -> hooks
 */
	onload = function() {
 /*
		let h1 = document.getElementById("h1");
		console.log("h1 = " + typeof h1);
		// 태그 -> class
		// 속성 -> 멤버 변수
		h1.style.backgroundColor = 'yellow';
 */
	//let h1 = document.querySelector("h1"); // 가장 위에 있는 태그를 가져옴
 	//h1.style.backgroundColor = 'pink';
 	let h1 = document.querySelectorAll("h1"); // 배열
 	h1[0].style.backgroundColor = 'yellow';
 	h1[1].style.backgroundColor = 'pink';
 	let h2 = document.querySelector("h2");
 	h2.style.color = 'red';
	}
</script>
</head>
<body>
	<h1>Hello JavaScript-1</h1>
	<h2>Hello JavaScript-2</h2>
	<h1 id="h1">Hello JavaScript-3</h1>
</body>
</html>