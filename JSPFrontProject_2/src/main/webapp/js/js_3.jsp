<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 연산자 처리
/*
 	연산자
 	1. 단항 연산자
 		= 증감 연산자(++, --)
 		= 형변환 연산자
 			"10" -> Number("10") => 10
 					parseInt("10") => 10
			10 -> String(10) => "10"
			Boolean -> Boolean(1), Boolean(0) => 1 -> true, 0 -> false
 	2. 이항 연산자
 		= 산술 연산자(+, -, *, /, %)
				  => 문자열 결합 가능
	  	= 비교 연산자(==, !=, <, >, <=, >=)
	  	= 논리 연산자(&&, ||, !)
	  	= 대입 연산자(=, +=, -=)
 	3. 삼항 연산자
 		(조건) ? 값 : 값
 */
// 증감 연산자
window.onload = function() {
	let a = 10;
	console.log("a = " + a);
	a++;
	console.log("a = " + a);
	++a;
	console.log("a = " + a);
	
	let b = 10;
	console.log("b = " + b);
	a--;
	console.log("b = " + b);
	--a;
	console.log("b = " + b);
	
	let c = 10;
	console.log("c = " + c);
	let d = c++;
	console.log("c = " + c);
	console.log("d = " + d);
	
	// 반복문
	let k = true;
	console.log("k = " + k);
	k = !k
	console.log("k = " + k);
	
	let m = 1;
	console.log("m = " + m);
	m = !m
	console.log("m = " + m)
	// 0, 0.0 아 이난 숫자는 모두 true
	// ! 를 사용하면 데이터형은 boolean
	
	// 형변환 연산자
	let p = 1;
	document.write("변경 전 p = " + p + ", " + typeof p + "<br>");
	p = !p
	document.write("변경 후 p = " + p + ", " + typeof p + "<br>");
	// number 는 필요 시에 boolean 변경이 가능, 문자열            
	let n = "10";
	document.write("변경 전 n =" + n + ", " + typeof n + "<br>")
	document.write("변경 후 n =" + n + ", " + typeof Number(n) + "<br>")
	document.write("변경 후 n =" + n + ", " + typeof parseInt(n) + "<br>")
	document.write("변경 후 n =" + n + ", " + typeof Boolean(n) + "<br>")

	let h = 10;
	document.write("변경 전 h = " + h + ", " + typeof h + "<br>");
	document.write("변경 전 h = " + h + ", " + typeof String(h) + "<br>");
	document.write("변경 전 h = " + h + ", " + typeof boolean(h) + "<br>");
	
	/*
		형변환 => 브라우저에서 값을 읽어오는 경우 = 문자열로 읽어 온다
				=> 필요 시에 숫자형으로 변경해서 사용할 때
		1. 정수형 => Number(), parseInt()
		2. 문자형 => String()
		3. 논리형 => Boolean
		   --------------- 숫자 : 0, 0.0 -> false
		   						나머지는 true
		   				   문자열 : null -> false
		   				   		나머지는 true
	*/
	let name = "홍길동";
	document.write("name = " + name + ", " + typeof name + "<br>")
	document.write("type = " + Boolean(name) + ", " + typeof Boolean(name) + "<br>")
}
</script>
</head>
<body>

</body>
</html>