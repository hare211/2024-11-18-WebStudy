<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<%-- <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> --%>
<script type="text/javascript">
$(function() {
    $('#btn').click(function() {
        let kor = $('#kor').val();  // ✅ 올바른 선택자
        let eng = $('#eng').val();
        let math = $('#math').val();

        let total = Number(kor) + Number(eng) + Number(math);
        $('#total').val(total);
    });
});
/*
$(function() {
	$('#btn').click(function() {
		let kor = $('#kor').val();
		// document.querySelecter("#kor").value
		let eng = $('#eng').val();
		let math = $('#math').val();
		
		let total = Number(kor) + Number(eng) + Number(math);
		$('#total').val(total);
	})
})
*/
</script>
</head>
<body>
	국어 : <input type=text id=kor size=20 style="margin-left: 10px;"><br>
	영어 : <input type=text id=eng size=20 style="margin-left: 10px;"><br>
	수학 : <input type=text id=math size=20 style="margin-left: 10px;"><br>
	<input type=button id=btn value="계산">
	총점 : <input type=text id=total size=20 style="margin-left: 10px;">
</body>
</html>