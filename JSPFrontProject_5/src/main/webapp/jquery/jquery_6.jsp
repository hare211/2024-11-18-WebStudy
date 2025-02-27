<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
<script type="text/javascript">
/*
	이벤트
		=> 함수 호출
		---------------
		let btn = document.querySelector("#id")
		btn.onclick = function(){처리코드}
		----- $('#id명').click(function(){})
		=> 고전 이벤트 처리
		=> 이벤트 리스너
		let btn = docuemnt.querySelector("#id")
		btn.addEventListener('click', function(){처리코드})
		------ $('#id명').on('click', function(){})
 */
 $(function() {
	$('#count').change(function() {
		let count = $(this).val(); // this -> #count // val() -> <input> <select> <textarea> 이 태그들은 val() 로 값을 가져와야 함
		//alert(count);
		let price = $('#price').text() // text() -> <span>a</span> <td>a</td> 이 태그들은 text() 로 값을 가져와야 함 
		// attr() -> <a href=""> <img src=""> 이 태그들은 attr()로 값을 가져와야 함
		// val("admin") -> 매개변수 존재할 시 setter / val() -> 매개변수 없을 시 getter / text() 도 마찬가지
		//alert(price);
		price = price.replace(',', '');
		price = price.replace('원', '');
		//alert(price); 
		let total = count * price;
		//alert(total);
		$('#total').text(total.toLocaleString() + '원'); // toLocaleString() -> 1000 자리 마다 , 찍어줌
		/*
			글자 조작
			-------
			val() / text() / html() / attr() / append()
							 -----
							 
					-----
					모든 태그
					<tag>value<tag> -> value 를 조작
			-----	
			value 값으로 채우는 태그
			<input> <select> <textarea>
			
			
			$('span h1').text() -> Hello -> text() 는 value 만 가져온다
			$('span h1').html() -> <h1>Hello</h1> -> html() 은 value 포함 html 의 tag 까지 가져온다
			<span>
				<h1>Hello</h1>
			</span>
			<p>
				<h1>Hello</h1>
			</p>
		*/
	}); 
 });
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<span id="price">30,000원</span>
			<select class="input-sm" id="count">
				<option value="1">1개</option>
				<option value="2">2개</option>
				<option value="3">3개</option>
				<option value="4">4개</option>
				<option value="5">5개</option>
			</select>
			<br>
			총금액:<span id="total"></span>
		</div>
	</div>
</body>
</html>