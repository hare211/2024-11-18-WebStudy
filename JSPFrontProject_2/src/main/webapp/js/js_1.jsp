<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. HTML / CSS : 브라우저에 출력(XML)
	2. Java : 오라클 연결 / 브라우저 연결
			  데이터 관리
			  => 오라클 데이터를 읽어서 브라우저로 전송
	3. Oracle : 사이트에 필요한 데이터를 저장
	4. JavaScript : 브라우저에서 사용자 요청 처리 담당
					------
					=> 브라우저에서는 오라클을 연결할 수 없다
						=> 오라클 연결(서버측 사이드 : NodeJS)
					=> React / Vue / Next -> 동적 화면 출력
					   ------------------> Java 연결
	   자바스크립트
	   	자바와 매칭
	   	=> ~VO -> {} Object -> JSON
	   	=> List -> [] Array
	   	=> 브라우저 안에서 자바를 통해 전송을 받아 동적으로 화면 출력
	   	
	   	=> 동적
	   		언어
	   			소스코드 - 컴파일러 - 기계어 출력 - 출력
   			자바스크립트
   				소스코드 - 인터프리터 - 출력
   						 ------
   						 | 한 줄씩 읽어서 출력
   						 | 에러 처리가 어렵다(에러 출력이 없다)
   						 | 흰 화면만 출력
	 	사용법
	 		1) 내부 스크립트 : 파일 한 개만 제어
	 			<script type="text/javascript">
	 				처리 내용
	 			</script>
	 			=> React / Vue
	 			<script type="text/babel">
	 			=> HTML 안에서 자바스크립트 작업 : CDN
	 			</script>
	 		2) 외부 스크립트 : 파일을 제작해서 필요 시마다 첨부
	 						확장자 (.js)
				<script text="text/javascript" src="파일명|URL">
				</script>
			3) 인라인 스크립트 : 태그 한 개에 대한 처리
				<tag event="javascript:func">
				<a href="javascript:history.back()"></a>
				
			버전
				ES5 => 변수선언 : var
								---
								자동 지정 변수
								scope(사용범위) 가 명확하지 않다
								지역변수 : {} 종료
						function a() {
							if () {
								var i = 10;
							}
							{} 벗어나도 변수 i 사용 가능
						}
				ES6 => let / const
							 | 상수형 변수
					   | {} 종료되면 메모리 회수
					   
					   function a() {
					   		if() {
					   			let a = 10;
					   		}
					   		{} 벗어나면 메모리 사라짐(지역변수 역할)
					   }
					   
					   const : final 
					   
					   화살표 함수
					   --------
					   function a() {}
					   let a = function (){}
					   let a = () => {} -> function 과 return 제거
					   ----- 함수 안에서 처리 가능
			   	
			   	자바스크립트 출력
			   		1) alert() : 팝업창
			   		2) console.log() : 도스 출력
			   		3) document.write() : 브라우저 출력
			   		4) innerHTML : 두 태그 사이에 데이터 출력
			   			=> Ajax / React / Vue / JQuery
			   			
			   			
			자바스크립트 문법
				변수 선언 / 연산자 / 제어문
				함수 제작 -> 처리
				태그제어(DOM) -> 이벤트 처리
				내장함수 / 브라우저 함수
				라이브러리 : JQuery
				
				1) 변수 선언
					변수의 데이터 타입을 지정하지 않는다(자동 지정)
					예)
						값에 따라 데이터형이 정해짐
						let i = 10; -> int -> Number
						let i = "aaa" -> String
						let i = 10.0; -> double -> Number
												   => Number 로 통일
					    let i = 'A' -> String
					    let i = {} -> Object
					    let i = [] -> Arrary
					    let i = function(){} -> function
					    		------------ 데이터형으로 취급
					    		
					    		function a(){}
					    		function b(a){}
					    		=> callback 함수 -> 이벤트 처리 시에 주로 등장
						
						단점)
							let i = 10;
							i = "aaa";
							i = [1, 2, 3, 4...]
							=> 데이터형의 변경이 가능하기 때문에 데이터형의 확인 필요
					변수 식별자
						1) 키워드 사용 불가(if / let / const)
						2) 특수문자 사용 가능(_, $)
						3) 숫자 사용 가능(첫 글자 불가)
						4) 공백 사용 불가
						5) 대소문자 구분
						6) 문자열 길이 상관 없음
					오류 발생 시 에러 출력이 없음
						=> 소스가 길어지면 개발자 도구에서 직접 확인
					데이터형 확인이 어렵다
						=> let i = 10.5 => i:double -> TypeScript
					let sa = {name:"홍길동", age:20}
							  ----		   ----
							  name, age -> 멤버변수
					  | object 형
					  | 자바에서 데이터 전송 : JSON
					  
				  	자바 : main()
				  	자바스크립트 : window.onload = function(){}
				  		jQuery : $(function(){})
				  		Reat : componentDidMount() -> useEffect()
				  		VueJS : mounted()
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>