<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 이항 연산자
/*
 	1. 산술 연산자
 	2. 비교 연산자
 	3. 논리 연산자
 	4. 대입 연산자
 */
window.onload = function() {
	// $(function(){}), $(document).reday(function(){})
	// mounted(), componentDidMount(), useEffect()
	let a = 5;
	let b = 2;
	console.log("a + b = " + (a + b))
	console.log("a - b = " + (a - b))
	console.log("a * b = " + (a * b))
	console.log("a / b = " + (a / b))
	console.log("a % b = " + (a % b))
	
	// 장바구니 계산 -> select 에서 넘어오는 값은 문자열
	let c = "10";
	let f = 20000;
	console.log(c + f);
	console.log(parseInt(c) * f); // 문자열 결합 1020000
	console.log(c === f);
	/*
		주의점
		숫자가 아닌 경우 => + 외에 다른 산술 연산 => 연산이 불가능 -> NaN
		"", '' -> 문자열
		HTML 을 통해서 숫자를 가지고 오는 경우 -> 문자열
	*/
	
}
</script>
</head>
<body>

</body>
</html>