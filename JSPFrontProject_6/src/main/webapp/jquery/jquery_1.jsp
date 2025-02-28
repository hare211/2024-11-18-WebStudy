<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	
 -->    
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
	width: 350px;
}
</style>
<script type="text/javascript">
	$(function() {
		/*
			change(): 선택이 변경됐을 때 -> <select>
			mousedown / mouseup
			hover
			keyup / keydown
			
			$().함수()
				--
				1. css(): 디자인 변경
				2. attr(): 속성 변경
				3. val() / text() / html(): 값 읽기
				4. val("") / text("") / html(""): 값 설정
		*/
		// 고전 이벤트
		// $('#login').click(function(){})
		// 이벤트 리스너
		$('#login').on('click', function() {
			// 1. id 읽기
			let id = $('#id').val();
			if (id.trim() === "") {
				$('#print').html('<font color="red">아이디를 입력하세요</font>');
				$('#id').focus();
				return;
			} else {
				$('#print').text("");
			}
			
			let pwd = $('#pwd').val();
			if (pwd.trim() === "") {
				$('#print').html('<font color="red">비밀번호를 입력하세요</font>');
				$('#pwd').focus();
				return;
			} else {
				$('#print').text("");
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">Login(val() / text())</h3>
			<table class="table">
				<tr>
					<th class="text-center" width=20%>ID</th>
					<td width=80%>
						<input type="text" id="id" size="15" class="input-sm">
					</td>
				</tr>
				<tr>
					<th class="text-center" width=20%>PW</th>
					<td width=80%>
						<input type="password" id="pwd" size="15" class="input-sm">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="button" class="btn-sm btn-success" id="login" value="로그인">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span id="print"></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>